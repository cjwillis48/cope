package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.PatientEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.repository.PatientRepository;
import com.ihi.cope.copeserver.service.AddressService;
import com.ihi.cope.copeserver.service.PatientService;
import com.ihi.cope.domain.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final Mapper<Patient, PatientEntity> patientMapper;
    private final AddressService addressService;

    public PatientServiceImpl(PatientRepository patientRepository,
                              Mapper<Patient, PatientEntity> patientMapper,
                              AddressService addressService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.addressService = addressService;

    }

    @Override
    public Patient save(Patient patient) {
        patient.setAddress(addressService.saveIfNotExists(patient.getAddress()));

        PatientEntity entity = patientMapper.modelToEntity(patient);
        entity = patientRepository.save(entity);
        return patientMapper.entityToModel(entity);

    }
}
