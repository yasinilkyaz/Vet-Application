package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.AvailableDateRequest;
import com.example.veterinarymanagementsystem.dto.response.AvailableDateResponse;
import com.example.veterinarymanagementsystem.entities.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AvailableDateMapper {
    AvailableDate asEntity(AvailableDateRequest availableDateRequest);

    AvailableDateResponse asOutput(AvailableDate availableDate);

    List<AvailableDateResponse> asOutput(List<AvailableDate> customers);

    void update(@MappingTarget AvailableDate availableDate, AvailableDateRequest availableDateRequest);
}
