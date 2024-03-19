package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.IVaccineService;
import com.example.veterinarymanagementsystem.dto.request.VaccineDateRangeRequest;
import com.example.veterinarymanagementsystem.dto.request.VaccineRequest;
import com.example.veterinarymanagementsystem.dto.response.VaccineResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccine")
public class VaccineController {
    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> findAll(){return vaccineService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse getById(@PathVariable("id") Long id){
        return vaccineService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse save(@RequestBody VaccineRequest vaccineRequest){
        return vaccineService.save(vaccineRequest);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse update(@PathVariable("id") Long id,@RequestBody VaccineRequest request){
        return vaccineService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        vaccineService.deleteById(id);
    }

    @PostMapping("/vaccinesByProtectionDateRange")
    public List<VaccineResponse> getVaccinesByProtectionDateRange(@RequestBody VaccineDateRangeRequest dateRangeRequest) {
        LocalDate startDate=dateRangeRequest.getStartDate();
        LocalDate endDate=dateRangeRequest.getEndDate();
        return vaccineService.findVaccinesByProtectionDateRange(
                startDate, endDate);
    }

    @GetMapping("/byAnimal/{animalId}")
    public ResponseEntity<List<VaccineResponse>> getVaccinesByAnimal(@PathVariable Long animalId) {
        List<VaccineResponse> vaccines = vaccineService.findVaccinesByAnimalId(animalId);
        return ResponseEntity.ok(vaccines);
    }
}
