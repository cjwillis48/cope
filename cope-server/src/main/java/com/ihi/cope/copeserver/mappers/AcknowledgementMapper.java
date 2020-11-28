package com.ihi.cope.copeserver.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihi.cope.copeserver.entity.AcknowledgementEntity;
import com.ihi.cope.copeserver.entity.ClientEntity;
import com.ihi.cope.copeserver.entity.ParameterEntity;
import com.ihi.cope.domain.Acknowledgement;
import com.ihi.cope.domain.Client;
import com.ihi.cope.domain.Parameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AcknowledgementMapper extends Mapper<Acknowledgement, AcknowledgementEntity> {
    private final ObjectMapper objectMapper;
    private final Mapper<Parameter, ParameterEntity> parameterMapper;
    private final Mapper<Client, ClientEntity> clientMapper;

    public AcknowledgementMapper(ObjectMapper objectMapper,
                                 Mapper<Parameter, ParameterEntity> parameterMapper,
                                 Mapper<Client, ClientEntity> clientMapper) {
        this.objectMapper = objectMapper;
        this.parameterMapper = parameterMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public AcknowledgementEntity modelToEntity(Acknowledgement model) {
        AcknowledgementEntity entity = new AcknowledgementEntity();

        if (!StringUtils.isEmpty(model.getUuid())) {
            entity.setUuid(model.getUuid());
        } else {
            entity.setUuid(UUID.randomUUID().toString());
        }


        entity.setInput(
                model.getInput().stream().map(parameterMapper::modelToEntity).collect(Collectors.toList()));

        entity.setOperation(model.getOperation());
        entity.setSuccess(model.isSuccess());
        entity.setStartTime(model.getStartTime());
        entity.setCompleteTime(model.getCompleteTime());

        entity.setClient(clientMapper.modelToEntity(model.getClient()));

        try {
            entity.setOutput(objectMapper.writeValueAsString(model.getOutput()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Acknowledgement entityToModel(AcknowledgementEntity entity) {
        Acknowledgement model = new Acknowledgement();
        model.setInput(
                entity.getInput().stream().map(parameterMapper::entityToModel).collect(Collectors.toList()));
        model.setUuid(entity.getUuid());
        model.setOperation(entity.getOperation());
        model.setSuccess(entity.isSuccess());
        model.setOutput(entity.getOutput());
        model.setStartTime(entity.getStartTime());
        model.setCompleteTime(entity.getCompleteTime());
        model.setClient(clientMapper.entityToModel(entity.getClient()));
        return model;
    }
}
