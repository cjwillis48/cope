package com.ihi.cope.copeserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "prescription")
@Table(name = "prescription")
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @NotNull
    private PrescriberEntity prescriber;

    @ManyToOne
    @NotNull
    private PatientEntity patient;

    @ManyToOne
    @NotNull
    private PharmacyEntity pharmacy;

    @ManyToOne
    @NotNull
    private ClientEntity client;

    @NotNull
    private String drugName;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double dose;

    @NotNull
    private String doseMeasurementAbbreviation;

    private String instructions;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate prescriptionDate;

    public long getId() {
        return id;
    }

    public PrescriberEntity getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(PrescriberEntity prescriberEntity) {
        this.prescriber = prescriberEntity;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patientEntity) {
        this.patient = patientEntity;
    }

    public PharmacyEntity getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyEntity pharmacyEntity) {
        this.pharmacy = pharmacyEntity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public String getDoseMeasurementAbbreviation() {
        return doseMeasurementAbbreviation;
    }

    public void setDoseMeasurementAbbreviation(String doseMeasurementAbbreviation) {
        this.doseMeasurementAbbreviation = doseMeasurementAbbreviation;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }
}
