package com.narxoz.rpg.observer;
public class BattleLogger implements GameObserver {
    public void onEvent(GameEvent e) { System.out.println("[BATTLE] " + e.getType() + " by " + e.getSourceName() + " : " + e.getValue()); }
}