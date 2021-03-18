package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;

/**
 * This class is responsible for the cleaning of the collection.
 */
public class CommandClear extends Command {

    /**
     * Executes the command.
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        vector.removeAllElements();
        System.out.println("Коллекция очищена");
    }
}
