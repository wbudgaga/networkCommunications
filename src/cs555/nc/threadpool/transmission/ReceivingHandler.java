package cs555.nc.transmission;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import cs555.nc.node.Setting;
import cs555.nc.tasks.ReadTask;
import cs555.nc.tasks.Task;
import cs555.nc.threadpool.ThreadPoolManager;

public class ReceivingHandler extends Thread{
	private ServerSocket 		serverSocket;
	private	DataTracker 		dataTracker;
	private ThreadPoolManager 	threadPool;	

	public ReceivingHandler(DataTracker dataTracker, int port) throws IOException{
		this.dataTracker = dataTracker;
		serverSocket = new ServerSocket(port);
		startThreadPool(Setting.THREADPOOL_SIZE);
		System.out.println("Peer at "+InetAddress.getLocalHost().getHostName()+" is listening on port: "+port );
	}
	//Starting the thread pool manager
	private void startThreadPool(int threadPoolSize){
		threadPool = new ThreadPoolManager(threadPoolSize);
		threadPool.start();
	}
	
	public void stopListening(){
		try {
			serverSocket.close();
		} catch (IOException e) {}    
	} 
	@Override
	public void run() {
		try{
		 while (true) {
			 Socket connectionSocket = serverSocket.accept(); 
			 Task rTask= new ReadTask(dataTracker, connectionSocket);
			 threadPool.addTask(rTask);
		}
		}catch(IOException ioE){}
		threadPool.stop();
	}
}
