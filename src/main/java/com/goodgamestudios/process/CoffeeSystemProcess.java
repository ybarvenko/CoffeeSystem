package com.goodgamestudios.process;

import com.goodgamestudios.model.Programmer;

import java.util.concurrent.Callable;

/**
 * Created by unkiss on 22.10.15.
 */
public abstract class CoffeeSystemProcess implements Callable<Programmer>
{

	protected final Programmer programmer;

	public CoffeeSystemProcess(Programmer programmer){
		this.programmer = programmer;
	}
}
