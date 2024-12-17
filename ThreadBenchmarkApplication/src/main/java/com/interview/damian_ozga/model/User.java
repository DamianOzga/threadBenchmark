package com.interview.damian_ozga.model;

import com.interview.damian_ozga.service_const.ThreadBenchmarkConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Model class representing a User.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = ThreadBenchmarkConst.USER_COLLECTION)
public class User extends MongoDbDocument {
    @Id
    private String key;
    private String name;
    private String email;
    private Address address;
    private List<PhoneNumber> phoneNumbers;
}