package lab5.commands;

import lab5.utility.CollectionManager;

/**
 * This class is responsible for the removing all of the elements from the collection.
 */
public class CommandClear extends Command {
    private final CollectionManager collectionManager;

    public CommandClear(CollectionManager collectionManager) {
        super("clear");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param S the full name of the entered command
     */
    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            collectionManager.clear();
            System.out.println("Коллекция очищена");
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
