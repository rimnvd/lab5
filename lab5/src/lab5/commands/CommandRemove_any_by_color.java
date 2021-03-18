package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * This class is responsible for the removing one element from the the collection, color of which
 * coincides with the entered color.
 */
public class CommandRemove_any_by_color extends Command {

    /**
     * Executes the command.
     * @param vector Collection.
     * @param S The full name of the entered command.
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        if (checkCommand(S)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            int x = 0;
            if (vector.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                for (int i = 0; i < vector.size() && x == 0; i++) {
                    if(vector.get(i).getColor().toString().equalsIgnoreCase(argument(S))) {
                        vector.removeElementAt(i);
                        x = 1;
                    }
                }
                if (x == 0) {
                    System.out.println("Элемента с таким полем Color нет");
                }
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
        Pattern pattern = Pattern.compile("^remove_any_by_color(\\s\\w+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }

}
