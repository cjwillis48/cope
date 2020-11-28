package com.ihi.cope.copeserver.mappers;

import com.ihi.cope.copeserver.entity.*;
import com.ihi.cope.domain.*;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper extends Mapper<Prescription, PrescriptionEntity> {
    private final Mapper<Client, ClientEntity> clientMapper;
    private final Mapper<Patient, PatientEntity> patientMapper;
    private final Mapper<Pharmacy, PharmacyEntity> pharmacyMapper;
    private final Mapper<Prescriber, PrescriberEntity> prescriberMapper;

    public PrescriptionMapper(Mapper<Client, ClientEntity> clientMapper,
                              Mapper<Patient, PatientEntity> patientMapper,
                              Mapper<Pharmacy, PharmacyEntity> pharmacyMapper,
                              Mapper<Prescriber, PrescriberEntity> prescriberMapper) {
        this.clientMapper = clientMapper;
        this.patientMapper = patientMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.prescriberMapper = prescriberMapper;
    }

    @Override
    public PrescriptionEntity modelToEntity(Prescription model) {
        PrescriptionEntity entity = new PrescriptionEntity();
        entity.setPatient(patientMapper.modelToEntity(model.getPatient()));
        entity.setPrescriber(prescriberMapper.modelToEntity(model.getPrescriber()));
        entity.setPharmacy(pharmacyMapper.modelToEntity(model.getPharmacy()));
        entity.setClient(clientMapper.modelToEntity(model.getClient()));
        entity.setDose(model.getDose());
        entity.setDoseMeasurementAbbreviation(model.getDosageMeasurement().getAbbreviation());
        entity.setPrescriptionDate(model.getPrescriptionDate());
        entity.setDrugName(model.getDrugName());
        entity.setInstructions(model.getInstructions());
        entity.setQuantity(model.getQuantity());
        return entity;
    }

    @Override
    public Prescription entityToModel(PrescriptionEntity entity) {
        Prescription model = new Prescription();
        model.setPatient(patientMapper.entityToModel(entity.getPatient()));
        model.setPrescriber(prescriberMapper.entityToModel(entity.getPrescriber()));
        model.setPharmacy(pharmacyMapper.entityToModel(entity.getPharmacy()));
        model.setClient(clientMapper.entityToModel(entity.getClient()));
        model.setDose(entity.getDose());
        model.setDosageMeasurement(DosageMeasurement.valueOfAbbreviation(entity.getDoseMeasurementAbbreviation()));
        model.setPrescriptionDate(entity.getPrescriptionDate());
        model.setDrugName(entity.getDrugName());
        model.setInstructions(entity.getInstructions());
        model.setQuantity(entity.getQuantity());
        return model;
    }
}
