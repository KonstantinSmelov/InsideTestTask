package com.smelov;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.smelov.entity.Message;
import com.smelov.entity.User;
import com.smelov.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class InsideTestTaskApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(InsideTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {

        User user1 = User.builder()
                .name("user1")
                .password(BCrypt.withDefaults().hashToString(8, "123".toCharArray()))
                .build();

        User user2 = User.builder()
                .name("user2")
                .password(BCrypt.withDefaults().hashToString(8, "123456".toCharArray()))
                .build();

        Message message1 = Message.builder().message("Сообщение 1").build();
        Message message2 = Message.builder().message("Сообщение 2").build();
        Message message3 = Message.builder().message("Сообщение 3").build();
        Message message4 = Message.builder().message("Сообщение 4").build();
        Message message5 = Message.builder().message("Сообщение 5").build();
        Message message11 = Message.builder().message("Message one").build();
        Message message12 = Message.builder().message("Message two").build();
        Message message13 = Message.builder().message("Message three").build();
        Message message14 = Message.builder().message("Message four").build();
        Message message15 = Message.builder().message("Message five").build();

        user1.addMessageToUser(message1);
        user1.addMessageToUser(message2);
        user1.addMessageToUser(message3);
        user1.addMessageToUser(message4);
        user1.addMessageToUser(message5);
        user2.addMessageToUser(message11);
        user2.addMessageToUser(message12);
        user2.addMessageToUser(message13);
        user2.addMessageToUser(message14);
        user2.addMessageToUser(message15);

        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
