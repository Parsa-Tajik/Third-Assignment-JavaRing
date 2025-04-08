package org.project.entity;

public interface Entity {
    void attack(Entity target);

    void defend();

    void heal(int health);

    boolean isAlive();

    void takeDamage(int damage);

    void takeDamageNoArmor(int damage);

    int getHp();

    int getMaxHP();

    String getAbilityName();
}
