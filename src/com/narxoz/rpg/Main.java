package com.narxoz.rpg;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.engine.*;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GamePublisher p = new GamePublisher();

        Hero h1 = new Hero("Warrior", 200, 55, 30, new DefenseStrategy());
        Hero h2 = new Hero("Mage", 130, 90, 10, new AttackStrategy());
        Hero h3 = new Hero("Rogue", 150, 70, 15, new BalancedStrategy());
        List<Hero> hs = Arrays.asList(h1, h2, h3);

        DungeonBoss b = new DungeonBoss("Malphas", 700, 45, 20, p);

        p.subscribe(new BattleLogger());
        p.subscribe(new AchievementTracker());
        p.subscribe(new PartySupport(hs));
        p.subscribe(new HeroStatusMonitor(hs)); // Осы жер түзетілді
        p.subscribe(new LootDropper());
        p.subscribe(b);

        DungeonEngine e = new DungeonEngine(hs, b, p);
        EncounterResult res = e.run();

        System.out.println("\n--- FINAL REPORT ---");
        System.out.println("Win: " + res.isHeroesWon() + " | Rounds: " + res.getRoundsPlayed() + " | Survivors: " + res.getSurvivingHeroes());
    }
}