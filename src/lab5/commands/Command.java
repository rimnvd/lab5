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
    public void execute(String EnteredCommand) {
    }

    /**
     * Checks whether the name of the entered command is right or not.
     *
     * @param EnteredCommand the full name of the entered command
     * @return true if the name is right; false otherwise
     */
    public boolean checkCommand(String EnteredCommand) {
        return EnteredCommand.equals(name);
    }

    /**
     * Returns the name of the argument for the commands which have the argument.
     *
     * @param EnteredCommand the full name of the entered command
     * @return the name of the argument for the commands which have the argument
     */
    public String argument(String EnteredCommand) {
        return EnteredCommand.substring(EnteredCommand.indexOf(" ") + 1);
    }

    /**
     * Returns the name of the entered command without argument (regardless of the command).
     *
     * @param EnteredCommand the full name of the entered command
     * @return the name of the command without argument
     */
    public String commandName(String EnteredCommand) {
        if (!EnteredCommand.contains(" ")) {
            return EnteredCommand;
        } else return EnteredCommand.substring(0, EnteredCommand.indexOf(" "));
    }


}
