package org.project.object.weapons;

import org.project.entity.Entity;
import org.project.object.Object;

public abstract class Weapon implements Object {
    private String name;
    private int damage;
    private int price;
    private int abilityPrice;

    public Weapon(String name, int damage, int price, int abilityPrice) {
        this.name = name;
        this.damage = damage;
        this.price = price;
        this.abilityPrice = abilityPrice;
    }

    @Override
    public void use(Entity target) {
        target.takeDamage(damage);
    }

    public int getDamage() {
        return damage;
    }
    public int getAbilityCost() {
        return abilityPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
