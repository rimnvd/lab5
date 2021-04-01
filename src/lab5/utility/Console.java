package lab5.utility;

import lab5.commands.*;


import java.util.*;

/**
 * This class is responsible for the application process.
 */
public class Console {
    private HashMap<String, Command> commands = new HashMap<>();
    private CollectionManager collectionManager;
    private ElementManager elementManager;
    private ArrayList<String> path = new ArrayList<>();

    public Console(CollectionManager collectionManager, ElementManager elementManager) {
        this.collectionManager = collectionManager;
        this.elementManager = elementManager;
    }

    /**
     * Asks to enter the name of the command and executes necessary command.
     */
    public void process() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Программа запущена в интерактивном режиме. Введите \"help\", чтобы посмотреть доступные команды");
        try {
            while (true) {
                createCommandsMap(commands);
                String S = scanner.nextLine().trim();
                System.out.println();
                if (commands.containsKey(new Command().commandName(S).toLowerCase())) {
                    commands.get(new Command().commandName(S).toLowerCase()).execute(S);
                } else {
                    System.out.println("Команда не найдена. Введите \"help\" для справки");
                }
                System.out.println();
                System.out.println("Введите команду");
            }
        } catch (NoSuchElementException ex) {
            System.out.println();
            System.out.println("Завершение работы программы");
            System.exit(0);
        }
    }

    public void createCommandsMap(HashMap<String, Command> commands ) {
        commands.put("add", new CommandAdd(collectionManager, elementManager));
        commands.put("add_if_max", new CommandAddIfMax(collectionManager, elementManager));
        commands.put("clear", new CommandClear(collectionManager));
        commands.put("execute_script", new CommandExecuteScript(collectionManager, elementManager, this, path));
        commands.put("exit", new CommandExit());
        commands.put("group_counting_by_character", new CommandGroupCountingByCharacter(collectionManager));
        commands.put("help", new CommandHelp());
        commands.put("info", new CommandInfo(collectionManager));
        commands.put("print_descending", new CommandPrintDescending(collectionManager));
        commands.put("remove_any_by_color", new CommandRemoveAnyByColor(collectionManager));
        commands.put("remove_by_id", new CommandRemoveById(collectionManager));
        commands.put("remove_last", new CommandRemoveLast(collectionManager));
        commands.put("remove_lower", new CommandRemoveLower(collectionManager, elementManager));
        commands.put("save", new CommandSave(collectionManager));
        commands.put("show", new CommandShow(collectionManager));
        commands.put("update", new CommandUpdate(collectionManager));
    }


}
