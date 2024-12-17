package com.interview.damian_ozga.service.ifc;

import com.interview.damian_ozga.dto.UserDTO;

/**
 * Service interface for managing UserDTO entities.
 */
public interface UserService extends GeneralService<UserDTO, String> {

    /**
     * Finds a UserDTO by their email.
     *
     * @param email the email of the user
     * @return the UserDTO with the specified email
     */
    UserDTO findByEmail(String email);
}