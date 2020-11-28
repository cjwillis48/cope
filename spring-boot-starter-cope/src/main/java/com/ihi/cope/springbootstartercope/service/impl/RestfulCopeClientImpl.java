package com.ihi.cope.springbootstartercope.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Prescription;
import com.ihi.cope.springbootstartercope.service.CopeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@ConditionalOnBean(value = { RestTemplate.class })
public class RestfulCopeClientImpl extends CopeWriter {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestfulCopeClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    protected Acknowledgement publishPrescription(Acknowledgement acknowledgement, Prescription prescription) {
        ResponseEntity<Acknowledgement> acknowledgementResponse;
        try {
            acknowledgementResponse = restTemplate.postForEntity("/prescription", prescription, Acknowledgement.class);
        } catch (HttpStatusCodeException e) {
            acknowledgement.setSuccess(false);
            return handleResponseError(e, acknowledgement);
        } catch (RestClientException e) {
            acknowledgement.setSuccess(false);
            acknowledgement.setOutput(e);
            return acknowledgement;
        }

        Acknowledgement childAcknowledgement = acknowledgementResponse.getBody();
        acknowledgement.setSuccess(childAcknowledgement.isSuccess());
        acknowledgement.setOutput(childAcknowledgement);
        return acknowledgement;

    }

    private Acknowledgement handleResponseError(
            HttpStatusCodeException errorResponse,
            Acknowledgement acknowledgement) {

        if (errorResponse.getStatusCode().is5xxServerError()) {
            try {
                acknowledgement.setOutput(objectMapper.readValue(errorResponse.getResponseBodyAsString(), Acknowledgement.class));
            } catch (JsonProcessingException e) {
                log.warn("Could not parse string body as Acknowledgement: {}", errorResponse.getResponseBodyAsString());
                acknowledgement.setOutput(errorResponse.getResponseBodyAsString());
            }
        } else {
            acknowledgement.setOutput(errorResponse);
        }

        acknowledgement.setSuccess(false);
        return acknowledgement;
    }
}
