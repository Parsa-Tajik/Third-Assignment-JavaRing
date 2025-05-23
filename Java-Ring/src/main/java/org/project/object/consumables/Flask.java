package org.project.object.consumables;

import org.project.Manager;
import org.project.entity.Entity;
import org.project.object.Object;

public class Flask extends Consumable {

    public static int price() {return 50;}

    public Flask() {
        super("Flask 20%", price());
    }
    @Override
    public void use(Entity target) {
        if (target.getHp() == target.getMaxHP()) {
            System.out.println("Your Hp Is Already Full!");
            Manager.wait(1500);
            return;
        }

        System.out.println("Healing...");
        Manager.wait(1500);

        int preHp = target.getHp();
        target.heal(target.getMaxHP() / 5);
        System.out.println("Your Hp Increased By " + (target.getHp() - preHp));
        System.out.println();
        Manager.wait(2000);
        Manager.player.consumables.remove(this);
    }

    public String toString() {
        return "Flask: 20%";
    }
}
