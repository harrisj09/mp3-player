package com.github.harrisj09.mp3.server;

import com.github.harrisj09.mp3.server.controller.ServerController;
import com.github.harrisj09.mp3.server.model.ServerModel;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@SpringBootApplication
public class Mp3ServerApplication {

	/*
	TODO
		- return file using spring
		- handling requests (of ids)
		- On startup index folder as hashmap
	 */
	public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
		ServerModel serverModel = new ServerModel();
		serverModel.createMusicMap();
		ServerController serverController = new ServerController(serverModel);
		SpringApplication.run(Mp3ServerApplication.class, args);
	}
}
