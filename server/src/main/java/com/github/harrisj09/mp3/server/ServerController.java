package com.github.harrisj09.mp3.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServerController {

    @GetMapping("/songs")
    public Map<Integer, music> getMusicList() {
        Map<Integer, music> songs = new HashMap<>();
        songs.put(1, new music(1, "hello"));
        songs.put(2, new music(2, "hello"));
        return songs;
    }

    private static class music {
        int id;
        String path;
        public music(int id, String path) {
            this.id = id;
            this.path = path;
        }
    }
}
