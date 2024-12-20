package com.gmail.drack.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.gmail.drack.constants.Regexp;
import com.gmail.drack.constants.UserErrorMessage;

import lombok.Data;

@Data
public class PasswordResetRequest {
    @Email(regexp = Regexp.USER_EMAIL_REGEXP, message = UserErrorMessage.EMAIL_NOT_VALID)
    private String email;

    @Size(min = 8, message = UserErrorMessage.SHORT_PASSWORD)
    private String password;

    @NotBlank(message = UserErrorMessage.EMPTY_PASSWORD)
    @Size(min = 8, message = UserErrorMessage.SHORT_PASSWORD)
    private String password2;
}
