package com.kacperp.clinic.service;

import com.kacperp.clinic.dto.DoctorDTO;
import com.kacperp.clinic.exception.DoctorException;
import com.kacperp.clinic.mapper.DoctorMapper;
import com.kacperp.clinic.model.Doctor;
import com.kacperp.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public void createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        doctorRepository.save(doctor);
    }

    public DoctorDTO getDoctorById(Long id) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorException("Doctor not found with id: " + id));
        return doctorMapper.toDTO(doctor);
    }

    public List<DoctorDTO> getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateDoctor(Long id, DoctorDTO doctorDTO) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorException("Doctor not found with id: " + id));
        doctor.setName(doctorDTO.getName());
        doctor.setSurname(doctorDTO.getSurname());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctorRepository.save(doctor);
    }

    public void removeDoctor(Long id) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }
}
