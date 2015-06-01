package Model;


import java.util.ArrayList;
import java.util.List;

public class ItemsBeans {

    private Item item = new Item();
    private List<Item> items = new ArrayList<Item>();

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
