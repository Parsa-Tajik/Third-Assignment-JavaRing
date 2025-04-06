package org.project.object.armors;

import org.project.entity.Entity;
import org.project.object.Object;

public abstract class Armor implements Object {
    private int defense;
    private int maxDefence;
    private int durability;
    private int maxDurability;
    private int repairPrice;

    public Armor(int maxDefense, int maxDurability, int repairPrice) {
        this.defense = maxDefense;
        this.maxDefence = maxDefense;
        this.durability = maxDurability;
        this.maxDurability = maxDurability;
        this.repairPrice = repairPrice;
    }

    public void repair() {
        defense = maxDefence;
        durability = maxDurability;
    }

    public void use(Entity target) {

    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public int getRepairPrice() {
        return repairPrice;
    }

    public boolean isBroke() {
        if (durability <= 0) {
            defense = 0;
            return true;
        }
        return false;
    }
}
