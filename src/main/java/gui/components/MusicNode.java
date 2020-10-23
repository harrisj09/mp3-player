package gui.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.text.Text;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class MusicNode {
    private File file;
    private String title;
    private String artist;
    private String length;

    public MusicNode(File file) throws TikaException, IOException, SAXException {
        this.file = file;
        createComponent();
    }

    public Node getComponent() throws TikaException, IOException, SAXException {
        return createComponent();
    }

    private Node createComponent() throws TikaException, IOException, SAXException {
        grabMetaData();
        return new HBox(new Button("Play/Pause"), new Text(title), new Text(artist), new Text(length));
    }

    // https://dzone.com/articles/how-retrieveextract-metadata
    private void grabMetaData() throws IOException, TikaException, SAXException {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext pcontext = new ParseContext();
        Mp3Parser mp3Parser = new Mp3Parser();
        mp3Parser.parse(inputstream, handler, metadata, pcontext);
        inputstream.close();
        title = metadata.get("title");
        artist = metadata.get("xmpdm:artist");
        length = metadata.get("xmpdm:duration");
    }

    private Button createButton() {
        Button playPause = new Button("Play/Pause");
        return null;
    }
}
