package com.interview.damian_ozga.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Benchmark")
public class User extends MongoDbDocument {
    @Id
    private String key;
    private String name;
    private String email;
    private Address address;
    private List<PhoneNumber> phoneNumbers;
}