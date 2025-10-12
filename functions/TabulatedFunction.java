package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int PointsCount;

    public TabulatedFunction (double leftX, double rightX, int pointsCount) {
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i, leftX += step)
            points[i] = new FunctionPoint(leftX, 0);
        PointsCount = pointsCount;
    }

    public TabulatedFunction (double leftX, double rightX, double[] values) {
        int pointsCount =  values.length;
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i, leftX += step)
            points[i] = new FunctionPoint(leftX, values[i]);
        PointsCount = pointsCount;
    }

    public double getLeftDomainBorder () {
        return points[0].getX();
    }

    public double getRightDomainBorder () {
        return points[PointsCount - 1].getX();
    }

    public double getFunctionValue (double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            for (int i = 0; i < PointsCount; ++i) {
                if (x == points[i].getX())
                    return points[i].getY();
                if (x > points[i].getX() && x <= points[i + 1].getX()) {
                    double x1 = points[i].getX();
                    double y1 = points[i].getY();
                    double x2 = points[i + 1].getX();
                    double y2 = points[i + 1].getY();
                    return ((x - x1) * (y2 - y1) / (x2 - x1)) + y1;
                }
            }
        }
        return Double.NaN;
    }

    public int getPointsCount () {
        return PointsCount;
    }

    public FunctionPoint getPoint (int index) {
        return new FunctionPoint(points[index]);
    }

    public void setPoint (int index, FunctionPoint point) {
        if ((index == 0 || index == PointsCount - 1)) {
            if (points[index].getX() != point.getX())
                return;
            points[index] = new FunctionPoint(point);
        }
        else
            if (point.getX() > points[index - 1].getX() && point.getX() < points[index + 1].getX())
                points[index] = new FunctionPoint(point);
    }

    public double getPointX (int index) {
        return points[index].getX();
    }

    public void setPointX (int index, double x) {
        if (index != 0 && index != PointsCount - 1 && x > points[index - 1].getX() && x < points[index + 1].getX()) 
            points[index].setX(x);
    }

    public double getPointY (int index) {
        return points[index].getY();
    }

    public void setPointY (int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint (int index) {
        System.arraycopy(points, index + 1, points, index, PointsCount - index - 1);
        --PointsCount;
    }

    public void addPoint (FunctionPoint point) {
        int index = 0;
        if (point.getX() > points[PointsCount - 1].getX())
            index = PointsCount;
        else {
            while (point.getX() > points[index].getX()) {
                if (point.getX() == points[index].getX()) return;
                ++index;
            }
        }
        if (PointsCount + 1 > points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[PointsCount * 2 + 1];
            System.arraycopy(points, 0, newPoints, 0, index);
            newPoints[index] = new FunctionPoint(point);
            System.arraycopy(points, index, newPoints, index + 1, PointsCount - index);
            points = newPoints;
        }
        else {
            System.arraycopy(points, index, points, index + 1, PointsCount - index);
            points[index] = new FunctionPoint(point);
        }
        ++PointsCount;
    }

    public void outClass () {
         for (int i = 0; i < PointsCount; ++i) {
            System.out.printf("(%.2f , %.2f)\n", points[i].getX(), points[i].getY());
        }
    }
}