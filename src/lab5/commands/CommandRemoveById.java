package lab5.commands;

import lab5.utility.CollectionManager;

import java.util.regex.Pattern;

/**
 * This class is responsible for the removing one element from the collection, id of which
 * coincides with the entered color.
 */
public class CommandRemoveById extends Command {
    private final CollectionManager collectionManager;

    public CommandRemoveById(CollectionManager collectionManager) {
        super("remove_by_id");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command.
     */
    @Override
    public void execute(String EnteredCommand) {
        if (!checkCommand(EnteredCommand)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else if (checkId(argument(EnteredCommand))) {
                collectionManager.removeById(Long.parseLong(argument(EnteredCommand)));
            } else {
                System.out.println("Команда не найдена. Введите \"help\" для справки");
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
        Pattern pattern = Pattern.compile("^remove_by_id(\\s\\d+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(EnteredCommand).find();
    }

}
