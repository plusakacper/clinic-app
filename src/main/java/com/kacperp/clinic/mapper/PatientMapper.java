package com.kacperp.clinic.mapper;

import com.kacperp.clinic.dto.PatientDTO;
import com.kacperp.clinic.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
}
