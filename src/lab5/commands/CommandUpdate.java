package lab5.commands;

import lab5.utility.CollectionManager;

import java.util.regex.Pattern;

/**
 * This class is responsible for the updating the element, id of which coincides with the entered id.
 */
public class CommandUpdate extends Command {
    private final CollectionManager collectionManager;

    public CommandUpdate(CollectionManager collectionManager) {
        super("update");
        this.collectionManager = collectionManager;
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
                    collectionManager.updateById(Long.parseLong(argument(EnteredCommand)));
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
}
