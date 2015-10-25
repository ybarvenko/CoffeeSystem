package com.goodgamestudios.model;

import com.goodgamestudios.CoffeeSystemController;

/**
 * Created by unkiss on 22.10.15.
 */
public class CashPayment extends PaymentType
{

	@Override
	public String getName()
	{
		return "Cash";
	}

	@Override
	protected void calcSoldPerPayment()
	{
		CoffeeSystemController.getInstance().getCoffeeSystemResult().getSoldCoffeeCash().incrementAndGet();

	}

	public long getDuration()
	{
		return 500;
	}



}
