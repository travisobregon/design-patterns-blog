package database;

import java.util.List;

public class Collection<T> {
    private List<T> items;

    public Collection() {
        this.items = null;
    }

    public Collection(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void addItem(T item) {
        this.items.add(item);
    }
}
