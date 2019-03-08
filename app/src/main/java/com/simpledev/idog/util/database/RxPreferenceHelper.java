package com.simpledev.idog.util.database;

public interface RxPreferenceHelper {
    void saveString(String key, String value);

    String getString(String key);

    void saveBoolean(String key, boolean value);

    boolean getBoolean(String key);

    boolean contains(String key);

    int getInt(String key);

    void saveInt(String key, int value);
}
