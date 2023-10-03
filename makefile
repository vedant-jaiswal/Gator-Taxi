JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

jar: $(classes)
	jar -cvmf manifest.txt gatorTaxi.jar $(classes)

clean :
	rm -f *.class
	rm gatorTaxi.jar
	rm output_file.txt