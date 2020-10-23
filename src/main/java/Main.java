import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.Gui;

import java.io.IOException;

public class Main {

    /**
     * @param args Includes a path to where the text file is. If arg doesn't exist check the folder you're in.
     *
     * @author John Harris
     */
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        Gui gui = new Gui();
        gui.startMp3();
    }
}
