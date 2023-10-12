package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "CategoryDto Model Description"
)
public class CategoryDto {
    private Long id;
    @Schema(description = "Name of the Category")
    @NotEmpty
    @Size(min = 2,message = "The name of the post must contain atleast 2 character")
    private String name;
    @Schema(description = "Description of the Category")
    @NotEmpty
    @Size(min = 10,message = "The description of the post must contain atleast 10 character")
    private String description;

}
