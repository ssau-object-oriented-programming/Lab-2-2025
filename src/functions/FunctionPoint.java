package functions;

/**
 * Represents a single point of a tabulated function.
 */
public class FunctionPoint {
    private double x; // Abscissa of the point.
    private double y; // Ordinate of the point.

    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public FunctionPoint(FunctionPoint point) {
        if (point == null) {
            throw new IllegalArgumentException("Point for copying must not be null.");
        }
        this.x = point.x;
        this.y = point.y;
    }

    public FunctionPoint() {
        this(0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
