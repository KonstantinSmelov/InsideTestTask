package com.smelov;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsideTestTaskApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InsideTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String pass = "pass";
        String pass2 = "pass2";
        String decodePass = BCrypt.withDefaults().hashToString(8, pass.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(pass2.toCharArray(), decodePass);

        System.out.println(decodePass);
        System.out.println(result.verified);


    }
}
