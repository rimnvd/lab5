package lab5.utility;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;
import lab5.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class is responsible for the creation of the collection.
 */

public class CollectionCreation {
    public static final Vector<Dragon> vector = new Vector<>(0);
    public static final LocalDate date = LocalDate.now();
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

    /**
     * Creates and sorts the collection.
     */
    public void creation() {
        parser();
        ElementManager elementManager = new ElementManager();
        elementManager.mySort(vector);
    }

    /**
     * Parse the Xml-document.
     * @exception IllegalArgumentException Thrown to indicate that Environment variable wasn't found.
     */
    public void parser() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(System.getenv("file"));
            Node root = document.getDocumentElement();
            NodeList dragons = root.getChildNodes();
            int z = 1;
            clearFields();
            for (int i = 0; i < dragons.getLength(); i++) {
                if (dragons.item(i).getNodeType() != Node.TEXT_NODE && dragons.item(i).getChildNodes().getLength() > 1) {
                    call = dragons.item(i).getNodeName();
                    NodeList dragon = dragons.item(i).getChildNodes();
                    for (int j = 0; j < dragon.getLength() && z == 1; j++) {
                        if (dragon.item(j).getNodeType() != Node.TEXT_NODE && dragon.item(j).getChildNodes().getLength() == 1) {
                            String S1 = dragon.item(j).getNodeName();
                            String S2 = dragon.item(j).getChildNodes().item(0).getTextContent().replace("\n", "" ).trim();
                            z = field(S1, S2, "a");
                        } else if (dragon.item(j).getNodeType() != Node.TEXT_NODE && dragon.item(j).getChildNodes().getLength() > 1) {
                            NodeList dragon1 = dragon.item(j).getChildNodes();
                            String S3 = dragon.item(j).getNodeName();
                            for (int k = 0; k < dragon1.getLength() && z == 1; k++) {
                                if (dragon1.item(k).getNodeType() != Node.TEXT_NODE && dragon1.item(k).getChildNodes().getLength() == 1) {
                                    String S4 = dragon1.item(k).getNodeName();
                                    String S5 = dragon1.item(k).getChildNodes().item(0).getTextContent();
                                    z = field(S3, S4, S5);
                                }
                            }
                        }
                    }
                    System.out.println();
                } else if (dragons.item(i).getNodeType() != Node.TEXT_NODE && dragons.item(i).getChildNodes().getLength() <= 1) {
                    String S = dragons.item(i).getNodeName();
                    System.out.println("Элемент " + S + " не может быть добавлен в коллекцию");
                }
                if (dragons.item(i).getNodeType() != Node.TEXT_NODE) {
                    if (z == 1) elementCreation();
                    else {
                        String S = dragons.item(i).getNodeName();
                        System.out.println("Элемент " + S + " не может быть добавлен в коллекцию");
                    }
                    clearFields();
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Невозможно считать данные из-за ошибки конфигурации");
        } catch (SAXException | IOException e) {
            System.out.println("Невозможно считать данные из файла по одной из следующих причин:");
            System.out.println("\t-файл не существует");
            System.out.println("\t-файл пустой");
            System.out.println("\t-отсутвуют права на чтения файла");
        } catch (IllegalArgumentException ex) {
            System.out.println("Системная переменная с загрузочным файлом не найдена");
        }
    }

    /**
     * Checks if the String S is one of the characteristics of the dragon or not and sets the value of this characteristic, using String S1 or S2.
     * @return 1 if String S is one of the characteristics of the dragon, 0 if String S is not one of the characteristics.
     */
    public int field(String S, String S1, String S2) {
        int z;
        if (S.equalsIgnoreCase("name")) {
            if (name != null) z = 0;
            else {
                name = S1;
                z = 1;
            }
        } else if (S.equalsIgnoreCase("age")) {
            if (age != 0) z = 0;
            else {
                age = checkAge(S1);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("color")) {
            if (color != null) z = 0;
            else {
                color = checkColor(S1);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("DragonType")) {
            if (type != null) z = 0;
            else {
                type = checkType(S1);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("DragonCharacter")) {
            if (character != null) z = 0;
            else {
                character = checkCharacter(S1);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("Coordinates") && S1.equalsIgnoreCase("x")) {
            if (x != null) z = 0;
            else {
                x = checkInt(S2);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("Coordinates") && S1.equalsIgnoreCase("y")) {
            if (y != null) z = 0;
            else {
                y = checkInt(S2);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("DragonHead") && S1.equalsIgnoreCase("size")) {
            if (size != null) z = 0;
            else {
                size = checkPositiveInt(S2);
                z = 1;
            }
        } else if (S.equalsIgnoreCase("DragonHead") && S1.equalsIgnoreCase("eyesCount")) {
            if (eyesCount != null) z = 0;
            else {
                eyesCount = checkPositiveDouble(S2);
                z = 1;
            }
        } else z = 0;
        return z;
    }

    /**
     * Sets the value of the age of the dragon.
     * @param S set value.
     * @return The set value of the age or 0 if it was not set.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public long checkAge(String S) {
        try {
            age = Long.parseLong(S);
            if (age < 0) {
                age = 0;
            }
        }
        catch (NumberFormatException ex) {
            age = 0;
        }
        return age;
    }

    /**
     *
     * Sets the value of the color of the dragon.
     * @param S set value.
     * @return The set value of the color or null if it was not set.
     */
    public Color checkColor(String S) {
        if (S.equalsIgnoreCase("RED")) {
            return Color.RED;
        } else if (S.equalsIgnoreCase("YELLOW")) {
            return Color.YELLOW;
        } else if (S.equalsIgnoreCase("WHITE")) {
            return Color.WHITE;
        } else if (S.equalsIgnoreCase("ORANGE")) {
            return Color.ORANGE;
        } else if (S.equalsIgnoreCase("BROWN")) {
            return Color.BROWN;
        } else {
            return null;
        }
    }

    /**
     * Sets the value of the type of the dragon.
     * @param S set value.
     * @return The set value of the type or null if it was not set.
     */
    public DragonType checkType(String S) {
        if (S.equalsIgnoreCase("WATER")) {
            return DragonType.WATER;
        } else if (S.equalsIgnoreCase("FIRE")) {
            return DragonType.FIRE;
        } else if (S.equalsIgnoreCase("AIR")) {
            return DragonType.AIR;
        } else if (S.equalsIgnoreCase("UNDERGROUND")) {
            return DragonType.UNDERGROUND;
        } else {
            return null;
        }
    }

    /**
     * Sets the value of the character of the dragon.
     * @param S set value.
     * @return The set value of the character or null if it was not set.
     */
    public DragonCharacter checkCharacter(String S) {
        if (S.equalsIgnoreCase("CHAOTIC_EVIL")) {
            return DragonCharacter.CHAOTIC_EVIL;
        } else if (S.equalsIgnoreCase("CUNNING")) {
            return DragonCharacter.CUNNING;
        } else if (S.equalsIgnoreCase("GOOD")) {
            return DragonCharacter.GOOD;
        } else {
            return null;
        }
    }

    /**
     * Checks whether String S can be parsed to Integer or not.
     * @return The Integer value if it is possible or null in the opposite situation.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Integer checkInt(String S) {
        Integer a;
        try {
            a = Integer.parseInt(S);
        }
        catch (NumberFormatException ex) {
            a = null;
        }
        return a;
    }

    /**
     * Checks whether String S can be parsed to Integer or not and checks whether the value is positive.
     * @return The positive Integer value if it possible or null in the opposite situation.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Integer checkPositiveInt(String S) {
        Integer a;
        try {
            a = Integer.parseInt(S);
            if (a <= 0) {
                a = null;
            }
        }
        catch (NumberFormatException ex) {
            a = null;
        }
        return a;
    }

    /**
     * Checks whether String S can be parsed to Double or not and checks whether the value is positive.
     * @return The positive Double value if it possible or null in the opposite situation.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Double checkPositiveDouble(String S) {
        Double a;
        try {
            a = Double.parseDouble(S);
            if (a <= 0) {
                a = null;
            }
        }
        catch (NumberFormatException ex) {
            a = null;
        }
        return a;
    }

    /**
     * Checks whether at least one of the dragon characteristics is null(= 0 for age) or not.
     * @return True if none of the characteristics is null.
     */
    public boolean checkNull() {
        return name != null && age != 0 && color != null && type != null && character != null && x != null && y != null;
    }

    /**
     * Creates new object Dragon and adds it to collection, if it is possible, or indicates that it is impossible.
     */
    public void elementCreation() {
        if (checkNull() && checkNullHead()) {
            vector.add(new Dragon(name, age, type, color, character, new DragonHead(size, eyesCount), new Coordinates(x, y), call));
            System.out.println("Элемент " + call + " успешно добавлен в коллекцию");
        } else if (checkNull()){
            vector.add(new Dragon(name, age, type, color, character, new Coordinates(x, y), call));
            System.out.println("Элемент " + call + " успешно добавлен в коллекцию");
        } else {
            System.out.println("Элемент " + call + " не может быть добавлен в коллекцию");
        }
    }

    /**
     * Checks whether at least one of the characteristics of the head of the dragon is null.
     * @return true if none of the characteristics is null.
     */
    public boolean checkNullHead() {
        return size != null && eyesCount != null;
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

    }
}
