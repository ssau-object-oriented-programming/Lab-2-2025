package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount+10];

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i=0; i<pointsCount; i++) {
            double x = leftX + i*step;
            points[i] = new FunctionPoint(x,0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount+10];

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i=0; i<pointsCount; i++) {
            double x = leftX + i*step;
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

        for (int i = 0; i < pointsCount; i++) {
            if (x == points[i].getX()) {
                return points[i].getY();
            }
        }

        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();

            if (x1 == x2) {
                continue; // Пропускаем одинаковые точки
            }

            if (x >= x1 && x <= x2) {
            // Линейная интерполяция по уравнению прямой
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();

                // Уравнение прямой через две точки: y = y1 + (y2 - y1) * (x - x1) / (x2 - x1)
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

        // проверка левой границы
        if (index > 0) {
            double LeftNeighborX = points[index - 1].getX();
            if (point.getX() <= LeftNeighborX) {
                return;
            }
        }

        // проверка правой границы
        if (index > pointsCount - 1) {
            double RightNeighborX = points[index + 1].getX();
            if (point.getX() >= RightNeighborX) {
                return;
            }
        }

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {

        // проверка левой границы
        if (index > 0) {
            double LeftNeighborX = points[index - 1].getX();
            if (x <= LeftNeighborX) {
                return;
            }
        }

        // проверка правой границы
        if (index > pointsCount - 1) {
            double RightNeighborX = points[index + 1].getX();
            if (x >= RightNeighborX) {
                return;
            }
        }

        // безопасно заменяем
        double oldY = points[index].getY();
        points[index] = new FunctionPoint(x, oldY);
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        double oldX = points[index].getX();
        points[index] = new FunctionPoint(oldX, y);
    }

    public void deletePoint(int index) {
        for (int i = index; i < pointsCount - 1; i++) {
            points[i] = points[i+1];
        }

        pointsCount--;
        points[pointsCount] = null;
    }

    public void addPoint(FunctionPoint point) {
        FunctionPoint newPoint = new FunctionPoint(point);

        int insertIndex = 0; // поиск индекса куда вставить точку
        while (insertIndex < pointsCount && points[insertIndex].getX() < newPoint.getX()) {
            insertIndex++;
        }

        if ( pointsCount == points.length) { // если не хватает места
            int newCapacity = points.length+10;
            FunctionPoint[] newArray = new FunctionPoint[newCapacity];

            System.arraycopy(points, 0, newArray, 0, pointsCount);
            points = newArray;
        }
        
        // сдвиг точек право (для освобождения места)
        if (pointsCount - insertIndex > 0) {
            System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        }

        points[insertIndex] = newPoint;
        pointsCount++;
    }
}
