package com.example.demo;

import com.example.demo.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(Config.class);
	}

}