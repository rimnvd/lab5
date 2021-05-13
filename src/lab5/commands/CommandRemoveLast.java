package lab5.commands;


import lab5.utility.CollectionManager;

/**
 * This class is responsible for the removing the last element from the collection.
 */
public class CommandRemoveLast extends Command {
    private final CollectionManager collectionManager;

    public CommandRemoveLast(CollectionManager collectionManager) {
        super("remove_last");
        this.collectionManager = collectionManager;
    }


    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            if (collectionManager.isEmpty()) {
                System.out.println("Невозможно выполнить данную команду, так как коллекция пуста");
            } else {
                collectionManager.removeLast();
                System.out.println("Элемент успешно удален из коллекции");
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
