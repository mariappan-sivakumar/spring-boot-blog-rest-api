package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "RegisterDto Model Information")
public class RegisterDto {
    @NotEmpty
    @Size(min = 2,message = "Name must contain atleast 2 character")
    private String name;
    @NotEmpty
    @Size(min = 5,message = "User name must contain atleast 5 character")
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 8,max = 20,message = "Password must have minimum of 8character and maximum of 20 Character")
    private String password;
}
