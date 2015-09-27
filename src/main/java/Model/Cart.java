package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> Items;

    public Cart(){
        Items = new ArrayList<Item>();
    }

    public int getAmountPrice() {
        int sum = 0;
        for (Item item:Items){
            sum =+ item.getPrice();
        }
        return sum;
    }

    public int getAmountItems() {
        return Items.size();
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public void addItem(Item item){
        Items.add(item);
    }

}
