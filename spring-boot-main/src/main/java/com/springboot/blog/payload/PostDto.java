package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {
    private Long id;

    @Schema(description = "Blog Post Title")
    @NotEmpty
    @Size(min = 2,message = "Post Title should have at least 2 character")
    private String title;

    @Schema(description = "Blog Post Description")
    @NotEmpty
    @Size(min = 10,message = "Description should have at least 10 character")
    private String description;

    @NotEmpty
    @Schema(description = "Blog Post Content")
    private String content;
    private Set<CommentDto> comments;

    @Schema(description = "Blog Post Category")
    private Long categoryId;


}
