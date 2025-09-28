package functions;

// Описывает табулированную функцию, заданную набором точек.
public class TabulatedFunction {
    private FunctionPoint[] points;
    public TabulatedFunction(double leftX, double rightX, int pointsCount) { /* создаёт объект табулированной функции по заданным левой и правой границе области определения, а также количеству точек для табулирования (значения функции в точках при этом равны 0)*/
        this.points = new FunctionPoint[pointsCount];
        double step = (pointsCount > 1) ? (rightX - leftX) / (pointsCount - 1) : 0;
        for (int i = 0; i < pointsCount; ++i) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values) { /* аналогичен предыдущему конструктору, но вместо количества точек получает значения функции в виде массива*/
        int count = values.length;
        this.points = new FunctionPoint[count];
        double step = (count > 1) ? (rightX - leftX) / (count - 1) : 0;
        for (int i = 0; i < count; ++i) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }
    // Основные методы
    public double getLeftDomainBorder() {
        return points[0].getX();
    }
    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        for (int i = 0; i < points.length - 1; ++i) {
            if (points[i].getX() <= x && x <= points[i + 1].getX()) {
                double x1 = points[i].getX();
                double y1 = points[i].getY();
                double x2 = points[i + 1].getX();
                double y2 = points[i + 1].getY();
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }
    // Методы для работы с точками
    public int getPointsCount() {
        return points.length;
    }
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }
    public void setPoint(int index, FunctionPoint point) {
        double newX = point.getX();
        double leftBound = (index > 0) ? points[index - 1].getX() : Double.NEGATIVE_INFINITY;
        double rightBound = (index < points.length - 1) ? points[index + 1].getX() : Double.POSITIVE_INFINITY;

        if (newX > leftBound && newX < rightBound) {
            points[index] = new FunctionPoint(point);
        }
    }
    public double getPointX(int index) {
        return points[index].getX();
    }
    public void setPointX(int index, double x) {
        double leftBound = (index > 0) ? points[index - 1].getX() : Double.NEGATIVE_INFINITY;
        double rightBound = (index < points.length - 1) ? points[index + 1].getX() : Double.POSITIVE_INFINITY;
        
        if (x > leftBound && x < rightBound) {
            points[index].setX(x);
        }
    }
    public double getPointY(int index) {
        return points[index].getY();
    }
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }
    // Методы изменения кол-ва точек
    public void deletePoint(int index) {
        if (index < 0 || index >= points.length || points.length < 3) return;
        FunctionPoint[] newPoints = new FunctionPoint[points.length - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, points.length - index - 1);
        points = newPoints;
    }
    public void addPoint(FunctionPoint point) {
        int insertIndex = 0;
        while (insertIndex < points.length && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }
        if (insertIndex < points.length && points[insertIndex].getX() == point.getX()) {
            return;
        }
        FunctionPoint[] newPoints = new FunctionPoint[points.length + 1];
        System.arraycopy(points, 0, newPoints, 0, insertIndex);
        newPoints[insertIndex] = new FunctionPoint(point);
        System.arraycopy(points, insertIndex, newPoints, insertIndex + 1, points.length - insertIndex);
        points = newPoints;
    }
}
