package com.kacperp.clinic.mapper;

import com.kacperp.clinic.dto.DoctorDTO;
import com.kacperp.clinic.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDTO toDTO(Doctor doctor);
    Doctor toEntity(DoctorDTO doctorDTO);
}
