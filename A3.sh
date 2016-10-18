#!/bin/bash
#runtests.sh
ant build
cd bin
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_1.ps > ../out1.xpm
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_2.ps > ../out2.xpm
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_3.ps > ../out3.xpm
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_4.ps > ../out4.xpm
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_5.ps > ../out5.xpm
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_6.ps > ../out6.xpm
java edu.drexel.cs430.assignments.A3 -f ../testinput/hw3_7.ps > ../out7.xpm



