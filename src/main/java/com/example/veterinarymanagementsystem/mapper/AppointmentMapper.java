package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.AppointmentRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    Appointment asEntity(AppointmentRequest appointmentRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "appointmentDate", source = "appointmentDate")
    @Mapping(target = "animal", source = "animal")
    @Mapping(target = "doctor", source = "doctor")
    AppointmentResponse asOutput(Appointment appointment);

    List<AppointmentResponse> asOutput(List<Appointment> appointments);

    void update(@MappingTarget Appointment appointment, AppointmentRequest appointmentRequest);

    List<AppointmentResponse> mapAppointmentsToResponse(List<Appointment> appointments);
}
