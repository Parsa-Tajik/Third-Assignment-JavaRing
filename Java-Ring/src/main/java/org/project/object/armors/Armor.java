package org.project.object.armors;

import org.project.object.Object;

// TODO: UPDATE IMPLEMENTATION
public abstract class Armor implements Object {
    private int defense;
    private int maxDefense;
    private int durability;
    private int maxDurability;

    public Armor(int maxDefense, int maxDurability) {
        this.defense = maxDefense;
        this.maxDefense = maxDurability;
        this.durability = maxDurability;
        this.maxDurability = maxDurability;
    }

    public void repair() {
        defense = maxDefense;
        durability = maxDurability;
    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBroke() {
        if (durability <= 0) {
            defense = 0;
            return true;
        }
        return false;
    }
}
