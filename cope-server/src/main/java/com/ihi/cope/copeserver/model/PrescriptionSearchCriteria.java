package com.ihi.cope.copeserver.model;

import com.ihi.cope.copeserver.entity.PrescriptionEntity;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@Data
@ToString
public class PrescriptionSearchCriteria implements Specification<PrescriptionEntity> {
    private String patientSsn;
    private String prescriberSsn;
    private String clientId;
    private String drugName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer limit;
    private Integer offset;

    @Override
    public Predicate toPredicate(Root<PrescriptionEntity> root,
                                 CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();
        if (!StringUtils.isEmpty(patientSsn)) {
            p.getExpressions().add(cb.equal(root.join("patient").get("ssn"), patientSsn));
        }

        if (!StringUtils.isEmpty(prescriberSsn)) {
            p.getExpressions().add(cb.equal(root.join("prescriber").get("ssn"), prescriberSsn));
        }

        if (!StringUtils.isEmpty(clientId)) {
            p.getExpressions().add(cb.equal(root.join("client").get("clientId"), clientId));
        }

        if (!StringUtils.isEmpty(drugName)) {
            p.getExpressions().add(cb.like(cb.upper(root.get("drugName")), drugName.toUpperCase()));
        }

        if (startDate != null) {
            p.getExpressions().add(cb.greaterThanOrEqualTo(root.get("prescriptionDate"), startDate));
        }

        if (endDate != null) {
            p.getExpressions().add(cb.lessThanOrEqualTo(root.get("prescriptionDate"), endDate));
        }
        return p;
    }


}
