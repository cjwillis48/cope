package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String> {
}
