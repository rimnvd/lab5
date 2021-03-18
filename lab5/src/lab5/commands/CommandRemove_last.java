package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;

/**
 * This class is responsible for the removing the last element from the collection.
 */
public class CommandRemove_last extends Command {

    /**
     * Executes the command.
     * @param vector Collection
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        if (vector.isEmpty()) {
            System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
        } else {
            vector.removeElementAt(vector.size()-1);
            System.out.println("Элемент успешно удален из коллекции");
        }
    }
}
