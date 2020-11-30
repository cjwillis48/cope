package com.ihi.cope.copeserver.config;

import com.ihi.cope.copeserver.service.AcknowledgementService;
import com.ihi.cope.copeserver.service.PrescriptionService;
import com.ihi.cope.domain.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

@Profile("local")
@Configuration
public class DataBootStrapper {
    // This class is just used to bootstrap data when running locally

    private final PrescriptionService prescriptionService;
    private final AcknowledgementService acknowledgementService;

    public DataBootStrapper(PrescriptionService prescriptionService, AcknowledgementService acknowledgementService) {
        this.prescriptionService = prescriptionService;
        this.acknowledgementService = acknowledgementService;
    }

    @PostConstruct
    private void fillWithTestData() {
        Address.AddressBuilder addressBuilder = Address.builder()
                .state(State.KENTUCKY)
                .city("Louisville")
                .zipCode("47129");

        Client client = Client.builder()
                .clientId("1234567")
                .locality(Locality.STATE)
                .localizationName("Kentucky")
                .softwareTitle("KASPER")
                .contactPhone("502-555-5555")
                .contactAddress(addressBuilder.street("1234 Sesame St").build())
                .build();

        Patient[] patients = {
                Patient.builder().ssn("101-01-0101").firstName("Michael").lastName("Meyers").address(addressBuilder.street("100 Elm St").build()).build(),
                Patient.builder().ssn("102-02-0202").firstName("Jason").lastName("Vorhees").address(addressBuilder.street("101 Pearl St").build()).build(),
                Patient.builder().ssn("103-03-0303").firstName("Peter").lastName("Griffin").address(addressBuilder.street("102 State St").build()).build(),
                Patient.builder().ssn("104-04-0404").firstName("Frasier").lastName("Crane").address(addressBuilder.street("103 Indiana Ave").build()).build(),
                Patient.builder().ssn("105-05-0505").firstName("Daphne").lastName("Moon").address(addressBuilder.street("104 Bardstown Road").build()).build(),
                Patient.builder().ssn("106-06-0707").firstName("Michael").lastName("Scott").address(addressBuilder.street("105 Ekin St").build()).build(),
                Patient.builder().ssn("107-07-0707").firstName("Dwight").lastName("Schrute").address(addressBuilder.street("106 Oak Ave").build()).build(),
                Patient.builder().ssn("108-08-0808").firstName("Ross").lastName("Geller").address(addressBuilder.street("101 Elm St").build()).build(),
                Patient.builder().ssn("109-09-0909").firstName("Barney").lastName("Stinson").address(addressBuilder.street("103 Elm St").build()).build(),
                Patient.builder().ssn("100-00-0000").firstName("Freddy").lastName("Kreuger").address(addressBuilder.street("1000 E Main St").build()).build(),
                Patient.builder().ssn("110-10-0010").firstName("Patrick").lastName("Star").address(addressBuilder.street("491 Davis Dr").build()).build(),
                Patient.builder().ssn("111-11-0011").firstName("Jim").lastName("Halpert").address(addressBuilder.street("1101 Market St").build()).build(),
                Patient.builder().ssn("112-12-0012").firstName("Ted").lastName("Mosby").address(addressBuilder.street("1110 Hollywood Ave").build()).build()
        };

        Prescriber[] prescribers = {
                Prescriber.builder().ssn("123-11-3331").firstName("Chris").lastName("Kringle").address(addressBuilder.street("101 4th St").build()).build(),
                Prescriber.builder().ssn("456-91-4321").firstName("Sheldon").lastName("Cooper").address(addressBuilder.street("202 5th St").build()).build(),
                Prescriber.builder().ssn("949-01-1121").firstName("Niles").lastName("Crane").address(addressBuilder.street("303 6th St").build()).build()
        };

        Pharmacy[] pharmacies = {
                Pharmacy.builder().name("Walgreens").address(addressBuilder.street("4101 Charlestown Rd").build()).build(),
                Pharmacy.builder().name("CVS").address(addressBuilder.street("1001 Grant Line Rd").build()).build()
        };

        String[] drugNames = {
                "Oxycodone",
                "Hydrocodone",
                "Oxymorphone",
                "Morphine",
                "Hydromorphone",
                "Subutex",
                "Fentanyl"
        };

        double[] dosages = {5, 10, 15, 20, 30, 50, 80};
        int[] quantities = {5, 10, 15, 30, 90, 180};

        String[] instructions = {null, "Take twice a day with food", "take as needed for pain"};

        for (Patient patient : patients) {
            Prescription prescription = new Prescription();
            prescription.setPatient(patient);
            prescription.setPrescriber(prescribers[ThreadLocalRandom.current().nextInt(0, 3)]);
            prescription.setPharmacy(pharmacies[ThreadLocalRandom.current().nextInt(0, 2)]);
            prescription.setDrugName(drugNames[ThreadLocalRandom.current().nextInt(0, 7)]);
            prescription.setDose(dosages[ThreadLocalRandom.current().nextInt(0, 7)]);
            prescription.setDosageMeasurement(prescription.getDrugName().equalsIgnoreCase("fentanyl") ?
                    DosageMeasurement.MICROGRAM : DosageMeasurement.MILLIGRAM);
            prescription.setClient(client);
            prescription.setQuantity(quantities[ThreadLocalRandom.current().nextInt(0, 6)]);
            prescription.setInstructions(instructions[ThreadLocalRandom.current().nextInt(0, 3)]);
            prescription.setPrescriptionDate(LocalDate.of(2020, Month.NOVEMBER, ThreadLocalRandom.current().nextInt(1, 31)));

            acknowledgementService.saveAcknowledgement(prescriptionService.recordPrescription(prescription));
        }
    }
}
