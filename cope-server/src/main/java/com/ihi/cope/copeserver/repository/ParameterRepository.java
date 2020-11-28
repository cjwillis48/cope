package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.ParameterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends CrudRepository<ParameterEntity, Long> {

}
