package xuenn.lesson_4;

/**
 * Created by SsuChi on 1/5/2016.
 */
public class CardItem {
    private int backgroundID;
    private String name;

    public int getBackgroundID() {
        return backgroundID;
    }

    public CardItem setBackgroundID(int backgroundID) {
        this.backgroundID = backgroundID;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardItem setName(String name) {
        this.name = name;
        return this;
    }
}
