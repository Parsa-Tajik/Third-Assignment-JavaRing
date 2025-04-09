package org.project.entity.enemies;

import org.project.Manager;
import org.project.entity.Entity;
import org.project.object.weapons.Sword;


public class Skeleton extends Enemy {
    public boolean isDeadOnce = false;

    public Skeleton() {
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
            if (!isDeadOnce) {
                System.out.println(toString() + " Is Being Revived...");
                Manager.wait(2000);

                isDeadOnce = true;
                heal(50);
                System.out.println("Skeleton Became A Revenant And Was Healed!");
                Manager.wait(3000);
            }else{
                return false;
            }
        }
        return true;
    }

    public String getAbilityName() {
        return "Resurrection";
    }

    public String toString()
    {
        return "Skeleton💀" + " (" + this.getHp() + ")";
    }

}
