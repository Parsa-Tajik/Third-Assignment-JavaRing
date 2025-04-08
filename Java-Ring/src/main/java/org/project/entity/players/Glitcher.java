package org.project.entity.players;

import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Glitcher extends Player {
    public Glitcher(int hp, Weapon weapon, Armor armor) {
        super(hp, 100, weapon, armor);
    }

    public void useAbility(Enemy target) {
        target.glitch();
    }

    public String getAbilityName() {
        return "Glitch";
    }
}