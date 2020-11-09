package com.github.harrisj09.mp3.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Mp3Controller {

    @GetMapping("/Hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/songs")
    public List<String> getMusicList() {
        return List.of("hello", "birthday", "My name");
    }
}
