package com.smelov.mapper;

import com.smelov.dto.MessageDto;
import com.smelov.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message toMessage(MessageDto messageDto);
    MessageDto toMessageDto(Message message);

}
