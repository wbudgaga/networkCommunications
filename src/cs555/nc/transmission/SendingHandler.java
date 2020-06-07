package cs555.nc.transmission;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import cs555.nc.node.Setting;
import cs555.nc.util.UtilClass;
// This class reads the hosts from the file "hosts" and connects and sends them massages
public class SendingHandler {
	private	DataTracker			dataTracker;
	private ArrayList<String>  	hosts = new ArrayList<String>();
	private ArrayList<Integer> 	ports = new ArrayList<Integer>();
	
	public SendingHandler(DataTracker dataTracker) throws IOException{
		this.dataTracker = dataTracker;
		UtilClass.readHostsFromFile(getClass().getResourceAsStream("hosts"),hosts,ports);
	}

	public void start() {
		Socket connection = null;
		System.out.println("Start sending .......");
		for (int i = 0; i < Setting.NUMBER_OF_ROUNDS; ++i){
			int index = UtilClass.getRandomNumber(0,hosts.size() - 1 );
			try{
				connection = new Socket(hosts.get(index), ports.get(index));
				doRound(connection.getOutputStream());
				connection.close();
			}catch(IOException e) {System.out.println("connection to "+hosts.get(index) +":"+  ports.get(index)+ "could not be established");}
		}
		System.out.println("sending is finished!");
	}

	private void doRound(OutputStream outStream) throws IOException {
		for (int i = 0; i < Setting.MESSAGES_PER_ROUND; ++i){
			int data = new Random().nextInt();
			outStream.write(UtilClass.intToByteArray(data));
			outStream.flush();
			dataTracker.trackSentMSG(data);
		}		
	}
}
