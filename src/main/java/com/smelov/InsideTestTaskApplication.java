package com.smelov;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.smelov.dao.MessageRepository;
import com.smelov.dao.UserRepository;
import com.smelov.entity.Message;
import com.smelov.entity.User;
import com.smelov.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class InsideTestTaskApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(InsideTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String decodePass = BCrypt.withDefaults().hashToString(8, "123".toCharArray());
//        String decodePass2 = BCrypt.withDefaults().hashToString(8, "123456".toCharArray());
//
//        BCrypt.Result result = BCrypt.verifyer().verify("123456".toCharArray(), decodePass2);
//
//        System.out.println(decodePass);
//        System.out.println(decodePass2);
//        System.out.println(result.verified);

        User user1 = User.builder()
                .name("user123")
                .password(BCrypt.withDefaults().hashToString(8, "123".toCharArray()))
                .build();

        User user2 = User.builder()
                .name("user123456")
                .password(BCrypt.withDefaults().hashToString(8, "123456".toCharArray()))
                .build();


//        Message message1 = Message.builder().message("Привет1").build();
//        user1.addMessageToUser(message1);

        userService.saveUser(user1);
        userService.saveUser(user2);

    }
}
