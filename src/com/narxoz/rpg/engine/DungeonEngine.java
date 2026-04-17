package com.narxoz.rpg.engine;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.observer.*;
import java.util.*;

public class DungeonEngine {
    private List<Hero> heroes;
    private DungeonBoss boss;
    private GamePublisher publisher;
    private Set<String> warnedHeroes = new HashSet<>();

    public DungeonEngine(List<Hero> heroes, DungeonBoss boss, GamePublisher publisher) {
        this.heroes = heroes;
        this.boss = boss;
        this.publisher = publisher;
    }

    public EncounterResult run() {
        int round = 0;
        while (boss.isAlive() && heroes.stream().anyMatch(Hero::isAlive)) {
            round++;
            for (Hero h : heroes) {
                if (h.isAlive() && boss.isAlive()) {
                    int dmg = Math.max(1, h.getStrategy().calculateDamage(h.getAttackPower()) - boss.getStrategy().calculateDefense(boss.getDefense()));
                    boss.takeDamage(dmg);
                    publisher.notifyObservers(new GameEvent(GameEventType.ATTACK_LANDED, h.getName(), dmg));
                }
            }
            if (boss.isAlive()) {
                for (Hero h : heroes) {
                    if (h.isAlive()) {
                        int dmg = Math.max(1, boss.getStrategy().calculateDamage(boss.getAttackPower()) - h.getStrategy().calculateDefense(h.getDefense()));
                        h.takeDamage(dmg);
                        publisher.notifyObservers(new GameEvent(GameEventType.ATTACK_LANDED, boss.getName(), dmg));
                        if (h.getHp() < h.getMaxHp() * 0.3 && !warnedHeroes.contains(h.getName())) {
                            warnedHeroes.add(h.getName());
                            publisher.notifyObservers(new GameEvent(GameEventType.HERO_LOW_HP, h.getName(), h.getHp()));
                        }
                        if (!h.isAlive()) publisher.notifyObservers(new GameEvent(GameEventType.HERO_DIED, h.getName(), 0));
                    }
                }
            }
        }
        if (!boss.isAlive()) publisher.notifyObservers(new GameEvent(GameEventType.BOSS_DEFEATED, boss.getName(), 0));
        return new EncounterResult(!boss.isAlive(), round, (int)heroes.stream().filter(Hero::isAlive).count());
    }
}