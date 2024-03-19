package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.IAvailableDateService;
import com.example.veterinarymanagementsystem.dto.request.AvailableDateRequest;
import com.example.veterinarymanagementsystem.dto.response.AvailableDateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/available-date")
public class AvailableDateController {

    private final IAvailableDateService dateService;

    public AvailableDateController(IAvailableDateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return dateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getById(@PathVariable("id") Long id){
        return dateService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse save(@RequestBody AvailableDateRequest availableDateRequest){
        return dateService.save(availableDateRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse uodate(@PathVariable("id") Long id,@RequestBody AvailableDateRequest availableDateRequest){
        return dateService.update(id,availableDateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        dateService.deleteById(id);
    }
}
