This is my implementation of an n-body simulation, it's based on an assignment from nifty projects. https://www.cs.princeton.edu/courses/archive/spring17/cos126/assignments/nbody.html

All input text files, image files, and audio files are taken from their assignment.

INSTRUCTIONS:
The program takes 3 arguments: the total length of the simulation (in seconds, as a double), the length of each time-step (also in seconds, as a double) and the filepath for the text file containing the starting information.
Shorter time steps improves the accuracy of the simulation but takes longer to compute (and therefore display!)

As an example, running the program with the arguments "3.154e+7 86400 planets.txt" will simulate Earth, Mars, Venus and Mercury orbiting the Sun for a one-year period across 24-hour timesteps.

The input files are formatted the same way to contain the number of bodies, n, the size of the simulation space, and then a row of data per body which contains:
y co-ordinate, y co-ordinate, x velocity component, y velocity component, mass, and image filename for that body. The units are in m, m/s, and kg respectively.
