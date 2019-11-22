package com.example.observerpattern;

import android.os.Handler;

import com.example.observerpattern.interfaces.RepositoryObserver;
import com.example.observerpattern.interfaces.Subject;

import java.util.ArrayList;


public class UserDataRepository implements Subject {

    private String mFullName;
    private String mAge;
    private static UserDataRepository INSTANCE = null;

    private ArrayList<RepositoryObserver> mObservers;

    private UserDataRepository() {
        mObservers = new ArrayList<>();
        getNewDataFromRemote();
    }

    // Simulate network
    private void getNewDataFromRemote() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData(mFullName, mAge);
            }
        }, 10000);
    }

    // Creates a Singleton of the class
    public static UserDataRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserDataRepository();
        }
        return INSTANCE;
    }

    @Override
    public void registerObserver(RepositoryObserver repositoryObserver) {
        if(!mObservers.contains(repositoryObserver)) {
            mObservers.add(repositoryObserver);
        }
    }

    @Override
    public void removeObserver(RepositoryObserver repositoryObserver) {
        if(mObservers.contains(repositoryObserver)) {
            mObservers.remove(repositoryObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for (RepositoryObserver observer: mObservers) {
            observer.onUserDataChanged(mFullName, mAge);
        }
    }

    public void setUserData(String fullName, String age) {
        mFullName = fullName;
        mAge = age;
        notifyObservers();
    }
}
