package backend.server.PostWave.mapper;

import backend.server.PostWave.dto.PostDto;
import backend.server.PostWave.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


//we can use this mapper methods in our services and PostMapperImpl will be automatically genarated in target/generated
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PostMapper {

    Post ToEntity(PostDto postDto);
    @Mapping(source = "id", target = "id")
    PostDto ToDto(Post post);

    List<Post> ToEntityList(List<PostDto> postDtoList);
    @Mapping(source = "id", target = "id")
    List<PostDto> ToDtoList(List<Post> postList);
}
