#!/bin/bash
#runtests.sh
ant build
cd bin
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw2_1.ps > ../out1.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw2_2.ps > ../out2.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw2_3.ps > ../out3.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw2_4.ps > ../out4.xpm
java edu.drexel.cs430.assignments.A1 -f ../testinput/hw2_5.ps > ../out5.xpm



