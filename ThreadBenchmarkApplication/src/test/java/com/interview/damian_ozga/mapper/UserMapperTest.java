package com.interview.damian_ozga.mapper;


import com.interview.damian_ozga.dto.AddressDTO;
import com.interview.damian_ozga.dto.PhoneNumberDTO;
import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.model.Address;
import com.interview.damian_ozga.model.PhoneNumber;
import com.interview.damian_ozga.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    @DisplayName("User to userDto")
    public void user_to_userDto() {
        // given
        User user = User.builder()
                .key("XyBBa910")
                .name("John Doe")
                .email("john.doe@example.com")
                .address(Address.builder().street("123 Main St").city("Anytown").state("CA").zipcode("12345").build())
                .phoneNumbers(Arrays.asList(
                        PhoneNumber.builder().type("home").number("555-555-5555").build(),
                        PhoneNumber.builder().type("work").number("555-555-5556").build()
                ))
                .build();

        // when
        UserDTO userDTO = userMapper.toDto(user);

        // then
        assertNotNull(userDTO);
        assertEquals("XyBBa910", userDTO.getKey());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("john.doe@example.com", userDTO.getEmail());
        assertNotNull(userDTO.getAddress());
        assertEquals("123 Main St", userDTO.getAddress().getStreet());
        assertEquals(2, userDTO.getPhoneNumbers().size());
    }

    @Test
    @DisplayName("UserDto to user")
    public void userDto_to_user() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .key("XyBBa910")
                .name("John Doe")
                .email("john.doe@example.com")
                .address(AddressDTO.builder().street("123 Main St").city("Anytown").state("CA").zipcode("12345").build())
                .phoneNumbers(Arrays.asList(
                        PhoneNumberDTO.builder().type("home").number("555-555-5555").build(),
                        PhoneNumberDTO.builder().type("work").number("555-555-5556").build()
                ))
                .build();

        // when
        User user = userMapper.toEntity(userDTO);
        
        // then
        assertNotNull(user);
        assertEquals("XyBBa910", user.getKey());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertNotNull(user.getAddress());
        assertEquals("123 Main St", user.getAddress().getStreet());
        assertEquals(2, user.getPhoneNumbers().size());
    }

    @Test
    @DisplayName("address to addressDto and reverse")
    public void address_to_addressDto_and_reverse() {
        // given
        Address address = Address.builder().street("123 Main St").city("Anytown").state("CA").zipcode("12345").build();
        
        // when
        AddressDTO addressDTO = userMapper.toDto(address);
        Address mappedAddress = userMapper.toEntity(addressDTO);

        // then
        assertNotNull(addressDTO);
        assertEquals("123 Main St", addressDTO.getStreet());
        
        assertNotNull(mappedAddress);
        assertEquals("123 Main St", mappedAddress.getStreet());
    }

    @Test
    @DisplayName("phoneNumber to phoneNumberDto and reverse")
    public void phoneNumber_to_phoneNumberDto_and_reverse() {
        // given
        PhoneNumber phoneNumber = PhoneNumber.builder().type("home").number("555-555-5555").build();

        // when
        PhoneNumberDTO phoneNumberDTO = userMapper.toDto(phoneNumber);
        PhoneNumber mappedPhoneNumber = userMapper.toEntity(phoneNumberDTO);

        // then
        assertNotNull(phoneNumberDTO);
        assertEquals("home", phoneNumberDTO.getType());

        assertNotNull(mappedPhoneNumber);
        assertEquals("home", mappedPhoneNumber.getType());
    }
}