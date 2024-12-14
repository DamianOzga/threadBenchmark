package com.interview.damian_ozga.mapper;

import com.interview.damian_ozga.model.User;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static Document toDocument(User user) {
        Document addressDoc = AddressMapper.toDocument(user.getAddress());
        List<Document> phoneNumbersDocs = user.getPhoneNumbers().stream()
                                              .map(PhoneNumberMapper::toDocument)
                                              .collect(Collectors.toList());
        return new Document("id", user.getId())
                .append("name", user.getName())
                .append("email", user.getEmail())
                .append("address", addressDoc)
                .append("phoneNumbers", phoneNumbersDocs);
    }
}