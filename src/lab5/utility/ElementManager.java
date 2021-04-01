package lab5.utility;

import lab5.data.*;

import java.util.*;

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
    private Integer size = 0;
    private Double eyesCount;
    private Integer x;
    private Integer y;
    private final ArrayList<String> fields = new ArrayList<>();

    public void fields() {
        fields.clear();
        fieldsName();
        boolean f1 = true;
        for (String list : fields) {
            if (!list.equals("размер головы") && !list.equals("количество глаз") && !list.equals("x") && !list.equals("y")) {
                System.out.println("Введите " + list);
            } else if (list.equals("x") || list.equals("y")) {
                System.out.println("Введите координату " + list);
            }
            boolean f = true;
            String S;
            try {
                while (f) {
                    if (!list.equals("количество глаз") && !list.equals("размер головы")
                            && !list.equals("цвет") && !list.equals("тип") && !list.equals("характер")) {
                        S = scanner.nextLine().trim();
                        System.out.println();
                        if (checkNull(S)) {
                            System.out.println("Данное поле не может быть null. Повторите ввод");
                        } else {
                            switch (list) {
                                case "имя":
                                    f = false;
                                    name = S;
                                    break;
                                case "возраст":
                                    f = chooseNumber(list, S);
                                    if (!f) age = Long.parseLong(S);
                                    break;
                                case "x":
                                    f = chooseNumber(list, S);
                                    if (!f) x = Integer.parseInt(S);
                                    break;
                                case "y":
                                    f = chooseNumber(list, S);
                                    if (!f) y = Integer.parseInt(S);
                                    break;
                            }
                        }
                    } else if (list.equals("цвет") || list.equals("тип") || list.equals("характер")) {
                        System.out.println();
                        System.out.print("Выберите один из предложенных вариантов:");
                        StringBuilder options = new StringBuilder();
                        switch (list){
                            case "цвет":
                                for (Color color : Color.values()) {
                                    options.append("\n").append(color.name());
                                }
                                break;
                            case "тип":
                                for (DragonType type : DragonType.values()) {
                                    options.append("\n").append(type.name());
                                }
                                break;
                            default:
                                for (DragonCharacter character : DragonCharacter.values()) {
                                    options.append("\n").append(character.name());
                                }
                        }
                        System.out.println(options);
                        S = scanner.nextLine().trim();
                        System.out.println();
                        if (checkNull(S)) {
                            System.out.println("Данное поле не может быть null. Повторите ввод");
                        } else {
                            f = chooseEnum(list, S);
                            if (!f) {
                                switch (list) {
                                    case "цвет":
                                        color = Color.valueOf(S.toUpperCase());
                                        break;
                                    case "тип":
                                        type = DragonType.valueOf(S.toUpperCase());
                                        break;
                                    default:
                                        character = DragonCharacter.valueOf(S.toUpperCase());

                                }
                            }

                        }

                    }
                    else if (list.equals("размер головы")){
                        System.out.println("Вы хотите ввести размер головы и количество глаз? (YES/NO)");
                        String answer = scanner.nextLine().trim();
                        while (!answer.equalsIgnoreCase("YES") && !answer.equalsIgnoreCase("NO")) {
                            System.out.println("Вы хотите ввести размер головы и количество глаз? (YES/NO)");
                            answer = scanner.nextLine().trim();
                            System.out.println();
                        }
                        if (answer.equalsIgnoreCase("NO")) {
                            f = false;
                            f1 = false;
                        } else {
                            System.out.println();
                            System.out.println("Введите " + list);
                            S = scanner.nextLine().trim();
                            System.out.println();
                            if (checkNull(S)) {
                                System.out.println("Данное поле не может быть null. Повторите ввод");
                            } else {
                                f = chooseNumber(list, S);
                                if (!f) size = Integer.parseInt(S);
                                f1 = true;
                            }
                        }
                    } else {
                        if (f1) {
                            System.out.println("Введите " + list);
                            S = scanner.nextLine().trim();
                            System.out.println();
                            if (checkNull(S)) {
                                System.out.println("Данное поле не может быть null. Повторите ввод");
                            } else {
                                f = chooseNumber(list, S);
                                if (!f) eyesCount = Double.parseDouble(S);
                            }
                        } else f = false;

                    }
                }
            }
            catch (NoSuchElementException ex) {
                System.exit(0);
            }

        }
    }

    /**
     * Asks to enter the name of the dragon.
     *
     * @return Entered name.
     * @throws NoSuchElementException Thrown to indicate that the element being requested does not exist.
     */
    public boolean chooseEnum(String S, String S1) {
        boolean f = true;
        try {
            switch (S) {
                case "цвет":
                    Color.valueOf(S1.toUpperCase());
                    f = false;
                    break;
                case "тип":
                    DragonType.valueOf(S1.toUpperCase());
                    f = false;
                    break;
                default:
                    DragonCharacter.valueOf(S1.toUpperCase());
                    f = false;
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Данные введены неверно. Повторите ввод");
        }
        return f;
    }

    public boolean chooseNumber(String S, String S1) {
        boolean f = true;
        boolean f1 = true;
        try {
            long b = -1;
            int c = -1;
            double d = -1.;
            if (S.equals("x") || S.equals("y")) {
                Integer.parseInt(S1);
                f =false;
            } else {
                if (S.equals("возраст")) {
                    b = Long.parseLong(S1);
                } else if (S.equals("размер головы")) {
                    c = Integer.parseInt(S1);
                } else {
                    d = Double.parseDouble(S1);
                    long l = Math.round(d);
                    if (d != l) {
                        System.out.println("Значение данного поля должно быть целым. Повторите ввод");
                        f1 = false;
                    }
                }
                if ((b > 0 || c > 0 || d > 0) && f1) {
                    f = false;
                } else {
                    System.out.println("Значение данного поля должно быть положительным. Повторите ввод");
                }
           }
        } catch (NumberFormatException ex) {
            System.out.println("Неверный формат данных. Повторите ввод");
        }
        return f;
    }

    /**
     * Checks if the String S is empty or not
     *
     * @return True if the String S is empty.
     */
    public boolean checkNull(String S) {
        return (S.equals(""));
    }

    /**
     * Creates new object Dragon
     *
     * @return New object Dragon
     */
    public Dragon createElement() {
        fields();
        Dragon dragon;
        if (size == 0) {
            dragon = new Dragon(name,
                    age,
                    type,
                    color,
                    character,
                    new Coordinates(x, y));
        } else {
            dragon = new Dragon(name,
                    age,
                    type,
                    color,
                    character,
                    new DragonHead(size, eyesCount),
                    new Coordinates(x, y));
        }
        return dragon;
    }

    public void fieldsName() {
        fields.add("имя");
        fields.add("возраст");
        fields.add("тип");
        fields.add("цвет");
        fields.add("характер");
        fields.add("x");
        fields.add("y");
        fields.add("размер головы");
        fields.add("количество глаз");

    }
}
