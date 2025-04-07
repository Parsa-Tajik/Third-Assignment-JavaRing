package org.project;

import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Player;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    private static List<Location> locations = new ArrayList<>();
    public static Player player;
    private static Location currentLocation;

    public static void playGame(int level) {
        System.out.println("**ROUND " + level + "**\n");
        createGame(level);
        boolean roundDone = false;
        while(!roundDone) {

            for (Location location : locations) {
                if (location.isPlayerOn) {
                    System.out.println("**********");
                    System.out.println(location);
                    System.out.println("**********");
                }else {
                    System.out.println(location);
                }
                System.out.println();
            }

            playerTurn();

            // checks if the player is alive
            if (!player.isAlive()) {
                System.out.println("Oh, What A Shame! You are dead!");
                System.out.println("You've Done It Until Level " + (level - 1));
                System.out.println();
                roundDone = true;
                Main.die();
            }

            // checks if all locations are clear
            boolean allLocsClear = true;
            for (Location location : locations) {
                if (!location.isClear()){
                    allLocsClear = false;
                }
            }
            if (allLocsClear) { roundDone = true; }
        }
    }

    private static void playerTurn() {
        System.out.println("HP: " + player.getHp());
        System.out.println("Armor Durability: " + player.getArmor().getDurabilityPercentage() + "%");
        System.out.println();
        System.out.println("Enter 1 To Attack.");
        System.out.println("Enter 2 To Move To Another Location.");
        System.out.println("Enter 3 To Open Inventory.");
        int choice = getIntInput(1, 3);

        if (choice == 1) {
            Entity target = currentLocation.getEnemies().get(0);
            player.attack(target);
            if (!target.isAlive()) {
                currentLocation.getEnemies().remove(target);
                System.out.println("Congrats, This Location Is Clear.");
                changeLocation();
            }else {
                enemyTurn();
            }
        }else if (choice == 2) {
            changeLocation();
        }else {
            Inventory.inventory();
        }
    }

    private static void enemyTurn() {
        if (currentLocation.getEnemies().isEmpty()) {
            return;
        }
        int playerPreHp = player.getHp();
        currentLocation.getEnemies().get(0).attack(player);
        int damage = playerPreHp - player.getHp();
        System.out.println(currentLocation.getEnemies().get(0) + " Attacked You On Location " + currentLocation.getName() + "!");
        System.out.println("You Took " + damage + " Damage");
        System.out.println();
    }

    private static void changeLocation() {
        // checks whether there is any available locations to move to
        int availableLocations = 0;
        for (Location location : locations) {
            if (!location.isPlayerOn && !location.isClear()) {
                availableLocations++;
            }
        }
        if (availableLocations == 0) {
            System.out.println("No Available Locations!\n");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the location you wish to move to");
        Location choosedLocation = locations.get(getIntInput(1, locations.size()) - 1);
        while (choosedLocation.isPlayerOn || choosedLocation.isClear()) {
            if (choosedLocation.isPlayerOn) {
                System.out.println("This Is Your Current Location. Move To Another One.");
            } else {
                System.out.println("This Location Is Clear. Move To Another One.");
            }

            choosedLocation = locations.get(getIntInput(1, locations.size()) - 1);
        }

        currentLocation.isPlayerOn = false;
        choosedLocation.isPlayerOn = true;
        currentLocation = choosedLocation;

        enemyTurn();
    }

    private static void createGame(int locCount) {
        // clears all locations in the list from previous rounds
        locations.clear();
        locations = new ArrayList<>();

        for (int i = 0; i < locCount; i++) {
            ArrayList<Enemy> enemies = new ArrayList<>();
            enemies.add(createRandomMonster());

            locations.add(new Location(Integer.toString(i + 1), enemies));
        }

        locations.getFirst().isPlayerOn = true;
        currentLocation = locations.getFirst();
    }

    private static Enemy createRandomMonster() {
        return new Skeleton();
    }

    public static int getIntInput(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while (choice < min || choice > max) {
            System.out.println("Out Of Range");
            choice = sc.nextInt();
        }
        return choice;
    }
}