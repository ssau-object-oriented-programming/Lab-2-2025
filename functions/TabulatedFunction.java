package functions;

public class TabulatedFunction {

    private FunctionPoint[] points;
    private int size;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points = new FunctionPoint[pointsCount];
        size = pointsCount;
        double interval = (rightX - leftX) / (size - 1);

        for (int i = 0; i < size; ++i) points[i] = new FunctionPoint((leftX + i * interval), 0);
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        points = new FunctionPoint[values.length];
        size = values.length;
        double interval = (rightX - leftX) / (values.length - 1);

        for (int i = 0; i < values.length; ++i) points[i] = new FunctionPoint((leftX + i * interval), values[i]);
    }

    public double getLeftDomainBorder() {
        if (size == 0) return Double.NaN;
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        if (size == 0) return Double.NaN;
        return points[size - 1].getX();
    }

    public double getFunctionValue(double x) {
        if ((size == 0) || (x < getLeftDomainBorder()) || x > getRightDomainBorder()) return Double.NaN;

        for (int i = 0; i < size - 1; i++) {
            if (x >= points[i].getX() && x <= points[i + 1].getX()) {
                double x1 = points[i].getX();
                double y1 = points[i].getY();
                double x2 = points[i + 1].getX();
                double y2 = points[i + 1].getY();

                return (y1 + (x - x1) * (y2 - y1) / (x2 - x1));
            }
        }
        return Double.NaN;
    }

    public int getPointsCount() {return size;}

    public FunctionPoint getPoint(int index) {
        if (index >= 0 && index < size) return new FunctionPoint(points[index]);
        else return null;
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index >= 0 && index < size) {

            FunctionPoint newPoint = new FunctionPoint(point);

            if (size > 1) {
                if (index == 0 && newPoint.getX() <= points[1].getX()) points[0] = newPoint;
                else if (index == size - 1 && newPoint.getX() >= points[size - 2].getX()) points[size - 1] = newPoint;
                else if (index > 0 && index < size - 1 && newPoint.getX() >= points[index - 1].getX() && newPoint.getX() <= points[index + 1].getX()) {
                    points[index] = newPoint;
                }
            } else points[index] = newPoint;
        }
    }

    public double getPointX(int index) {
        if (index >= 0 && index < size) return points[index].getX();
        else return Double.NaN;
    }

    public void setPointX(int index, double x) {
        if (index >= 0 && index < size) {
            if (size > 1) {
                if (index == 0 && x <= points[1].getX()) points[0].setX(x);
                else if (index == size - 1 && x >= points[size - 2].getX()) points[size - 1].setX(x);
                else if (index > 0 && index < size - 1 && x >= points[index - 1].getX() &&
                        x <= points[index + 1].getX()) {
                    points[index].setX(x);
                }
            } else points[index].setX(x);
        }
    }

    public double getPointY(int index) {
        if (index >= 0 && index < size) return points[index].getY();
        else return Double.NaN;
    }

    public void setPointY(int index, double y) {
        if (index >= 0 && index < size) points[index].setY(y);
    }

    public void deletePoint(int index) {
        if (index >= 0 && index < size) {
            --size;
            System.arraycopy(points, index + 1, points, index, size - index);
        }
    }

    public void addPoint(FunctionPoint point) {
        FunctionPoint newPoint = new FunctionPoint(point);
        double x = newPoint.getX();

        int insert_index = size;
        for (int i = 0; i < size; i++) {
            if (x < points[i].getX()) {
                insert_index = i;
                break;
            } else if (x == points[i].getX()) {
                points[i] = newPoint;
                return;
            }
        }

        if (size >= points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[size + 10];
            System.arraycopy(points, 0, newPoints, 0, size);
            points = newPoints;
        }

        if (insert_index < size)
            System.arraycopy(points, insert_index, points, insert_index + 1, size - insert_index);

        points[insert_index] = newPoint;
        size++;
    }
}