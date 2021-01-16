import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Body {
    public static final double G = 6.67E-11;

    private Vector2D position;
    private Vector2D force;
    private Vector2D velocity;
    private double mass;
    private String image;

    public void move(double timeStep) {
        // calculate acceleration
        double xAccel = force.getX() / getMass();
        double yAccel = force.getY() / getMass();

        // calculate and update velocity
        velocity = velocity.add(new Vector2D(timeStep * xAccel, timeStep * yAccel));

        // calculate new position
        position = position.add(velocity.scale(timeStep));
    }

    public Vector2D calculateForcesWithOtherBody(Body otherBody) {
        // compare two bodies' positions, get angle and length of the line between them
        double deltaX = otherBody.getPosition().getX() - getPosition().getX();
        double deltaY = otherBody.getPosition().getY() - getPosition().getY();
        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        // calculate Total force, and therefore the x and y components of the force
        double force = (G * getMass() * otherBody.getMass()) / (distance * distance);
        double xForce = force * deltaX / distance;
        double yForce = force * deltaY / distance;

        return new Vector2D(xForce, yForce);
    }

    public void addForce(Vector2D force) {
        this.force = this.force.add(force);
    }

    @Override
    public String toString() {
        return "Body{" +
                "xPos=" + position.getX() +
                ", yPos=" + position.getY() +
                ", xVelocity=" + velocity.getX() +
                ", yVelocity=" + velocity.getY() +
                ", mass=" + mass +
                ", image='" + image + '\'' +
                '}';
    }
}