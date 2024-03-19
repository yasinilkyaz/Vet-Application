package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.CustomerRequest;
import com.example.veterinarymanagementsystem.dto.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    CustomerResponse save(CustomerRequest customerRequest);

    List<CustomerResponse> findAll();

    CustomerResponse getById(Long id);

    CustomerResponse update(Long id, CustomerRequest request);

    void deleteById(Long id);
}
