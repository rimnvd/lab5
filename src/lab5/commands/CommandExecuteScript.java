package lab5.commands;

import lab5.utility.CollectionManager;
import lab5.utility.ProgramProcess;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * This class is responsible for the executing commands from the script.
 */
public class CommandExecuteScript extends Command {
    private final ArrayList<String> path;
    private final HashMap<String, Command> commands = new HashMap<>();
    private final CollectionManager collectionManager;
    private final ProgramProcess programProcess;

    public CommandExecuteScript(CollectionManager collectionManager, ProgramProcess programProcess, ArrayList<String> path) {
        super("execute_script");
        this.collectionManager = collectionManager;
        this.programProcess = programProcess;
        this.path = path;
    }

    /**
     * Executes the command.
     *
     * @param EnteredCommand the full name of the entered command
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            programProcess.createCommandsMap(commands);
            File file = new File(argument(EnteredCommand));
            if (file.exists() && !file.canRead()) {
                System.out.println("Невозможно выполнить данную команду, так как у указанного файла отсутвуют права на чтение");
            } else {
                try {
                    int data = 0;
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                    if (path.contains(file.getAbsolutePath())) {
                        data = -1;
                        System.out.println("Невозможно выполнить команду " + EnteredCommand);
                    } else {
                        path.add((file).getAbsolutePath());
                    }
                    StringBuilder CommandName = new StringBuilder();
                    while (data != -1) {
                        data = reader.read();
                        if ((char) data != '\n' && data != -1) {
                            CommandName.append((char) data);
                        } else {
                            if (data != -1) {
                                CommandName.deleteCharAt(CommandName.length() - 1);
                            }
                            String FullCommandName = CommandName.toString().trim();
                            if (commands.containsKey(new Command().commandName(FullCommandName).toLowerCase())) {
                                System.out.println(FullCommandName);
                                System.out.println();
                                commands.get(new Command().commandName(FullCommandName).toLowerCase()).execute(FullCommandName);
                            } else {
                                System.out.println("Команда " + FullCommandName + " не найдена");
                            }
                            CommandName.delete(0, CommandName.length());
                            System.out.println();
                            if (data == -1) {
                                path.remove(path.size() - 1);
                                System.out.println("Выполнение скрипта " + argument(EnteredCommand) + " завершено");
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Указанный файл не найден");
                } catch (IOException e) {
                    System.out.println("Ошибка ввода-вывода");
                }
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }

    /**
     * Checks whether the name of the argument is right or not.
     *
     * @param EnteredCommand the full name of the entered command.
     * @return true if the name is not right; false otherwise
     */
    @Override
    public boolean checkCommand(String EnteredCommand) {
        Pattern pattern = Pattern.compile("^execute_script(\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(EnteredCommand).find();
    }


}
