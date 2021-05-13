package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import lab5.utility.ElementCreation;


/**
 * This class is responsible for the adding an element to the collection.
 */
public class CommandAdd extends Command {
    private final CollectionManager collectionManager;
    private final ElementCreation elementCreation;

    public CommandAdd(CollectionManager collectionManager, ElementCreation elementCreation) {
        super("add");
        this.collectionManager = collectionManager;
        this.elementCreation = elementCreation;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the command
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            collectionManager.addToCollection(elementCreation.createElement());
            System.out.println("Элемент успешно добавлен в коллекцию");
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }

    @Override
    public void execute(String[] fields) {
        Dragon dragon = elementCreation.createFromScript(fields);
        if (dragon != null) {
            collectionManager.addToCollection(dragon);
            System.out.println("Элемент успешно добавлен в коллекцию");
        } else {
            System.out.println("Элемент не может быть добавлен в коллекцию");
        }
        System.out.println();
    }
}


