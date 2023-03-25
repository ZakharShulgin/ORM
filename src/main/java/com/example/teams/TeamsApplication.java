package com.example.teams;

import com.example.teams.cli.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamsApplication implements CommandLineRunner {
	@Autowired
	private MainMenu menu;

	public static void main(String[] args) {
		SpringApplication.run(TeamsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.menu();
	}
}
