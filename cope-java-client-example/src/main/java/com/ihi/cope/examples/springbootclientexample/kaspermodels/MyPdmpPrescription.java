package com.ihi.cope.examples.springbootclientexample.kaspermodels;

import com.ihi.cope.domain.Address;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MyPdmpPrescription {
    // Medication Info
    private String medicationName;
    private String dose;
    private int quantity;
    private LocalDateTime prescriptionDate;

    // Prescriber info
    private String prescriberSsn;
    private String prescriberName;
    private Address prescriberAddress;

    // Patient info
    private String patientSsn;
    private String patientName;
    private Address patientAddress;

    // Pharmacy info
    private String pharmacyName;
    private Address pharmacyAddress;
}
