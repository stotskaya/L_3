package Model;

public class Item {

    private Long Id;
    private String Name;
    private int Price;
    private Long ItemGroup;
    private String Description;
    private String image_1;
    private String image_2;


    public Item(){};

    public Item(Long id, String name, int price){
        this.Id = id;
        this.Name = name;
        this.Price = price;
    };

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Long getItemGroup() {
        return ItemGroup;
    }

    public void setItemGroup(Long itemGroup) {
        ItemGroup = itemGroup;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
