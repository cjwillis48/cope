package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, String> {
    PatientEntity findPatientBySsn(String ssn);

    List<PatientEntity> findPatientsByFirstNameIsAndLastNameIs(String firstName, String lastName);
}
