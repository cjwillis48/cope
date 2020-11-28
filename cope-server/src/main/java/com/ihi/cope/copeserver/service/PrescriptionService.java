package com.ihi.cope.copeserver.service;

import com.ihi.cope.copeserver.model.PrescriptionSearchCriteria;
import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Prescription;

import java.util.List;

public interface PrescriptionService {
    Acknowledgement recordPrescription(Prescription prescription);

    List<Prescription> getPrescriptionsForPatient(String ssn);

    List<Prescription> getPrescriptionsForSearchCriteria(PrescriptionSearchCriteria searchCriteria);
}
