package com.ihi.cope.copeserver.service;

import com.ihi.cope.domain.Acknowledgement;

import java.util.List;
import java.util.Optional;

public interface AcknowledgementService {
    void saveAcknowledgement(Acknowledgement acknowledgement);

    List<Acknowledgement> loadAcknowledgements();

    List<Acknowledgement> loadAcknowledgements(String clientId, Boolean success);

    Optional<Acknowledgement> loadAcknowledgement(String uuid);
}
