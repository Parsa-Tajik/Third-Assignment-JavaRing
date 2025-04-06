package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;

public class Axe extends Weapon {

    private static int price = 300;

    public Axe() {
    super(50, 40);
    }

    public void uniqueAbility(ArrayList<Entity> targets) {
        for (Entity target : targets) {
            target.takeDamage(getDamage());
        }
    }

    public static int getPrice() {
        return price;
    }
}
