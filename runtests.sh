#!/bin/bash
#runtests.sh
ant build
cd bin
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_1.ps > ../out1.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_2.ps > ../out2.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_3.ps > ../out3.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_4.ps > ../out4.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_5.ps > ../out5.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_6.ps > ../out6.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_7.ps > ../out7.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw1_8.ps > ../out8.xpm



