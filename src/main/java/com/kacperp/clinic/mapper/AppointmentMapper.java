package com.kacperp.clinic.mapper;

import com.kacperp.clinic.dto.AppointmentDTO;
import com.kacperp.clinic.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO toDTO(Appointment appointment);
    Appointment toEntity(AppointmentDTO appointmentDTO);
}
