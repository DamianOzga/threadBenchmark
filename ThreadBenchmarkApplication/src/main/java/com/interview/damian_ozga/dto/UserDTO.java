package com.interview.damian_ozga.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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