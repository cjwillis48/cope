package com.ihi.cope.copeserver.mappers;

import com.ihi.cope.copeserver.entity.AddressEntity;
import com.ihi.cope.copeserver.entity.ClientEntity;
import com.ihi.cope.domain.Address;
import com.ihi.cope.domain.Client;
import com.ihi.cope.domain.Locality;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends Mapper<Client, ClientEntity> {
    private final Mapper<Address, AddressEntity> addressMapper;

    public ClientMapper(Mapper<Address, AddressEntity> addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public ClientEntity modelToEntity(Client model) {
        ClientEntity entity = new ClientEntity();
        entity.setClientId(model.getClientId());
        entity.setContactAddress(addressMapper.modelToEntity(model.getContactAddress()));
        entity.setContactPhone(model.getContactPhone());
        entity.setLocalityTypeCode(model.getLocality().getCode());
        entity.setLocalizationName(model.getLocalizationName());
        entity.setSoftwareTitle(model.getSoftwareTitle());
        return entity;
    }

    @Override
    public Client entityToModel(ClientEntity entity) {
        Client model = new Client();
        model.setClientId(entity.getClientId());
        model.setContactAddress(addressMapper.entityToModel(entity.getContactAddress()));
        model.setContactPhone(entity.getContactPhone());
        model.setLocality(Locality.valueOfCode(entity.getLocalityTypeCode()));
        model.setLocalizationName(entity.getLocalizationName());
        model.setSoftwareTitle(entity.getSoftwareTitle());
        return model;
    }
}
