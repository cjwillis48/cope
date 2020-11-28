package com.ihi.cope.copeserver.repository;

import com.ihi.cope.copeserver.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    AddressEntity findByStreetAndCityAndStateAbbreviationAndZipCode(
            String street, String city, String stateAbbreviation, String zipCode);

    default boolean addressExists(
            String street, String city, String stateAbbreviation, String zipCode) {
        return findByStreetAndCityAndStateAbbreviationAndZipCode(street, city, stateAbbreviation, zipCode) != null;
    }

    default boolean addressExists(AddressEntity address) {
        return addressExists(address.getStreet(), address.getCity(), address.getStateAbbreviation(), address.getZipCode());
    }

}
