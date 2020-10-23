package gui.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class MusicNode {
    private File file;
    private Metadata metadata;
    private String title;
    private String artist;
    private String length;

    public MusicNode(File file) throws TikaException, IOException, SAXException {
        this.file = file;
        createComponent();
    }

    public Node createComponent() throws TikaException, IOException, SAXException {
        grabMetaData();
        HBox component = new HBox(createButton());
        return null;
    }

    public Node getComponent() throws TikaException, IOException, SAXException {
        createComponent();
        return null;
    }


    /*
    TODO: Fix this alex says it was fucking horrible
     */

    // https://dzone.com/articles/how-retrieveextract-metadata
    private void grabMetaData() throws IOException, TikaException, SAXException {
        createObjects();
        title = metadata.get("title");
        artist = metadata.get("xmpdm:artist");
        length = metadata.get("xmpdm:duration");
    }

    private void createObjects() throws IOException, TikaException, SAXException {
        metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext pcontext = new ParseContext();
        Mp3Parser mp3Parser = new Mp3Parser();
        mp3Parser.parse(inputstream, handler, metadata, pcontext);
        inputstream.close();
    }

    private Button createButton() {
        Button playPause = new Button("Play/Pause");
        return null;
    }
}
