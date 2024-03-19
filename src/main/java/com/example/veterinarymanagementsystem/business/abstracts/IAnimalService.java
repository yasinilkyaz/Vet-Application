package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.AnimalRequest;
import com.example.veterinarymanagementsystem.dto.response.AnimalResponse;

import java.util.List;

public interface IAnimalService {
    AnimalResponse save(AnimalRequest saveRequest);

    List<AnimalResponse> findAll();

    AnimalResponse getById(Long id);

    AnimalResponse update(Long id, AnimalRequest request);

    void deleteById(Long id);
    List<AnimalResponse> findByOwnerName(String ownerName);
    List<AnimalResponse> findAnimalsByName(String name);
    List<AnimalResponse> findAnimalsByOwner(Long customerId);
}
