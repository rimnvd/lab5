package lab5.commands;


/**
 * General class, superclass for the all classes from the package commands.
 */
public class Command {
    private String name;

    public Command(String name) {
        this.name = name;
    }

    public Command() {

    }

    public String getName() {
        return name;
    }

    /**
     * Executes the command.
     */
    public void execute(String S) {
    }

    /**
     * Checks whether the name of the entered command is right or not.
     *
     * @param S the full name of the entered command
     * @return true if the name is right; false otherwise
     */
    public boolean checkCommand(String S) {
        return S.equals(name);
    }

    /**
     * Returns the name of the argument for the commands which have the argument.
     *
     * @param S the full name of the entered command
     * @return the name of the argument for the commands which have the argument
     */
    public String argument(String S) {
        return S.substring(S.indexOf(" ") + 1);
    }

    /**
     * Returns the name of the entered command without argument (regardless of the command).
     *
     * @param S the full name of the entered command
     * @return the name of the command without argument
     */
    public String commandName(String S) {
        if (!S.contains(" ")) {
            return S;
        } else return S.substring(0, S.indexOf(" "));
    }


}
