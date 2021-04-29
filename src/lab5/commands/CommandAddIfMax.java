package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import lab5.utility.ElementCreation;


/**
 * This class is responsible for the adding an element to the collection
 * if this element is greater than the max element in the collection.
 */
public class CommandAddIfMax extends Command {
    private final CollectionManager collectionManager;
    private final ElementCreation elementCreation;

    public CommandAddIfMax(CollectionManager collectionManager, ElementCreation elementCreation) {
        super("add_if_max");
        this.collectionManager = collectionManager;
        this.elementCreation = elementCreation;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command
     */
    @Override
    public void execute(String EnteredCommand) {
        if (!checkCommand(EnteredCommand)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            Dragon dragon = elementCreation.createElement();
            if (collectionManager.isEmpty()) {
                collectionManager.addToCollection(dragon);
                System.out.println("Элемент успешно добавлен в коллекцию");
            } else if (dragon.compareTo(collectionManager.maxElement()) > 0) {
                collectionManager.addToCollection(dragon);
                System.out.println("Элемент успешно добавлен в коллекцию");
            }
        }
    }


}
