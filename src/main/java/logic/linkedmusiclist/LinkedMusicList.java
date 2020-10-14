package logic.linkedmusiclist;

import java.io.File;

public class LinkedMusicList {
    private int size;
    private MusicNode head;
    private MusicNode tail;

    public LinkedMusicList() {
        size = 0;
        head = null;
        tail = null;
    }

    public void add(File file) {
        if(head == null) {
            //head =
        }
        size++;
    }

    public MusicNode remove(MusicNode song) {
        size--;
        return null;
    }

    public int size() {
        return size;
    }

    public MusicNode getHead() {
        return head;
    }

    public void setHead(MusicNode newTail) {
        tail = newTail;
    }
}
