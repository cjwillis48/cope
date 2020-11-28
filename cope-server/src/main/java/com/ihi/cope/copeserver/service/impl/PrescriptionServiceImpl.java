package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.PrescriptionEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.model.PrescriptionSearchCriteria;
import com.ihi.cope.copeserver.repository.PrescriptionRepository;
import com.ihi.cope.copeserver.service.*;
import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Parameter;
import com.ihi.cope.domain.Prescription;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PharmacyService pharmacyService;
    private final PrescriberService prescriberService;
    private final PatientService patientService;
    private final ClientService clientService;
    private final Mapper<Prescription, PrescriptionEntity> prescriptionMapper;

    public PrescriptionServiceImpl(
            PrescriptionRepository prescriptionRepository,
            PharmacyService pharmacyService,
            PrescriberService prescriberService,
            PatientService patientService,
            ClientService clientService,
            Mapper<Prescription, PrescriptionEntity> prescriptionMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.pharmacyService = pharmacyService;
        this.prescriberService = prescriberService;
        this.patientService = patientService;
        this.clientService = clientService;
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public Acknowledgement recordPrescription(@Valid Prescription prescription) {
        Acknowledgement ack = new Acknowledgement();
        ack.setStartTime(LocalDateTime.now());
        ack.setOperation("Record Prescription");
        ack.getInput().add(new Parameter("prescription", prescription));

        try {
            // Save in place for any enrichment that the services might cause, such as getting IDs
            prescription.setPharmacy(pharmacyService.save(prescription.getPharmacy()));
            prescription.setPatient(patientService.save(prescription.getPatient()));
            prescription.setPrescriber(prescriberService.save(prescription.getPrescriber()));
            prescription.setClient(clientService.save(prescription.getClient()));
            ack.setClient(prescription.getClient());

            PrescriptionEntity entity = prescriptionMapper.modelToEntity(prescription);
            entity = prescriptionRepository.save(entity);
            ack.setSuccess(true);
            ack.setOutput(prescriptionMapper.entityToModel(entity));
            ack.setCompleteTime(LocalDateTime.now());
        } catch (Exception e) {
            ack.setSuccess(false);
            ack.setOutput(e);
            ack.setCompleteTime(LocalDateTime.now());
        }
        return ack;
    }

    @Override
    public List<Prescription> getPrescriptionsForPatient(String ssn) {
        return prescriptionRepository.findAllByPatientSsn(ssn)
                .stream()
                .map(prescriptionMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> getPrescriptionsForSearchCriteria(PrescriptionSearchCriteria searchCriteria) {
        return prescriptionRepository.findAll(searchCriteria)
                .stream()
                .map(prescriptionMapper::entityToModel)
                .collect(Collectors.toList());
    }
}
