package com.example.Notes.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponce {

    private String firstName;
    private String lastName;
    private String email;
    private Integer id;
    private Date createdAt;

}
