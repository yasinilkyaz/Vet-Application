package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.VaccineRequest;
import com.example.veterinarymanagementsystem.dto.response.VaccineResponse;
import com.example.veterinarymanagementsystem.entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {
    Vaccine asEntity(VaccineRequest vaccineRequest);


    VaccineResponse asOutput(Vaccine vaccine);

    @Mapping(source = "report.id", target = "reportId")
    List<VaccineResponse> asOutput(List<Vaccine> vaccines);

    void update(@MappingTarget Vaccine vaccine, VaccineRequest vaccineRequest);
}
