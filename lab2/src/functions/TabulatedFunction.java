package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount];

        if (pointsCount == 1) {
            this.points[0] = new FunctionPoint(leftX, 0.0);
            return;
        }

        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            this.points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount];

        if (pointsCount == 1) {
            this.points[0] = new FunctionPoint(leftX, values[0]);
            return;
        }

        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            double y = values[i];
            this.points[i] = new FunctionPoint(x, y);
        }
    }
    public double getLeftDomainBorder() {
        if (pointsCount == 0) return Double.NaN;
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        if (pointsCount == 0) return Double.NaN;
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (pointsCount == 0) return Double.NaN;

        double left = getLeftDomainBorder();
        double right = getRightDomainBorder();
        if (x < left || x > right) return Double.NaN;

        if (pointsCount == 1) {
            if (x == left) {
                return points[0].getY();
            } else {
                return Double.NaN;
            }
        }

        for (int i = 0; i < pointsCount - 1; i++) {
            double x0 = points[i].getX();
            double x1 = points[i + 1].getX();
            if (x == x0) return points[i].getY();
            if (x == x1) return points[i + 1].getY();
            if (x > x0 && x < x1) {
                double y0 = points[i].getY();
                double y1 = points[i + 1].getY();
                return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
            }
        }

        return Double.NaN;
    }
    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= pointsCount) return null;
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) return;

        double newX = point.getX();
        if (index > 0 && newX <= points[index - 1].getX()) return;
        if (index < pointsCount - 1 && newX >= points[index + 1].getX()) return;

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        if (index < 0 || index >= pointsCount) return Double.NaN;
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) return;

        if (index > 0 && x <= points[index - 1].getX()) return;
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) return;

        points[index].setX(x);
    }

    public double getPointY(int index) {
        if (index < 0 || index >= pointsCount) return Double.NaN;
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        if (index < 0 || index >= pointsCount) return;
        points[index].setY(y);
    }
    public void deletePoint(int index) {
        if (index < 0 || index >= pointsCount  || pointsCount <= 1) return;


        for (int i = index; i < pointsCount - 1; i++) {
            points[i] = points[i + 1];
        }
        points[pointsCount - 1] = null;
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) {
        if (point == null) return;

        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        for (int i = pointsCount; i > insertIndex; i--) {
            points[i] = points[i - 1];
        }

        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}
