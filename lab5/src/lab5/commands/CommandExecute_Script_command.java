package lab5.commands;

import lab5.data.Dragon;
import lab5.utility.ProgramProcess;


import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * This class is responsible for the executing commands from the script.
 */
public class CommandExecute_Script_command extends Command {

    ProgramProcess programProcess = new ProgramProcess();
    HashMap<String, Command> commands = new HashMap<>();

    /**
     * Executes the command.
     * @param vector collection.
     * @param S The full name of the entered command.
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        try {
            if (checkCommand(S)) {
                System.out.println("Команда не найдена. Введите \"help\" для справки");
            } else {
                int data = 0;
                programProcess.commandsMap(commands);
                InputStreamReader reader = new InputStreamReader(new FileInputStream(argument(S)));
                if (ProgramProcess.path.contains(new File(argument(S)).getAbsolutePath())) {
                    data = -1;
                    System.out.println("Невозможно выполнить команду " + S);
                } else {
                    ProgramProcess.path.add(new File(argument(S)).getAbsolutePath());
                }
                StringBuilder S1 = new StringBuilder();
                while (data != -1) {
                    data = reader.read();
                    if ((char)data != '\n' && data != -1) {
                        S1.append((char)data);
                    }
                    else {
                        if (data != -1) {
                            S1.deleteCharAt(S1.length() - 1);
                        }
                        String S2 = S1.toString();
                        if (commands.containsKey(new Command().commandName(S2).toLowerCase())) {
                            System.out.println(S2);
                            System.out.println();
                            commands.get(new Command().commandName(S2).toLowerCase()).execute(vector, S2);
                        } else {
                            System.out.println("Команда " + S2 + " не найдена");
                        }
                        S1.delete(0, S1.length());
                        System.out.println();
                        if (data == -1) {
                            ProgramProcess.path.remove(ProgramProcess.path.size() - 1);
                            System.out.println("Выполнение скрипта " + argument(S) + " завершено" );
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Невозможно выполнить данную команду, так как указанный файл не найден");
        } catch (IOException ex) {
            System.out.println("Невозможно выполнить данную команду");
        }
    }

    /**
     * Checks whether the name of the argument is right or not.
     * @param S The full name of the entered command.
     * @return True if the name is not right.
     */
    @Override
    public boolean checkCommand(String S) {
        Pattern pattern = Pattern.compile("^execute_script(\\s\\S+)$", Pattern.CASE_INSENSITIVE);
        return !pattern.matcher(S).find();
    }



}
