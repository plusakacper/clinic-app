package com.kacperp.clinic.service;

import com.kacperp.clinic.dto.PatientDTO;
import com.kacperp.clinic.exception.PatientException;
import com.kacperp.clinic.mapper.PatientMapper;
import com.kacperp.clinic.model.Patient;
import com.kacperp.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public void createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patientRepository.save(patient);
    }

    public PatientDTO getPatientById(Long id) throws PatientException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient not found with id: " + id));
        return patientMapper.toDTO(patient);
    }

    public List<PatientDTO> getAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updatePatient(Long id, PatientDTO patientDTO) throws PatientException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient not found with id: " + id));
        patient.setName(patientDTO.getName());
        patient.setSurname(patientDTO.getSurname());
        patient.setPesel(patientDTO.getPesel());
        patient.setAge(patientDTO.getAge());
        patientRepository.save(patient);
    }

    public void removePatient(Long id) throws PatientException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }
}
