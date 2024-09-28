package backend.server.PostWave.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    private String description;
    private UserDto user;
    private List<LikeDto> likes;
//    private List<CommentDto> comments ;

}
