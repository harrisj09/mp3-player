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

        /*
        TODO:

            Mp3 mp3 = new Mp3(new MusicHandler(), new AudioPlayer(), new Gui());
            mp3.start();
         */
        Gui gui = new Gui();
        gui.buildGui();
    }
}
