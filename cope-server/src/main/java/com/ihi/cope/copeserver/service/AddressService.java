package com.ihi.cope.copeserver.service;

import com.ihi.cope.domain.Address;

public interface AddressService {
    boolean addressExists(Address address);

    Address saveIfNotExists(Address address);
}
