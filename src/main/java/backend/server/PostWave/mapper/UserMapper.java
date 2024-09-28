package backend.server.PostWave.mapper;

import backend.server.PostWave.dto.PostDto;
import backend.server.PostWave.dto.UserDto;
import backend.server.PostWave.model.Post;
import backend.server.PostWave.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User ToEntity(UserDto userDto);
    @Mapping(source = "lastname", target = "lastname")
    UserDto ToDto(User user);

    List<UserDto> ToDtoList(List<User> userList);
}
