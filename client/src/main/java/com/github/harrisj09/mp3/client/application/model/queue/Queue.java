package com.github.harrisj09.mp3.client.application.model.queue;

public class Queue<T> {

    private QueueNode<T> front;
    private QueueNode<T> back;
    private int size = 0;

    public void enqueue(T node) {
        System.out.println(this);
        QueueNode<T> temp = new QueueNode<>(node);
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

    public T dequeue() {
        QueueNode<T> temp = null;
        if (back != null) {
            temp = back;
            back = back.getNext();
            size--;
        }
        return temp == null ? null : temp.getSong();
    }

    public T getBack() {
        return back == null ? null : back.getSong();
    }

    public T getFront() {
        return front == null ? null : front.getSong();
    }

    public int getSize() {
        return size;
    }

    static private class QueueNode<T> {
        private T song;
        private QueueNode<T> next;

        public QueueNode(T node) {
            this.song = node;
            next = null;
        }

        public T getSong() {
            return song;
        }

        public QueueNode<T> getNext() {
            return next;
        }

        public void setNext(QueueNode<T> next) {
            this.next = next;
        }
    }
}