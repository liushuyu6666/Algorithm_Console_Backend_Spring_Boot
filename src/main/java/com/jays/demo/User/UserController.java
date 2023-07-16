package com.jays.demo.User;

import com.jays.demo.Auth.AuthService;
import com.jays.demo.Payload.LoginRequest;
import com.jays.demo.Payload.RegisterRequest;
import com.jays.demo.Payload.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/jays")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = this.userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail());
            ResponseBody<User> responseBody = new ResponseBody<>(user,
                    "User registered successfully", null);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e){
            ResponseBody<User> responseBody = new ResponseBody<>(null,
                    e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(responseBody);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            UserResponse userResponse = this.userService.verifyUserAndReturnUserId(loginRequest.getEmail(), loginRequest.getPassword());
            String token = this.authService.generateToken(userResponse.getUserId());
            userResponse.setToken(token);
            ResponseBody<UserResponse> responseBody = new ResponseBody<>(userResponse,
                    "User logged in successfully", null);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            ResponseBody<User> responseBody = new ResponseBody<>(null,
                    e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }
    }
}
