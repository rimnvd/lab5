package lab5.utility;

import lab5.data.*;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

/**
 * This class is responsible for the creation of the new Dragon object.
 */
public class ElementManager {
    Scanner scanner = new Scanner(System.in);
    private String name;
    private long age;
    private Color color;
    private DragonType type;
    private DragonCharacter character;
    private Coordinates coordinates;
    private DragonHead head;

    /**
     *
     * Asks to enter the name of the dragon.
     * @return Entered name.
     * @exception NoSuchElementException Thrown to indicate that the element being requested does not exist.
     */
    public String chooseName()  {
        System.out.println("Введите имя");
        String name = null;
        int x = 0;
        try {
            while (x == 0) {
                name = scanner.nextLine();
                if (checkNull(name)) {
                    System.out.println("Данное поле не может быть null. Повторите ввод");
                } else {
                    x = 1;
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        System.out.println();
        return name;
    }

    /**
     *
     * Asks to enter the age of the dragon.
     * @return Entered age.
     * @exception NoSuchElementException Thrown to indicate that the element being requested does not exist.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public long chooseAge() {
        System.out.println("Введите возраст");
        long age = 0;
        String sAge;
        int y = 0;
        try {
            while (y == 0) {
                sAge = scanner.nextLine();
                if (!checkNull(sAge)) {
                    try {
                        age = Long.parseLong(sAge);
                        if (age > 0) {
                            y = 1;
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Возраст должен быть положительным. Повторите ввод");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println();
                        System.out.println("Данные введены неверно. Повторите ввод");
                    }
                } else {
                    System.out.println("Данное поле не может быть null. Повторите ввод");
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        return age;
    }

    /**
     *Asks to enter the color of the dragon.
     * @return Entered color.
     * @exception NoSuchElementException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Color chooseColor() {
        System.out.println("Выберите цвет");
        System.out.println("RED");
        System.out.println("YELLOW");
        System.out.println("ORANGE");
        System.out.println("WHITE");
        System.out.println("BROWN");
        int x = 0;
        Color color = null;
        try {
            while (x == 0) {
                String colour = scanner.nextLine();
                if (colour.equalsIgnoreCase("RED")) {
                    color = Color.RED;
                    x = 1;
                } else if (colour.equalsIgnoreCase("YELLOW")) {
                    color = Color.YELLOW;
                    x = 1;
                } else if (colour.equalsIgnoreCase("ORANGE")) {
                    color = Color.ORANGE;
                    x = 1;
                } else if (colour.equalsIgnoreCase("WHITE")) {
                    color = Color.WHITE;
                    x = 1;
                } else if (colour.equalsIgnoreCase("BROWN")) {
                    color = Color.BROWN;
                    x = 1;
                } else {
                    System.out.println();
                    System.out.println("Данные введены неверно. Повторите ввод");
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        System.out.println();
        return color;
    }

    /**
     * Asks to enter the X-Y coordinates of the dragon.
     * @return Entered X-Y coordinates.
     * @exception NoSuchElementException Thrown to indicate that the element being requested does not exist.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public Coordinates chooseCoordinates() {
        int z = 0;
        Integer x = null;
        Integer y = null;
        String sX;
        String sY;
        System.out.println("Введите координату x");
        try {
            while (z == 0) {
                sX = scanner.nextLine();
                if (!checkNull(sX)) {
                    try {
                        x = Integer.parseInt(sX);
                        z = 1;
                    } catch (NumberFormatException ex) {
                        System.out.println();
                        System.out.println("Данные введены неверно. Повторите ввод");
                    }
                } else {
                    System.out.println("Данное поле не может быть null. Повторите ввод");
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        System.out.println("Введите координату y");
        z = 0;
        try {
            while (z == 0) {
                sY = scanner.nextLine();
                if (!checkNull(sY)) {
                    try {
                        y = Integer.parseInt(sY);
                        z = 1;
                    } catch (NumberFormatException ex) {
                        System.out.println();
                        System.out.println("Данные введены неверно. Повторите ввод");
                    }
                } else {
                    System.out.println("Данное поле не может быть null. Повторите ввод");
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        return new Coordinates(x, y);
    }

    /**
     * Asks to enter the character of the dragon.
     * @return Entered character.
     * @exception NoSuchElementException Thrown to indicate that the element being requested does not exist.
     */
    public DragonCharacter chooseCharacter() {
        System.out.println("Выберите характер");
        System.out.println("CUNNING");
        System.out.println("GOOD");
        System.out.println("CHAOTIC_EVIL");
        int x = 0;
        DragonCharacter character = null;
        try {
            while (x == 0) {
                String Character = scanner.nextLine();
                if (Character.equalsIgnoreCase("CHAOTIC_EVIL")) {
                    character = DragonCharacter.CHAOTIC_EVIL;
                    x = 1;
                } else if (Character.equalsIgnoreCase("CUNNING")) {
                    character = DragonCharacter.CUNNING;
                    x = 1;
                } else if (Character.equalsIgnoreCase("GOOD")) {
                    character = DragonCharacter.GOOD;
                    x = 1;
                } else {
                    System.out.println();
                    System.out.println("Данные введены неверно. Повторите ввод");
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        System.out.println();
        return character;
    }

    /**
     * Asks to enter the size of the head and the amount of eyes of the dragon.
     * @return New object DragonHead with the set value of the size and the amount of eyes.
     * @exception NoSuchElementException Thrown to indicate that the element being requested does not exist.
     * @exception NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
     */
    public DragonHead chooseHead() {
        System.out.println("Введите размер головы");
        int y = 0;
        String sSize;
        String sEyesCount;
        Integer size = null;
        Double eyesCount = null;
        try {
            while (y == 0) {
                sSize = scanner.nextLine();
                if (!checkNull(sSize)) {
                    try {
                        size = Integer.parseInt(sSize);
                        if (size > 0) {
                            y = 1;
                        } else {
                            System.out.println();
                            System.out.println("Размер головы должен быть положительным. Повторите ввод");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println();
                        System.out.println("Данные введены неверно. Повторите ввод");
                    }

                } else {
                    return (new DragonHead(0, 0.));
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        y = 0;
        System.out.println("Введите количество глаз");
        try {
            while (y == 0) {
                sEyesCount = scanner.nextLine();
                if (!checkNull(sEyesCount)) {
                    try {
                        eyesCount = Double.parseDouble(sEyesCount);
                        if (eyesCount > 0) {
                            y = 1;
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Количество глаз должно быть положительным. Повторите ввод");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println();
                        System.out.println("Данные введены неверно. Повторите ввод");
                    }
                } else {
                    return (new DragonHead(0, 0.));
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        return new DragonHead(size, eyesCount);
    }

    /**
     * Asks to enter the type of the dragon.
     * @return Entered type.
     * @exception NoSuchElementException Thrown to indicate that the element being requested does not exist.
     */
    public DragonType chooseType() {
        System.out.println("Выберите тип");
        System.out.println("WATER");
        System.out.println("FIRE");
        System.out.println("AIR");
        System.out.println("UNDERGROUND");
        int x = 0;
        DragonType type = null;
        try {
            while (x == 0) {
                String Type = scanner.nextLine();
                if (Type.equalsIgnoreCase("WATER")) {
                    type = DragonType.WATER;
                    x = 1;
                } else if (Type.equalsIgnoreCase("FIRE")) {
                    type = DragonType.FIRE;
                    x = 1;
                } else if (Type.equalsIgnoreCase("AIR")) {
                    type = DragonType.AIR;
                    x = 1;
                } else if (Type.equalsIgnoreCase("UNDERGROUND")) {
                    type = DragonType.UNDERGROUND;
                    x = 1;
                } else {
                    System.out.println();
                    System.out.println("Данные введены неверно. Повторите ввод");
                }
            }
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
        System.out.println();
        return type;
    }

    /**
     * Checks if the String S is empty or not
     * @return True if the String S is empty.
     */
    public boolean checkNull(String S) {
        return (S.equals(""));
    }

    /**
     * Sort the vector by the name of the dragon.
     * @param vector Sortable vector.
     */
    public void mySort(Vector<Dragon> vector) {
        Comparator<Dragon> comparator = (d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName());
        vector.sort(comparator);
    }

    /**
     * set the values of the fields.
     */
    public void fields() {
        name = chooseName();
        age = chooseAge();
        type = chooseType();
        color = chooseColor();
        character = chooseCharacter();
        head = chooseHead();
        coordinates = chooseCoordinates();
    }

    /**
     * Creates new object Dragon
     * @param S the call of the dragon.
     * @return New object Dragon
     */
    public Dragon createElement(String S) {
        Dragon dragon;
        if (head.getSize() == 0) {
            dragon = new Dragon(name,
                    age,
                    type,
                    color,
                    character,
                    coordinates,
                    S);
        } else {
            dragon = new Dragon(name,
                    age,
                    type,
                    color,
                    character,
                    head,
                    coordinates,
                    S);
        }
        return dragon;
    }
}
