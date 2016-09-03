package com.bretana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Holds the main method of the program.
 * @author Cesar Bretana
 */
@SpringBootApplication
public class ConnectApplication {

    /**
     * Main method of the programs, handle the execution of the web service.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConnectApplication.class, args);
    }
}
