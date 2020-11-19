package com.github.harrisj09.mp3.server.controller;

import com.github.harrisj09.mp3.server.model.ServerModel;
import com.github.harrisj09.mp3.server.nodes.ServerMusicNode;
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

@RestController
public class ServerController {

    private final ServerModel serverModel;

    public ServerController(ServerModel serverModel) {
        this.serverModel = serverModel;
    }

    @GetMapping("/songs")
    public Collection<ServerMusicNode> getMusicList() {
        HashMap<Integer, ServerMusicNode> musicNodeHashMap = serverModel.getMusicMap();
        int size = musicNodeHashMap.size();
        Collection<ServerMusicNode> serverMusicNodes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            serverMusicNodes.add(musicNodeHashMap.get(i));
        }
        return serverMusicNodes;
    }

    // myStream.transferTo(response.getOutputStream()
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