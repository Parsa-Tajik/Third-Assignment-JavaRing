package org.project.entity;

public interface Entity {
    void attack(Entity target);

    void defend();

    void heal(int health);

    void fillMana(int mana);

    boolean isAlive();

    void takeDamage(int damage);

    int getHp();

    int getMaxHP();

    int getMaxMP();
}
