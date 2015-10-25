package com.goodgamestudios.model;

import com.goodgamestudios.CoffeeSystemController;

/**
 * Created by unkiss on 22.10.15.
 */
public class CreditPayment extends PaymentType
{

	@Override
	public String getName()
	{
		return "Credit";
	}

	@Override
	protected void calcSoldPerPayment()
	{
		CoffeeSystemController.getInstance().getCoffeeSystemResult().getSoldCoffeeCredit().incrementAndGet();
	}

	@Override
	public long getDuration()
	{
		return 250;
	}



}
