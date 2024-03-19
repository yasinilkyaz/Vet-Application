package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.AppointmentRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.dto.response.FilteredAppointmentsResponse;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {

    AppointmentResponse save(AppointmentRequest appointmentRequest);
    List<AppointmentResponse> findAll();
    AppointmentResponse getById(Long id);
    AppointmentResponse update(Long id, AppointmentRequest appointmentRequest);

    void deleteById(Long id);
    FilteredAppointmentsResponse getFilteredAppointmentsByDoctor(LocalDate startDate, LocalDate endDate, Long id);
    FilteredAppointmentsResponse getFilteredAppointmentsByAnimal(LocalDate startDate, LocalDate endDate, Long id);
}
