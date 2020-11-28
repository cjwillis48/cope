package com.ihi.cope.examples.springbootclientexample.service.impl;

import com.ihi.cope.domain.*;
import com.ihi.cope.examples.springbootclientexample.kaspermodels.KentuckyPrescription;
import com.ihi.cope.examples.springbootclientexample.service.PrescriptionService;
import com.ihi.cope.springbootstartercope.service.CopeWriter;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final CopeWriter copeWriter;

    public PrescriptionServiceImpl(CopeWriter copeWriter) {
        this.copeWriter = copeWriter;
    }

    @Override
    public void publishPrescription(KentuckyPrescription kentuckyPrescription) {

        log.info("Acknowledgement Response: {}",
                copeWriter.publishPrescription(() -> createPrescription(kentuckyPrescription)));
    }

    private Prescription createPrescription(KentuckyPrescription kentuckyPrescription) {
        return Prescription.builder()
                .drugName(kentuckyPrescription.getMedicationName())
                .prescriptionDate(kentuckyPrescription.getPrescriptionDate().toLocalDate())
                .quantity(kentuckyPrescription.getQuantity())
                .dose(Double.valueOf(kentuckyPrescription.getDose().split(" ")[0]))
                .dosageMeasurement(DosageMeasurement.valueOfAbbreviation(kentuckyPrescription.getDose().split(" ")[1]))
                .patient(Patient.builder()
                        .ssn(kentuckyPrescription.getPatientSsn())
                        .firstName(kentuckyPrescription.getPatientName().split(" ")[0])
                        .lastName(kentuckyPrescription.getPatientName().split(" ")[1])
                        .address(kentuckyPrescription.getPatientAddress())
                        .build()
                )
                .prescriber(
                        Prescriber.builder()
                                .address(kentuckyPrescription.getPrescriberAddress())
                                .firstName(kentuckyPrescription.getPrescriberName().split(" ")[0])
                                .lastName(kentuckyPrescription.getPrescriberName().split(" ")[1])
                                .ssn(kentuckyPrescription.getPrescriberSsn())
                                .build()
                )
                .pharmacy(
                        Pharmacy.builder()
                                .address(kentuckyPrescription.getPharmacyAddress())
                                .name(kentuckyPrescription.getPharmacyName())
                                .build()
                )
                .build();
    }
}
