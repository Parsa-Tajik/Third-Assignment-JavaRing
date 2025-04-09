package org.project.entity.players;

import org.project.Manager;
import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

import java.util.concurrent.TimeUnit;

public class Wizard extends Player {
    public Wizard(int hp, Weapon weapon, Armor armor) {
        super(hp, 100, weapon, armor);
    }

    public void useAbility(Enemy target) {
        System.out.println("You Are Healing And " + target + " Is Taking Damage...");
        target.takeDamage(20);
        Manager.player.heal(30);
        Manager.wait(3000);

        System.out.println("Done! You Healed By 30 And " + target + " Took 20 Damage.");
        System.out.println();
        Manager.wait(3000);
    }

    public String getAbilityName() {
        return "Special Spell";
    }
}