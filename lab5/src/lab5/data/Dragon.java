package lab5.data;

import java.time.LocalDate;

/**
 * This class contains the description of the elements stored in the collection.
 */
public class Dragon {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private long age;
    private Color color;
    private DragonType type;
    private DragonCharacter character;
    private DragonHead head;
    private String call;

    public Dragon(String name,
                  long age,
                  DragonType type,
                  Color color,
                  DragonCharacter character,
                  DragonHead head,
                  Coordinates coordinates,
                  String call) {
        this.id = (long)this.hashCode();
        this.name = name;
        this.age = age;
        this.creationDate = LocalDate.now();
        this.color = color;
        this.type = type;
        this.character = character;
        this.head = head;
        this.coordinates = coordinates;
        this.call = call;
    }

    public Dragon(String name,
                  long age,
                  DragonType type,
                  Color color,
                  DragonCharacter character,
                  Coordinates coordinates,
                  String call) {
        this.id = (long)this.hashCode();
        this.name = name;
        this.age = age;
        this.creationDate = LocalDate.now();
        this.color = color;
        this.type = type;
        this.character = character;
        this.coordinates = coordinates;
        this.call = call;
    }

    /**
     *
     * @return Id of the dragon.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return Name of the dragon.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Coordinates of the dragon.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @return Character of the dragon.
     */
    public DragonCharacter getCharacter() {
        return character;
    }

    /**
     *
     * @return Color of the dragon.
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @return Call of the dragon.
     */
    public String getCall() {
        return call;
    }

    @Override
    public String toString() {
        return getCall();
    }

    /**
     *
     * set the value of the field "id".
     * @param x - the setting value.
     */
    public void setId(Long x) {
        id = x;
    }

    /**
     *
     * @return Creation date of the dragon.
     */
    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return Age of the dragon.
     */
    public long getAge() {
        return age;
    }

    /**
     *
     * @return type of the dragon
     */
    public DragonType getType() {
        return type;
    }

    /**
     *
     * @return head of the dragon
     */
    public DragonHead getHead() {
        return head;
    }

}
