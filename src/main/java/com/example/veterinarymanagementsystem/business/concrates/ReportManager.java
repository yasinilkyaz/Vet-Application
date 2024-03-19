package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.IReportService;
import com.example.veterinarymanagementsystem.dto.request.ReportRequest;

import com.example.veterinarymanagementsystem.dto.request.ReportUpdateRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.dto.response.ReportResponse;
import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Appointment;
import com.example.veterinarymanagementsystem.entities.Report;

import com.example.veterinarymanagementsystem.mapper.AppointmentMapper;
import com.example.veterinarymanagementsystem.mapper.ReportMapper;
import com.example.veterinarymanagementsystem.mapper.VaccineMapper;
import com.example.veterinarymanagementsystem.repository.AppointmentDao;
import com.example.veterinarymanagementsystem.repository.ReportDao;
import com.example.veterinarymanagementsystem.repository.VaccineDao;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportManager implements IReportService {

    private final ReportMapper reportMapper;
    private final ReportDao reportDao;

    private final AppointmentDao appointmentDao;
    private final AppointmentMapper appointmentMapper;


    public ReportManager(ReportMapper reportMapper, ReportDao reportDao, VaccineDao vaccineDao, AppointmentDao appointmentDao, VaccineMapper vaccineMapper, VaccineManager vaccineManager, AppointmentMapper appointmentMapper) {
        this.reportMapper = reportMapper;
        this.reportDao = reportDao;
        this.appointmentDao = appointmentDao;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public ReportResponse save(ReportRequest reportRequest) {
        Optional<Appointment> appointmentOptional = appointmentDao.findById(reportRequest.getAppointmentId());
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();


            if (appointment.getReport() != null) {
                throw new RuntimeException("Bu randevuya ait rapor daha önce düzenlenmiştir.");
            }

            Animal animal = appointment.getAnimal();
            if (animal != null) {
                Report report = reportMapper.asEntity(reportRequest);
                report.setAppointment(appointment);
                report.setAnimalId(animal.getId());
                reportDao.save(report);

                return reportMapper.asOutput(report);
            } else {
                throw new RuntimeException("Hayvan Veritabanında Bulunamadı.");
            }
        } else {
            throw new RuntimeException("Randevu Veritabanında bulunamadı.");
        }
    }

    @Override
    public List<ReportResponse> findAll() {
        return reportMapper.asOutput(reportDao.findAll());
    }

    @Override
    public ReportResponse getById(Long id) {
        return reportMapper.asOutput(reportDao.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li rapor bulunamadı.")));
    }

    @Override
    public ReportResponse update(Long id, ReportUpdateRequest reportRequest) {
        Optional<Report> reportOptional = reportDao.findById(id);
        if (reportOptional.isPresent()) {
            Report existingReport = reportOptional.get();


            existingReport.setTitle(reportRequest.getTitle());
            existingReport.setDiagnosis(reportRequest.getDiagnosis());
            existingReport.setPrice(reportRequest.getPrice());

            reportDao.save(existingReport);

            return reportMapper.asOutput(existingReport);
        } else {
            throw new RuntimeException("Rapor Veritabanında bulunamadı.");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Report> reportFromDB = reportDao.findById(id);
        if (reportFromDB.isPresent()) {
            reportDao.delete(reportFromDB.get());
        } else {
            throw new RuntimeException(id + " id li Rapor sistemde bulunamadı !!!");
        }
    }
    @Override
    public List<AppointmentResponse> findUnreportedAppointments() {
        List<Appointment> appointments = appointmentDao.findAll();
        List<AppointmentResponse> unreportedAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getReport() == null) {
                AppointmentResponse appointmentResponse = appointmentMapper.asOutput(appointment);
                unreportedAppointments.add(appointmentResponse);
            }
        }

        return unreportedAppointments;
    }
}