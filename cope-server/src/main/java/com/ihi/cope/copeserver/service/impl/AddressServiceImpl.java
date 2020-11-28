package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.AddressEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.repository.AddressRepository;
import com.ihi.cope.copeserver.service.AddressService;
import com.ihi.cope.domain.Address;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final Mapper<Address, AddressEntity> addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository,
                              Mapper<Address, AddressEntity> addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public boolean addressExists(Address address) {

        AddressEntity entity = addressMapper.modelToEntity(address);
        return addressRepository.addressExists(entity);
    }

    @Override
    public Address saveIfNotExists(Address address) {
        AddressEntity entity = addressMapper.modelToEntity(address);
        AddressEntity existingAddress = addressRepository.findByStreetAndCityAndStateAbbreviationAndZipCode(
                entity.getStreet(), entity.getCity(), entity.getStateAbbreviation(), entity.getZipCode());
        if (existingAddress == null) {
            existingAddress = addressRepository.save(entity);

        }
        return addressMapper.entityToModel(existingAddress);
    }
}
