package com.securityConfig.securitiesPractise.services.auth;

import com.securityConfig.securitiesPractise.dto.SignUpRequest;
import com.securityConfig.securitiesPractise.dto.UserDto;
import com.securityConfig.securitiesPractise.entities.User;
import com.securityConfig.securitiesPractise.enums.UserRole;
import com.securityConfig.securitiesPractise.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
   @PostConstruct
    public void createAnAdminAccount(){
        Optional<User>optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if(optionalUser.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created Successfully!");
        } else {
            System.out.println("Admin account already exist!!");
        }
    }

    @Override
    public UserDto signupUser(SignUpRequest signUpRequest) {
       User user = new User();
       user.setEmail(signUpRequest.getEmail());
       user.setName(signUpRequest.getName());
       user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
       user.setUserRole(UserRole.EMPLOYEE);
      User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
