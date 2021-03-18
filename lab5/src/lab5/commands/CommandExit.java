package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;

/**
 * This class is responsible for the
 */
public class CommandExit extends Command {

    @Override
    public void execute(Vector<Dragon> vector, String S) {
        System.exit(0);
    }
}
