package org.project;

import org.project.entity.players.Player;
import org.project.object.armors.Armor;
import org.project.object.consumables.Consumable;
import org.project.object.consumables.Flask;
import org.project.object.weapons.Axe;
import org.project.object.weapons.Weapon;

public class Inventory {

    public static void inventory() {
        boolean exit = false;

        while (!exit) {
            System.out.println("**INVENTORY**");
            System.out.println("1. Weapons");
            System.out.println("2. Armors");
            System.out.println("3. Consumables");
            System.out.println("0. Return To Game");

            int choice = Manager.getIntInput(0, 3);
            switch (choice) {
                case 1:
                    weapons();
                    break;
                case 2:
                    armors();
                    break;
                case 3:
                    consumables();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private static void weapons() {
        boolean exit = false;

        while (!exit) {
            System.out.println("**WEAPONS**");

            int count = 1;
            for (Weapon weapon : Manager.player.weapons) {
                System.out.print(count + ". " + weapon.getName());
                if (weapon.getName().equals(Manager.player.getWeapon().getName())) {
                    System.out.print(" (Selected)");
                }
                System.out.println();
                count++;
            }
            System.out.println(count + ". Buy New Weapon");
            System.out.println("0. Return");

            int choice = Manager.getIntInput(0, count);
            if (choice == count) {
                buyWeapons();
            } else if (choice == 0) {
                exit = true;
            } else {
                Manager.player.changeWeapon(Manager.player.weapons.get(choice - 1));
                exit = true;
            }
        }
    }
    private static void buyWeapons() {
        boolean exit = false;

        while (!exit) {
            System.out.println("**BUY WEAPONS**");
            System.out.println("* Coins: " + Manager.player.getCoins());
            System.out.println("1. Axe (" + Axe.price() + " coins)");
            System.out.println("0. Return");

            int choice = Manager.getIntInput(0, 1);
            switch (choice) {
                case 1:
                    Manager.player.buyWeapon(new Axe());
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private static void armors() {
        boolean exit = false;

        while (!exit) {
            System.out.println("**ARMORS**");

            System.out.println("* Coins: " + Manager.player.getCoins());
            System.out.println("* Current Armor: " + Manager.player.getArmor()
                    + " (" + Manager.player.getArmor().getDurabilityPercentage() + "%)"
                    + " Repair Cost: " + Manager.player.getArmor().getRepairPrice());

            System.out.println("1. Repair Armor");
            System.out.println("0. Return");

            int choice = Manager.getIntInput(0, 1);
            if (choice == 1) {
                Manager.player.repairArmor();
            }
            exit = true;

//            int count = 1;
//            for (Armor armor : Manager.player.armors) {
//                System.out.print(count + ". " + armor);
//                if (armor.toString().equals(Manager.player.getArmor().toString())) {
//                    System.out.print(" (Selected)");
//                }
//                System.out.println();
//                count++;
//            }
//            System.out.println(count + ". Buy New Armor");
//            System.out.println("0. Return");
//
//            int choice = Manager.getIntInput(0, count);
//            if (choice == count) {
//                buyWeapons();
//            } else if (choice == 0) {
//                exit = true;
//            } else {
//                Manager.player.changeArmor(Manager.player.armors.get(choice - 1));
//                exit = true;
//            }
        }
    }

    private static void consumables() {
        boolean exit = false;

        while (!exit) {
            System.out.println("**CONSUMABLES**");

            int count = 1;
            if (Manager.player.consumables.isEmpty()) {
                System.out.println("* You Don't Have Any Consumables Yet :(");
            }else {
                for (Consumable consumable : Manager.player.consumables) {
                    System.out.println(count + ". " + consumable);
                    count++;
                }
            }
            System.out.println(count + ". Buy New Consumable");
            System.out.println("0. Return");

            int choice = Manager.getIntInput(0, count);
            if (choice == count) {
                buyConsumables();
            } else if (choice == 0) {
                exit = true;
            } else {
                Manager.player.consumables.get(choice - 1).use(Manager.player);
                exit = true;
            }
        }
    }
    private static void buyConsumables() {
        boolean exit = false;

        while (!exit) {
            System.out.println("**BUY CONSUMABLES**");
            System.out.println("* Coins: " + Manager.player.getCoins());
            System.out.println("1. Flask 20% (" + Flask.price() + " coins)");
            System.out.println("0. Return");

            int choice = Manager.getIntInput(0, 1);
            switch (choice) {
                case 1:
                    Manager.player.buyConsumable(new Flask());
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }
}
