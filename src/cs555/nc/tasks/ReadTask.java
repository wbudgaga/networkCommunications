package cs555.nc.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import cs555.nc.transmission.DataTracker;
import cs555.nc.util.UtilClass;

// This class's job is receiving the data and inform the tracker about received data
public class ReadTask  extends Task{
	private 	Socket socket;
				
	public ReadTask(DataTracker dt, Socket socket){
		super(dt);
		this.socket = socket;
	}
	
	@Override
	// try to read data on socket channel
	public void execute() {
		byte[] byteBuffer	= new byte[4];
		
		try {
			InputStream inStream = socket.getInputStream();
			while (true){
				if (inStream.read(byteBuffer, 0,4) < 4){
					socket.close();
					return;
				}
				// reading the message's payload which is an integer value(4bytes)
				int payload  	= UtilClass.byteArrayToInt(byteBuffer); 
				dataTracker.trackReceivedMSG(payload);
			}
		} catch (IOException e) {e.printStackTrace();}
	}
}
