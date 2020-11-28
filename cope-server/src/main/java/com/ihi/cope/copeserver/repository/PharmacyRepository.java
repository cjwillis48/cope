package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.PharmacyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends CrudRepository<PharmacyEntity, Long> {
    PharmacyEntity findByNameAndAddressId(String name, Long addressId);
}
