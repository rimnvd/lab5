package lab5.commands;

import lab5.data.Dragon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
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

    /**
     * Executes the command.
     * @param vector Collection.
     * @param S The full name of the entered command.
     */
    @Override
    public void execute (Vector<Dragon> vector, String S) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            if (vector.isEmpty()) {
                writeDocument(document);
            } else {
                Element root = document.createElement("Dragons");
                document.appendChild(root);
                for (Dragon dragon : vector) {
                    Element call = document.createElement(dragon.toString());
                    root.appendChild(call);
                    Element name = document.createElement("name");
                    name.appendChild(document.createTextNode(dragon.getName()));
                    call.appendChild(name);
                    Element age = document.createElement("age");
                    age.appendChild(document.createTextNode(String.valueOf(dragon.getAge())));
                    call.appendChild(age);
                    Element type = document.createElement("DragonType");
                    type.appendChild(document.createTextNode(String.valueOf(dragon.getType())));
                    call.appendChild(type);
                    Element color = document.createElement("Color");
                    color.appendChild(document.createTextNode(String.valueOf(dragon.getColor())));
                    call.appendChild(color);
                    Element character = document.createElement("DragonCharacter");
                    character.appendChild(document.createTextNode(String.valueOf(dragon.getCharacter())));
                    call.appendChild(character);
                    Element head = document.createElement("DragonHead");
                    call.appendChild(head);
                    Element size = document.createElement("size");
                    size.appendChild(document.createTextNode(String.valueOf(dragon.getHead().getSize())));
                    head.appendChild(size);
                    Element eyesCount = document.createElement("eyesCount");
                    eyesCount.appendChild(document.createTextNode(String.valueOf(dragon.getHead().getEyesCount())));
                    head.appendChild(eyesCount);
                    Element coordinates = document.createElement("Coordinates");
                    call.appendChild(coordinates);
                    Element x = document.createElement("x");
                    x.appendChild(document.createTextNode(String.valueOf(dragon.getCoordinates().getX())));
                    coordinates.appendChild(x);
                    Element y = document.createElement("y");
                    y.appendChild(document.createTextNode(String.valueOf(dragon.getCoordinates().getY())));
                    coordinates.appendChild(y);
                    writeDocument(document);
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Невозможно выполнить данную команду из-за ошибки конфигурации");
        }
    }

    /**
     * Write the collection to the xml-document.
     */
    public void writeDocument(Document document) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource dom = new DOMSource(document);
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(System.getenv("file")));
            StreamResult result = new StreamResult(outputStream);
            tr.transform(dom, result);
        } catch (TransformerException ex) {
            System.out.println("Невозможно выполнить данную команду");
        } catch (FileNotFoundException ex) {
            System.out.println("Невозможно выполнить данную команду из-за отсутвия права на запись в файл");
        } catch (IllegalArgumentException ex) {
            System.out.println("Системная переменная с данным файлом не найдена");
        }
    }
}
