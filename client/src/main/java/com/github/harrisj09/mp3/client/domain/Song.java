package com.github.harrisj09.mp3.client.domain;

import lombok.NonNull;
import lombok.Value;

import java.time.Duration;

@Value
public class Song {
    int id;
    @NonNull String artist;
    @NonNull String name;
    @NonNull Duration length;
}