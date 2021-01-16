import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NBody {
    double timeStep;
    double duration;

    public static void main(String[] args) {
        NBody nBody = new NBody();
        nBody.simulate(args);
    }

    private void simulate(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdAudio.play("src/main/resources/2001.wav");

        // parse command line arguments
        try {
            duration = Double.parseDouble(args[0]);
            timeStep = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.err.println(String.format("Arguments %s and %s must both be doubles.", args[0], args[1]));
            e.printStackTrace();
            System.exit(1);
        }
        try {
            System.setIn(new FileInputStream(new File(args[2])));
        } catch (FileNotFoundException e) {
            System.err.println(String.format("file %s wasn't found", args[2]));
            e.printStackTrace();
            System.exit(1);
        }

        // get info about state of sim
        int numberOfBodies = StdIn.readInt();
        double radius = StdIn.readDouble();
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);

        // create objects from input
        List<Body> bodies = new ArrayList<>();
        for (int i = 0; i < numberOfBodies; i++) {
            Body body = new Body(new Vector2D(StdIn.readDouble(), StdIn.readDouble()), new Vector2D(0, 0),
                    new Vector2D(StdIn.readDouble(), StdIn.readDouble()), StdIn.readDouble(), StdIn.readString());
            bodies.add(body);
        }

        for (double timeElapsed = 0; timeElapsed < duration; timeElapsed += timeStep) {
            // Calculate all forces acting on each particle
            calculateForcesForBodies(bodies);

            // Use calculated forces to move each body
            for (Body body : bodies) {
                body.move(timeStep);
            }

            // Draw animation
            drawBodies(bodies);
        }

        // Print final state of the simulation in same format as input
        StdOut.println(numberOfBodies);
        StdOut.println(radius);
        for (Body body : bodies) {
            StdOut.println("" + body.getPosition().getX() + " " + body.getPosition().getY() + " " + body.getVelocity().getX() + " " + body.getVelocity().getY() + " " + body.getMass() + " " + body.getImage());
        }
    }

    private void drawBodies(List<Body> bodies) {
        // Clear canvas, then draw background, then bodies on top
        StdDraw.clear();
        StdDraw.picture(0, 0, "src/main/resources/starfield.jpg");
        for (Body body : bodies) {
            StdDraw.picture(body.getPosition().getX(), body.getPosition().getY(), "src/main/resources/" + body.getImage());
        }
        StdDraw.show();
        StdDraw.pause(20);
    }

    private void calculateForcesForBodies(List<Body> bodies) {
        for (int i = 0; i < bodies.size(); i++) {
            Body currentBody = bodies.get(i);
            currentBody.setForce(new Vector2D(0, 0));

            for (int j = 0; j < bodies.size(); j++) {
                if (i == j) {
                    continue;
                }
                Body otherBody = bodies.get(j);
                Vector2D force = currentBody.calculateForcesWithOtherBody(otherBody);
                currentBody.addForce(force);
            }
        }
    }
}