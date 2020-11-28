package com.ihi.cope.copeserver.mappers;

import com.ihi.cope.copeserver.entity.AddressEntity;
import com.ihi.cope.copeserver.entity.PatientEntity;
import com.ihi.cope.domain.Address;
import com.ihi.cope.domain.Patient;
import org.springframework.stereotype.Component;


@Component
public class PatientMapper extends Mapper<Patient, PatientEntity> {
    private final Mapper<Address, AddressEntity> addressMapper;

    public PatientMapper(Mapper<Address, AddressEntity> addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public PatientEntity modelToEntity(Patient model) {
        PatientEntity entity = new PatientEntity();
        entity.setAddress(addressMapper.modelToEntity(model.getAddress()));
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setSsn(model.getSsn());
        return entity;
    }

    @Override
    public Patient entityToModel(PatientEntity entity) {
        Patient model = new Patient();
        model.setAddress(addressMapper.entityToModel(entity.getAddress()));
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setSsn(entity.getSsn());
        return model;
    }
}
