package com.ihi.cope.copeserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "pharmacy")
@Table(
        name = "pharmacy",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name", "address_id"}
                )
        }
)
public class PharmacyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @OneToMany(mappedBy = "pharmacy")
    private List<PrescriptionEntity> prescriptions;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionEntity> prescriptionEntities) {
        this.prescriptions = prescriptionEntities;
    }
}
