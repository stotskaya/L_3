package model;

public class Item {

    private Long id;
    private String name;
    private int price;
    private Long itemGroup;
    private String description;
    private String imageOne;
    private String imageTwo;

    public Item(){
    }

    public Item(Long id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(Long itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }
}
