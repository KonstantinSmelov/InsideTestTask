package com.smelov.service.impl;

import com.smelov.dao.MessageRepository;
import com.smelov.dto.MessageDto;
import com.smelov.entity.Message;
import com.smelov.entity.User;
import com.smelov.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final SecurityService securityService;
    private final UserService userService;
    private final MessageRepository messageRepository;

    public void saveMessage(MessageDto messageDto) {
        log.debug("saveMessage(): получен messageDto: {}", messageDto);
        User user = userService.findUserByName(messageDto.getName());
        Message message = Message.builder().message(messageDto.getMessage()).build();
        user.addMessageToUser(message);
        userService.saveUser(user);
    }

    private List<String> getAllMessagesByUser(User user) {
        log.debug("getAllMessagesByUser(): получен user: {}", user);
        List<String> messagesList = messageRepository.findAllMessagesByUsername(user.getName());
        log.debug("getAllMessagesByUser(): получены все сообщения пользователя: {}", messagesList);
        return messagesList;
    }

    private List<String> getLastMessages(MessageDto messageDto) {
        log.debug("getLastMessages(): получен messageDto: {}", messageDto);
        int number = Integer.parseInt(messageDto.getMessage().substring(8));
        log.debug("getLastMessages(): распарсена команда \"history X\". X = {}", number);
        User user = userService.findUserByName(messageDto.getName());
        List<String> allMessagesList = getAllMessagesByUser(user);

        if (number > allMessagesList.size()) {
            number = allMessagesList.size();
        }
        List<String> lastMessagesList = getAllMessagesByUser(user).subList(allMessagesList.size() - number, allMessagesList.size());
        log.debug("getLastMessages(): получены последнии {} сообщения пользователя: {}", number, lastMessagesList);
        return lastMessagesList;
    }

    public List<String> saveOrShowLastMessages(MessageDto messageDto, HttpServletRequest request) throws AuthException {

        if (securityService.verifyMessageDto(messageDto, request)) {
            if (messageDto.getMessage().matches("^history [0-9]+$")) {
                return getLastMessages(messageDto);
            } else {
                saveMessage(messageDto);
                return List.of("Сообщение сохранено");
            }
        }
        return List.of("Невозможная ошибка :)");
    }
}
