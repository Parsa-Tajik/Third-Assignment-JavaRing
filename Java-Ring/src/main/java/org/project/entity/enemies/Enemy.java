package org.project.entity.enemies;

import org.project.object.weapons.Weapon;
import org.project.entity.Entity;

public abstract class Enemy implements Entity {
    Weapon weapon;
    protected int hp;

    private boolean isGlitched = false;

    public Enemy(int hp, Weapon weapon) {
        this.hp = hp;
        this.weapon = weapon;
    }

    public void attack(Entity target) {
        if (isGlitched) {
            this.takeDamage(getWeapon().getDamage());
            isGlitched = false;
            return;
        }

        if (this instanceof Dragon) {
            target.takeDamageNoArmor(getWeapon().getDamage());
            return;
        }

        target.takeDamage(getWeapon().getDamage());
    }

    @Override
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void takeDamageNoArmor(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void glitch() {
        isGlitched = true;
    }

    public String toString() {
        return "Unknown Enemy!";
    }
}
