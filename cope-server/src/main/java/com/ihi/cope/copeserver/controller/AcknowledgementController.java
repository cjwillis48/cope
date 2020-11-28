package com.ihi.cope.copeserver.controller;

import com.ihi.cope.copeserver.service.AcknowledgementService;
import com.ihi.cope.domain.Acknowledgement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/acknowledgement")
@RestController
public class AcknowledgementController {
    private final AcknowledgementService acknowledgementService;

    public AcknowledgementController(AcknowledgementService acknowledgementService) {
        this.acknowledgementService = acknowledgementService;
    }

    @GetMapping
    public ResponseEntity<List<Acknowledgement>> getAcknowledgements(
            @RequestParam(value = "clientId", required = false) String clientId,
            @RequestParam(value = "success", required = false) Boolean status) {
        return ResponseEntity.ok(acknowledgementService.loadAcknowledgements(clientId, status));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<Acknowledgement> getAcknowledgementById(@PathVariable String uuid) {
        Optional<Acknowledgement> acknowledgement = acknowledgementService.loadAcknowledgement(uuid);
        return acknowledgement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

}
