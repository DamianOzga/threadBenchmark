package com.interview.damian_ozga.service.ifc;


import com.interview.damian_ozga.dto.UserDTO;

public interface UserService extends GeneralService<UserDTO, String> {

    UserDTO findByEmail(String email);
}