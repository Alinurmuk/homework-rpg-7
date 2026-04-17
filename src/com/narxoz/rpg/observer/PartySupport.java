package com.narxoz.rpg.observer;
import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class PartySupport implements GameObserver {
    private List<Hero> heroes;
    public PartySupport(List<Hero> heroes) { this.heroes = heroes; }
    public void onEvent(GameEvent e) {
        if (e.getType() == GameEventType.HERO_LOW_HP) {
            heroes.stream().filter(h -> h.getName().equals(e.getSourceName())).forEach(h -> h.heal(30));
        }
    }
}