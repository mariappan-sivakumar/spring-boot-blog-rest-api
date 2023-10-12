package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "LoginDto Information")
public class LoginDto {
    @Schema(description = "User name or email of the user to login")
    @NotEmpty
    private String usernameOrEmail;
    @Schema(description = "Password of the user to login")
    @NotEmpty
    private String password;
}
