package com.ihi.cope.copeserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "patient")
@Table(name = "patient")
public class PatientEntity {

    @Id
    protected String ssn;
    protected String firstName;
    protected String lastName;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    protected AddressEntity address;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "patient")
    private List<PrescriptionEntity> prescriptionEntities;

    @JsonIgnore
    public List<PrescriptionEntity> getPrescriptionEntities() {
        return prescriptionEntities;
    }

    public void setPrescriptionEntities(List<PrescriptionEntity> prescriptionEntities) {
        this.prescriptionEntities = prescriptionEntities;
    }
}
