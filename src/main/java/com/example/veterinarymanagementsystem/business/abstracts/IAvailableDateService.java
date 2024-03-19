package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.AvailableDateRequest;
import com.example.veterinarymanagementsystem.dto.response.AvailableDateResponse;

import java.util.List;

public interface IAvailableDateService {

    AvailableDateResponse save(AvailableDateRequest availableDateRequest);

    List<AvailableDateResponse> findAll();

    AvailableDateResponse getById(Long id);

    AvailableDateResponse update(Long id,AvailableDateRequest availableDateRequest);

    void deleteById(Long id);
}
