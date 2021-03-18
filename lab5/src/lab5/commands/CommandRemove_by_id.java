package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * this class is responsible for the removing element from the collection, id of which
 * coincides with the entered color.
 */
public class CommandRemove_by_id extends Command {

    /**
     * Executes the command.
     * @param vector Collection
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
                for (Dragon dragon: vector) {
                    if (dragon.getId().equals(makeId(S))) {
                        vector.remove(dragon);
                        x = 1;
                    }
                }
                if (x == 0) {
                    System.out.println("В коллекции нет элемента с таким значением id");
                }
            }
        }

    }

    /**
     * Finds the id value in the String S.
     * @return id value.
     */
    public Long makeId(String S) {
        String[] x = S.split(" ");
        return Long.valueOf(x[1]);
    }

    /**
     * Checks whether the name of the argument is right or not.
     * @param S The full name of the entered command.
     * @return True if the name is not right.
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^remove_by_id(\\s\\d+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }

}
