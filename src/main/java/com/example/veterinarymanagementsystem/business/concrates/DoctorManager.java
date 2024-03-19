package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.IDoctorService;

import com.example.veterinarymanagementsystem.dto.request.DoctorRequest;
import com.example.veterinarymanagementsystem.dto.response.DoctorResponse;
import com.example.veterinarymanagementsystem.entities.Doctor;
import com.example.veterinarymanagementsystem.mapper.DoctorMapper;
import com.example.veterinarymanagementsystem.repository.DoctorDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService{

    private final DoctorDao doctorDao;

    private final DoctorMapper mapper;

    public DoctorManager(DoctorDao doctorDao, DoctorMapper mapper) {
        this.doctorDao = doctorDao;
        this.mapper = mapper;
    }

    @Override
    public DoctorResponse save(DoctorRequest saveRequest) {
        Optional<Doctor> isDoctorExist=doctorDao.findByNameAndAddressAndCityAndEmailAndPhone(saveRequest.getName(),saveRequest.getAddress(),saveRequest.getCity(),saveRequest.getEmail(),saveRequest.getPhone());
        if (isDoctorExist.isEmpty()){
            Doctor doctor=doctorDao.save(mapper.asEntity(saveRequest));
            return mapper.asOutput(doctor);
        }
        throw new RuntimeException("Bu doktor daha önce sisteme kaydedilmiştir.");
    }

    @Override
    public List<DoctorResponse> findAll() {
        return mapper.asOutput(doctorDao.findAll());
    }

    @Override
    public DoctorResponse getById(Long id) {
        return mapper.asOutput(doctorDao.findById(id).orElseThrow(() ->new RuntimeException(id+" id li doktor bulunamadı")));
    }

    @Override
    public DoctorResponse update(Long id, DoctorRequest request) {
        Optional<Doctor> doctorFromDB=doctorDao.findById(id);
        Optional<Doctor> isDoctorExist=doctorDao.findByNameAndAddressAndCityAndEmailAndPhone(request.getName(),request.getAddress(),request.getCity(),request.getEmail(),request.getPhone());

        if (doctorFromDB.isEmpty()){
            throw new RuntimeException(" Güncellemeye çalıştığınız doktor sistemde bulunamadı.");
        }

        if (isDoctorExist.isPresent()){
            throw new RuntimeException(" Bu doktor daha önce sisteme kayıt olmuştur.");
        }
        Doctor doctor=doctorFromDB.get();
        doctor.setName(request.getName());
        doctor.setPhone(request.getPhone());
        doctor.setEmail(request.getEmail());
        doctor.setAddress(request.getAddress());
        doctor.setCity(request.getCity());
        return mapper.asOutput(doctorDao.save(doctor));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Doctor> doctorFromDB=doctorDao.findById(id);
        if (doctorFromDB.isPresent()){
            doctorDao.delete(doctorFromDB.get());
        }else {
            throw new RuntimeException(id + " id li Doktor sistemde bulunamadı !!!");
        }
    }


}
