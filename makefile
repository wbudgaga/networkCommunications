# define a makefile variable for the java compiler
#
JCC = javac

#source & output directory
#
OUT_DIR=bin/
SRC_DIR=src/

#packages
#
LIST = \
	cs555/nc/threadpool \
	cs555/nc/tasks \
	cs555/nc/util \
	cs555/nc/transmission \
	cs555/nc/node

# define a makefile variable for compilation flags
#
JFLAGS = -g -classpath $(SRC_DIR) -d $(OUT_DIR)


all:
	$(foreach var,$(LIST),$(JCC) $(JFLAGS) $(SRC_DIR)$(var)/*.java;)

#
# RM is a predefined macro in make (RM = rm -f)
#
clean:
	$(foreach var,$(LIST),$(RM) $(OUT_DIR)$(var)/*.class;)

