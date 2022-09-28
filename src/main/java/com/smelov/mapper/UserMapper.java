package com.smelov.mapper;

import com.smelov.dto.UserDto;
import com.smelov.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser (UserDto userDto);
    UserDto toUserDto (User user);
}
