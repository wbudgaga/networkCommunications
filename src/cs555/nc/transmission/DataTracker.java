package cs555.nc.transmission;

// This class tracks the data transmission and prints report about that
public class DataTracker {
	private	int		receiveTracker 		= 0;
	private int		sendTracker 		= 0;
	private long	receiveSummation 	= 0;
	private long	sendSummation 		= 0;
	
	public synchronized void  trackReceivedMSG(int payload){
		++receiveTracker;
		receiveSummation += payload;
	}
	
	public void  trackSentMSG(int payload){
		++sendTracker;
		sendSummation += payload;
	}

	private int getReceiveTracker() {
		return receiveTracker;
	}

	private int getsentTracker() {
		return sendTracker;
	}

	private long getReceiveSummation() {
		return receiveSummation;
	} 

	private long getSentSummation() {
		return sendSummation;
	} 
	
	private int getTrackerDif() {
		return sendTracker - receiveTracker;
	}

	private long getSummationDif() {
		return sendSummation - receiveSummation;
	} 

	public synchronized  boolean hasReceived(){
		if (receiveTracker > 0)
			return true;
		return false;
	}
	
	public void report(){
		String sentMessages 	= String.format("* %-22s: %6s      %-15s: %15s *","# Of Sent Messages",		getsentTracker(),	"Sent Data", 		getSentSummation());
		String receivedMessages = String.format("* %-22s: %6s      %-15s: %15s *","# Of Received Messages",	getReceiveTracker(),"Received Data", 	getReceiveSummation());
		String difference 		= String.format("* %-22s: %6s      %-15s: %15s *","Messages Difference",	getTrackerDif(),	"Data Difference", 	getSummationDif());

		System.out.println("\n\n\n\n************************ TRANSMISSION REPORT ***************************");
		System.out.println(sentMessages+"\n"+receivedMessages);
		System.out.println("*----------------------------------------------------------------------*");
		System.out.println(difference);
		System.out.println("************************************************************************\n\n\n\n");
	}
}
