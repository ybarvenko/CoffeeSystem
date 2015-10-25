package com.goodgamestudios.model;

import com.goodgamestudios.CoffeeSystemController;

import java.util.Random;

/**
 * Created by unkiss on 22.10.15.
 */
public abstract class PaymentType
{

	public void paymentFinish(){
		calcTotalSold();
		calcSoldPerPayment();
	}

	public abstract String getName();

	private void calcTotalSold()
	{
		CoffeeSystemController.getInstance().getCoffeeSystemResult().getSoldCoffeeTotal().incrementAndGet();
	}

	protected abstract void calcSoldPerPayment();

	public abstract long getDuration();

	public static PaymentType generateRandomPaymentType(final Random r)
	{

		return r.nextInt(2)==0?new CashPayment():new CreditPayment();

	}
}
