package lab5.commands;

import lab5.data.Dragon;

import java.util.Vector;

/**
 * This class is responsible for giving information about the elements in the collection
 * and their constituents. The elements are showed in descending order.
 */
public class CommandPrint_descending extends Command {

    @Override
    public void execute(Vector<Dragon> vector, String S) {
        if (vector.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (int i = vector.size()-1; i >= 0; i--) {
                System.out.println(vector.get(i));
                System.out.println("\tId: " + vector.get(i).getId());
                System.out.println("\tДата создания: " + vector.get(i).getCreationDate());
                System.out.println("\tИмя: " + vector.get(i).getName());
                System.out.println("\tВозраст: " + vector.get(i).getAge());
                System.out.println("\tЦвет: " + vector.get(i).getColor());
                System.out.println("\tТип: " + vector.get(i).getType());
                System.out.println("\tХарактер: " + vector.get(i).getCharacter());
                System.out.println("\tРазмер головы: " + vector.get(i).getHead().getSize());
                System.out.println("\tКоличество глаз: " + vector.get(i).getHead().getEyesCount());
                System.out.println("\tКоординаты: ");
                System.out.println("\t\tx = " + vector.get(i).getCoordinates().getX());
                System.out.println("\t\ty = " + vector.get(i).getCoordinates().getY());
                if (i != vector.size() - 1) {
                    System.out.println();
                }
            }
        }
    }
}
