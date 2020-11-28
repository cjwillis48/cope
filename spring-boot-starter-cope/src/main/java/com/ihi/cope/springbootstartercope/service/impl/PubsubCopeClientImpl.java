package com.ihi.cope.springbootstartercope.service.impl;

import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Prescription;
import com.ihi.cope.springbootstartercope.service.CopeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnBean(value = {RabbitTemplate.class})
public class PubsubCopeClientImpl extends CopeWriter {
    private final RabbitTemplate rabbitTemplate;

    public PubsubCopeClientImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    protected Acknowledgement publishPrescription(Acknowledgement acknowledgement, Prescription prescription) {
        try {
            rabbitTemplate.convertAndSend(prescription, message -> {
                message.getMessageProperties().setHeader("acknowledgement-uuid", acknowledgement.getUuid());
                return message;
            });
        } catch (Exception e) {
            acknowledgement.setSuccess(false);
            acknowledgement.setOutput(e);
            return acknowledgement;
        }
        acknowledgement.setSuccess(true);
        acknowledgement.setOutput(acknowledgement.getUuid());
        return acknowledgement;
    }
}
