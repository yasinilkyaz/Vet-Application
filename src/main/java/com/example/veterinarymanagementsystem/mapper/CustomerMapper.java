package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.CustomerRequest;
import com.example.veterinarymanagementsystem.dto.response.CustomerResponse;
import com.example.veterinarymanagementsystem.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer asEntity(CustomerRequest customerRequest);

    CustomerResponse asOutput(Customer customer);

    List<CustomerResponse> asOutput(List<Customer> customers);

    void update(@MappingTarget Customer customer,CustomerRequest customerRequest);
}
