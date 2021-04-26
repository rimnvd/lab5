package lab5.commands;

import lab5.utility.CollectionManager;
import lab5.utility.Console;

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
    private final Console console;

    public CommandExecuteScript(CollectionManager collectionManager, Console console, ArrayList<String> path) {
        super("execute_script");
        this.collectionManager = collectionManager;
        this.console = console;
        this.path = path;
    }

    /**
     * Executes the command.
     *
     * @param S the full name of the entered command
     */
    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            console.createCommandsMap(commands);
            File file = new File(argument(S));
            if (file.exists() && !file.canRead()) {
                System.out.println("Невозможно выполнить данную команду, так как у указанного файла отсутвуют права на чтение");
            } else {
                try {
                    int data = 0;
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                    if (path.contains(file.getAbsolutePath())) {
                        data = -1;
                        System.out.println("Невозможно выполнить команду " + S);
                    } else {
                        path.add((file).getAbsolutePath());
                    }
                    StringBuilder S1 = new StringBuilder();
                    while (data != -1) {
                        data = reader.read();
                        if ((char) data != '\n' && data != -1) {
                            S1.append((char) data);
                        } else {
                            if (data != -1) {
                                S1.deleteCharAt(S1.length() - 1);
                            }
                            String S2 = S1.toString().trim();
                            if (commands.containsKey(new Command().commandName(S2).toLowerCase())) {
                                System.out.println(S2);
                                System.out.println();
                                commands.get(new Command().commandName(S2).toLowerCase()).execute(S2);
                            } else {
                                System.out.println("Команда " + S2 + " не найдена");
                            }
                            S1.delete(0, S1.length());
                            System.out.println();
                            if (data == -1) {
                                path.remove(path.size() - 1);
                                System.out.println("Выполнение скрипта " + S + " завершено");
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
     * @param S the full name of the entered command.
     * @return true if the name is not right; false otherwise
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^execute_script(\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(S).find();
    }


}
