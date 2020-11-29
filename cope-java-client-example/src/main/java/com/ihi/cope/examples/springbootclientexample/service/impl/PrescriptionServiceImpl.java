package com.ihi.cope.examples.springbootclientexample.service.impl;

import com.ihi.cope.domain.*;
import com.ihi.cope.examples.springbootclientexample.kaspermodels.MyPdmpPrescription;
import com.ihi.cope.examples.springbootclientexample.service.PrescriptionService;
import com.ihi.cope.springbootstartercope.service.CopeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final CopeWriter copeWriter;

    public PrescriptionServiceImpl(CopeWriter copeWriter) {
        this.copeWriter = copeWriter;
    }

    @Override
    public void publishPrescription(MyPdmpPrescription myPdmpPrescription) {
        copeWriter.publishPrescription(createPrescription(myPdmpPrescription));
    }

    private Prescription createPrescription(MyPdmpPrescription myPdmpPrescription) {
        return Prescription.builder()
                .drugName(myPdmpPrescription.getMedicationName())
                .prescriptionDate(myPdmpPrescription.getPrescriptionDate().toLocalDate())
                .quantity(myPdmpPrescription.getQuantity())
                .dose(Double.valueOf(myPdmpPrescription.getDose().split(" ")[0]))
                .dosageMeasurement(DosageMeasurement.valueOfAbbreviation(myPdmpPrescription.getDose().split(" ")[1]))
                .patient(Patient.builder()
                        .ssn(myPdmpPrescription.getPatientSsn())
                        .firstName(myPdmpPrescription.getPatientName().split(" ")[0])
                        .lastName(myPdmpPrescription.getPatientName().split(" ")[1])
                        .address(myPdmpPrescription.getPatientAddress())
                        .build()
                )
                .prescriber(
                        Prescriber.builder()
                                .address(myPdmpPrescription.getPrescriberAddress())
                                .firstName(myPdmpPrescription.getPrescriberName().split(" ")[0])
                                .lastName(myPdmpPrescription.getPrescriberName().split(" ")[1])
                                .ssn(myPdmpPrescription.getPrescriberSsn())
                                .build()
                )
                .pharmacy(
                        Pharmacy.builder()
                                .address(myPdmpPrescription.getPharmacyAddress())
                                .name(myPdmpPrescription.getPharmacyName())
                                .build()
                )
                .build();
    }
}
