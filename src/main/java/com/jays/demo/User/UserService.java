package com.jays.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String username, String password, String email) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if(existingUser.isPresent()) {
            throw new Exception("User with the same email already exists");
        }


        String userId = UUID.randomUUID().toString();
        String hashedPassword = this.passwordEncoder.encode(password);

        User newUser = new User(username, hashedPassword, email);

        return userRepository.save(newUser);
    }

    public UserResponse verifyUserAndReturnUserId(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("No such User"));


        if(this.passwordEncoder.matches(password, user.getPassword())) {
            return new UserResponse(user.getUserId(), user.getUsername(), user.getEmail(), "");
        } else {
            throw new Exception("Password does not match the user");
        }

    }
}
