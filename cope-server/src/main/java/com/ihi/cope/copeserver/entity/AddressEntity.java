package com.ihi.cope.copeserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "address")
@Table(
        name = "address",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"street", "city", "state_abbreviation", "zip_code"}
                )
        }
)
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private String city;

    @JsonIgnore
    @Column(name = "state_abbreviation")
    private String stateAbbreviation;

    @Column(name = "zip_code")
    private String zipCode;

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
