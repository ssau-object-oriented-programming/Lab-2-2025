package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    // создаёт табулированную функцию с нулевыми значениями
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }

    // создаёт табулированную функцию с готовыми значениями
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        pointsCount = values.length;
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();
            if (x >= x1 && x <= x2) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index > 0 && index < pointsCount - 1) {
            if (point.getX() > points[index - 1].getX() && point.getX() < points[index + 1].getX()) {
                points[index] = new FunctionPoint(point);
            }
        } else if (index == 0 && point.getX() < points[1].getX()) {
            points[index] = new FunctionPoint(point);
        } else if (index == pointsCount - 1 && point.getX() > points[index - 1].getX()) {
            points[index] = new FunctionPoint(point);
        }
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index > 0 && index < pointsCount - 1) {
            if (x > points[index - 1].getX() && x < points[index + 1].getX()) {
                points[index].setX(x);
            }
        }
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        if (pointsCount <= 2) return;
        FunctionPoint[] newPoints = new FunctionPoint[pointsCount - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, pointsCount - index - 1);
        points = newPoints;
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) {
        for (int i = 0; i < pointsCount; i++) {
            if (points[i].getX() == point.getX()) {
                return;
            }
        }

        FunctionPoint[] newPoints = new FunctionPoint[pointsCount + 1];
        int i = 0;
        while (i < pointsCount && points[i].getX() < point.getX()) {
            newPoints[i] = points[i];
            i++;
        }
        newPoints[i] = new FunctionPoint(point);
        System.arraycopy(points, i, newPoints, i + 1, pointsCount - i);
        points = newPoints;
        pointsCount++;
    }
}
