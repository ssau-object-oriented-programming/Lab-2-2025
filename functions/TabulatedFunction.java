package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    // Конструктор(равномерный шаг, y = 0)
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX || pointsCount < 2) {
            throw new IllegalArgumentException();
        }
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount + 10];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }
    }

    // Конструктор: с заданными y
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX || values.length < 2) {
            throw new IllegalArgumentException();
        }
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount + 10];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }

    // Пустой - для addPoint
    public TabulatedFunction() {
        points = new FunctionPoint[10];
        pointsCount = 0;
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
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        if (index > 0 && point.getX() <= points[index - 1].getX()) return;
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) return;
        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        if (index > 0 && x <= points[index - 1].getX()) return;
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) return;
        points[index] = new FunctionPoint(x, points[index].getY());
    }

    public double getPointY(int index) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        points[index] = new FunctionPoint(points[index].getY(), y);
    }

    public void deletePoint(int index) {
        if (index < 0 || index >= pointsCount || pointsCount <= 2) return;
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;
        points[pointsCount] = null;
    }

    public void addPoint(FunctionPoint point) {
        if (pointsCount == points.length) {
            FunctionPoint[] newArray = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newArray, 0, pointsCount);
            points = newArray;
        }

        int insertIndex = pointsCount;
        for (int i = 0; i < pointsCount; i++) {
            if (points[i].getX() > point.getX()) {
                insertIndex = i;
                break;
            }
        }

        if (insertIndex < pointsCount) {
            System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        }

        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}

