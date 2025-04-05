package org.project.entity.enemies;

import org.project.object.weapons.Weapon;
import org.project.entity.Entity;

// TODO: UPDATE IMPLEMENTATION
public abstract class Enemy implements Entity {
    Weapon weapon;
    private int hp;
    private int mp;

    public Enemy(int hp, int mp, Weapon weapon) {
        this.hp = hp;
        this.mp = mp;

        this.weapon = weapon;
    }

    @Override
    public void takeDamage(int damage) {
        hp -= damage;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String toString() {
        return "Unknown Enemy!";
    }
}
