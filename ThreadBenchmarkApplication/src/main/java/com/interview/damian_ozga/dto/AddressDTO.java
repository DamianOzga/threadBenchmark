package com.interview.damian_ozga.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Address information.
 * This class is used to transfer address data between different layers of the application.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String zipcode;
}