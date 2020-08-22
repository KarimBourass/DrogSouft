package org.sid.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResetPassword {

    private String token;
    private String password;
    private String confirmPassword;
}
