package org.project.entity.players;

import org.project.Manager;
import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Knight extends Player {
    public Knight(int hp, Weapon weapon, Armor armor) {
        super(hp, 100, weapon, armor);
    }

    public void useAbility(Enemy target) {
        System.out.println("You Are Using Strong Kick...");
        Manager.wait(1500);

        int preHp = target.getHp();
        target.takeDamage((int)(weapon.getDamage() * 1.5));
        System.out.println("You Attacked " + target + ", Using " + getAbilityName());
        System.out.println(target + " Took " + (preHp - target.getHp()) + " Damage.");
        System.out.println();
        Manager.wait(3000);
    }

    public String getAbilityName() {
        return "Strong Kick";
    }
}