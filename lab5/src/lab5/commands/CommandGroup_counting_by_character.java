package lab5.commands;

import lab5.data.Dragon;
import lab5.data.DragonCharacter;

import java.util.Vector;

/**
 * This class is responsible for the grouping elements of the collection be character
 * and counting the number of the elements in each group.
 */
public class CommandGroup_counting_by_character extends Command {

    Vector<Dragon> cunning = new Vector<>(0);
    Vector<Dragon> good = new Vector<>(0);
    Vector<Dragon> evil = new Vector<>(0);

    /**
     * Executes the command.
     * @param vector Collection.
     */
    @Override
    public void execute(Vector<Dragon> vector, String S) {
        groups(vector);
        count(cunning, good, evil);
    }

    /**
     * Groups elements of the collection by character.
     * @param vector Collection.
     */
    public void groups(Vector<Dragon> vector) {
        for (Dragon dragon : vector) {
            if (dragon.getCharacter().equals(DragonCharacter.GOOD)) {
                good.add(dragon);
            } else if (dragon.getCharacter().equals(DragonCharacter.CUNNING)) {
                cunning.add(dragon);
            } else {
                evil.add(dragon);
            }
        }
    }

    /**
     * Counts the number of the elements in each group.
     * @param vector1 Collection for the elements with character: "CUNNING".
     * @param vector2 Collection for the elements with character: "GOOD".
     * @param vector3 Collection for the elements with character: "CHAOTIC_EVIL".
     */
    public void count(Vector<Dragon> vector1, Vector<Dragon> vector2, Vector<Dragon> vector3) {
        System.out.println("CUNNING: " + vector1.size());
        System.out.println("GOOD: " + vector2.size());
        System.out.println("CHAOTIC_EVIL: " + vector3.size());
    }



}
