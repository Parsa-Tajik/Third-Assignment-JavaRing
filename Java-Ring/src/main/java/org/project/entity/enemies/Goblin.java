package org.project.entity.enemies;

import org.project.object.weapons.Sword;


public class Goblin extends Enemy {

    public Goblin() {
        super(100, new Sword());
    }

    public void defend() {

    }

    public int getMaxHP() {
        return 100;
    }

    public void heal(int health) {
        hp += health;
    }

    public int getMaxMP() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        if (hp <= 0) {
            return false;
        }
        return true;
    }

    public String getAbilityName() {
        return "No Ability";
    }

    public String toString()
    {
        return "Goblin\uD83D\uDC79" + " (" + this.getHp() + ")";
    }

}
