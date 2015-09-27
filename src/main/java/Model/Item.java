package model;

public class Item {

    private Long id;
    private String name;
    private int price;
    private Long itemGroup;
    private String description;
    private String image_1;
    private String image_2;


    public Item(){};

    public Item(Long id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }
}
