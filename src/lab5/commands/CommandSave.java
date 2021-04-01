package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * This class is responsible for saving collection to the file.
 */
public class CommandSave extends Command {
    private CollectionManager collectionManager;

    public CommandSave(CollectionManager collectionManager) {
        super("save");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param S      The full name of the entered command.
     */
    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            collectionManager.safeToFile();
        } else {
            System.err.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
