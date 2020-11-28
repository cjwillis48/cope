package com.ihi.cope.copeserver.controller;

import com.ihi.cope.copeserver.model.PrescriptionSearchCriteria;
import com.ihi.cope.copeserver.service.AcknowledgementService;
import com.ihi.cope.copeserver.service.PrescriptionService;
import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Prescription;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/prescription")
@RestController
@Validated
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final AcknowledgementService acknowledgementService;

    public PrescriptionController(PrescriptionService prescriptionService, AcknowledgementService acknowledgementService) {
        this.prescriptionService = prescriptionService;
        this.acknowledgementService = acknowledgementService;
    }

    @PostMapping
    public ResponseEntity<Acknowledgement> insert(@Valid @NotNull @RequestBody Prescription prescription) {

        Acknowledgement acknowledgement = prescriptionService.recordPrescription(prescription);
        storeAcknowledgement(acknowledgement);
        if (acknowledgement.isSuccess()) {
            return ResponseEntity.ok(acknowledgement);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(acknowledgement);
        }
    }

    @GetMapping
    public ResponseEntity<List<Prescription>> getPrescriptionsWithSearchCriteria(
            @RequestParam(value = "patientSsn", required = false) String patientSsn,
            @RequestParam(value = "prescriberSsn", required = false) String prescriberSsn,
            @RequestParam(value = "clientId", required = false) String clientId,
            @RequestParam(value = "drugName", required = false) String drugName,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        PrescriptionSearchCriteria searchCriteria = new PrescriptionSearchCriteria();
        searchCriteria.setPatientSsn(patientSsn);
        searchCriteria.setPrescriberSsn(prescriberSsn);
        searchCriteria.setClientId(clientId);
        searchCriteria.setDrugName(drugName);
        searchCriteria.setStartDate(startDate);
        searchCriteria.setEndDate(endDate);
        return ResponseEntity.ok(prescriptionService.getPrescriptionsForSearchCriteria(searchCriteria));
    }
//
//    @GetMapping("/patient/{ssn}")
//    public ResponseEntity<List<Prescription>> getPrescriptions(@PathVariable String ssn) {
//        return ResponseEntity.ok(prescriptionService.getPrescriptionsForPatient(ssn));
//    }

    @Async
    public void storeAcknowledgement(Acknowledgement acknowledgement) {
        acknowledgementService.saveAcknowledgement(acknowledgement);
    }
}
