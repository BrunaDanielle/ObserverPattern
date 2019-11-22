package com.example.observerpattern.interfaces;

/**
 * the observer interface
 */
public interface RepositoryObserver {
    void onUserDataChanged(String fullname, String age);
}
