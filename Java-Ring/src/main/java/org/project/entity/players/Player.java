package org.project.entity.players;

import org.project.entity.Entity;
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
    private int mp;
    private int maxMP;

    private int coins = 20000;

    public List<Weapon> weapons = new ArrayList<>();
    public List<Armor> armors = new ArrayList<>();
    public List<Consumable> consumables = new ArrayList<>();

    public Player(int hp, int maxHp, int mp, Weapon weapon, Armor armor) {
        this.hp = hp;
        this.maxHP = maxHp;
        this.mp = mp;

        this.weapon = weapon;
        weapons.add(weapon);
        this.armor = armor;
        armors.add(armor);
    }

    @Override
    public void attack(Entity target) {
        int preHp = target.getHp();
        target.takeDamage(weapon.getDamage());
        System.out.println("You Attacked " + target);
        System.out.println(target + " Took " + (preHp - target.getHp()) + " Damage.");
        System.out.println();
    }

    @Override
    public void defend() {

    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void takeDamage(int damage) {
        int finalDamage;
        if (hp >= 0) {
            finalDamage = damage;
        }else {
            finalDamage = hp;
        }

        hp -= finalDamage;
        armor.use(this, finalDamage);
    }

    @Override
    public void heal(int health) {
        hp += health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    @Override
    public void fillMana(int mana) {
        mp += mana;
        if (mp > maxMP) {
            mp = maxMP;
        }
    }

    public void changeWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public int getMp() {
        return mp;
    }

    @Override
    public int getMaxMP() {
        return maxMP;
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
                return;
            }
        }

        if (weapon.getPrice() > coins) {
            System.out.println("Not Enough Coins To Buy " + weapon.getName() + "\n");
            return;
        }

        coins -= weapon.getPrice();
        weapons.add(weapon);
        System.out.println(weapon.getName() + " Purchased Successfully.\n");
    }

    public void buyConsumable(Consumable consumable) {
        if (consumable.getPrice() > coins) {
            System.out.println("Not Enough Coins To Buy " + consumable.getName() + "\n");
            return;
        }

        coins -= consumable.getPrice();
        consumables.add(consumable);
        System.out.println(consumable.getName() + " Purchased Successfully.\n");
    }

    public void repairArmor() {
        if (armor.getDurability() == armor.getMaxDurability()) {
            System.out.println("Your Armor Is Already Fully Repaired\n");
            return;
        }

        if (armor.getRepairPrice() > coins) {
            System.out.println("Not Enough Coins To Repair Armor\n");
            return;
        }

        coins -= armor.getRepairPrice();
        armor.repair();
        System.out.println("Armor Repaired Successfully!\n");
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }
}
