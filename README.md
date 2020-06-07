# Managing Network Communications Between Nodes
Experimenting with network communications to manage communications between nodes. The implemented application should verify that: (1) the number of messages that you send and receive within the system match, and (2) these messages have been not corrupted in transit to the intended recipient.

## Compiling and executing:
- To compile the source files, you have to execute the command "make all" 
- To delete class files from the bin folder, you have to execute the command "make clean" 
- To execute the programs, you have to go inside the bin folder then execute the the programs as described below:
	-	java cs555.nc.node.Process listening-port [SENDING]
	
    Where
		  SENDING is an optional parameter used to start sending, and it should be given for the last peer enjoined the network.
