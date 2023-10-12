package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "CommentDto Model Information")
public class CommentDto {
    private long id;
    @Schema(description = "Name of the user post the comment")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @Schema(description = "Email Address of the user")
    @NotEmpty(message = "Email is not empty")
    @Email
    private String email;
    @Schema(description = "Comment content or the body of the the comment")
    @NotEmpty(message = "Comment body should not be empty or null")
    @Size(min = 10,message = "Comment should be at least 10 character")
    private String body;
}
