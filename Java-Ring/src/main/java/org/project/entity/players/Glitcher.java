package org.project.entity.players;

import org.project.Manager;
import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

import java.util.concurrent.TimeUnit;

public class Glitcher extends Player {
    public Glitcher(int hp, Weapon weapon, Armor armor) {
        super(hp, 100, weapon, armor);
    }

    public void useAbility(Enemy target) {
        System.out.println(target + " Is Glitched For The Next Round...");
        System.out.println();
        target.glitch();

        Manager.wait(3000);
    }

    public String getAbilityName() {
        return "Glitch";
    }
}