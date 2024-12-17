package com.interview.damian_ozga.mapper;

import com.interview.damian_ozga.dto.AddressDTO;
import com.interview.damian_ozga.dto.PhoneNumberDTO;
import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.model.Address;
import com.interview.damian_ozga.model.PhoneNumber;
import com.interview.damian_ozga.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Service class for mapping between User entities and UserDTOs.
 */
@Service
public class UserMapper implements CommonMapper<User, UserDTO> {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the user entity to convert
     * @return the corresponding UserDTO
     */
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .key(user.getKey())
                .name(user.getName())
                .email(user.getEmail())
                .address(toDto(user.getAddress()))
                .phoneNumbers(user.getPhoneNumbers() != null ?
                        user.getPhoneNumbers().stream()
                                .map(this::toDto)
                                .collect(Collectors.toList()) : null)
                .build();
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return the corresponding User entity
     */
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .key(userDTO.getKey())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .address(toEntity(userDTO.getAddress()))
                .phoneNumbers(userDTO.getPhoneNumbers() != null ?
                        userDTO.getPhoneNumbers().stream()
                                .map(this::toEntity)
                                .collect(Collectors.toList()) : null)
                .build();
    }

    /**
     * Converts an Address entity to an AddressDTO.
     *
     * @param address the address entity to convert
     * @return the corresponding AddressDTO
     */
    public AddressDTO toDto(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDTO.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipcode(address.getZipcode())
                .build();
    }

    /**
     * Converts an AddressDTO to an Address entity.
     *
     * @param addressDTO the AddressDTO to convert
     * @return the corresponding Address entity
     */
    public Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .zipcode(addressDTO.getZipcode())
                .build();
    }

    /**
     * Converts a PhoneNumber entity to a PhoneNumberDTO.
     *
     * @param phoneNumber the phone number entity to convert
     * @return the corresponding PhoneNumberDTO
     */
    protected PhoneNumberDTO toDto(PhoneNumber phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        return PhoneNumberDTO.builder()
                .type(phoneNumber.getType())
                .number(phoneNumber.getNumber())
                .build();
    }

    /**
     * Converts a PhoneNumberDTO to a PhoneNumber entity.
     *
     * @param phoneNumberDTO the phone number DTO to convert
     * @return the corresponding PhoneNumber entity
     */
    protected PhoneNumber toEntity(PhoneNumberDTO phoneNumberDTO) {
        if (phoneNumberDTO == null) {
            return null;
        }
        return PhoneNumber.builder()
                .type(phoneNumberDTO.getType())
                .number(phoneNumberDTO.getNumber())
                .build();
    }
}