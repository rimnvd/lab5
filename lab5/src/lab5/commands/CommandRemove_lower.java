package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * This class is responsible for the removing the elements from the collection which are less than the entered element.
 */
public class CommandRemove_lower extends Command {

    /**
     *
     * @param vector Collection.
     * @param S The full name of the command.
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        if (checkCommand(S)) {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        } else {
            if (vector.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                int x = 0;
                for (int i = 0; i < vector.size() && x == 0; i++) {
                    if (check(argument(S), vector.get(i))) {
                        delete1(vector, i);
                        x = 1;
                    }
                }
                if (x == 0) {
                    elementManager.fields();
                    Dragon dragon = elementManager.createElement(argument(S));
                    delete(vector, dragon);
                }
            }
        }

    }

    public boolean check(String S, Dragon dragon) {
        return S.equals(dragon.toString());
    }

    public void delete(Vector<Dragon> vector, Dragon dragon) {
        int i = 0;
        while (i == 0 && vector.size() > 0) {
            if (vector.get(0).getName().compareToIgnoreCase(dragon.getName()) < 0) {
                vector.removeElementAt(0);
            } else {
                i = 1;
            }
        }
    }

    public void delete1(Vector<Dragon> vector, int x) {
        int i = 0;
        while (i < x) {
            vector.removeElementAt(0);
            i++;
        }
    }

    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^remove_lower(\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }
}
