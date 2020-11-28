package com.ihi.cope.springbootstartercope.service;

import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Client;
import com.ihi.cope.domain.Parameter;
import com.ihi.cope.domain.Prescription;
import com.ihi.cope.springbootstartercope.model.CopeServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class CopeWriter {

    protected Client client;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    protected abstract Acknowledgement publishPrescription(Acknowledgement acknowledgement, Prescription prescription);

    @Autowired
    public void setClient(CopeServerConfiguration copeServerConfiguration) {
        this.client = copeServerConfiguration.getClient();
    }

    public Acknowledgement publishPrescription(Supplier<Prescription> prescriptionSupplier) {
        Acknowledgement acknowledgement = new Acknowledgement();
        String uuid = UUID.randomUUID().toString();
        acknowledgement.setUuid(uuid);
        acknowledgement.setOperation("Publish prescription");
        acknowledgement.setStartTime(LocalDateTime.now());
        acknowledgement.setClient(client);

        Prescription prescription = prescriptionSupplier.get();
        prescription.setClient(client);

        acknowledgement.setInput(Collections.singletonList(new Parameter("prescription", prescription)));

        Set<ConstraintViolation<Prescription>> violations = validator.validate(prescription);

        if (!violations.isEmpty()) {
            acknowledgement.setOutput(violations);
            acknowledgement.setSuccess(false);
            acknowledgement.setCompleteTime(LocalDateTime.now());
            return acknowledgement;
        }

        acknowledgement = publishPrescription(acknowledgement, prescription);
        acknowledgement.setCompleteTime(LocalDateTime.now());
        return acknowledgement;
    }
}
