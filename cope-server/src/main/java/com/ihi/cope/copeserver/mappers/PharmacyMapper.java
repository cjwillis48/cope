package com.ihi.cope.copeserver.mappers;

import com.ihi.cope.copeserver.entity.AddressEntity;
import com.ihi.cope.copeserver.entity.PharmacyEntity;
import com.ihi.cope.domain.Address;
import com.ihi.cope.domain.Pharmacy;
import org.springframework.stereotype.Component;

@Component
public class PharmacyMapper extends Mapper<Pharmacy, PharmacyEntity> {

    private final Mapper<Address, AddressEntity> addressMapper;

    public PharmacyMapper(Mapper<Address, AddressEntity> addressMapper) {
        this.addressMapper = addressMapper;
    }


    @Override
    public PharmacyEntity modelToEntity(Pharmacy model) {
        PharmacyEntity entity = new PharmacyEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setAddress(addressMapper.modelToEntity(model.getAddress()));
        return entity;
    }

    @Override
    public Pharmacy entityToModel(PharmacyEntity entity) {
        Pharmacy model = new Pharmacy();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setAddress(addressMapper.entityToModel(entity.getAddress()));
        return model;
    }
}
