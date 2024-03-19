package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.IAppointmentService;
import com.example.veterinarymanagementsystem.dto.request.AppointmentRequest;
import com.example.veterinarymanagementsystem.dto.request.FilteredAppointmentsRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.dto.response.FilteredAppointmentsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAll(){return appointmentService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getById(@PathVariable("id") Long id){
        return appointmentService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse save(@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.save(appointmentRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse update(@PathVariable("id") Long id,@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.update(id,appointmentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        appointmentService.deleteById(id);
    }

    @PostMapping(value = "/filteredByDoctor")
    @ResponseStatus(HttpStatus.OK)
    public FilteredAppointmentsResponse getFilteredAppointmentsByDoctor(@RequestBody FilteredAppointmentsRequest request) {
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        Long doctorId = request.getId();

        return appointmentService.getFilteredAppointmentsByDoctor(startDate, endDate, doctorId);
    }
    @PostMapping("/filteredByAnimal")
    @ResponseStatus(HttpStatus.OK)
    public FilteredAppointmentsResponse getFilteredAppointmentsByAnimal(@RequestBody FilteredAppointmentsRequest request) {
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        Long animalId = request.getId();

        return appointmentService.getFilteredAppointmentsByAnimal(startDate, endDate, animalId);
    }
}
