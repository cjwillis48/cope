package com.ihi.cope.copeserver.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ihi.cope.copeserver.service.AcknowledgementService;
import com.ihi.cope.copeserver.service.PrescriptionService;
import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Prescription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "cope.inbound.prescription")
public class PrescriptionConsumer {
    private final PrescriptionService prescriptionService;
    private final AcknowledgementService acknowledgementService;

    public PrescriptionConsumer(PrescriptionService prescriptionService,
                                AcknowledgementService acknowledgementService) {
        this.prescriptionService = prescriptionService;
        this.acknowledgementService = acknowledgementService;
    }

    @RabbitHandler
    public void consume(Prescription prescriptionMessage, @Header("acknowledgement-uuid") String uuid) throws JsonProcessingException {
        log.info("Message received. UUID: {} Prescription: {}", uuid, prescriptionMessage);
        Acknowledgement ack = prescriptionService.recordPrescription(prescriptionMessage);
        ack.setUuid(uuid);
        acknowledgementService.saveAcknowledgement(ack);
    }
}
