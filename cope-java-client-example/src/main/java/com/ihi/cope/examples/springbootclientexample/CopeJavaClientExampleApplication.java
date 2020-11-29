package com.ihi.cope.examples.springbootclientexample;

import com.ihi.cope.domain.Address;
import com.ihi.cope.domain.State;
import com.ihi.cope.examples.springbootclientexample.kaspermodels.MyPdmpPrescription;
import com.ihi.cope.examples.springbootclientexample.service.PrescriptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class CopeJavaClientExampleApplication {

    private final PrescriptionService prescriptionService;

    public CopeJavaClientExampleApplication(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CopeJavaClientExampleApplication.class, args);
    }

    @PostConstruct
    private void feedData() {
        Address.AddressBuilder addressBuilder = Address.builder()
                .state(State.KENTUCKY)
                .city("Louisville")
                .zipCode("47129");

        MyPdmpPrescription myPdmpPrescription = MyPdmpPrescription.builder()
                .medicationName("Oxycodone")
                .dose("10 mg")
                .quantity(30)
                .prescriptionDate(LocalDateTime.now())
                .prescriberSsn("000-11-1111")
                .prescriberName("Dr. Shivago")
                .prescriberAddress(addressBuilder.street("1234 Somewhere Ave").build())
                .patientSsn("111-00-1110")
                .patientName("Timmy Turner")
                .patientAddress(addressBuilder.street("1211 Nowhere St").build())
                .pharmacyName("Walgreens")
                .pharmacyAddress(addressBuilder.street("1212 Anywhere Dr").build())
                .build();

        prescriptionService.publishPrescription(myPdmpPrescription);
    }

}
