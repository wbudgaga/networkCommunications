# Managing Network Communications Between Nodes
Experimenting with network communications to manage communications between nodes. The implemented application should verify that: (1) the number of messages that you send and receive within the system match, and (2) these messages have been not corrupted in transit to the intended recipient. Message exchanges in the system will be done in the presence of rapid setup/teardown of connections and while a connection is alive. 

## Description
I implemented a network communication system that creates a set of processes (peers) **P** that are launched on different machines. Each peer reads information about all peers in the system from a text file (each line in the file contains the host and port information for a different process). A peer should never attempt to connect to itself. 

Each process participates in a set of rounds (default: 10,000 rounds). In each round, each process sends connects to a randomly chosen peer in the set of processes **P**, and once a connection is established to a random node, the initiating peer sends **n** messages to the targeted process.   
The payload of each message is a random integer (positive or negative). 
At the end of each round the socket connection is closed and the process is repeated by choosing another peer at random from the set **P**. Each peer will maintain two integer variables that are initialized to zero to track the number of messages that were sent and received by a peer. 

Since the sending process chooses the recipient peer for each round at random, the number of messages received by different receivers would be different; however, because each round has **n** messages, the total number of messages received at a receiver would a multiple of  **n**. 

Each peer maintains two additional integer variables that are initialized to zero. One continuously sums the values of the random numbers that are sent as part of the payload, while the other variable continuously sums values of the payloads that are received. 

**To verify Correctness**, each peer prints a repoort about the the sent and received messages. The total number of messages that were sent and received by the set of peers **P** must match (i.e. the cumulative sum of the sent and received messages must match. Also, to check that the packets were not corrupted,  the cumulative sum of the integer numbers that have been sent must exactly match the sum the integer numbers that have been received.


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
