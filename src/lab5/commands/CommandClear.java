package lab5.commands;


import lab5.utility.CollectionManager;



/**
 * This class is responsible for the cleaning of the collection.
 */
public class CommandClear extends Command {
    private CollectionManager collectionManager;

    public CommandClear(CollectionManager collectionManager) {
        super("clear");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
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
