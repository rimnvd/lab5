package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import lab5.utility.ElementManager;


/**
 * This class is responsible for the removing the elements from the collection which are less than the entered element.
 */
public class CommandRemoveLower extends Command {
    private CollectionManager collectionManager;
    private ElementManager elementManager;

    public CommandRemoveLower(CollectionManager collectionManager, ElementManager elementManager) {
        super("remove_lower");
        this.collectionManager = collectionManager;
        this.elementManager = elementManager;
    }

    /**
     * @param S      The full name of the command.
     */
    @Override
    public void execute(String S) {
        if (!checkCommand(S)) {
            System.err.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                Dragon dragon = elementManager.createElement();
                collectionManager.removeLower(dragon);
            }
        }

    }


}
