package lab5.commands;


/**
 * This class is responsible for the
 */
public class CommandExit extends Command {

    public CommandExit() {
        super("exit");
    }

    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            System.exit(0);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
