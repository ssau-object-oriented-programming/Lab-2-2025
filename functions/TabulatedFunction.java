package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        points = new FunctionPoint[values.length];
        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public FunctionPoint getPoint(int index) {
        return points[index];
    }

    public FunctionPoint[] getPoints() {
        return points;
    }
    //Задание 4
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }

    // вычисление значения функции
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i].getX() == x) {
                return points[i].getY();
            }
        }

        for (int i = 0; i < points.length - 1; i++) {
            double x1 = points[i].getX();
            double y1 = points[i].getY();
            double x2 = points[i + 1].getX();
            double y2 = points[i + 1].getY();

            if (x > x1 && x < x2) {
                // линейная интерполяция
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN;
    }

    //Задание 5
    public int getPointsCount() {
        return points.length;
    }

    public FunctionPoint getPointCopy(int index) {
        return new FunctionPoint(points[index].getX(), points[index].getY());
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= points.length) return; 
        if ((index > 0 && point.getX() <= points[index - 1].getX()) ||
                (index < points.length - 1 && point.getX() >= points[index + 1].getX())) {
            return;
        }
        points[index] = new FunctionPoint(point);
    }


    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if ((index > 0 && x <= points[index - 1].getX()) ||
                (index < points.length - 1 && x >= points[index + 1].getX())) {
            return;
        }
        points[index].setX(x);
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }
    //Задание 6
    public void deletePoint(int index) {
        if (index < 0 || index >= points.length) return;
        FunctionPoint[] newPoints = new FunctionPoint[points.length - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, points.length - index - 1);
        points = newPoints;
    }
    public void addPoint(FunctionPoint point) {
        double x = point.getX();
        int insertIndex = 0;
        while (insertIndex < points.length && points[insertIndex].getX() < x) {
            insertIndex++;
        }
        FunctionPoint[] newPoints = new FunctionPoint[points.length + 1];
        System.arraycopy(points, 0, newPoints, 0, insertIndex);
        newPoints[insertIndex] = new FunctionPoint(point);
        System.arraycopy(points, insertIndex, newPoints, insertIndex + 1, points.length - insertIndex);
        points = newPoints;
    }
}