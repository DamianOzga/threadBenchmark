package com.interview.damian_ozga.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) for User information.
 * This class is used to transfer user data between different layers of the application.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String key;
    private String name;
    private String email;
    private AddressDTO address;
    private List<PhoneNumberDTO> phoneNumbers;
}