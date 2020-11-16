package com.github.harrisj09.mp3.server.controller;

import com.github.harrisj09.mp3.server.model.ServerModel;
import com.github.harrisj09.mp3.server.nodes.ServerMusicNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}