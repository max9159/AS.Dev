package Model;

/**
 * Created by Max on 2016/1/13.
 */
public class FoodItem {
    private String imgUrl;
    private String name;
    private String desc;
    private String location;
    private int rating;

    public FoodItem() {

    }

    public FoodItem(String name, String imgUrl) {
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getLocation() {
        return location;
    }

    public int getRating() {
        return rating;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
