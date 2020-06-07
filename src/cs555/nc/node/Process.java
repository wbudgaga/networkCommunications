package cs555.nc.node;

import java.io.IOException;
import java.net.SocketException;

import cs555.nc.transmission.DataTracker;
import cs555.nc.transmission.ReceivingHandler;
import cs555.nc.transmission.SendingHandler;

// This class represent a peer node which starts sending and receiving operations
// through the objects  ReceivingHandler and SendingHandler.
public class Process {
	private	DataTracker dataTracker; 	// object that tracks the transmission of data 
	private int 		port;		 	// port the peer is listening on
	
	public Process(int port){
		dataTracker 		= new DataTracker();
		this.port 			= port;
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

	private void start(boolean startSending) throws IOException, InterruptedException{
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
			System.err.println("   java cs555.nc.node.Process listening-port [SENDING]");
		    return;
		}
		
		int port = Integer.parseInt(args[0]);
		boolean startSending = false;/* Indicator that determines if this peer should start sending */
		if (args.length == 2 && args[1].compareToIgnoreCase("sending")==0)
			startSending = true;
			
		try {
			Process process = new Process(port);
			process.start(startSending);
		} catch (SocketException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}	
}
