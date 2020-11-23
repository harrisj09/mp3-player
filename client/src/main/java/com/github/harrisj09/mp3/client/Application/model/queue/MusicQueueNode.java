package com.github.harrisj09.mp3.client.Application.model.queue;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;

public class MusicQueueNode {
    private MusicNode song;
    private MusicQueueNode next;

    public MusicQueueNode(MusicNode node) {
        this.song = node;
        next = null;
    }

    public MusicNode getSong() {
        return song;
    }

    public MusicQueueNode getNext() {
        return next;
    }

    public void setNext(MusicQueueNode next) {
        this.next = next;
    }
}
