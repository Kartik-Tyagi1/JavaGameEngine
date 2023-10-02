package com.test.Util;

public class Time {

    // Time the application starts, will not change ever
    public static float timeStarted = System.nanoTime();

    // Returns time elapsed from when application started, in seconds
    public static float getTime() {
        return (float)((System.nanoTime() - timeStarted) * 1E-9);
    }
}
