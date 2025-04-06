package org.project.entity.players;

import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Knight extends Player {
    public Knight(String name, int hp, int mp, Weapon weapon, Armor armor) {
        super(name, hp, 100, mp, weapon, armor);
    }
}
