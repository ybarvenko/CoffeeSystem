package com.goodgamestudios;

import com.goodgamestudios.model.CoffeeSystemResult;
import com.goodgamestudios.model.Programmer;
import com.goodgamestudios.process.ChoseTypeOfCoffeeProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class CoffeeSystemController {

    private static final Logger LOG = LoggerFactory.getLogger(CoffeeSystemController.class);


    public static ExecutorService pickTheFavoriteTypeOfCoffeeExecutor;
    public static ExecutorService paymentProcessExecutor;
    private static  ExecutorService findAndPickupExecutor;
    public static CompletionService<Programmer> findAndPickupExecutorCompletionService;

    private static CoffeeSystemController instance = new CoffeeSystemController();
    private CoffeeSystemResult coffeeSystemResult;

    private CoffeeSystemController()
    {
        //init executors
        pickTheFavoriteTypeOfCoffeeExecutor = Executors.newFixedThreadPool(10);
        paymentProcessExecutor = Executors.newFixedThreadPool(5);
        findAndPickupExecutor = Executors.newFixedThreadPool(2);
        findAndPickupExecutorCompletionService = new ExecutorCompletionService<>(findAndPickupExecutor);
    }

    public static CoffeeSystemController getInstance()
    {
        return instance;
    }

    public Collection<Programmer> start(int numberOfProgrammers) throws InterruptedException, ExecutionException
    {

        LOG.info(String.format("The coffee system with %s programmers is running... .  For mor Information you can change the logging-level to DEBUG in logback.xml file. Please wait.... .\n\n\n",numberOfProgrammers));

        prepareCoffeeSystemResultObject();

        // Generate programmer
        Collection<Programmer> programmers = Programmer.createRandomProgrammers(numberOfProgrammers, 24335L);


        //Start the process
        programmers.forEach(programmer ->
        {
            ChoseTypeOfCoffeeProcess pickTheFavoriteTypeOfCoffee = new ChoseTypeOfCoffeeProcess(programmer);
            programmer.setExecutionStartTime(System.currentTimeMillis());
            pickTheFavoriteTypeOfCoffeeExecutor.submit(pickTheFavoriteTypeOfCoffee);
        });


        //wait for finish and show progress
        for (int i=0; i<programmers.size(); i++)
        {
            Programmer programmer = findAndPickupExecutorCompletionService.take().get();

            int size = programmers.size();
            System.out.print(String.format("Complete: %d%%\r", (i*100/size)));

            LOG.debug(String.format("Complete = %s \texecution time = %s ms.", programmer, programmer.getExecutionTime()));
        }


        return  programmers;
    }

    private void prepareCoffeeSystemResultObject()
    {
        coffeeSystemResult=null;
        coffeeSystemResult = new CoffeeSystemResult();
    }

    public void shutdownExecutors()
    {
        //Shutdown executors
        pickTheFavoriteTypeOfCoffeeExecutor.shutdown();
        paymentProcessExecutor.shutdown();
        findAndPickupExecutor.shutdown();
    }

    public void printMeasure(Collection<Programmer> programmers)
    {
        LongSummaryStatistics stats = programmers.stream().mapToLong((programmer) -> programmer.getExecutionTime()).summaryStatistics();
        LOG.info(String.format("Time to getting a coffee of average programmer: %s ms.",stats.getAverage()));
        LOG.info(String.format("Fastest time to getting a coffee: %s ms.",stats.getMin()));
        LOG.info(String.format("Slowest time to getting a coffee: %s ms.",stats.getMax()));
        LOG.info(this.getCoffeeSystemResult().getResultInfo());
    }

    public CoffeeSystemResult getCoffeeSystemResult()
    {
        return coffeeSystemResult;
    }


}
