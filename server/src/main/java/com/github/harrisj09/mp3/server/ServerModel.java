package com.github.harrisj09.mp3.server;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerModel {

    Map<Integer,File> music = new ConcurrentHashMap<>();

}