package lab5.utility;

import lab5.data.Color;
import lab5.data.Dragon;
import lab5.data.DragonCharacter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;

/**
 * This class is responsible for the working with the collection.
 */
public class CollectionManager {
    private LocalDate date;
    private final ElementCreation elementCreation;
    private Vector<Dragon> vector = new Vector<>(0);
    private final FileManager fileManager;
    private final HashSet<Long> idSet = new HashSet<>();

    public CollectionManager(ElementCreation elementCreation, FileManager fileManager) {
        this.elementCreation = elementCreation;
        this.fileManager = fileManager;
    }

    /**
     * Loads the collection from the file.
     */
    public void loadCollection() {
        vector = fileManager.readCollection();
        date = LocalDate.now();
    }

    /**
     * Determines type of this collection.
     *
     * @return type of this collection as a String
     */
    public String collectionType() {
        return vector.getClass().getName();
    }

    /**
     * Returns the collection.
     *
     * @return the collection
     */
    public Vector<Dragon> getCollection() {
        return vector;
    }

    /**
     * Removes all of the elements from this collection.
     */
    public void clear() {
        vector.clear();
    }

    /**
     * Returns the number of the components in this collection.
     *
     * @return the number of the components in this collection
     */
    public int collectionSize() {
        return vector.size();
    }

    /**
     * Returns the creation date of this collection.
     *
     * @return the creation date of this collection
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Appends the specified element to the end of this collection.
     */
    public void addToCollection(Dragon dragon) {
        vector.add(dragon);
        dragon.setId(createNewId(dragon.getId()));
        idSet.add(dragon.getId());
    }

    /**
     * Removes the element from this collection with the specified id value.
     * If the collection does not contain the specified element, it is unchanged.
     *
     * @param id id value
     */
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

    /**
     * Returns the number of the components in this collection with specified character value.
     *
     * @param character character value
     * @return the number of the components in this collection with specified character value
     */
    public int countCharacter(String character) {
        int count = 0;
        for (Dragon dragon : vector) {
            if (dragon.getCharacter().equals(DragonCharacter.valueOf(character.toUpperCase()))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Removes from this collection all of the elements that are lower than the specified element.
     *
     * @param d the element to be compared.
     */
    public void removeLower(Dragon d) {
        int i = 0;
        while (vector.get(0).compareTo(d) < 0 && i < vector.size()) {
            vector.removeElementAt(0);
            i++;
        }
    }

    /**
     * Tests if this collection has no components.
     *
     * @return true if this vector has no components; false otherwise
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Returns the maximum component of this collection.
     *
     * @return the maximum component of this collection
     */
    public Dragon maxElement() {
        Dragon maxElement = vector.get(0);
        for (Dragon dragon : vector) {
            if (dragon.compareTo(maxElement) > 0) {
                maxElement = dragon;
            }
        }
        return maxElement;
    }

    /**
     * Sorts the collection in descending order
     */
    public void reverseSort() {
        Collections.sort(vector);
        Collections.reverse(vector);
    }

    /**
     * Updates the element of this collection with the specified if value.
     *
     * @param x id value
     */
    public void updateById(Long x) {
        boolean f = true;
        for (int i = 0; i < vector.size() && f; i++) {
            if (vector.get(i).getId().equals(x)) {
                Dragon dragon = elementCreation.createElement();
                dragon.setId(x);
                vector.set(i, dragon);
                f = false;
            }
        }
    }

    /**
     * Removes the last element from this collection.
     */
    public void removeLast() {
        vector.removeElementAt(vector.size() - 1);
    }

    /**
     * Removes the element with the specified color value from this collection.
     *
     * @param color color value
     */
    public void removeByColor(Color color) {
        boolean f = true;
        for (int i = 0; i < vector.size() - 1 && f; i++) {
            if (vector.get(i).getColor().equals(color)) {
                vector.removeElementAt(i);
                f = false;
            }
        }
    }

    /**
     * Safes this collection to the file.
     */
    public void safeToFile() {
        fileManager.writeCollection(vector);
    }

    /**
     * Checks whether the element with such id value is in the collection; generates new id value if yes.
     *
     * @param x checked id value
     * @return id value
     */
    public Long createNewId(Long x) {
        Long newId = x;
        if (idSet.contains(newId)) {
            if (newId >= Long.MAX_VALUE / 2) {
                while (idSet.contains(newId)) {
                    newId--;
                }
            } else {
                while (idSet.contains(newId)) {
                    newId++;
                }
            }
        }
        return newId;
    }

}