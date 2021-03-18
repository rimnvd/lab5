package lab5.commands;

import lab5.data.*;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * This class is responsible for the adding an element to the collection.
 */
public class CommandAdd extends Command {

    /**
     * Adding new element to the collection if it is possible.
     * @param vector Collection.
     * @param S The full name of the command.
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        if (checkCommand(S)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else if (checkElement(vector, S)) {
            System.out.println("Данный элемент уже есть в коллекции");
        } else {
            elementManager.fields();
            vector.add(elementManager.createElement(argument(S)));
            elementManager.mySort(vector);
            System.out.println("Элемент успешно добавлен в коллекцию");
        }
    }

    /**
     * Checks whether the collection has already contained this element or not.
     * @param vector Collection.
     * @param S The full name of the entered command.
     * @return true if the collection has already contained this element or not.
     */
    public boolean checkElement(Vector<Dragon> vector, String S) {
        int x = 0;
        for (Dragon dragon: vector) {
            if(argument(S).equals(dragon.toString())) {
                x = 1;
            }
        }
        return x == 1;
    }

    /**
     * Checks whether the name of the argument is right or not.
     * @param S The full name of the entered command.
     * @return True if the name is not right.
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^add(\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }



}


