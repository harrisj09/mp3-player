package com.github.harrisj09.mp3.server.controller;

import com.github.harrisj09.mp3.server.model.ServerModel;
import com.github.harrisj09.mp3.server.nodes.ServerMusicNode;
import com.github.harrisj09.mp3.server.nodes.ServerMusicNodeDto;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class ServerController {

    private final ServerModel serverModel;

    public ServerController(ServerModel serverModel) {
        this.serverModel = serverModel;
    }

    @GetMapping("/songs")
    public Collection<ServerMusicNodeDto> getMusicList() {
        return serverModel.getMusicMap().values().stream().map(ServerController::convertToDto).collect(Collectors.toList());
    }

    private static ServerMusicNodeDto convertToDto(ServerMusicNode serverMusicNode) {
        return new ServerMusicNodeDto(serverMusicNode.getId(), serverMusicNode.getArtist(), serverMusicNode.getSong(), serverMusicNode.getLength());
    }

    @RequestMapping(value="download/{id}", method=RequestMethod.GET)
    public void getDownload(HttpServletResponse response, @PathVariable String id) throws IOException {
        ServerMusicNode node = serverModel.getMusicMap().get(Integer.parseInt(id));
        Path path = Path.of(node.getPath());
        String fileName = node.getArtist() + " - " + node.getSong() + ".mp3";
        // Get your file stream from wherever.
        InputStream myStream = Files.newInputStream(path);
        // Set the content type and attachment header.
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("audio/mpeg");
        // Copy the stream to the response's output stream.
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }
}