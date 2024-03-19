package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.IAvailableDateService;
import com.example.veterinarymanagementsystem.dto.request.AvailableDateRequest;
import com.example.veterinarymanagementsystem.dto.response.AvailableDateResponse;
import com.example.veterinarymanagementsystem.entities.AvailableDate;
import com.example.veterinarymanagementsystem.entities.Doctor;
import com.example.veterinarymanagementsystem.mapper.AvailableDateMapper;
import com.example.veterinarymanagementsystem.repository.AvailableDateDao;
import com.example.veterinarymanagementsystem.repository.DoctorDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateDao dateDao;
    private final AvailableDateMapper dateMapper;
    private final DoctorDao doctorDao;

    public AvailableDateManager(AvailableDateDao dateDao, AvailableDateMapper dateMapper, DoctorDao doctorDao) {
        this.dateDao = dateDao;
        this.dateMapper = dateMapper;
        this.doctorDao = doctorDao;
    }

    @Override
    public AvailableDateResponse save(AvailableDateRequest availableDateRequest) {

        Optional<AvailableDate> existingAvailableDate = dateDao.findByAvailableDateAndDoctor(
                availableDateRequest.getAvailableDate(), doctorDao.findById(availableDateRequest.getDoctorId()).orElseThrow(() -> new RuntimeException("Bu doktor sistemde bulunamadı.")));

        if (existingAvailableDate.isPresent()) {
            throw new RuntimeException("Bu doktor ve tarih kombinasyonu zaten sistemde kayıtlı.");
        } else {
            Optional<Doctor> isDoctorExist = doctorDao.findById(availableDateRequest.getDoctorId());
            if (isDoctorExist.isPresent()){
                Doctor doctor = isDoctorExist.get();
                AvailableDate availableDate = dateMapper.asEntity(availableDateRequest);
                availableDate.setDoctor(doctor);
                availableDate = dateDao.save(availableDate);
                return dateMapper.asOutput(availableDate);
            }

        }
        throw new RuntimeException("");
    }

    @Override
    public List<AvailableDateResponse> findAll() {
        return dateMapper.asOutput(dateDao.findAll());
    }

    @Override
    public AvailableDateResponse getById(Long id) {
        return dateMapper.asOutput(dateDao.findById(id).orElseThrow(() -> new RuntimeException(id + " id li müsait gün bulunamadı")));
    }

    @Override
    public AvailableDateResponse update(Long id, AvailableDateRequest availableDateRequest) {
        Optional<AvailableDate> availableDateFromDB = dateDao.findById(id);
        Optional<Doctor> doctor = doctorDao.findById(availableDateRequest.getDoctorId());

        if (availableDateFromDB.isEmpty()) {
            throw new RuntimeException("Güncellemeye çalıştığınız müsait gün sistemde bulunamadı.");
        }

        if (doctor.isEmpty()) {
            throw new RuntimeException(availableDateRequest.getDoctorId() + " Bu doktor sistemde kayıtlı bulunamadı.");
        }

        AvailableDate availableDate = availableDateFromDB.get();
        Optional<AvailableDate> existingAvailableDate = dateDao.findByAvailableDateAndDoctor(
                availableDateRequest.getAvailableDate(), doctorDao.findById(availableDateRequest.getDoctorId()).orElseThrow(() -> new RuntimeException("Bu doktor sistemde bulunamadı.")));

        if (existingAvailableDate.isPresent()) {
            throw new RuntimeException("Bu doktor ve tarih kombinasyonu zaten sistemde kayıtlı.");
        }

        if (!availableDate.getAvailableDate().equals(availableDateRequest.getAvailableDate())
                || !Objects.equals(availableDate.getDoctor(), doctor.get())) {


            availableDate.setAvailableDate(availableDateRequest.getAvailableDate());
            availableDate.setDoctor(doctor.get());
            availableDate = dateDao.save(availableDate);
            return dateMapper.asOutput(availableDate);
        }else {
            throw new RuntimeException("Bu tarih ve doktor ile başka bir müsait gün daha önce sisteme kayıt olmuştur.");
        }

    }

    @Override
    public void deleteById(Long id) {
        Optional<AvailableDate> availableDateFromDB = dateDao.findById(id);
        if (availableDateFromDB.isPresent()) {
            dateDao.delete(availableDateFromDB.get());
        } else {
            throw new RuntimeException(id + " id li müsait gün bulunamadı !!!");
        }
    }
}
