package com.goodgamestudios.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class Programmer {


    private long executionStartTime;
    private long executionEndTime;


    private long id;

    private CoffeeType coffeeType;

    private PaymentType paymentType;


    private Programmer(long id, CoffeeType coffeeType, PaymentType paymentType) {
        this.id = id;
        this.coffeeType = coffeeType;
        this.paymentType = paymentType;
    }

    public static Programmer createProgrammer(long id, CoffeeType coffeeType, PaymentType paymentType) {
        return new Programmer(id, coffeeType, paymentType);
    }

    public static List<Programmer> createRandomProgrammers(long numberOfProgrammers, long seed){
        List<Programmer> programmers = new ArrayList<>();
        Random r = new Random(seed);
        for (int i = 0; i <numberOfProgrammers ; i++) {
            CoffeeType coffeeType = CoffeeType.generateRandomCoffeeType(r);
            PaymentType paymentType = PaymentType.generateRandomPaymentType(r);


            Programmer p = createProgrammer(i, coffeeType, paymentType);
            programmers.add(p);
        }

        return programmers;
    }


    public long getExecutionTime(){
        return executionEndTime- executionStartTime;
    }



    public long getId() {
        return id;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public long getExecutionStartTime() {
        return executionStartTime;
    }

    public void setExecutionStartTime(long executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    public long getExecutionEndTime()
    {
        return executionEndTime;
    }

    public void setExecutionEndTime(final long executionEndTime)
    {
        this.executionEndTime = executionEndTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Programmer{");
        sb.append("executionStartTime=").append(executionStartTime);
        sb.append(", id=").append(id);
        sb.append(", coffeeType=").append(coffeeType);
        sb.append(", paymentType=").append(paymentType);
        sb.append('}');
        return sb.toString();
    }
}
