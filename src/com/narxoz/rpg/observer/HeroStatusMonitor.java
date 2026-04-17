package com.narxoz.rpg.observer;
import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class HeroStatusMonitor implements GameObserver {
    private List<Hero> heroes;
    public HeroStatusMonitor(List<Hero> heroes) { this.heroes = heroes; }
    public void onEvent(GameEvent e) {
        if (e.getType() == GameEventType.HERO_DIED) System.out.println("ALERT: " + e.getSourceName() + " has fallen!");
    }
}