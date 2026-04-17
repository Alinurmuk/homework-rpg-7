package com.narxoz.rpg.combatant;

import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;

public class DungeonBoss implements GameObserver {
    private String name;
    private int hp;
    private int maxHp;
    private int attackPower;
    private int defense;
    private CombatStrategy strategy;
    private GamePublisher publisher;

    public DungeonBoss(String name, int hp, int attackPower, int defense, GamePublisher publisher) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.publisher = publisher;
        this.strategy = new BalancedStrategy();
    }

    public void takeDamage(int amount) {
        int oldHp = hp;
        hp = Math.max(0, hp - amount);
        double oldPct = (double) oldHp / maxHp;
        double newPct = (double) hp / maxHp;

        if (oldPct >= 0.6 && newPct < 0.6) publisher.notifyObservers(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, 2));
        if (oldPct >= 0.3 && newPct < 0.3) publisher.notifyObservers(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, 3));
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {
            if (event.getValue() == 2) strategy = new AttackStrategy();
            if (event.getValue() == 3) strategy = new AttackStrategy();
            System.out.println("!!! BOSS ADAPTING: Phase " + event.getValue() + " activated !!!");
        }
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public boolean isAlive() { return hp > 0; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
    public CombatStrategy getStrategy() { return strategy; }
}