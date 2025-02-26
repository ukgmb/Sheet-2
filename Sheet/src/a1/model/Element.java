package a1.model;

/**
 * Represents all the elements in @code{Game}.
 *
 * @author ukgmb
 */
public enum Element {

    /**
     * The element of water.
     */
    WATER(1),
    /**
     * The element of fire.
     */
    FIRE(2),
    /**
     * The element of earth.
     */
    EARTH(0),
    /**
     * No specific element.
     */
    NORMAL(3);

    private static final String PRINTABLE_NAME = "element";

    private final int beatsElementOrdinal;

    Element(int beatsElementOrdinal) {
        this.beatsElementOrdinal = beatsElementOrdinal;
    }

    /**
     * Compares the two given elements and returns the better element.
     * @param element1 Element 1
     * @param element2 Element 2
     * @return The better element
     */
    public static Element compare(Element element1, Element element2) {
        if (element1 == NORMAL || element2 == NORMAL || element1 == element2) {
            return NORMAL;
        } else if (element1.beatsElementOrdinal == element2.ordinal()) {
            return element1;
        }
        return element2;
    }

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }

    public int getInt() {
        return this.beatsElementOrdinal;
    }
}
