package lab5.commands;

import lab5.utility.CollectionManager;


/**
 * This class is responsible for giving information about the elements in the collection.
 */
public class CommandShow extends Command {
    private final CollectionManager collectionManager;

    public CommandShow(CollectionManager collectionManager) {
        super("show");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command
     */
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            if (collectionManager.isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                for (int i = 0; i < collectionManager.collectionSize(); i++) {
                    System.out.println(collectionManager.getCollection().get(i).toString());
                    if (i != collectionManager.collectionSize() - 1) {
                        System.out.println();
                    }
                }
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }

    }
}
