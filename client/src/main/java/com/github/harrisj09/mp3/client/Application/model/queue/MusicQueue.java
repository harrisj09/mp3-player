package com.github.harrisj09.mp3.client.Application.model.queue;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;

public class MusicQueue {

    private MusicQueueNode front;
    private MusicQueueNode back;
    private int size = 0;

    public void enqueue(MusicNode node) {
        MusicQueueNode temp = new MusicQueueNode(node);
        if (back == null) {
            back = temp;
        }
        if (front == null) {
            front = temp;
        }
        else {
            front.setNext(temp);
            front = front.getNext();
        }
        size++;
    }

    public MusicQueueNode dequeue() {
        MusicQueueNode temp = null;
        if (back != null) {
            temp = back;
            back = back.getNext();
            size--;
        }
        return temp;
    }

    public MusicQueueNode getBack() {
        return back;
    }

    public MusicQueueNode getFront() {
        return front;
    }

    public int getSize() {
        return size;
    }
}