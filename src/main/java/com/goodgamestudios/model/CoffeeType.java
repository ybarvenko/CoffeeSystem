package com.goodgamestudios.model;

import java.util.Random;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public enum CoffeeType {

    ESPRESSO(250), LATTE(500), CAPPUCCINO(750);

    private long duration;

    /**
     *
     * @param duration in ms
     */
    private CoffeeType(long duration){
        this.duration = duration;
    }

    static CoffeeType generateRandomCoffeeType(Random r) {
        int min = 0; //inclusive
        int max = 3;//exclusive
        int index = r.nextInt(max-min) + min;

        return CoffeeType.values()[index];

    }

    public long getDuration() {
        return duration;
    }
}
