package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;

// TODO: UPDATE IMPLEMENTATION
public class Skeleton extends Enemy {
    public Skeleton() {
        super(100, 100, new Sword());
    }

    public void attack(Entity target) {

    }
    public void defend() {

    }
    public void takeDamage(int damage) {

    }
    public int getMaxHP() {
        return 0;
    }
    public void heal(int health) {

    }
    public void fillMana(int mana) {

    }
    public int getMaxMP() {
        return 0;
    }
    public int getMP() {
        return 0;
    }

    public String toString()
    {
        return "Skeleton💀";
    }

}
