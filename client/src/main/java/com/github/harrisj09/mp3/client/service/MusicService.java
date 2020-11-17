package com.github.harrisj09.mp3.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class MusicService {

    private final Path musicPath;

    public List<ServiceMusicNodeDto> fetchMusicList() {
        try {
            HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI("http://localhost:8080/songs")).build();
            HttpResponse<String> send = HttpClient.newBuilder()
                    .build()
                    .send(build, HttpResponse.BodyHandlers.ofString()); // response handles the result of the request
            ObjectMapper objectMapper = new ObjectMapper();
            List<ServiceMusicNodeDto> songs = objectMapper.readValue(send.body(), new TypeReference<>() {
            });
            System.out.println(send.body());
            return songs;
        } catch(Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Path fetchMusicFile(String name) {
        try {
            HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI("http://localhost:8080/songs")).build();
            HttpResponse<Path> send = HttpClient.newBuilder()
                    .build()
                    .send(build, HttpResponse.BodyHandlers.ofFile(musicPath.resolve(name + ".mp3")));
            return send.body();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
