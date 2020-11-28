package com.ihi.cope.copeserver.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihi.cope.copeserver.entity.ParameterEntity;
import com.ihi.cope.domain.Parameter;
import org.springframework.stereotype.Component;

@Component
public class ParameterMapper extends Mapper<Parameter, ParameterEntity> {

    private final ObjectMapper objectMapper;

    public ParameterMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ParameterEntity modelToEntity(Parameter model) {
        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setField(model.getField());
        try {
            parameterEntity.setValue(objectMapper.writeValueAsString(model.getValue()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parameterEntity;
    }

    @Override
    public Parameter entityToModel(ParameterEntity entity) {
        Parameter parameter = new Parameter();
        parameter.setField(entity.getField());
        parameter.setValue(entity.getValue());
        return parameter;
    }
}
