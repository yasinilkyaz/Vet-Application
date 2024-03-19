package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.IAppointmentService;
import com.example.veterinarymanagementsystem.dto.request.AppointmentRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.dto.response.FilteredAppointmentsResponse;
import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Appointment;
import com.example.veterinarymanagementsystem.entities.AvailableDate;
import com.example.veterinarymanagementsystem.entities.Doctor;
import com.example.veterinarymanagementsystem.mapper.AppointmentMapper;
import com.example.veterinarymanagementsystem.repository.AnimalDao;
import com.example.veterinarymanagementsystem.repository.AppointmentDao;
import com.example.veterinarymanagementsystem.repository.AvailableDateDao;
import com.example.veterinarymanagementsystem.repository.DoctorDao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentDao appointmentDao;
    private final AppointmentMapper appointmentMapper;
    private final AvailableDateDao availableDateDao;
    private final DoctorDao doctorDao;
    private final AnimalDao animalDao;

    public AppointmentManager(AppointmentDao appointmentDao, AppointmentMapper appointmentMapper, AvailableDateDao availableDateDao, DoctorDao doctorDao, AnimalDao animalDao) {
        this.appointmentDao = appointmentDao;
        this.appointmentMapper = appointmentMapper;
        this.availableDateDao = availableDateDao;
        this.doctorDao = doctorDao;
        this.animalDao = animalDao;
    }

    @Override
    public AppointmentResponse save(AppointmentRequest appointmentRequest) {
        Doctor doctor = getDoctorById(appointmentRequest.getDoctorId());
        Animal animal = getAnimalById(appointmentRequest.getAnimalId());
        LocalDateTime appointmentDateTime = combineDateAndHour(appointmentRequest.getAppointmentDate(), appointmentRequest.getAppointmentHour());

        validateDoctorAvailability(appointmentRequest.getAppointmentDate(), doctor);

        validateNoDoctorAppointmentConflict(appointmentDateTime, doctor);

        validateNoAnimalAppointmentConflict(appointmentDateTime, animal);

        validateWorkingHours(appointmentRequest.getAppointmentHour());

        Optional<Appointment> isAppointmentExist = getAppointmentByDateDoctorAndAnimal(appointmentDateTime, doctor, animal);

        if (isAppointmentExist.isEmpty()) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(appointmentDateTime);
            appointment.setAnimal(animal);
            appointment.setDoctor(doctor);
            appointment = appointmentDao.save(appointment);
            return appointmentMapper.asOutput(appointment);
        }

        throw new RuntimeException("Bu randevu daha önce sisteme kaydedilmiştir.");
    }

    @Override
    public List<AppointmentResponse> findAll() {
        return appointmentMapper.asOutput(appointmentDao.findAll());
    }

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentMapper.asOutput(appointmentDao.findById(id).orElseThrow(() ->new RuntimeException(id+" id li randevu bulunamadı")));
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest appointmentRequest) {
        Doctor doctor = getDoctorById(appointmentRequest.getDoctorId());
        Animal animal = getAnimalById(appointmentRequest.getAnimalId());

        validateWorkingHours(appointmentRequest.getAppointmentHour());

        LocalDateTime appointmentDateTime = combineDateAndHour(appointmentRequest.getAppointmentDate(), appointmentRequest.getAppointmentHour());

        validateDoctorAvailability(appointmentRequest.getAppointmentDate(), doctor);

        validateDoctorExistingAppointment(appointmentDateTime, id);

        validateNoAnimalAppointmentConflict(appointmentDateTime, animal);

        Optional<Appointment> isAppointmentExist = getAppointmentById(id);
        if (isAppointmentExist.isEmpty()) {
            throw new RuntimeException("Belirtilen randevu bulunamadı.");
        }

        Appointment appointment = isAppointmentExist.get();
        appointment.setAppointmentDate(appointmentDateTime);
        appointment.setAnimal(animal);
        appointment.setDoctor(doctor);
        appointment = appointmentDao.save(appointment);

        return appointmentMapper.asOutput(appointment);
    }
    @Override
    public void deleteById(Long id) {
        Optional<Appointment> appointmentFromDB=appointmentDao.findById(id);
        if (appointmentFromDB.isPresent()){
            appointmentDao.delete(appointmentFromDB.get());
        }else {
            throw new RuntimeException(id + " id li Randevu sistemde bulunamadı !!!");
        }
    }

    @Override
    public FilteredAppointmentsResponse getFilteredAppointmentsByDoctor(LocalDate startDate, LocalDate endDate, Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        List<Appointment> filteredAppointments = appointmentDao.findByAppointmentDateBetweenAndDoctor(
                startDate.atStartOfDay(), endDate.atTime(23, 59, 59), doctor);

        List<AppointmentResponse> appointmentResponses = mapAppointmentsToResponse(filteredAppointments);

        return new FilteredAppointmentsResponse(appointmentResponses);
    }
    @Override
    public FilteredAppointmentsResponse getFilteredAppointmentsByAnimal(LocalDate startDate, LocalDate endDate, Long animalId) {
        Animal animal = getAnimalById(animalId);
        List<Appointment> filteredAppointments = appointmentDao.findByAppointmentDateBetweenAndAnimal( startDate.atStartOfDay(), endDate.atTime(23, 59, 59), animal);

        List<AppointmentResponse> appointmentResponses = mapAppointmentsToResponse(filteredAppointments);

        return new FilteredAppointmentsResponse(appointmentResponses);
    }

    private List<AppointmentResponse> mapAppointmentsToResponse(List<Appointment> appointments) {
        return appointmentMapper.mapAppointmentsToResponse(appointments);
    }
    private LocalDateTime combineDateAndHour(LocalDate date, int hour) {
        return date.atTime(hour, 0);
    }
    private Doctor getDoctorById(Long doctorId) {
        return doctorDao.findById(doctorId).orElseThrow(() -> new RuntimeException("Belirtilen doktor bulunamadı."));
    }

    private Animal getAnimalById(Long animalId) {
        return animalDao.findById(animalId).orElseThrow(() -> new RuntimeException("Belirtilen hayvan bulunamadı."));
    }

    private void validateDoctorAvailability(LocalDate appointmentDate, Doctor doctor) {
        Optional<AvailableDate> isDoctorAvailable = availableDateDao.findByAvailableDateAndDoctor(appointmentDate, doctor);
        if (isDoctorAvailable.isEmpty()) {
            throw new RuntimeException("Doktor belirtilen tarihte çalışmıyor");
        }
    }

    private void validateDoctorExistingAppointment(LocalDateTime appointmentDateTime, Long appointmentId) {
        Optional<Appointment> existingAppointment = getAppointmentById(appointmentId);
        existingAppointment.ifPresent(appointment -> {
            Optional<Appointment> anotherAppointmentAtSameDateTime = getAppointmentByDateAndDoctor(appointmentDateTime, appointment.getDoctor());
            if (anotherAppointmentAtSameDateTime.isPresent() && !anotherAppointmentAtSameDateTime.get().getId().equals(appointmentId)) {
                throw new RuntimeException("Doktorun belirtilen saatte başka bir randevusu bulunmaktadır.");
            }
        });
    }

    private Optional<Appointment> getAppointmentById(Long id) {
        return appointmentDao.findById(id);
    }

    private void validateNoDoctorAppointmentConflict(LocalDateTime appointmentDateTime, Doctor doctor) {
        Optional<Appointment> existingAppointmentOptional = appointmentDao.findByAppointmentDateAndDoctor(appointmentDateTime, doctor);

        existingAppointmentOptional.ifPresent(existingAppointment -> {

            if (existingAppointment.getAppointmentDate().toLocalTime().equals(appointmentDateTime.toLocalTime())) {
                throw new RuntimeException("Doktorun belirtilen saatte başka bir randevusu bulunmaktadır.");
            }
        });
    }
    private void validateNoAnimalAppointmentConflict(LocalDateTime appointmentDateTime, Animal animal) {
        List<Appointment> animalAppointments = appointmentDao.findByAppointmentDateAndAnimal(appointmentDateTime, animal);

        if (!animalAppointments.isEmpty()) {
            throw new RuntimeException("Bu hayvanın seçilen tarihte başka bir randevusu bulunmaktadır.");
        }
    }

    private Optional<Appointment> getAppointmentByDateAndDoctor(LocalDateTime appointmentDateTime, Doctor doctor) {
        return appointmentDao.findByAppointmentDateAndDoctor(appointmentDateTime, doctor);
    }

    private Optional<Appointment> getAppointmentByDateDoctorAndAnimal(LocalDateTime appointmentDateTime, Doctor doctor, Animal animal) {
        return appointmentDao.findByAppointmentDateAndDoctorAndAnimal(appointmentDateTime, doctor, animal);
    }
    private void validateWorkingHours(int appointmentHour) {
        int workingStartHour = 9;
        int workingEndHour = 17;

        if (appointmentHour < workingStartHour || appointmentHour > workingEndHour) {
            throw new RuntimeException("Doktorlar çalışma saatleri dışında randevu almamaktadır.");
        }
    }

}
