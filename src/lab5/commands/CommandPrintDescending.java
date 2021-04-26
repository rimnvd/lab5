package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;


/**
 * This class is responsible for printing the collection in descending order.
 */
public class CommandPrintDescending extends Command {
    private final CollectionManager collectionManager;

    public CommandPrintDescending(CollectionManager collectionManager) {
        super("print_descending");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param S the name of the specified command
     */
    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            if (collectionManager.isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                collectionManager.reverseSort();
                for (Dragon dragon : collectionManager.getCollection()) {
                    System.out.println("Dragon " + dragon.getName());
                }
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
