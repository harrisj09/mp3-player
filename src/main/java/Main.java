import Application.Mp3;
import Application.logic.AudioPlayer;
import Application.logic.MusicHandler;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import Application.gui.Gui;

import java.io.IOException;

public class Main {

    /**
     * @param args Includes a path to where the text file is. If arg doesn't exist check the folder you're in.
     *
     * @author John Harris
     */
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        Mp3 mp3 = new Mp3();
        mp3.start();
    }
}
