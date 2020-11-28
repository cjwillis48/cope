package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.PharmacyEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.repository.PharmacyRepository;
import com.ihi.cope.copeserver.service.AddressService;
import com.ihi.cope.copeserver.service.PharmacyService;
import com.ihi.cope.domain.Pharmacy;
import org.springframework.stereotype.Service;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final Mapper<Pharmacy, PharmacyEntity> pharmacyMapper;
    private final AddressService addressService;

    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository,
                               Mapper<Pharmacy, PharmacyEntity> pharmacyMapper,
                               AddressService addressService) {
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacyMapper = pharmacyMapper;
        this.addressService = addressService;
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        pharmacy.setAddress(addressService.saveIfNotExists(pharmacy.getAddress()));

        PharmacyEntity entity = pharmacyRepository.findByNameAndAddressId(pharmacy.getName(), pharmacy.getAddress().getId());

        if (entity == null) {
            entity = pharmacyMapper.modelToEntity(pharmacy);
            entity = pharmacyRepository.save(entity);
        }

        return pharmacyMapper.entityToModel(entity);
    }
}
