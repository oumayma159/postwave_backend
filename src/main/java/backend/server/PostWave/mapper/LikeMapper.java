package backend.server.PostWave.mapper;

import backend.server.PostWave.dto.CommentDto;
import backend.server.PostWave.dto.LikeDto;
import backend.server.PostWave.model.Comment;
import backend.server.PostWave.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = UserMapper.class)
public interface LikeMapper {

    LikeDto toDto(Like like);


}
