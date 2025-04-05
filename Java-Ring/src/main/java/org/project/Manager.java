package org.project;

import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    private static List<Location> locations = new ArrayList<>();

    public static void playGame(int level) {

        createGame(level);

        while(true) {
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

            System.out.println("Enter 1 to attack.");
            System.out.println("Enter 2 to move to another location.");
            int choice = getIntInput(1, 2);

            if (choice == 1) {
                // attack
            }else {
                changeLocation();
            }
        }
    }

    private static void changeLocation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the location you wish to move to");
        int choice = getIntInput(1, locations.size());
        while (locations.get(choice - 1).isPlayerOn) {
            System.out.println("this is your current location, choose another one");
            choice = getIntInput(1, locations.size());
        }

        for (Location location : locations) {
            location.isPlayerOn = false;
        }
        locations.get(choice - 1).isPlayerOn = true;
    }

    private static void createGame(int locCount) {
        for (int i = 0; i < locCount; i++) {
            ArrayList<Enemy> enemies = new ArrayList<>();
            enemies.add(createRandomMonster());

            locations.add(new Location(Integer.toString(i + 1), enemies));
        }

        locations.getFirst().isPlayerOn = true;
    }

    private static Enemy createRandomMonster() {
        return new Skeleton();
    }

    private static int getIntInput(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while (choice < min || choice > max) {
            System.out.println("invalid input");
            choice = sc.nextInt();
        }
        return choice;
    }
}