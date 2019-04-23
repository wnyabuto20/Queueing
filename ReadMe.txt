# Queueing-Theory
Explores how various parameters (like arrival rate lambda , service rate Mew) of exponentially generated jobs affect mean response time of a server.
A write up is attached.
# Instructions
Clone the repository and delete the output files (output.csv and output1.csv) or alternatively download everything but the output files. When you run the code,fresh files will be generated.The generated files have the same names as our sample outputs so deleting the attached sample output files ensures we avoid errors that might arise from attempting to generate files with names similar to those of the sample output files.
# Compiling and running
Compile each of the following files:
Simulator.java
extSimulator.java
Generator.java
Job.java
Server.java
Main.java
Compile them as: javac filename.java
Run the code with the following command:
java Main
# output
The code outputs mean response times for four variances (1, 10,20,50) under different lambdas into two different csv files for the 2 different experiments.
