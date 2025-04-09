package org.project.object.consumables;

import org.project.entity.Entity;
import org.project.object.Object;

public abstract class Consumable implements Object {
    private String name;
    private int price;

    public Consumable(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public abstract void use(Entity target);

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
