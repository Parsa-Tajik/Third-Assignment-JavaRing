package org.project.object.weapons;

import org.project.entity.Entity;
import org.project.object.Object;

public abstract class Weapon implements Object {
    private int damage;
    private int abilityPrice;

    public Weapon(int damage, int abilityPrice) {
        this.damage = damage;
        this.abilityPrice = abilityPrice;
    }

    @Override
    public void use(Entity target) {
        target.takeDamage(damage);
    }

    public int getDamage() {
        return damage;
    }
    public int getAbilityCost() {
        return abilityPrice;
    }
}
