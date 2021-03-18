package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * This class is responsible for the adding an element to the collection
 * if this element is greater than the max element in the collection.
 */
public class CommandAdd_if_max extends CommandAdd {

    /**
     * Adding new element to the collection if this element is greater than the max element in the collection.
     * @param vector Collection.
     * @param S The full name of the entered command.
     */
    @Override
    public void execute(Vector<Dragon> vector ,String S) {
        if (checkCommand(S)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            elementManager.fields();
            Dragon dragon = elementManager.createElement(argument(S));
            if (vector.isEmpty()) {
                vector.add(dragon);
                System.out.println("Элемент успешно добавлен в коллекцию");
            } else if(checkElement(vector, S)) {
                System.out.println("Данный элемент уже есть в коллекции");
            } else if (dragon.getName().compareToIgnoreCase(vector.lastElement().getName()) > 0) {
                vector.add(dragon);
                System.out.println("Элемент успешно добавлен в коллекцию");
            }
        }
    }

    /**
     * Checks whether the name of the argument is right or not.
     * @param S The full name of the entered command.
     * @return True if the name is not right.
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^add_if_max(\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }
}
