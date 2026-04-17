package com.narxoz.rpg.observer;
public class LootDropper implements GameObserver {
    public void onEvent(GameEvent e) {
        if (e.getType() == GameEventType.BOSS_PHASE_CHANGED) System.out.println("LOOT: Boss dropped rare crystals!");
    }
}