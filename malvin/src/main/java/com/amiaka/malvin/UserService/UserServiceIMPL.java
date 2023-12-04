package com.amiaka.malvin.UserService;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amiaka.malvin.Model.User;
import com.amiaka.malvin.Repository.UserRepo;
import com.amiaka.malvin.UserDto.UserDto;
@Service
public class UserServiceIMPL implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;




public UserServiceIMPL(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }
@Override
public void saveUser(UserDto userDto) {
    User user = new User();
    user.setFirstname(userDto.getFirstname());
    user.setLastname(userDto.getLastname());
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setRole("ROLE_USER");

   userRepo.save(user);

}

@Override
public List<User> findAllUser() {
    return userRepo.findAll();

}
}