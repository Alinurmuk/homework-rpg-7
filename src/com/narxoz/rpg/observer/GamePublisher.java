package com.narxoz.rpg.observer;
import java.util.*;

public class GamePublisher {
    private List<GameObserver> observers = new ArrayList<>();
    public void subscribe(GameObserver o) { observers.add(o); }
    public void notifyObservers(GameEvent e) { observers.forEach(o -> o.onEvent(e)); }
}