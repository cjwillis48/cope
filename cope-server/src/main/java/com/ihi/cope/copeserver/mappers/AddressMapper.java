package com.ihi.cope.copeserver.mappers;

import com.ihi.cope.copeserver.entity.AddressEntity;
import com.ihi.cope.domain.Address;
import com.ihi.cope.domain.State;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends Mapper<Address, AddressEntity> {
    @Override
    public AddressEntity modelToEntity(Address model) {
        AddressEntity entity = new AddressEntity();
        entity.setId(model.getId());
        entity.setStreet(model.getStreet());
        entity.setCity(model.getCity());
        entity.setStateAbbreviation(model.getState().getAbbreviation());
        entity.setZipCode(model.getZipCode());
        return entity;
    }

    @Override
    public Address entityToModel(AddressEntity entity) {
        Address model = new Address();
        model.setId(entity.getId());
        model.setStreet(entity.getStreet());
        model.setCity(entity.getCity());
        model.setState(State.valueOfAbbreviation(entity.getStateAbbreviation()));
        model.setZipCode(entity.getZipCode());
        return model;
    }
}
