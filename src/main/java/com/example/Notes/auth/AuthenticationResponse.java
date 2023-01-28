package com.example.Notes.auth;

import com.example.Notes.user.User;
import com.example.Notes.user.UserResponce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private UserResponce user;
    private String token;
    private String message;
}
