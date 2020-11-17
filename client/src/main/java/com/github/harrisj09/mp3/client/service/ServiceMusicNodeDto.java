package com.github.harrisj09.mp3.client.service;

import java.io.Serializable;
import lombok.Data;

@Data
public class ServiceMusicNodeDto implements Serializable {
    private String path;
    private String artist;
    private String song;
    private int id;
    private long length;
}
