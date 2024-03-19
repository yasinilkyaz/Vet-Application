package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.IVaccineService;
import com.example.veterinarymanagementsystem.dto.request.VaccineRequest;
import com.example.veterinarymanagementsystem.dto.response.VaccineResponse;
import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Report;
import com.example.veterinarymanagementsystem.entities.Vaccine;
import com.example.veterinarymanagementsystem.mapper.VaccineMapper;
import com.example.veterinarymanagementsystem.repository.AnimalDao;
import com.example.veterinarymanagementsystem.repository.ReportDao;
import com.example.veterinarymanagementsystem.repository.VaccineDao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineMapper vaccineMapper;
    private final VaccineDao vaccineDao;
    private final AnimalDao animalDao;
    private final ReportDao reportDao;

    public VaccineManager(VaccineMapper vaccineMapper, VaccineDao vaccineDao, AnimalDao animalDao, ReportDao reportDao) {
        this.vaccineMapper = vaccineMapper;
        this.vaccineDao = vaccineDao;
        this.animalDao = animalDao;
        this.reportDao = reportDao;
    }

    @Override
    public VaccineResponse save(VaccineRequest saveRequest) {
        Animal animal = checkAnimalExistence(saveRequest.getAnimalId());

        checkVaccineExistence(animal, saveRequest.getName(), saveRequest.getCode(),
                saveRequest.getProtectionStartDate(), saveRequest.getProtectionFinishDate(), null);

        Vaccine vaccine = vaccineMapper.asEntity(saveRequest);
        vaccine.setAnimal(animal);


        if (saveRequest.getReportId() != null) {
            Optional<Report> reportOptional = reportDao.findById(saveRequest.getReportId());
            if (reportOptional.isEmpty()) {
                throw new RuntimeException("Belirtilen rapor bulunamadı: " + saveRequest.getReportId());
            }
            vaccine.setReport(reportOptional.get());
        }

        vaccineDao.save(vaccine);

        return vaccineMapper.asOutput(vaccine);
    }

    @Override
    public List<VaccineResponse> findAll() {
        return vaccineMapper.asOutput(vaccineDao.findAll());
    }

    @Override
    public VaccineResponse getById(Long id) {
        return vaccineMapper.asOutput(vaccineDao.findById(id).orElseThrow(() -> new RuntimeException(id + " id li aşı bulunamadı")));
    }

    @Override
    public VaccineResponse update(Long id, VaccineRequest saveRequest) {
        Animal animal = checkAnimalExistence(saveRequest.getAnimalId());

        Optional<Vaccine> vaccineFromDB = vaccineDao.findById(id);
        if (vaccineFromDB.isEmpty()) {
            throw new RuntimeException("Güncellemeye çalıştığınız aşı sistemde bulunamadı.");
        }

        Vaccine existingVaccine = vaccineFromDB.get();

        checkVaccineExistence(animal, saveRequest.getName(), saveRequest.getCode(),
                saveRequest.getProtectionStartDate(), saveRequest.getProtectionFinishDate(), id);

        existingVaccine.setName(saveRequest.getName());
        existingVaccine.setCode(saveRequest.getCode());
        existingVaccine.setProtectionStartDate(saveRequest.getProtectionStartDate());
        existingVaccine.setProtectionFinishDate(saveRequest.getProtectionFinishDate());
        existingVaccine.setAnimal(animal);


        if (saveRequest.getReportId() != null) {
            Optional<Report> reportOptional = reportDao.findById(saveRequest.getReportId());
            if (reportOptional.isEmpty()) {
                throw new RuntimeException("Belirtilen rapor bulunamadı: " + saveRequest.getReportId());
            }
            existingVaccine.setReport(reportOptional.get());
        }

        vaccineDao.save(existingVaccine);

        return vaccineMapper.asOutput(existingVaccine);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Vaccine> vaccineFromDB = vaccineDao.findById(id);
        if (vaccineFromDB.isPresent()) {
            vaccineDao.delete(vaccineFromDB.get());
        } else {
            throw new RuntimeException(id + " id li Aşı sistemde bulunamadı !!!");
        }
    }
    @Override
    public List<VaccineResponse> findVaccinesByProtectionDateRange(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineDao.findByProtectionFinishDateBetween(startDate, endDate);
        return vaccines.stream().map(vaccineMapper::asOutput).collect(Collectors.toList());
    }

    public List<VaccineResponse> findVaccinesByAnimalId(Long animalId) {
        Animal animal = animalDao.findById(animalId).orElseThrow(() -> new RuntimeException("Hayvan bulunamadı."));
        List<Vaccine> vaccines = vaccineDao.findByAnimal(animal);
        return vaccineMapper.asOutput(vaccines);
    }

    private Animal checkAnimalExistence(Long animalId) {
        return animalDao.findById(animalId).orElseThrow(() ->
                new RuntimeException(animalId + " Bu idli hayvan sistemde bulunamadı."));
    }

    private void checkVaccineExistence(Animal animal, String name, String code, LocalDate startDate, LocalDate endDate, Long id) {
        Optional<Vaccine> existingVaccine = vaccineDao.findByNameAndCodeAndProtectionStartDateAndProtectionFinishDateAndAnimal(
                name, code, startDate, endDate, animal);

        if (existingVaccine.isPresent() && !existingVaccine.get().getId().equals(id)) {
            throw new RuntimeException("Bu aşı daha önce sisteme kayıt olmuştur.");
        }
    }
}

