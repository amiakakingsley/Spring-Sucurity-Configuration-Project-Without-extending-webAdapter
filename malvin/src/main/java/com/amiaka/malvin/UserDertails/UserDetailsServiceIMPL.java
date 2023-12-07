package com.amiaka.malvin.UserDertails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amiaka.malvin.Repository.UserRepo;
@Service
public class UserDetailsServiceIMPL implements UserDetailsService {
   private final UserRepo userRepo;
    
    public UserDetailsServiceIMPL(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).map(UserDetailsIMPL::new)
        .orElseThrow(() -> new UsernameNotFoundException("404"));
    }
    
}
