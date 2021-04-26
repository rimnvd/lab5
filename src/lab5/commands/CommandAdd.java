package lab5.commands;

import lab5.utility.CollectionManager;
import lab5.utility.ElementManager;

/**
 * This class is responsible for the adding an element to the collection.
 */
public class CommandAdd extends Command {
    private final CollectionManager collectionManager;
    private final ElementManager elementManager;

    public CommandAdd(CollectionManager collectionManager, ElementManager elementManager) {
        super("add");
        this.collectionManager = collectionManager;
        this.elementManager = elementManager;
    }

    /**
     * Executes the command.
     *
     * @param S the full name of the command
     */
    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            collectionManager.addToCollection(elementManager.createElement());
            System.out.println();
            System.out.println("Элемент успешно добавлен в коллекцию");
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}


