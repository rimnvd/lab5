package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;


/**
 * This class is responsible for giving information about the elements in the collection.
 */
public class CommandShow extends Command {
    private CollectionManager collectionManager;

    public CommandShow(CollectionManager collectionManager) {
        super("show");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    public void execute(String S) {
        if (checkCommand(S)) {
            if (collectionManager.isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                for (Dragon dragon : collectionManager.getCollection()) {
                    System.out.println(dragon.toString());
                }
                System.out.println();
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }

    }
}
