package com.ihi.cope.copeserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "client")
@Table(name = "client")
public class ClientEntity {
    @Id
    private String clientId;

    @OneToMany(mappedBy = "client")
    private List<PrescriptionEntity> prescriptions;

    @OneToMany(mappedBy = "client")
    private List<AcknowledgementEntity> acknowledgements;

    private String localizationName;
    private String localityTypeCode;
    private String softwareTitle;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity contactAddress;

    private String contactPhone;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionEntity> prescriptionEntity) {
        this.prescriptions = prescriptionEntity;
    }

    public String getLocalizationName() {
        return localizationName;
    }

    public void setLocalizationName(String localizationName) {
        this.localizationName = localizationName;
    }

    public String getLocalityTypeCode() {
        return localityTypeCode;
    }

    public void setLocalityTypeCode(String localityTypeCode) {
        this.localityTypeCode = localityTypeCode;
    }

    public String getSoftwareTitle() {
        return softwareTitle;
    }

    public void setSoftwareTitle(String softwareTitle) {
        this.softwareTitle = softwareTitle;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public AddressEntity getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(AddressEntity contactAddress) {
        this.contactAddress = contactAddress;
    }
}
