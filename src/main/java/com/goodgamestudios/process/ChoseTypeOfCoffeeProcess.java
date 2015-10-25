package com.goodgamestudios.process;

import com.goodgamestudios.CoffeeSystemController;
import com.goodgamestudios.model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class ChoseTypeOfCoffeeProcess extends CoffeeSystemProcess {

    private static final Logger LOG = LoggerFactory.getLogger(ChoseTypeOfCoffeeProcess.class);
    public static final long PICK_THE_FAVORITE_TYPE_OF_COFFEE = 500;

    public ChoseTypeOfCoffeeProcess(Programmer programmer) {
        super(programmer);
    }


    @Override
    public Programmer call() throws Exception {
        LOG.debug(String.format("Programmer %s picking the favorite type of coffee ... (duration: %s ms.) ",programmer.getId(),PICK_THE_FAVORITE_TYPE_OF_COFFEE));
        Thread.sleep(PICK_THE_FAVORITE_TYPE_OF_COFFEE);

        PaymentProcess p = new PaymentProcess(programmer);
        CoffeeSystemController.paymentProcessExecutor.submit(p);


        return programmer;
    }
}
