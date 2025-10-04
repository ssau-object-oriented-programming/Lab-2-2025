package functions;

public class TabulatedFunction {
    private int pointsCount;
    private FunctionPoint[] points;
    private void checkIndex(int index) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointsCount);
        }
    }

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount + 5];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount + 5];
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
        if (x < getLeftDomainBorder() || x > getRightDomainBorder())
            return Double.NaN;
        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();
            if (x >= x1 && x <= x2) {
                // Линейная интерполяция: y = y1 + ((y2 - y1) * (x - x1)) / (x2 - x1)
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                return y1 + ((y2 - y1) * (x - x1)) / (x2 - x1);
            }
        }
        return Double.NaN;
    }


    public int getPointsCount() {
        return pointsCount;
    }
    public FunctionPoint getPoint(int index) {
        checkIndex(index);
        return new FunctionPoint(points[index]);
    }
    public void setPoint(int index, FunctionPoint point) {
        checkIndex(index);
        // Левый сосед
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            return;
        }
        // Правый сосед
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) {
            return;
        }
        points[index] = new FunctionPoint(point);
    }
    public double getPointX(int index) {
        checkIndex(index);
        return points[index].getX();
    }
    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) {
            return;
        }
        // Левый сосед
        if (index > 0 && x <= points[index - 1].getX()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointsCount);
        }
        // Правый сосед
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) {
            return;
        }
        points[index].setX(x);
    }
    public double getPointY(int index) {
        checkIndex(index);
        return points[index].getY();
    }
    public void setPointY(int index, double y) {
        checkIndex(index);
        points[index].setY(y);
    }


    public void deletePoint(int index) {
        checkIndex(index);
        // Сдвиг точек влево
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;
    }
    public void addPoint(FunctionPoint point) {
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }
        if (insertIndex < pointsCount && Math.abs(points[insertIndex].getX() - point.getX()) < 1e-10) {
            return;
        }
        if (pointsCount >= points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length + 5];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}
