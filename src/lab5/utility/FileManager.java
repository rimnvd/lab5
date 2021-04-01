package lab5.utility;

import lab5.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

public class FileManager {
    private String name;
    private long age;
    private Color color;
    private DragonType type;
    private DragonCharacter character;
    private Integer x;
    private Integer y;
    private String call;
    private Integer size;
    private Double eyesCount;
    private Long id;
    private LocalDate creationDate;
    private Vector<Dragon> vector = new Vector<>(0);
    private File file;
    public FileManager(String envVariable) {
        try {
            this.file = new File(System.getenv(envVariable));
        } catch (IllegalArgumentException ex) {
            System.err.println("Переменная окружения c загрузочным файлом не найдена. Создайте системную переменную и запустите программу снова.");
            System.exit(0);
        }
    }

    public Vector<Dragon> readCollection() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(file);
            Node root = document.getDocumentElement();
            NodeList dragons = root.getChildNodes();
            boolean f = true;
            clearFields();
            for (int i = 0; i < dragons.getLength(); i++) {
                if (dragons.item(i).getNodeType() != Node.TEXT_NODE && dragons.item(i).getChildNodes().getLength() > 1) {
                    call = dragons.item(i).getNodeName();
                    NodeList dragon = dragons.item(i).getChildNodes();
                    for (int j = 0; j < dragon.getLength() && f; j++) {
                        if (dragon.item(j).getNodeType() != Node.TEXT_NODE && dragon.item(j).getChildNodes().getLength() == 1) {
                            String S1 = dragon.item(j).getNodeName();
                            String S2 = dragon.item(j).getChildNodes().item(0).getTextContent().replace("\n", "").trim();
                            f= field(S1, S2, "a");
                        } else if (dragon.item(j).getNodeType() != Node.TEXT_NODE && dragon.item(j).getChildNodes().getLength() > 1) {
                            NodeList dragon1 = dragon.item(j).getChildNodes();
                            String S3 = dragon.item(j).getNodeName();
                            for (int k = 0; k < dragon1.getLength() && f; k++) {
                                if (dragon1.item(k).getNodeType() != Node.TEXT_NODE && dragon1.item(k).getChildNodes().getLength() == 1) {
                                    String S4 = dragon1.item(k).getNodeName();
                                    String S5 = dragon1.item(k).getChildNodes().item(0).getTextContent();
                                    f = field(S3, S4, S5);
                                }
                            }
                        }
                    }
                    System.out.println();
                }
                if (dragons.item(i).getNodeType() != Node.TEXT_NODE) {
                    if (f) elementCreation();
                    clearFields();
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Невозможно считать данные из-за ошибки конфигурации");
        } catch (IOException ex) {
            if (!file.exists()) {
                System.out.println("Файл с таким именем не найден. Создайте файл и запустите программу снова.");
                System.exit(0);

            } else if (file.exists() && !file.canRead()) {
                System.out.println("Нет прав для чтения файла. Добавьте необходимые права и запустите программу снова.");
                System.exit(0);
            }
        } catch (SAXException ex) {
            System.out.println();
        }
        return vector;
    }

    /**
     * Clears the values of the characteristics of the dragon.
     */

    public void clearFields() {
        name = null;
        age = 0;
        color = null;
        type = null;
        character = null;
        x = null;
        y = null;
        call = null;
        size = null;
        eyesCount = null;
        id = null;
        creationDate = null;
    }

    /**
     * Checks if the String S is one of the characteristics of the dragon or not and sets the value of this characteristic, using String S1 or S2.
     *
     * @return true if String S is one of the characteristics of the dragon, false if String S is not one of the characteristics.
     */
    public boolean field(String S, String S1, String S2) {
        boolean f;
        if (S.equalsIgnoreCase("name")) {
            if (name != null) f = false;
            else {
                name = S1;
                f = true;
            }
        } else if (S.equalsIgnoreCase("age")) {
            if (age != 0) f = false;
            else {
                age = checkAge(S1);
                f = true;
            }
        } else if (S.equalsIgnoreCase("color")) {
            if (color != null) f = false;
            else {
                color = checkColor(S1.toUpperCase());
                f = true;
            }
        } else if (S.equalsIgnoreCase("DragonType")) {
            if (type != null) f = false;
            else {
                type = checkType(S1.toUpperCase());
                f = true;
            }
        } else if (S.equalsIgnoreCase("DragonCharacter")) {
            if (character != null) f = false;
            else {
                character = checkCharacter(S1.toUpperCase());
                f = true;
            }
        } else if (S.equalsIgnoreCase("Coordinates") && S1.equalsIgnoreCase("x")) {
            if (x != null) f = false;
            else {
                x = checkInt(S2);
                f = true;
            }
        } else if (S.equalsIgnoreCase("Coordinates") && S1.equalsIgnoreCase("y")) {
            if (y != null) f = false;
            else {
                y = checkInt(S2);
                f = true;
            }
        } else if (S.equalsIgnoreCase("DragonHead") && S1.equalsIgnoreCase("size")) {
            if (size != null) f = false;
            else {
                size = checkPositiveInt(S2);
                f = true;
            }
        } else if (S.equalsIgnoreCase("DragonHead") && S1.equalsIgnoreCase("eyesCount")) {
            if (eyesCount != null) f = false;
            else {
                eyesCount = checkEyesCount(S2);
                f = true;
            }
        } else if (S.equalsIgnoreCase("id")) {
            if (id != null) f = false;
            else {
                id = checkId(S1);
                f = true;
            }
        } else if (S.equalsIgnoreCase("CreationDate")) {
            if (creationDate != null) f = false;
            else {
                creationDate = checkData(S1);
                f = true;
            }
        }
        else f = false;
        return f;
    }

    public Long checkId(String S) {
        try {
            id = Long.parseLong(S);
            if (id <= 0) {
                id = null;
            }
        } catch (NumberFormatException ex) {
            id = null;
        }
        return id;
    }

    /**
     * Sets the value of the age of the dragon.
     *
     * @param S set value.
     * @return The set value of the age or 0 if it was not set.
     * @throws NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public long checkAge(String S) {
        try {
            age = Long.parseLong(S);
            if (age < 0) {
                age = 0;
            }
        } catch (NumberFormatException ex) {
            age = 0;
        }
        return age;
    }

    /**
     * Sets the value of the color of the dragon.
     *
     * @param S set value.
     * @return The set value of the color or null if it was not set.
     */
    public Color checkColor(String S) {
        try {
            color = Color.valueOf(S);
        }
        catch (IllegalArgumentException ex) {
            color = null;
        }
        return color;
    }

    /**
     * Sets the value of the type of the dragon.
     *
     * @param S set value.
     * @return The set value of the type or null if it was not set.
     */
    public DragonType checkType(String S) {
        try {
            type = DragonType.valueOf(S);
        }
        catch (IllegalArgumentException ex) {
            type = null;
        }
        return type;
    }

    /**
     * Sets the value of the character of the dragon.
     *
     * @param S set value.
     * @return The set value of the character or null if it was not set.
     */
    public DragonCharacter checkCharacter(String S) {
        try {
            character = DragonCharacter.valueOf(S);
        }
        catch (IllegalArgumentException ex) {
            character = null;
        }
        return character;
    }

    /**
     * Checks whether String S can be parsed to Integer or not.
     *
     * @return The Integer value if it is possible or null in the opposite situation.
     * @throws NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Integer checkInt(String S) {
        Integer a;
        try {
            a = Integer.parseInt(S);
        } catch (NumberFormatException ex) {
            a = null;
        }
        return a;
    }

    /**
     * Checks whether String S can be parsed to Integer or not and checks whether the value is positive.
     *
     * @return The positive Integer value if it possible or null in the opposite situation.
     * @throws NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Integer checkPositiveInt(String S) {
        Integer a;
        try {
            a = Integer.parseInt(S);
            if (a <= 0) {
                a = null;
            }
        } catch (NumberFormatException ex) {
            a = null;
        }
        return a;
    }

    /**
     * Checks whether String S can be parsed to Double or not and checks whether the value is positive.
     *
     * @return The positive Double value if it possible or null in the opposite situation.
     * @throws NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Double checkEyesCount(String S) {
        try {
            eyesCount = Double.parseDouble(S);
            if (eyesCount <= 0) {
                eyesCount = null;
            }
        } catch (NumberFormatException ex) {
            eyesCount = null;
        }
        return eyesCount;
    }

    /**
     * Checks whether at least one of the dragon characteristics is null(= 0 for age) or not.
     *
     * @return True if none of the characteristics is null.
     */
    public boolean checkNull() {
        return id != null && creationDate != null && name != null && age != 0 && color != null && type != null && character != null && x != null && y != null;
    }

    /**
     * Creates new object Dragon and adds it to collection, if it is possible, or indicates that it is impossible.
     */
    public void elementCreation() {
        if (checkNull() && checkNullHead() && checkCall()) {
            vector.add(new Dragon(id, creationDate, name, age, type, color, character, new DragonHead(size, eyesCount), new Coordinates(x, y)));
        } else if (checkNull() && checkCall()) {
            vector.add(new Dragon(id, creationDate, name, age, type, color, character, new Coordinates(x, y)));
        }
    }

    /**
     * Checks whether at least one of the characteristics of the head of the dragon is null.
     *
     * @return true if none of the characteristics is null.
     */
    public boolean checkNullHead() {
        return size != null && eyesCount != null;
    }

    public LocalDate checkData(String S) {
        try {
            creationDate = LocalDate.parse(S);
        } catch (DateTimeParseException ex) {
            creationDate = null;
        }
        return creationDate;
    }

    public boolean checkCall() {
        return call.equalsIgnoreCase("Dragon");
    }

    public void writeCollection(Vector<Dragon> vector) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            if (vector.isEmpty()) {
                writeDocument(document);
            } else {
                Element root = document.createElement("Dragons");
                document.appendChild(root);
                for (Dragon dragon : vector) {
                    Element call = document.createElement("Dragon");
                    root.appendChild(call);
                    Element id = document.createElement("id");
                    id.appendChild(document.createTextNode(String.valueOf(dragon.getId())));
                    call.appendChild(id);
                    Element creationDate = document.createElement("CreationDate");
                    creationDate.appendChild(document.createTextNode(String.valueOf(dragon.getCreationDate())));
                    call.appendChild(creationDate);
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
                    if (dragon.getHead() != null) {
                        Element head = document.createElement("DragonHead");
                        call.appendChild(head);
                        Element size = document.createElement("size");
                        size.appendChild(document.createTextNode(String.valueOf(dragon.getHead().getSize())));
                        head.appendChild(size);
                        Element eyesCount = document.createElement("eyesCount");
                        eyesCount.appendChild(document.createTextNode(String.valueOf(dragon.getHead().getEyesCount())));
                        head.appendChild(eyesCount);
                    }
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
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            StreamResult result = new StreamResult(outputStream);
            tr.transform(dom, result);
        } catch (TransformerException ex) {
            System.out.println("Невозможно выполнить данную команду");
        } catch (FileNotFoundException ex) {
            if (!file.exists()) {
                System.err.println("Невозможно выполнить данную команду, так как файл с таким именем не найден");
            } else if (file.exists() && !file.canWrite()) {
                System.err.println("Невозможно выполнить данную команду, так как отсутвуют права для записи в файл");
            }
            System.out.println("Невозможно выполнить данную команду из-за отсутвия права на запись в файл");
        } catch (IllegalArgumentException ex) {
            System.out.println("Системная переменная с данным файлом не найдена");
        }
    }
}
