#!/bin/bash
#runtests.sh
ant build
cd bin
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_1.ps > ../out1.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_2.ps > ../out2.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_3.ps > ../out3.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_4.ps > ../out4.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_5.ps > ../out5.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_6.ps > ../out6.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_7.ps > ../out7.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_7.ps > ../out8.xpm
java edu.drexel.cs430.assignments.A4 -f ../testinput/hw4_7.ps > ../out9.xpm



