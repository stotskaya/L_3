package Model;

public class Item {

    private Long Id;
    private String Name;
    private int Price;

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
}
