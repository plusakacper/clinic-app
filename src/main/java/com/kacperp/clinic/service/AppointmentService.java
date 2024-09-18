package com.kacperp.clinic.service;

import com.kacperp.clinic.dto.AppointmentDTO;
import com.kacperp.clinic.exception.AppointmentException;
import com.kacperp.clinic.mapper.AppointmentMapper;
import com.kacperp.clinic.model.Appointment;
import com.kacperp.clinic.model.Doctor;
import com.kacperp.clinic.model.Patient;
import com.kacperp.clinic.repository.AppointmentRepository;
import com.kacperp.clinic.repository.DoctorRepository;
import com.kacperp.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        appointmentRepository.save(appointment);
    }

    public AppointmentDTO getAppointmentById(Long id) throws AppointmentException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentException("Appointment not found with id: " + id));
        return appointmentMapper.toDTO(appointment);
    }

    public List<AppointmentDTO> getAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateAppointment(Long id, AppointmentDTO appointmentDTO) throws AppointmentException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentException("Appointment not found with id: " + id));
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new AppointmentException("Patient not found with id: " + appointmentDTO.getPatientId()));
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new AppointmentException("Doctor not found with id: " + appointmentDTO.getDoctorId()));
        appointment.setDate(appointmentDTO.getDate());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
    }

    public void removeAppointment(Long id) throws AppointmentException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentException("Appointment not found with id: " + id));
        appointmentRepository.delete(appointment);
    }
}
