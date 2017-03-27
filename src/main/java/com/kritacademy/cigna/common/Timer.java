package com.kritacademy.cigna.common;

import java.util.concurrent.TimeUnit;

/**
 * Simple time and memory usage recorder
 * Created by Chertpong on 01/19/2016.
 */
public class Timer {
    private Runtime runtime;
    private Long startTime;
    private Long startMemory;

    public void start() {
        startTime = System.nanoTime();
        runtime = Runtime.getRuntime();
        startMemory = runtime.freeMemory();
    }

    public void stop(){
        if(startTime != 0) {
            System.out.println("Total time: "+ elapseTime() + " ms");
            System.out.println("Used memory: " + calculateMemory() + "MB");
        }
        else {
            throw new RuntimeException("Timer object need start() to be called before stop()");
        }
    }

    /**
     * @return elapse time in ms
     */
    public Long elapseTime() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
    }

    /**
     * @return usage memory in MB which calculated from start memory - free memory
     */
    public Long calculateMemory(){
        return (startMemory - runtime.freeMemory()) / (long) Math.pow(1024, 2);
    }
}
