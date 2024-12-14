package com.interview.damian_ozga.mapper;

import com.interview.damian_ozga.model.PhoneNumber;
import org.bson.Document;

public class PhoneNumberMapper {
    protected static Document toDocument(PhoneNumber phoneNumber) {
        return new Document("type", phoneNumber.getType())
                .append("number", phoneNumber.getNumber());
    }
}
