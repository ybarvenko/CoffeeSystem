package com.goodgamestudios;

import java.util.concurrent.ExecutionException;

public class CoffeeSystemApplication {

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        {
            CoffeeSystemController coffeeSystemController = CoffeeSystemController.getInstance();
            coffeeSystemController.printMeasure(coffeeSystemController.start(100));

        }
        {
            CoffeeSystemController coffeeSystemController = CoffeeSystemController.getInstance();
            coffeeSystemController.printMeasure(coffeeSystemController.start(200));
        }
        {
            CoffeeSystemController coffeeSystemController = CoffeeSystemController.getInstance();
            coffeeSystemController.printMeasure(coffeeSystemController.start(500));
        }
        {
            CoffeeSystemController coffeeSystemController = CoffeeSystemController.getInstance();
            coffeeSystemController.printMeasure(coffeeSystemController.start(1000));
        }

        CoffeeSystemController.getInstance().shutdownExecutors();

    }
}
