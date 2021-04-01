package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionManager;


/**
 * This class is responsible for giving information about the elements in the collection
 * and their constituents. The elements are showed in descending order.
 */
public class CommandPrintDescending extends Command {
    private CollectionManager collectionManager;

    public CommandPrintDescending(CollectionManager collectionManager) {
        super("print_descending");
        this.collectionManager = collectionManager;
    }

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
