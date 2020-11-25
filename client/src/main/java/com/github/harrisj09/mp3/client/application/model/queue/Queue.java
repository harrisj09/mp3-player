package com.github.harrisj09.mp3.client.application.model.queue;

import com.github.harrisj09.mp3.client.application.components.MusicNode;

public class Queue {

    private QueueNode front;
    private QueueNode back;
    private int size = 0;

    public void enqueue(MusicNode node) {
        System.out.println(this);
        QueueNode temp = new QueueNode(node);
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

    public MusicNode dequeue() {
        QueueNode temp = null;
        if (back != null) {
            temp = back;
            back = back.getNext();
            size--;
        }
        return temp == null ? null : temp.getSong();
    }

    public MusicNode getBack() {
        return back == null ? null : back.getSong();
    }

    public MusicNode getFront() {
        return front == null ? null : front.getSong();
    }

    public int getSize() {
        return size;
    }

    static private class QueueNode {
        private MusicNode song;
        private QueueNode next;

        public QueueNode(MusicNode node) {
            this.song = node;
            next = null;
        }

        public MusicNode getSong() {
            return song;
        }

        public QueueNode getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }
    }
}