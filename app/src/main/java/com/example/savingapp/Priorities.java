package com.example.savingapp;

public class Priorities {
    static class helperholder{

    }
    private static class LoadSingleton {
        static final Priorities INSTANCE = new Priorities();
    }
    private Priorities() {}

    public static Priorities getInstance() {
        return LoadSingleton.INSTANCE;
    }
}
