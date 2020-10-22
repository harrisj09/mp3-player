import gui.Gui;

import java.io.IOException;

public class Main {

    /**
     * @param args Includes a path to where the text file is. If arg doesn't exist check the folder you're in.
     *
     * @author John Harris
     */

    /*
    Do this
    public static void main(String[]args) {
        Logic logic = new logic();
        Gui gui = new Gui(logic);
        gui.startApp();
    }
     */
    public static void main(String[] args) throws IOException {
        Gui gui = new Gui();
        gui.startMp3();
    }
}
