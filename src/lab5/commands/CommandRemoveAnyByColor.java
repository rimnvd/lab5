package lab5.commands;

import lab5.data.Color;
import lab5.utility.CollectionManager;

import java.util.regex.Pattern;

/**
 * This class is responsible for the removing one element from the the collection, color of which
 * coincides with the entered color.
 */
public class CommandRemoveAnyByColor extends Command {
    private final CollectionManager collectionManager;

    public CommandRemoveAnyByColor(CollectionManager collectionManager) {
        super("remove_any_by_color");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command
     */
    @Override
    public void execute(String EnteredCommand) {
        if (!checkCommand(EnteredCommand)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                if (checkColor(argument(EnteredCommand).toUpperCase())) {
                    collectionManager.removeByColor(Color.valueOf(argument(EnteredCommand).toUpperCase()));
                }
            }
        }

    }

    /**
     * Checks whether the name of the argument is right or not.
     *
     * @param EnteredCommand the full name of the entered command
     * @return true if the name is not right; false otherwise
     */
    @Override
    public boolean checkCommand(String EnteredCommand) {
        Pattern pattern = Pattern.compile("^remove_any_by_color(\\s\\w+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(EnteredCommand).find();
    }

    public boolean checkColor(String EnteredCommand) {
        boolean checkValue = true;
        try {
            Color.valueOf(EnteredCommand);
        } catch (IllegalArgumentException ex) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
            checkValue = false;
        }
        return checkValue;
    }
}
