package com.example.Notes.auth;

import com.example.Notes.config.JwtService;
import com.example.Notes.repository.UserRepository;
import com.example.Notes.user.Role;
import com.example.Notes.user.User;
import com.example.Notes.user.UserResponce;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(new Date(System.currentTimeMillis()))
                .role(Role.USER)
                .build();

        user = userRepository.save(user);

        var createdUser = UserResponce.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .user(createdUser)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var responceUser = UserResponce.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .user(responceUser)
                .token(jwtToken)
                .build();
    }
}
