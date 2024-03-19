package com.example.veterinarymanagementsystem.dto.response;

import java.util.List;

public class FilteredAppointmentsResponse {
    private List<AppointmentResponse> appointments;

    public FilteredAppointmentsResponse(List<AppointmentResponse> appointments) {
        this.appointments = appointments;
    }

    public List<AppointmentResponse> getAppointments() {
        return appointments;
    }

    public FilteredAppointmentsResponse() {
    }
}