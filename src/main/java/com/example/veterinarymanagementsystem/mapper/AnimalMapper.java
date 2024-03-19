package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.AnimalRequest;
import com.example.veterinarymanagementsystem.dto.response.AnimalResponse;
import com.example.veterinarymanagementsystem.dto.response.AnimalResponseWithCustomer;
import com.example.veterinarymanagementsystem.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AnimalMapper {
    Animal asEntity(AnimalRequest animalRequest);

    AnimalResponse asOutput(Animal animal);

    AnimalResponseWithCustomer asOutputWithCustomer(Animal animal);
    List<AnimalResponse> asOutput(List<Animal> animals);
    void update(@MappingTarget Animal animal,AnimalRequest animalRequest);
}
