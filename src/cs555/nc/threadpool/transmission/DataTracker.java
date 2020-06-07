package cs555.nc.transmission;

public class DataTracker {
	private	int		receiveTracker = 0;
	private int		sendTracker = 0;
	private long	receiveSummation = 0;
	private long	sendSummation = 0;
	
	public synchronized void  trackReceivedMSG(int payload){
		++receiveTracker;
		receiveSummation += payload;
//		report();
	}
	
	public void  trackSentMSG(int payload){
		++sendTracker;
		sendSummation += payload;
//		report();
	}

	protected int getReceiveTracker() {
		return receiveTracker;
	}

	protected long getReceiveSummation() {
		return receiveSummation;
	} 

	protected int getsentTracker() {
		return sendTracker;
	}

	protected long getSentSummation() {
		return sendSummation;
	} 
	
	public synchronized  boolean hasReceived(){
		if (receiveTracker > 0)
			return true;
		return false;
	}
	
	public void report(){

		String sentMessages 	= String.format("* %-22s : %6s      %-13s : %15s *","# of sent messages",getsentTracker(),"sent data", getSentSummation());
		String receivedMessages = String.format("* %-22s : %6s      %-13s : %15s *","# of received messages",getReceiveTracker(),"received data", getReceiveSummation());
		

		System.out.println("***********************************************************************");
//		System.out.println("# sent messages:"+getsentTracker()+"\t received messages:"+getReceiveTracker()+" dif:"+(getsentTracker()-getReceiveTracker()));
		//System.out.println("sent messages:"+getsentTracker()+"\t received messages:"+getReceiveTracker()+" dif:"+(getsentTracker()-getReceiveTracker()));
		System.out.println("\t\t\t\tTRANSMISSION REPORT\n"+sentMessages+"\n"+receivedMessages);
		System.out.println("***********************************************************************");
	}
}
