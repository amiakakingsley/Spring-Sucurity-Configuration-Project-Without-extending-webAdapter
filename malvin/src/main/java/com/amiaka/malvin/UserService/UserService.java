package com.amiaka.malvin.UserService;

import java.util.List;

import com.amiaka.malvin.Model.User;
import com.amiaka.malvin.UserDto.UserDto;

public interface UserService {
    void saveUser(UserDto user);

    List<User> findAllUser();
}
