package com.unimagdalena.airlines;

import org.springframework.boot.SpringApplication;

public class TestAirlinesApplication {

    public static void main(String[] args) {
        SpringApplication.from(AirlinesApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
