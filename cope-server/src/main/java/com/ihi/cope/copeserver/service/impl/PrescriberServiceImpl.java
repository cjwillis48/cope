package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.PrescriberEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.repository.PrescriberRepository;
import com.ihi.cope.copeserver.service.AddressService;
import com.ihi.cope.copeserver.service.PrescriberService;
import com.ihi.cope.domain.Prescriber;
import org.springframework.stereotype.Service;


@Service
public class PrescriberServiceImpl implements PrescriberService {
    private final PrescriberRepository prescriberRepository;
    private final Mapper<Prescriber, PrescriberEntity> prescriberMapper;
    private final AddressService addressService;


    public PrescriberServiceImpl(PrescriberRepository prescriberRepository,
                                 Mapper<Prescriber, PrescriberEntity> prescriberMapper,
                                 AddressService addressService) {
        this.prescriberRepository = prescriberRepository;
        this.prescriberMapper = prescriberMapper;
        this.addressService = addressService;

    }

    @Override
    public Prescriber save(Prescriber prescriber) {
        prescriber.setAddress(addressService.saveIfNotExists(prescriber.getAddress()));

        PrescriberEntity entity = prescriberMapper.modelToEntity(prescriber);
        entity = prescriberRepository.save(entity);
        return prescriberMapper.entityToModel(entity);
    }
}
