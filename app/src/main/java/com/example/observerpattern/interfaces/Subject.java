package com.example.observerpattern.interfaces;

public interface Subject {
    void registerObserver(RepositoryObserver repositoryObserver);
    void removeObserver(RepositoryObserver repositoryObserver);
    void setUserData (String name, String age);
    void notifyObservers();
}
