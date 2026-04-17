package com.narxoz.rpg.strategy;
public class BalancedStrategy implements CombatStrategy {
    public int calculateDamage(int p) { return p; }
    public int calculateDefense(int d) { return d; }
    public String getName() { return "Balanced"; }
}