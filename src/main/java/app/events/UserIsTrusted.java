package app.events;

import app.listeners.Observer;

import java.util.ArrayList;
import java.util.List;

public class UserIsTrusted implements UserSubject {
    private List<Observer> observers;
    private Integer userId;

    public UserIsTrusted(Integer userId) {
        this.observers = new ArrayList<>();
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.handle();
        }
    }
}
