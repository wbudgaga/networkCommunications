package cs555.nc.tasks;

import java.nio.channels.SelectionKey;

import cs555.nc.transmission.DataTracker;

public abstract class Task {
	protected DataTracker 	dataTracker;
	protected SelectionKey	key;
	
	public Task(DataTracker dt){
		dataTracker = dt;
	}

	public abstract void execute();

}
