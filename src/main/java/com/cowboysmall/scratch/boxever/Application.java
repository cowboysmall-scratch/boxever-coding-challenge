package com.cowboysmall.scratch.boxever;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {


    //_________________________________________________________________________

    public static void main(String... args) {

        SpringApplication.run(Application.class, args);
    }


    //_________________________________________________________________________

    @Override
    public void run(String... args) {

        System.out.println(">>> running...");
    }
}
