package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.PrescriptionEntity;
import com.ihi.cope.copeserver.model.PrescriptionSearchCriteria;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<PrescriptionEntity, Long>, JpaSpecificationExecutor<PrescriptionEntity> {
    List<PrescriptionEntity> findAllByPatientSsn(String patientSsn);


}
