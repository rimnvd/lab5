package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * This class is responsible for the updating the element, id of which coincides with the entered id.
 */
public class CommandUpdate extends Command {

    /**
     * Executes the command.
     * @param vector Collection.
     * @param S The full name of the entered command.
     */
    public void execute(Vector<Dragon> vector, String S) {
        if(checkCommand(S)) {
            System.out.println("Команда не найдена. Повторите ввод");
        } else {
            if (vector.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                int x = 0;
                for (int i = 0; i < vector.size() && x == 0; i++) {
                    if (makeId(S).equals(vector.get(i).getId())) {
                        elementManager.fields();
                        Dragon dragon = elementManager.createElement(argument(S));
                        dragon.setId(makeId(S));
                        vector.set(i, dragon);
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
     * Finds the call of the new element in the String S.
     * @return New element call.
     */
    @Override
    public String argument(String S) {
        String[] x = S.split(" ");
        return x[2];
    }

    /**
     * Checks whether the name of the argument is right or not.
     * @param S The full name of the entered command.
     * @return True if the name is not right.
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^update(\\s\\d+\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }
}
