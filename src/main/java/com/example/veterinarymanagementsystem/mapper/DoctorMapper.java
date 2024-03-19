package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.DoctorRequest;
import com.example.veterinarymanagementsystem.dto.response.DoctorResponse;
import com.example.veterinarymanagementsystem.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface DoctorMapper {
    Doctor asEntity(DoctorRequest doctorRequest);

    DoctorResponse asOutput(Doctor doctor);

    List<DoctorResponse> asOutput(List<Doctor> doctor);

    void update(@MappingTarget Doctor doctor,DoctorRequest doctorRequest);
}
