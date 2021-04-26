package lab5.commands;

import lab5.utility.CollectionManager;


/**
 * This class is responsible for the grouping elements of the collection by character
 * and counting the number of the elements in each group.
 */
public class CommandGroupCountingByCharacter extends Command {
    private final CollectionManager collectionManager;

    public CommandGroupCountingByCharacter(CollectionManager collectionManager) {
        super("group_counting_by_character");
        this.collectionManager = collectionManager;
    }


    /**
     * Executes the command.
     *
     * @param S the full name of the entered command
     */
    @Override
    public void execute(String S) {
        if (checkCommand(S)) {
            int good;
            int cunning;
            int chaotic_evil;
            good = collectionManager.countCharacter("GOOD");
            cunning = collectionManager.countCharacter("CUNNING");
            chaotic_evil = collectionManager.countCharacter("CHAOTIC_EVIL");
            System.out.println("CUNNING: " + cunning);
            System.out.println("GOOD: " + good);
            System.out.println("CHAOTIC_EVIL: " + chaotic_evil);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }


}
