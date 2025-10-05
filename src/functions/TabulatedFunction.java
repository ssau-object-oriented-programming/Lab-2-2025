package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int size;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.points = new FunctionPoint[pointsCount];
        this.size = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        int n = values.length;
        this.points = new FunctionPoint[n];
        this.size = n;
        double step = (rightX - leftX) / (n - 1);
        for (int i = 0; i < n; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[size - 1].getX();
    }

    public double getFunctionValue(double x) {
        for (int i = 0; i < size - 1; i++) {
            double x0 = points[i].getX();
            double x1 = points[i + 1].getX();
            double y0 = points[i].getY();
            double y1 = points[i + 1].getY();

            if (x == x0) return y0;
            if (x == x1) return y1;

            if (x > x0 && x < x1) {
                return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
            }
        }
        return Double.NaN;
    }

    public int getPointsCount() {
        return size;
    }

    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        points[index].setX(x);
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        for (int i = index; i < size - 1; i++) {
            points[i] = points[i + 1];
        }
        size--;
    }

    public void addPoint(FunctionPoint point) {
        if (size == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[size * 2];
            System.arraycopy(points, 0, newPoints, 0, size);
            points = newPoints;
        }

        int insertIndex = 0;
        double x = point.getX();
        while (insertIndex < size && points[insertIndex].getX() < x) {
            insertIndex++;
        }

        for (int i = size; i > insertIndex; i--) {
            points[i] = points[i - 1];
        }

        points[insertIndex] = new FunctionPoint(point);
        size++;
    }
}
