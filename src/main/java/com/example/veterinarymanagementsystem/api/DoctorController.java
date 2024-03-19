package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.IDoctorService;
import com.example.veterinarymanagementsystem.dto.request.DoctorRequest;
import com.example.veterinarymanagementsystem.dto.response.DoctorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> findAll(){return doctorService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse getById(@PathVariable("id") Long id){
        return doctorService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse save(@RequestBody DoctorRequest doctorRequest){
        return doctorService.save(doctorRequest);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse update(@PathVariable("id") Long id,@RequestBody DoctorRequest request){
        return doctorService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        doctorService.deleteById(id);
    }

}
