import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-spring-boot-starter-cope',
  templateUrl: './spring-boot-starter-cope.component.html',
  styleUrls: ['./spring-boot-starter-cope.component.css']
})
export class SpringBootStarterCopeComponent implements OnInit {
  yaml: string = `
cope:
  mode: pubsub
  restful-configuration:
    server-url: http://localhost:8080
    username: something
    password: something-else
  pubsub-configuration:
    rabbit-url: jaguar.rmq.cloudamqp.com
    username: bkglyvqr
    password: xdL-64KGn4pBt46c_uwkZfXGMYsh9_-L
    virtual-host: bkglyvqr
  client:
    client-id: 1234567
    software-title: KASPER
    localization-name: Kentucky
    contact-address:
      street: 1811 Somewhere Ct.
      city: Louisville
      state: Kentucky
      zip-code: 47129
    contact-phone: 502-555-5555
    locality: state`

  constructor() {
  }

  ngOnInit(): void {
  }

  getMavenDependencyCode() {
    return `
<dependency>
  <groupId>com.ihi.cope</groupId>
  <artifactId>spring-boot-starter-cope</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>`;
  }


  getConfigCode() {
    return `
cope:
  mode: pubsub
  restful-configuration:
    server-url: http://localhost:8080
    username: something
    password: something-else
  pubsub-configuration:
    rabbit-url: jaguar.rmq.cloudamqp.com
    username: bkglyvqr
    password: xdL-64KGn4pBt46c_uwkZfXGMYsh9_-L
    virtual-host: bkglyvqr
  client:
    client-id: 1234567
    software-title: KASPER
    localization-name: Kentucky
    contact-address:
      street: 1811 Somewhere Ct.
      city: Louisville
      state: Kentucky
      zip-code: 47129
    contact-phone: 502-555-5555
    locality: state`
  }

  getSamplePojo() {
    return `
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

    // Getters and setters
}
`
  }

  getBuilderExample() {
    return `
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
}`
  }

  getServiceExample() {
    return `
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
        // .... code to map MyPdmpPrescription into a Prescription
    }
}`
  }
}
