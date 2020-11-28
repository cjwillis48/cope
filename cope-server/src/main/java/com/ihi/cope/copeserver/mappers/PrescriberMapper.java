package com.ihi.cope.copeserver.mappers;

import com.ihi.cope.copeserver.entity.AddressEntity;
import com.ihi.cope.copeserver.entity.PrescriberEntity;
import com.ihi.cope.domain.Address;
import com.ihi.cope.domain.Prescriber;
import org.springframework.stereotype.Component;

@Component
public class PrescriberMapper extends Mapper<Prescriber, PrescriberEntity> {

    private final Mapper<Address, AddressEntity> addressMapper;

    public PrescriberMapper(Mapper<Address, AddressEntity> addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public PrescriberEntity modelToEntity(Prescriber model) {
        PrescriberEntity entity = new PrescriberEntity();
        entity.setAddress(addressMapper.modelToEntity(model.getAddress()));
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setSsn(model.getSsn());
        return entity;
    }

    @Override
    public Prescriber entityToModel(PrescriberEntity entity) {
        Prescriber model = new Prescriber();
        model.setAddress(addressMapper.entityToModel(entity.getAddress()));
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setSsn(entity.getSsn());
        return model;
    }
}
