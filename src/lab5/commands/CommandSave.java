package lab5.commands;


import lab5.utility.CollectionManager;

/**
 * This class is responsible for saving the collection to the file.
 */
public class CommandSave extends Command {
    private final CollectionManager collectionManager;

    public CommandSave(CollectionManager collectionManager) {
        super("save");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand The full name of the entered command.
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            collectionManager.safeToFile();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
