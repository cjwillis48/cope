package com.ihi.cope.copeserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "parameter")
@Table(name = "parameter")
public class ParameterEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String field;

    @Lob
    private String value;

    @ManyToOne
    @JoinColumn(name = "acknowledgement_id", referencedColumnName = "uuid")
    private AcknowledgementEntity acknowledgement;

    public ParameterEntity() {

    }

    public ParameterEntity(String field, String value) {
        this.value = value;
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AcknowledgementEntity getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(AcknowledgementEntity acknowledgement) {
        this.acknowledgement = acknowledgement;
    }
}
