package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import lab5.utility.ElementManager;


/**
 * This class is responsible for the adding an element to the collection
 * if this element is greater than the max element in the collection.
 */
public class CommandAddIfMax extends Command {
    private CollectionManager collectionManager;
    private ElementManager elementManager;

    public CommandAddIfMax(CollectionManager collectionManager, ElementManager elementManager) {
        super("add_if_max");
        this.collectionManager = collectionManager;
        this.elementManager = elementManager;
    }

    /**
     * Adding new element to the collection if this element is greater than the max element in the collection.
     *
     * @param S      The full name of the entered command.
     */
    @Override
    public void execute(String S) {
        if (!checkCommand(S)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            Dragon dragon = elementManager.createElement();
            if (collectionManager.isEmpty()) {
                collectionManager.addToCollection(dragon);
                System.out.println("Элемент успешно добавлен в коллекцию");
            } else if (dragon.compareTo(collectionManager.maxElement()) > 0) {
                collectionManager.addToCollection(dragon);
                System.out.println("Элемент успешно добавлен в коллекцию");
            }
        }
    }

    @Override
    public boolean checkCommand(String S) {
        return S.equals("add_if_max");
    }


}
