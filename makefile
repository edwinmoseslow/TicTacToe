
# makefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)  
# define a variable for the Java Virtual Machine (JVM)
# define a variable for a parameter. When you run make, you could use:
# make run FILE="Algo.csv" para sobre escribir el valor de FILE.

JFLAGS= -g
JC= javac
JVM= java 
FILE=

#
# Clear any default targets for building .class files from .java files; we 
# will provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o) 
# Currently, clearing the default for .java.class is not necessary since 
# make does not have a definition for this target, but later versions of 
# make may, so it doesn't hurt to make sure that we clear any default 
# definitions for these
#

.SUFFIXES: .java .class

#
# Here is our target entry for creating .class files from .java files 
# This is a target entry that uses the suffix rule syntax:
#	DSTS:
#		rule
#  'TS' is the suffix of the target file, 'DS' is the suffix of the dependency 
#  file, and 'rule'  is the rule for building a target	
# '$*' is a built-in macro that gets the basename of the current target 
# Remember that there must be a < tab > before the command line ('rule') 
#

.java.class: 
	$(JC) $*.java
#
# CLASSES is a macro consisting of 4 words (one for each java source file)
#

CLASSES = \
	TicTacToe.java \
	Constant.java \
	# TestRunnner.java \

#
# MAIN is a variable with the name of the file containing the main method
#
MAIN = TicTacToe
# TEST = TestRunner

PATH = game/

#
# the default make target entry
#

default: classes

#
# This target entry uses Suffix Replacement within a macro: 
# $(name:string1=string2)
# 	In the words in the macro named 'name' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES 
# with the .class suffix
#

classes: $(CLASSES:.java=.class)

#
# RM is a predefined macro in make (RM = rm -f)
#

clean: 
	rm -f game/*.class

# Next two lines contain a target for running the program
# Remember the tab in the second line.
# $(JMV) $(MAIN) are replaced by their values

run: 
	$(JVM) $(PATH)$(MAIN).java

# test: 
# 	$(JVM) $(PATH)$(TEST).java

hi:
echo hi