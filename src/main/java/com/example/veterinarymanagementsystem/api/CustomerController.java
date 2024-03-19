package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.example.veterinarymanagementsystem.dto.request.CustomerRequest;
import com.example.veterinarymanagementsystem.dto.request.DoctorRequest;
import com.example.veterinarymanagementsystem.dto.response.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(@PathVariable("id") Long id) {
        return customerService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse save(@RequestBody CustomerRequest customerRequest){
        return customerService.save(customerRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse update(@PathVariable("id") Long id,@RequestBody CustomerRequest customerRequest){
        return customerService.update(id,customerRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        customerService.deleteById(id);
    }
}
