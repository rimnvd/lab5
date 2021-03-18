package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.ElementManager;

import java.util.Vector;

/**
 * General class, superclass for the all classes from the package commands.
 */
public class Command {

    ElementManager elementManager = new ElementManager();

    /**
     * It is responsible for the execution of the command.
     */
    public void execute(Vector<Dragon> vector, String S) {}

    /**
     * Checks whether the name of the entered command is right or not.
     * @param S The full name of the entered command.
     * @return true if the name is right.
     */
    public boolean checkCommand(String S) {
        return false;
    }

    /**
     * Returns the name of the argument for the commands which have the argument.
     * @param S The full name of the entered command.
     * @return The name of the argument for the commands which have the argument.
     */
    public String argument(String S) {
        return S.substring(S.indexOf(" ") + 1);
    }

    /**
     * Returns the name of the entered command without argument (regardless of the command).
     * @param S The full name of the entered command.
     * @return The name of the command without argument
     */
    public String commandName(String S) {
        if (!S.contains(" ")) {
            return S;
        } else return S.substring(0, S.indexOf(" "));
    }


}
