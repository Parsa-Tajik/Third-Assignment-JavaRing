package org.project.entity.players;

import org.project.Manager;
import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;
import org.project.object.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Entity {
    Weapon weapon;
    Armor armor;
    private int hp;
    private int maxHP;

    private int coins = 20000;

    protected boolean isInvisible = false;

    public List<Weapon> weapons = new ArrayList<>();
    public List<Armor> armors = new ArrayList<>();
    public List<Consumable> consumables = new ArrayList<>();

    public Player(int hp, int maxHp, Weapon weapon, Armor armor) {
        this.hp = hp;
        this.maxHP = maxHp;

        this.weapon = weapon;
        weapons.add(weapon);
        this.armor = armor;
        armors.add(armor);
    }

    @Override
    public void attack(Entity target) {
        System.out.println("Attacking " + target + "...");
        Manager.wait(1500);

        int preHp = target.getHp();
        target.takeDamage(weapon.getDamage());
        System.out.println("You Attacked " + target);
        System.out.println(target + " Took " + (preHp - target.getHp()) + " Damage.");
        System.out.println();
        Manager.wait(3000);
    }

    public abstract void useAbility(Enemy target);

    @Override
    public void defend() {

    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void takeDamage(int damage) {
        if (isInvisible) {
            isInvisible = false;
            return;
        }

        hp -= damage;
        armor.use(this, damage);
        if (hp <= 0) { hp = 0; }
    }

    public void takeDamageNoArmor(int damage) {
        if (isInvisible) {
            isInvisible = false;
            return;
        }
        hp -= damage;
        if (hp <= 0) { hp = 0; }
    }

    @Override
    public void heal(int health) {
        hp += health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    public void changeWeapon(Weapon weapon) {
        System.out.println("Changing Weapon To " + weapon.getName() + "...");
        Manager.wait(2000);

        this.weapon = weapon;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void buyWeapon(Weapon weapon) {
        for (Weapon we : weapons) {
            if (we.getName().equals(weapon.getName())) {
                System.out.println("You Already Own " + weapon.getName() + "\n");
                Manager.wait(3000);
                return;
            }
        }

        if (weapon.getPrice() > coins) {
            System.out.println("Not Enough Coins To Buy " + weapon.getName() + "\n");
            Manager.wait(3000);
            return;
        }

        System.out.println("Buying " + weapon.getName() + "...");
        Manager.wait(2000);

        coins -= weapon.getPrice();
        weapons.add(weapon);
        System.out.println(weapon.getName() + " Purchased Successfully.\n");
        Manager.wait(3000);
    }

    public void buyConsumable(Consumable consumable) {
        if (consumable.getPrice() > coins) {
            System.out.println("Not Enough Coins To Buy " + consumable.getName() + "\n");
            Manager.wait(3000);
            return;
        }

        System.out.println("Buying " + consumable.getName() + "...");
        Manager.wait(2000);

        coins -= consumable.getPrice();
        consumables.add(consumable);
        System.out.println(consumable.getName() + " Purchased Successfully.\n");
        Manager.wait(3000);
    }

    public void repairArmor() {
        if (armor.getDurability() == armor.getMaxDurability()) {
            System.out.println("Your Armor Is Already Fully Repaired\n");
            Manager.wait(3000);
            return;
        }

        if (armor.getRepairPrice() > coins) {
            System.out.println("Not Enough Coins To Repair Armor\n");
            Manager.wait(3000);
            return;
        }

        System.out.println("Repairing Armor...");
        Manager.wait(2000);

        coins -= armor.getRepairPrice();
        armor.repair();
        System.out.println("Armor Repaired Successfully!\n");
        Manager.wait(3000);
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public boolean isInvisible() {
        return isInvisible;
    }
}
