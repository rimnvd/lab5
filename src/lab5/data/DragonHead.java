package lab5.data;

/**
 * This class is responsible for the head of the dragon.
 */
public class DragonHead {
    private final Integer size;
    private final Double eyesCount;

    public DragonHead(Integer size, Double eyesCount) {
        this.eyesCount = eyesCount;
        this.size = size;
    }

    /**
     * @return The amount of the eyes of the dragon.
     */
    public Double getEyesCount() {
        return eyesCount;
    }

    /**
     * @return The size of the head of the dragon.
     */
    public Integer getSize() {
        return size;
    }


}

