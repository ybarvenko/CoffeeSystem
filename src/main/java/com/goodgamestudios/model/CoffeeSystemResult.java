package com.goodgamestudios.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by unkiss on 22.10.15.
 */
public class CoffeeSystemResult
{

	private  AtomicInteger soldCoffeeTotal = new AtomicInteger();
	private  AtomicInteger soldCoffeeCash = new AtomicInteger();
	private  AtomicInteger soldCoffeeCredit = new AtomicInteger();
	private  ConcurrentMap<Thread,Map<String,Integer>> dispensedCoffeeByMachineMap = new ConcurrentHashMap<>();


	public String getResultInfo()
	{
		final StringBuilder sb = new StringBuilder("CoffeeSystemResult");
		sb.append("\n\tSold coffee total = ").append(soldCoffeeTotal.get()).
		append("\n\tSold coffee payed with cash = ").append(soldCoffeeCash.get()).
		append("\n\tSold coffee payed with credit = ").append(soldCoffeeCredit.get());

		sb.append(getResultInfoForDispendedCoffee());

		return sb.toString();
	}

	private String getResultInfoForDispendedCoffee() {
		StringBuffer sb = new StringBuffer("\n\nDispensed by each coffee machine ");
		Collection<Map<String, Integer>> machineResults =  dispensedCoffeeByMachineMap.values();
		int coffeeMachineCount = 1;
		for (Map<String, Integer> machineResultMap : machineResults)
		{
			sb.append("\n\t\tCoffee machine ").append(coffeeMachineCount++);
			sb.append("\n\t\t\t\t\t");

			final int[] total = {0};
			machineResultMap.entrySet().stream().
					sorted((e1,e2)->{
						return e1.getKey().compareTo(e2.getKey());
					}).
					forEach(e -> {
						sb.append("\t").append(e.getKey()).append(" = ").append(e.getValue()).append(",");
						total[0] += e.getValue();
					});
			sb.append("\tTotal = ").append(total[0]);
		}



		sb.append("\n");
		return sb.toString();
	}

	public AtomicInteger getSoldCoffeeTotal()
	{
		return soldCoffeeTotal;
	}

	public AtomicInteger getSoldCoffeeCash()
	{
		return soldCoffeeCash;
	}

	public AtomicInteger getSoldCoffeeCredit()
	{
		return soldCoffeeCredit;
	}

	public ConcurrentMap<Thread, Map<String, Integer>> getDispensedCoffeeByMachineMap()
	{
		return dispensedCoffeeByMachineMap;
	}
}
