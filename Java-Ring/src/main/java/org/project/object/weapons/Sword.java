package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;

public class Sword extends Weapon {

    public static int price() {return 0;};
    public static String name() {return "Sword";}

    public Sword() {
    super(name(), 30, price(), 20);
    }

    public void uniqueAbility(ArrayList<Entity> targets) {
        for (Entity target : targets) {
            target.takeDamage(getDamage());
        }
    }
}
