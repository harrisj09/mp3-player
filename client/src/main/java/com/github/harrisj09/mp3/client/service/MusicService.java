package com.github.harrisj09.mp3.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.mp3.client.Application.components.MusicNode;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class MusicService {

    /*
    http://zetcode.com/java/getpostrequest/
    use this to request from server
     */

    private final Path musicPath;

    public List<ServiceMusicNodeDto> fetchMusicList() {
        try {
            HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI("http://localhost:8080/songs")).build();
            HttpResponse<String> send = HttpClient.newBuilder()
                    .build()
                    .send(build, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            List<ServiceMusicNodeDto> songs = objectMapper.readValue(send.body(), new TypeReference<>() {});
            System.out.println(send.body());
            return songs;
        } catch(Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // https://stackoverflow.com/questions/19733612/how-to-download-an-httpresponse-into-a-file
    // https://stackabuse.com/how-to-download-a-file-from-a-url-in-java/
    public Path fetchMusicFile(MusicNode nodeDto, int id) {
        String fileName = nodeDto.getArtist() + " - " + nodeDto.getSong();
        try {
            Path targetFile = Paths.get("client-music-folder/" + fileName);
            HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI("http://localhost:8080/download/" + id)).build();
            HttpResponse<Path> send = HttpClient.newBuilder()
                    .build()
                    .send(build, HttpResponse.BodyHandlers.ofFile(targetFile));
            return send.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}