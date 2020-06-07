package cs555.nc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;

public class UtilClass {
	public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),// right shift 24 bits. the remaining, the most significant 8 bits are stored in position 0 in the array
                (byte)(value >>> 16),// right shift 16 bits. the remaining, the most significant 16 bits are casting to byte. the least significant 8 bits of them are stored in position 1 in the array
                (byte)(value >>> 8),// right shift 8 bits. the remaining, the most significant 16 bits are casting to byte. the least significant 8 bits of them are stored in position 2 in the array
                (byte)value};	// cast value to byte,  the least significant 8 bits are stored in position 3 in the array
	}

	public static final int byteArrayToInt(byte [] b) {
        return (b[0] << 24) // left shift 24 bits to put the contents of b[0] in int's positions from 24 to 31 
                + ((b[1] & 0xFF) << 16) // left shift 16 bits to put the contents of b[1] in int's positions from 16 to 23
                + ((b[2] & 0xFF) << 8) // left shift 8 bits to put the contents of b[2] in int's positions from 8 to 15
                + (b[3] & 0xFF);	// put the contents of b[3] in int's positions from 0 to 7
	}								// casting to int will convert from binary representation to decimal
	
	public  static int getRandomNumber(int min, int max){
		int range = max - min + 1;
		Random randomGenerater = new Random();
		int generatedNumber = (int) (range * randomGenerater.nextDouble());
		return generatedNumber + min;
	}
	// Reads and parses the hosts from the file "hosts". Then it stores and returns hosts names and ports in hosts and ports
	public static void readHostsFromFile(InputStream is,ArrayList<String>hosts,ArrayList<Integer>ports) throws IOException{	
		String 			thisHost= InetAddress.getLocalHost().getHostName();
		BufferedReader 	reader	= new BufferedReader(new InputStreamReader(is));
		String 			line 	= null; 
		String[] 		hostInfo;
		
		while (( line = reader.readLine()) != null){
		   	hostInfo = line.split(":");
		   	//Add all hosts except this one
		   	if (!hostInfo[0].toLowerCase().startsWith(thisHost.toLowerCase())){
		       	hosts.add(hostInfo[0]);
		       	ports.add( Integer.valueOf(hostInfo[1]));
		   	}
		}
		reader.close();
	}
}