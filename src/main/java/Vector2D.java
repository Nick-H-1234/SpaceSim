import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Vector2D {
    private double x;
    private double y;

    public Vector2D add(Vector2D other){
        return new Vector2D(x + other.getX(), y + other.getY());
    }

    public Vector2D scale(double scale){
        return new Vector2D(x*scale, y*scale);
    }
}
