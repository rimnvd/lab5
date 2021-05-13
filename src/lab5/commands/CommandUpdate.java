package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import lab5.utility.ElementCreation;

import java.util.regex.Pattern;

/**
 * This class is responsible for the updating the element, id of which coincides with the entered id.
 */
public class CommandUpdate extends Command {
    private final CollectionManager collectionManager;
    private final ElementCreation elementCreation;

    public CommandUpdate(CollectionManager collectionManager, ElementCreation elementCreation) {
        super("update");
        this.collectionManager = collectionManager;
        this.elementCreation = elementCreation;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command
     */
    public void execute(String EnteredCommand) {
        if (!checkCommand(EnteredCommand)) {
            System.out.println("Команда не найдена. Повторите ввод");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                if (checkId(argument(EnteredCommand))) {
                    if (collectionManager.updateById(Long.parseLong(argument(EnteredCommand)))) {
                        System.out.println("Невозможно выполнить данную команду, так как в коллекции нет элемента с такими значением id");
                    } else {
                        System.out.println("Элемент с id " + argument(EnteredCommand) + " успешно обновлен");
                    }
                }
            }
        }
    }

    /**
     * Checks whether String S is number or not.
     *
     * @param EnteredCommand checked String
     * @return true if String S is a number; false otherwise
     */
    public boolean checkId(String EnteredCommand) {
        boolean checkValue;
        try {
            Long.parseLong(EnteredCommand);
            checkValue = true;
        } catch (NumberFormatException ex) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
            checkValue = false;
        }
        return checkValue;
    }


    /**
     * Checks whether the name of the argument is right or not.
     *
     * @param EnteredCommand the full name of the entered command
     * @return true if the name is not right; false otherwise
     */
    @Override
    public boolean checkCommand(String EnteredCommand) {
        Pattern pattern = Pattern.compile("^update(\\s\\d+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(EnteredCommand).find();
    }

    @Override
    public void execute(String[] fields, Long value) {
        if (collectionManager.isEmpty()) {
            System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
        } else {
            Dragon dragon = elementCreation.createFromScript(fields);
            if (dragon != null) {
                boolean checkUpdating = true;
                for (int i = 0; i < collectionManager.getCollection().size() && checkUpdating; i++) {
                    if (collectionManager.getCollection().get(i).getId().equals(value)) {
                        dragon.setId(value);
                        collectionManager.getCollection().set(i, dragon);
                        checkUpdating = false;
                    }
                }
                if (checkUpdating)
                    System.out.println("Невозможно выполнить данную команду, так как в коллекции нет элемента с таким значением id");
                else {
                    System.out.println("Элемент с id " + value + " успено обновлен");
                }
            } else {
                System.out.println("Невозможно выполнить данную команду");
            }
            System.out.println();
        }
    }

}
