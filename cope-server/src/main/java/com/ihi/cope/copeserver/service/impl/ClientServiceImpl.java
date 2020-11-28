package com.ihi.cope.copeserver.service.impl;

import com.ihi.cope.copeserver.entity.ClientEntity;
import com.ihi.cope.copeserver.mappers.Mapper;
import com.ihi.cope.copeserver.repository.ClientRepository;
import com.ihi.cope.copeserver.service.AddressService;
import com.ihi.cope.copeserver.service.ClientService;
import com.ihi.cope.domain.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final Mapper<Client, ClientEntity> clientMapper;
    private final AddressService addressService;

    public ClientServiceImpl(ClientRepository clientRepository,
                             Mapper<Client, ClientEntity> clientMapper,
                             AddressService addressService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.addressService = addressService;

    }

    @Override
    public Client save(Client client) {
        client.setContactAddress(addressService.saveIfNotExists(client.getContactAddress()));

        ClientEntity entity = clientMapper.modelToEntity(client);
        entity = clientRepository.save(entity);
        return clientMapper.entityToModel(entity);
    }
}
