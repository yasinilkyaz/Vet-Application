package com.example.veterinarymanagementsystem.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public AnimalMapper animalMapper(){
        return Mappers.getMapper(AnimalMapper.class);
    }
    @Bean
    public DoctorMapper doctorMapper() {
        return Mappers.getMapper(DoctorMapper.class);
    }
    @Bean
    VaccineMapper vaccineMapper(){return Mappers.getMapper(VaccineMapper.class);
    }
    @Bean
    AvailableDateMapper availableDateMapper(){return Mappers.getMapper(AvailableDateMapper.class);}
    @Bean
    CustomerMapper customerMapper(){return Mappers.getMapper(CustomerMapper.class);}

    @Bean
    AppointmentMapper appointmentMapper(){return Mappers.getMapper(AppointmentMapper.class);}
    @Bean
    ReportMapper reportMapper(){return  Mappers.getMapper(ReportMapper.class);}
}
