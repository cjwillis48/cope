package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.PrescriberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriberRepository extends CrudRepository<PrescriberEntity, String> {
}
