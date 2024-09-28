package backend.server.PostWave.mapper;

import backend.server.PostWave.dto.CommentDto;
import backend.server.PostWave.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    @Mapping(source = "user", target = "author")
    CommentDto toDto(Comment comment);

    @Mapping(source = "author", target = "user")
    Comment toEntity(CommentDto commentDto);

    @Mapping(source = "user", target = "author")
    List<CommentDto> toDtoList(List<Comment> comments);
    @Mapping(source = "author", target = "user")
    List<Comment> toEntityList(List<CommentDto> commentDtos);
}
