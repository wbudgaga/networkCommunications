# Managing Network Communications Between Nodes
Experimenting with network communications to manage communications between nodes. The implemented application should verify that: (1) the number of messages that you send and receive within the system match, and (2) these messages have been not corrupted in transit to the intended recipient. Message exchanges in the system will be done in the presence of rapid setup/teardown of connections and while a connection is alive. 


## The implemented packages
The application is implemented with the following packages.
- **node**
   - **Setting interface** contains global veriables to configare the system behavior.
   - **Process class** represents a peer node that sends and receives messages by relying on ReceivingHandler and SendingHandler.
   - **host file** contains list of hosts with their listening ports that each peer can communicate with.
 
 - **tasks**
    - **Task class** is an abstract class of the involved tasks.   
    - **ReadTask class** is subclass of Task, and its job is to receiving the data and report to the tracker about received data.
 
 - **transmission**
    - **DataTracker class** tracks the data transmission and prints report about it.   
    - **ReceivingHandler class** is a thread class that deals with incoming connections.
    - **SendingHandler class** connects to the hosts existing in the "hosts" file starts sending massages to them.

  - **threadpool**
    - **ThreadPoolManager class** manages thread workers to execute tasks in parallel.
    - **Worker class** is a thread class that executes a task.

  - **util**
    - **UtilClass class** implements generic methods that are needed in the application (for example, these methods convert between byte and int, generate a random number, and read hosts from file). 


## Compiling and executing:
- To compile the source files, you have to execute the command "make all" 
- To delete class files from the bin folder, you have to execute the command "make clean" 
- To execute the programs, you have to go inside the bin folder then execute the the programs as described below:
	-	java cs555.nc.node.Process listening-port [SENDING]
	
    Where
		  SENDING is an optional parameter used to start sending, and it should be given for the last peer enjoined the network.
