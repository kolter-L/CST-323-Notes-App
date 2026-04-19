package com.example.notesapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApp {

	private static final Logger logger = LogManager.getLogger(NotesApp.class);

	public static void main(String[] args) {
		logger.info("ENTERING  NotesApp.main() - Starting Notes Application");
		SpringApplication.run(NotesApp.class, args);
		logger.info("EXITING   NotesApp.main() - Notes Application started successfully");
	}

}
