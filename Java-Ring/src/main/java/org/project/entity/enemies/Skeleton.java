package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;

// TODO: UPDATE IMPLEMENTATION
public class Skeleton extends Enemy {
    public boolean isDeadOnce = false;

    public Skeleton() {
        super(100, 100, new Sword());
    }

    public void attack(Entity target) {
        target.takeDamage(super.getWeapon().getDamage());
    }

    public void defend() {

    }

    public int getMaxHP() {
        return 100;
    }

    public void heal(int health) {
        hp += health;
    }

    public void fillMana(int mana) {

    }

    public int getMaxMP() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        if (hp <= 0) {
            if (!isDeadOnce) {
                isDeadOnce = true;
                heal(50);
                System.out.println("Skeleton became a revenant and was healed!");
            }else{
                return false;
            }
        }
        return true;
    }

    public String toString()
    {
        return "Skeleton💀" + " (" + this.getHp() + ")";
    }

}
