package com.narxoz.rpg.strategy;
public class DefenseStrategy implements CombatStrategy {
    public int calculateDamage(int p) { return (int)(p * 0.7); }
    public int calculateDefense(int d) { return (int)(d * 1.5); }
    public String getName() { return "Defensive"; }
}