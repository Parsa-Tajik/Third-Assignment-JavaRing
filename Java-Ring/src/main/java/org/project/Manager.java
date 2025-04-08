package org.project;

import org.project.entity.Entity;
import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Assassin;
import org.project.entity.players.Glitcher;
import org.project.entity.players.Player;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Manager {

    private static int level;

    private static List<Location> locations = new ArrayList<>();
    public static Player player;
    private static Location currentLocation;
    private static int abilityTurnCount = 0;

    public static void playGame(int l) {
        level = l;
        System.out.println("Loading Game...\n\n");
        wait(2000);

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
            if (allLocsClear) {
                int rewardCoins = level * 50;
                player.addCoins(rewardCoins);
                System.out.println("Congrats! You Earned " + rewardCoins + " Coins For Clearing All Locations At Round " + level);
                System.out.println("Be Ready To Fight In The Next Round.");
                System.out.println();
                wait(2000);
                roundDone = true;
            }
        }
    }

    private static void playerTurn() {


        System.out.println("HP: " + player.getHp());
        System.out.println("Armor Durability: " + player.getArmor().getDurabilityPercentage() + "%");
        System.out.println("Coins: " + player.getCoins());
        System.out.println();
        System.out.println("Enter 1 To Attack.");
        System.out.println("Enter 2 To Use Your Ability: " + player.getAbilityName() + " (" + isAbilityAvailableStr() + ")");
        System.out.println("Enter 3 To Move To Another Location.");
        System.out.println("Enter 4 To Open Inventory.");
        int choice = getIntInput(1, 4);

        if (choice == 1) {
            Entity target = currentLocation.getEnemies().get(0);
            player.attack(target);

            if (!target.isAlive()) {
                currentLocation.getEnemies().remove(target);
                System.out.println("Congrats, This Location Is Clear.");

                killReward(target);

                changeLocation();
            }else {
                enemyTurn();
            }
        }else if (choice == 2) {
            if (!isAbilityAvailable()) {
                System.out.println("You Can't Use Your Ability At This Time!\n");
                wait(3000);
                return;
            }

            Enemy target = currentLocation.getEnemies().get(0);
            player.useAbility(target);
            abilityTurnCount = 0;

            if (!(player instanceof Assassin || player instanceof Glitcher)) {
                if (!target.isAlive()) {
                    currentLocation.getEnemies().remove(target);
                    System.out.println("Congrats, This Location Is Clear.");

                    killReward(target);

                    changeLocation();
                } else {
                    enemyTurn();
                }
            }
        }else if (choice == 3) {
            changeLocation();
        }else {
            Inventory.inventory();
        }
    }

    private static void enemyTurn() {
        if (currentLocation.getEnemies().isEmpty()) {
            return;
        }
        System.out.println(currentLocation.getEnemies().get(0) + " Attacking...");
        wait(2000);

        int playerPreHp = player.getHp();
        currentLocation.getEnemies().get(0).attack(player);
        int damage = playerPreHp - player.getHp();
        System.out.println(currentLocation.getEnemies().get(0) + " Attacked You On Location " + currentLocation.getName() + "!");
        System.out.println("You Took " + damage + " Damage.\n");
        wait(3000);

        abilityTurnCount++;
    }

    private static void enemySelfAttack() {
        if (currentLocation.getEnemies().isEmpty()) {
            return;
        }
        Entity target = currentLocation.getEnemies().get(0);
        int enemyPreHp = target.getHp();
        target.attack(target);
        int damage = enemyPreHp - target.getHp();
        System.out.println("The Glitched " + target + " Attacked Itself On Location " + currentLocation.getName() + ", Fool :)");
        System.out.println(target + " Took " + damage + " Damage.");
        wait(3000);
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
            wait(2000);
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

        System.out.println("Moving To New Location...");
        wait(2000);

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
        Random rand = new Random();


        double easyProbability;
        double mediumProbability;
        double hardProbability;

        if (level < 5) {
            easyProbability = 0.75;
            mediumProbability = 0.20;
            hardProbability = 0.05;
        } else if (level < 10) {
            easyProbability = 0.55;
            mediumProbability = 0.30;
            hardProbability = 0.15;
        } else if (level < 14) {
            easyProbability = 0.30;
            mediumProbability = 0.50;
            hardProbability = 0.20;
        } else if (level < 17) {
            easyProbability = 0.20;
            mediumProbability = 0.45;
            hardProbability = 0.30;
        } else if (level < 20) {
            easyProbability = 0.15;
            mediumProbability = 0.43;
            hardProbability = 0.42;
        } else {
            easyProbability = 0.15;
            mediumProbability = 0.35;
            hardProbability = 0.50;
        }

        double randNum = rand.nextDouble();

        if (randNum < easyProbability) {
            return new Goblin();
        } else if (randNum < easyProbability + mediumProbability) {
            return new Skeleton();
        } else {
            return new Dragon();
        }
    }

    private static boolean isAbilityAvailable() {
        if (abilityTurnCount >= 3) {
            return true;
        }
        return false;
    }

    private static String isAbilityAvailableStr() {
        if (isAbilityAvailable()) {
            return "Enable";
        }
        return "Disable";
    }

    private static void killReward(Entity target) {
        int rewardCoins;
        if (target instanceof Goblin) {
            rewardCoins = 20;
        } else if (target instanceof Skeleton) {
            rewardCoins = 30;
        } else {
            rewardCoins = 50;
        }
        player.addCoins(rewardCoins);
        System.out.println("You Earned " + rewardCoins + " Coins!\n");
        wait(700);
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

    public static void wait(int milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}