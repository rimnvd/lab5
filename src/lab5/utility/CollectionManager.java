package lab5.utility;

import lab5.data.Color;
import lab5.data.Dragon;
import lab5.data.DragonCharacter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Vector;

public class CollectionManager {
    private LocalDate date;
    private ElementManager elementManager;
    private Vector<Dragon> vector = new Vector<>(0);
    private FileManager fileManager;

    public CollectionManager(ElementManager elementManager, FileManager fileManager) {
        this.elementManager = elementManager;
        this.fileManager = fileManager;
    }

    public void loadCollection() {
        vector = fileManager.readCollection();
        date = LocalDate.now();
    }

    public String collectionType() {
        return vector.getClass().getName();
    }

    public Vector<Dragon> getCollection() {
        return vector;
    }

    public void clear() {
        vector.clear();
    }

    public int collectionSize() {
        return vector.size();
    }

    public LocalDate getDate() {
        return date;
    }

    public void addToCollection(Dragon d) {
        vector.add(d);
    }

    public void removeById(Long id) {
        boolean f = false;
        for (Dragon dragon : vector) {
            if (dragon.getId().equals(id)) {
                vector.remove(dragon);
                f = true;
                System.out.println("Элемент успешно удален из коллекции");
            }
        }
        if (!f) {
            System.out.println("В коллекции нет элемента с таким значением id");
        }
    }

    public int countCharacter(String character) {
        int count = 0;
        for (Dragon dragon : vector) {
            if (dragon.getCharacter().equals(DragonCharacter.valueOf(character.toUpperCase()))) {
                count++;
            }
        }
        return count;
    }

    public void removeLower(Dragon d) {
        int i = 0;
        while (vector.get(0).compareTo(d) < 0 && i < vector.size()) {
            vector.removeElementAt(0);
            i++;
        }
    }


    public boolean isEmpty() {
        return vector.isEmpty();
    }

    public Dragon maxElement() {
        Dragon maxElement = vector.get(0);
        for (Dragon dragon : vector) {
            if (dragon.compareTo(maxElement) > 0) {
                maxElement = dragon;
            }
        }
        return maxElement;
    }

    public void reverseSort() {
        Collections.reverse(vector);
    }


    public void updateById(Long x) {
        boolean f = true;
        for (int i = 0; i < vector.size() && f; i++) {
            if (vector.get(i).getId().equals(x)) {
                Dragon dragon = elementManager.createElement();
                dragon.setId(x);
                vector.set(i, dragon);
                f = false;
            }
        }
    }

    public void removeLast() {
        vector.removeElementAt(vector.size() - 1);
    }

    public void removeByColor(Color color) {
        boolean f = true;
        for (int i = 0; i < vector.size() - 1 && f; i++) {
            if (vector.get(i).getColor().equals(color)) {
                vector.removeElementAt(i);
                f = false;
            }
        }
    }

    public void safeToFile() {
        fileManager.writeCollection(vector);
        System.out.println("Коллекция успешно загружена в файл");
    }


}