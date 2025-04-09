package org.project.object.consumables;

import org.project.Manager;
import org.project.entity.Entity;

public class Flask2 extends Consumable {

    public static int price() {return 100;}

    public Flask2() {
        super("Flask 50%", price());
    }
    @Override
    public void use(Entity target) {
        if (target.getHp() == target.getMaxHP()) {
            System.out.println("Your Hp Is Already Full!");
            return;
        }

        System.out.println("Healing...");
        Manager.wait(1500);

        int preHp = target.getHp();
        target.heal(target.getMaxHP() / 2);
        System.out.println("Your Hp Increased By " + (target.getHp() - preHp));
        System.out.println();
        Manager.wait(2000);
        Manager.player.consumables.remove(this);
    }

    public String toString() {
        return "Flask: 50%";
    }
}
