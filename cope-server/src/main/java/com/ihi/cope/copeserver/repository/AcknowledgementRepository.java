package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.AcknowledgementEntity;
import com.ihi.cope.domain.Acknowledgement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcknowledgementRepository extends CrudRepository<AcknowledgementEntity, String> {
    List<AcknowledgementEntity> findByClientClientId(String clientClientId);

    List<AcknowledgementEntity> findBySuccessIs(boolean success);

    List<AcknowledgementEntity> findByClientClientIdIsAndSuccessIs(String clientId, boolean success);
}
