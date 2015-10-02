package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> items;

    public Cart(){
        items = new ArrayList<Item>();
    }

    public int getAmountPrice() {
        int sum = 0;
        for (Item item: items){
            sum =+ item.getPrice();
        }
        return sum;
    }

    public int getAmountItems() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        items.add(item);
    }

}
