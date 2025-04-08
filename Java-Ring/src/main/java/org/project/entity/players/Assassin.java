package org.project.entity.players;

import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Assassin extends Player {
    public Assassin(int hp, Weapon weapon, Armor armor) {
        super(hp, 100, weapon, armor);
    }

    public void useAbility(Enemy target) {
        isInvisible = true;
    }

    public String getAbilityName() {
        return "Invisibility";
    }
}