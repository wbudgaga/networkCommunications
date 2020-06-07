package cs555.nc.node;

public interface Setting {
	public final int 	NUMBER_OF_ROUNDS	= 10000;
	public final int 	MESSAGES_PER_ROUND	= 5;
	public final int 	THREADPOOL_SIZE		= 10;
	public final int 	WAIT_TIME_TO_STOP	= 5000; //millisecond : the needed time to wait before end the program
}
