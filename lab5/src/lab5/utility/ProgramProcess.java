package lab5.utility;

import lab5.commands.*;
import lab5.data.Dragon;

import java.util.*;

/**
 * This class is responsible for the application process.
 */
public class ProgramProcess {

    HashMap<String, Command> commands = new HashMap<>();
    public static final ArrayList<String> path = new ArrayList<>(0);

    /**
     *Asks to enter the name of the command and executes necessary command.
     */
    public void process(Vector<Dragon> vector) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Программа запущена в интерактивном режиме. Введите \"help\", чтобы посмотреть доступные команды");
        commandsMap(commands);
        while (scanner.hasNext()) {
            String S = scanner.nextLine();
            System.out.println();
            if (commands.containsKey(new Command().commandName(S).toLowerCase())) {
                commands.get(new Command().commandName(S.toLowerCase())).execute(vector, S);
            } else {
                System.out.println("Команда не найдена. Введите \"help\" для справки");
            }
            System.out.println();
            System.out.println("Введите команду");
        }
    }

    /**
     * Adds the name of the commands and the their corresponding objects to the HashMap commands.
     */
    public void commandsMap(HashMap<String, Command> commands) {
        commands.put("help", new CommandHelp());
        commands.put("info", new CommandInfo());
        commands.put("show", new CommandShow());
        commands.put("add", new CommandAdd());
        commands.put("update", new CommandUpdate());
        commands.put("remove_by_id", new CommandRemove_by_id());
        commands.put("clear", new CommandClear());
        commands.put("save", new CommandSave());
        commands.put("execute_script", new CommandExecute_Script_command());
        commands.put("remove_last", new CommandRemove_last());
        commands.put("add_if_max", new CommandAdd_if_max());
        commands.put("remove_lower", new CommandRemove_lower());
        commands.put("remove_any_by_color", new CommandRemove_any_by_color());
        commands.put("group_counting_by_character", new CommandGroup_counting_by_character());
        commands.put("print_descending", new CommandPrint_descending());
        commands.put("exit", new CommandExit());
    }

}
