package org.project.entity.players;

import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Knight extends Player {
    public Knight(int hp, int mp, Weapon weapon, Armor armor) {
        super(hp, 100, mp, weapon, armor);
    }
}
