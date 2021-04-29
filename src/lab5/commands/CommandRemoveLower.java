package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import lab5.utility.ElementCreation;


/**
 * This class is responsible for the removing all the elements from the collection that are lower than the entered element.
 */
public class CommandRemoveLower extends Command {
    private final CollectionManager collectionManager;
    private final ElementCreation elementCreation;

    public CommandRemoveLower(CollectionManager collectionManager, ElementCreation elementCreation) {
        super("remove_lower");
        this.collectionManager = collectionManager;
        this.elementCreation = elementCreation;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand The full name of the command.
     */
    @Override
    public void execute(String EnteredCommand) {
        if (!checkCommand(EnteredCommand)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                Dragon dragon = elementCreation.createElement();
                collectionManager.removeLower(dragon);
            }
        }

    }


}
