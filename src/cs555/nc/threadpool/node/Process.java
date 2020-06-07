package cs555.nc.node;

import java.io.IOException;
import java.net.SocketException;

import cs555.nc.transmission.DataTracker;
import cs555.nc.transmission.ReceivingHandler;
import cs555.nc.transmission.SendingHandler;

public class Process {
	private	DataTracker dataTracker;
	private int 		port;
	private boolean 	startSending;
	
	public Process(int port, boolean startSending){
		dataTracker = new DataTracker();
		this.startSending = startSending;
		this.port 	= port;
	}
	
	private ReceivingHandler startReceiving() throws IOException{
		ReceivingHandler rHandler = new ReceivingHandler(dataTracker,port);
		rHandler.start();
		return rHandler;
	}
	
	private void startSending() throws IOException{
		SendingHandler sHandler = new SendingHandler(dataTracker);
		sHandler.start();
	}

	private void start() throws IOException, InterruptedException{
		ReceivingHandler rHandler = startReceiving();
		while(!startSending && !dataTracker.hasReceived());
		startSending();
		Thread.sleep(Setting.WAIT_TIME_TO_STOP);
		rHandler.stopListening();
		dataTracker.report();
	}

	
	public static void main(String args[]){
		if (args.length < 1) {
			System.err.println("Server:  Usage:");
			System.err.println("   java cs555.nc.node.Process listening-port ");
		    return;
		}
		
		int port = Integer.parseInt(args[0]);
		boolean startSending = false;
		if (args.length == 2)
			startSending = true;
			
		try {
			Process process = new Process(port,startSending);
			process.start();
		} catch (SocketException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}	

}
