package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.CollectionCreation;

import java.util.Vector;

/**
 * This class is responsible for giving information about the collection.
 */
public class CommandInfo extends Command {

    /**
     * Executes the command.
     * @param vector Collection
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        System.out.println("Тип коллекции: " + vector.getClass().getName());
        System.out.println("Дата инициализации коллекции: " + CollectionCreation.date);
        System.out.printf("Количество элементов коллекции: %d\n", vector.size());
        if (!vector.isEmpty() && vector.size() != 1) {
            System.out.println("Первый элемент коллекции: " + vector.firstElement());
            System.out.println("Последний элемент коллекции: " + vector.lastElement());
        }
    }
}

