package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;
import java.util.Vector;


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
     * @param EnteredCommand the name of the specified command
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            if (collectionManager.isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                Vector<Dragon> vector = new Vector<>(collectionManager.getCollection());
                collectionManager.reverseSort(vector);
                for (Dragon dragon : vector) {
                    System.out.println("Dragon " + dragon.getName());
                }
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }

}
