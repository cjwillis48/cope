package com.ihi.cope.examples.springbootclientexample.service;

import com.ihi.cope.examples.springbootclientexample.kaspermodels.MyPdmpPrescription;

public interface PrescriptionService {
    void publishPrescription(MyPdmpPrescription myPdmpPrescription);
}
