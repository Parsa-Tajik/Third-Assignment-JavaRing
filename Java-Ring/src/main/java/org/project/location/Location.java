package org.project.location;

import org.project.entity.enemies.Enemy;

import java.util.ArrayList;

public class Location {
    private String name;
    private ArrayList<Enemy> enemies;

    public boolean isPlayerOn = false;

    public Location(String name, ArrayList<Enemy> enemies) {
        this.name = name;
        //this.locations = locations;
        this.enemies = enemies;
    }

    /*
    TODO: (BONUS) RESET EACH LOCATION AFTER PLAYER LEAVES
    */

    public String getName() {
        return name;
    }

    //public ArrayList<Location> getLocations() {
        //return locations;
    //}

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public String toString() {
        String enemiesString = "";
        int count = 1;
        for (Enemy enemy : enemies) {
            enemiesString += count + ". " + enemy.toString();
            if (count != enemies.size()) {
                enemiesString += "\n";
            }
            count++;
        }

        return "Location: " + name + "\n" + enemiesString;
    }
}