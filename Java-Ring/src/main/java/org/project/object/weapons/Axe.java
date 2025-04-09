package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;

public class Axe extends Weapon {

    public static int price() {return 300;};
    public static String name() {return "Axe";}

    public Axe() {
    super(name(), 50, price(), 40);
    }

    public void uniqueAbility(ArrayList<Entity> targets) {
        for (Entity target : targets) {
            target.takeDamage(getDamage());
        }
    }
}
