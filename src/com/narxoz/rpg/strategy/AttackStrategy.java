package com.narxoz.rpg.strategy;
public class AttackStrategy implements CombatStrategy {
    public int calculateDamage(int p) { return (int)(p * 1.5); }
    public int calculateDefense(int d) { return (int)(d * 0.5); }
    public String getName() { return "Aggressive"; }
}