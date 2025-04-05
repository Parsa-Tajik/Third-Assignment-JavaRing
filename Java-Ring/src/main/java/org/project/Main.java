package org.project;

import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Enemy> enemies = new ArrayList<>();
            enemies.add(new Skeleton());
            enemies.add(new Skeleton());

            locations.add(new Location(Integer.toString(i + 1), enemies));
        }

        Scanner sc = new Scanner(System.in);
        int attackChoice;
        while(true) {
            for (Location location : locations) {
                System.out.println(location);
            }

            attackChoice = sc.nextInt();
        }

    }
}