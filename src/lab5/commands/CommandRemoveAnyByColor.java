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
     * @param S the full name of the entered command
     */
    @Override
    public void execute(String S) {
        if (!checkCommand(S)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                if (checkColor(argument(S).toUpperCase())) {
                    collectionManager.removeByColor(Color.valueOf(argument(S).toUpperCase()));
                }
            }
        }

    }

    /**
     * Checks whether the name of the argument is right or not.
     *
     * @param S the full name of the entered command
     * @return true if the name is not right; false otherwise
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^remove_any_by_color(\\s\\w+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(S).find();
    }

    public boolean checkColor(String S) {
        boolean f = true;
        try {
            Color.valueOf(S);
        } catch (IllegalArgumentException ex) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
            f = false;
        }
        return f;
    }
}
