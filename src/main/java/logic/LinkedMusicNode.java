package logic;

public class LinkedMusicNode {
    private int size;
    private MusicNode head;
    private MusicNode tail;

    public LinkedMusicNode() {
        size = 0;
        head = null;
        tail = null;
    }

    public void add() {
        size++;
    }

    public MusicNode remove(MusicNode song) {
        size--;
        return null;
    }

    public int size() {
        return size;
    }
}
