package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.config.JwtProvider;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.request.LoginRequest;
import com.webtech.socialBackendApi.response.AuthResponse;
import com.webtech.socialBackendApi.services.IUserService;
import com.webtech.socialBackendApi.services.impl.CustomerUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IUserService _userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerUserDetailsService customerUserDetailsService;

    public AuthController(IUserService userService, PasswordEncoder passwordEncoder,
    CustomerUserDetailsService customerUserDetailsService) {
        this._userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customerUserDetailsService = customerUserDetailsService;

    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws Exception {
        User userExist = _userService.findUserByEmail(user.getEmail());

        if (userExist !=null) {
            throw new Exception("This email is already use with another account");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = _userService.createUser(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(createdUser.getEmail(), createdUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(token, "Register Success");

        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(token, "login success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
        if (userDetails==null) {
            throw new BadCredentialsException("Invalid username");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw  new BadCredentialsException("Password not match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
    }
}
