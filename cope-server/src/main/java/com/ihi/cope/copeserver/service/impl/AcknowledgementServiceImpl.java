package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.AcknowledgementEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.repository.AcknowledgementRepository;
import com.ihi.cope.copeserver.repository.ParameterRepository;
import com.ihi.cope.copeserver.service.AcknowledgementService;
import com.ihi.cope.domain.Acknowledgement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcknowledgementServiceImpl implements AcknowledgementService {
    private final ParameterRepository parameterRepository;
    private final AcknowledgementRepository acknowledgementRepository;
    private final Mapper<Acknowledgement, AcknowledgementEntity> acknowledgementMapper;

    public AcknowledgementServiceImpl(ParameterRepository parameterRepository,
                                      AcknowledgementRepository acknowledgementRepository,
                                      Mapper<Acknowledgement, AcknowledgementEntity> acknowledgementMapper) {
        this.parameterRepository = parameterRepository;
        this.acknowledgementRepository = acknowledgementRepository;
        this.acknowledgementMapper = acknowledgementMapper;
    }


    @Override
    public void saveAcknowledgement(Acknowledgement acknowledgement) {
        AcknowledgementEntity entity = acknowledgementMapper.modelToEntity(acknowledgement);
        acknowledgementRepository.save(entity);

        entity.getInput().forEach(parameter -> {
            parameter.setAcknowledgement(entity);
            parameterRepository.save(parameter);
        });

    }

    @Override
    public List<Acknowledgement> loadAcknowledgements() {
        List<AcknowledgementEntity> entities = new ArrayList<>();
        acknowledgementRepository.findAll().forEach(entities::add);

        return entities.stream().map(acknowledgementMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Acknowledgement> loadAcknowledgements(String clientId, Boolean success) {
        List<AcknowledgementEntity> entities = new ArrayList<>();

        if (!StringUtils.isEmpty(clientId) && success != null) {
            entities = acknowledgementRepository.findByClientClientIdIsAndSuccessIs(clientId, success);
        } else if (!StringUtils.isEmpty(clientId)) {
            entities = acknowledgementRepository.findByClientClientId(clientId);
        } else if (success != null) {
            entities = acknowledgementRepository.findBySuccessIs(success);
        } else {
             return loadAcknowledgements();
        }

        return entities.stream().map(acknowledgementMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Acknowledgement> loadAcknowledgement(String uuid) {
        return acknowledgementRepository.findById(uuid).map(acknowledgementMapper::entityToModel);
    }
}
