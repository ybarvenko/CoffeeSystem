package com.goodgamestudios.model;




import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by ybarvenko on 22.10.2015.
 */
public class ProgrammerTest {

    @Test
    public void testCreateRandomProgrammers() throws Exception {
        List<Programmer> programmers = Programmer.createRandomProgrammers(2, 2435L);
        programmers.forEach(System.out::println);

        Assert.assertEquals(2,programmers.size());
        int i=0;
        {
            Assert.assertEquals(0, programmers.get(i).getId());
            Assert.assertEquals(CoffeeType.CAPPUCCINO, programmers.get(i).getCoffeeType());
            Assert.assertEquals("Cash", programmers.get(i).getPaymentType().getName());
            Assert.assertEquals(500L, programmers.get(i).getPaymentType().getDuration());
        }
        {
            i++;
            Assert.assertEquals(i, programmers.get(i).getId());
            Assert.assertEquals(CoffeeType.LATTE, programmers.get(i).getCoffeeType());
            Assert.assertEquals("Credit", programmers.get(i).getPaymentType().getName());
            Assert.assertEquals(250L, programmers.get(i).getPaymentType().getDuration());
        }


    }
}
