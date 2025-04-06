package org.project.object.consumables;

import org.project.entity.Entity;
import org.project.object.Object;

public class Flask extends Consumable {

    @Override
    public void use(Entity target) {
        target.heal(target.getMaxHP() / 5);
    }

    public String toString() {
        return "Flask: 20%";
    }
}
