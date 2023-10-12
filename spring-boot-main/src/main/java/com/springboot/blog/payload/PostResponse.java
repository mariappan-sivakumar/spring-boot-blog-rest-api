package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Post Response Information")
public class PostResponse {
    @Schema(description = "List of post to display")
    private List<PostDto> content;
    @Schema(description = "Page No.")
    private int pageNo;
    @Schema(description = "No. of post on a Page")
    private int pageSize;
    @Schema(description = "Total No of post")
    private long totalElement;
    @Schema(description = "Total No of page available")
    private int totalPages;
    @Schema(description = "Show the current page is last or not")
    private boolean last;

}
