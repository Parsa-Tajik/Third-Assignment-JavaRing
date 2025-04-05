package org.project;

import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Knight;
import org.project.entity.players.Player;
import org.project.location.Location;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String playerName = "";
        Scanner sc = new Scanner(System.in);
        printGameName();

        System.out.println("Hello Soldier, welcome to THE GLITCHER! (inspired by The Witcher)");
        System.out.println("What's your name, please?");
        playerName = sc.next();

        System.out.println("OK " + playerName + ", now choose your HERO:");
        System.out.println("1. Wizard (Special ability: Spell");
        System.out.println("2. Assassin (Special ability: Invisibility");
        System.out.println("3. Knight (Special ability: Strong Kick)");
        System.out.println("4. The Glitcher (Special ability: Glitch)");
        int heroChoice = sc.nextInt();
        while (heroChoice < 1 || heroChoice > 4) {
            System.out.println("Invalid option.");
            heroChoice = sc.nextInt();
        }

        Player player;
        switch (heroChoice) {
            case 1:
                player = new Knight(playerName, 100, 100, new Sword(), new KnightArmor(20, 3));
                break;
            case 2:
                player = new Knight(playerName, 100, 100, new Sword(), new KnightArmor(20, 3));
                break;
            case 3:
                player = new Knight(playerName, 100, 100, new Sword(), new KnightArmor(20, 3));
                break;
            case 4:
                player = new Knight(playerName, 100, 100, new Sword(), new KnightArmor(20, 3));
                break;

            default:
                player = new Knight(playerName, 100, 100, new Sword(), new KnightArmor(20, 3));
                break;
        }
        Manager.player = player;


        Manager.playGame(10);
    }

    private static void printGameName() {
        System.out.println("  _____ _   _ _____      ____ _     ___ _____ ____ _   _ _____ ____  ");
        System.out.println(" |_   _| | | | ____|    / ___| |   |_ _|_   _/ ___| | | | ____|  _  \\ ");
        System.out.println("   | | | |_| |  _|     | |  _| |    | |  | || |   | |_| |  _| | |_) |");
        System.out.println("   | | |  _  | |___    | |_| | |___ | |  | || |___|  _  | |___|  _ < ");
        System.out.println("   |_| |_| |_|_____|    \\____|_____|___| |_| \\____|_| |_|_____|_| \\_\\");
        System.out.println("\n");
    }
}