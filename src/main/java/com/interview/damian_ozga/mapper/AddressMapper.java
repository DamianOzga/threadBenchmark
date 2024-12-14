package com.interview.damian_ozga.mapper;

import com.interview.damian_ozga.model.Address;
import org.bson.Document;

public class AddressMapper {
    protected static Document toDocument(Address address) {
        return new Document("street", address.getStreet())
                .append("city", address.getCity())
                .append("state", address.getState())
                .append("zipcode", address.getZipcode());
    }
}

