package lab5.commands;

import lab5.utility.CollectionManager;

import java.util.regex.Pattern;

/**
 * This class is responsible for the updating the element, id of which coincides with the entered id.
 */
public class CommandUpdate extends Command {
    private CollectionManager collectionManager;

    public CommandUpdate(CollectionManager collectionManager) {
        super("update");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @param S The full name of the entered command.
     */
    public void execute(String S) {
        if(!checkCommand(S)) {
            System.out.println("Команда не найдена. Повторите ввод");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                if (checkId(argument(S))) {
                    collectionManager.updateById(Long.parseLong(argument(S)));
                }

            }
        }
    }

    /**
     * Finds the id value in the String S.
     * @return id value.
     */


    public boolean checkId(String S) {
        boolean f;
        try {
            Long.parseLong(S);
            f = true;
        } catch (NumberFormatException ex) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
            f = false;
        }
        return f;
    }




    /**
     * Checks whether the name of the argument is right or not.
     * @param S The full name of the entered command.
     * @return True if the name is not right.
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^update(\\s\\d+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(S).find();
    }
}
