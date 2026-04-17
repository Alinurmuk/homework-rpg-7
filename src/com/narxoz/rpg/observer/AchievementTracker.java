package com.narxoz.rpg.observer;
public class AchievementTracker implements GameObserver {
    public void onEvent(GameEvent e) {
        if (e.getType() == GameEventType.BOSS_DEFEATED) System.out.println("ACHIEVEMENT: Legendary Slayer!");
    }
}