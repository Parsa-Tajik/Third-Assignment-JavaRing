package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;

public class Sword extends Weapon {

    private static int price = 0;

    public Sword() {
    super(30, 20);
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
