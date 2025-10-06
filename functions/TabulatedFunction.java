package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    // конст создания объекта табул. ф-и через кол-во точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2) pointsCount = 2;
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount + 5]; // Запас памяти

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            points[i] = new FunctionPoint(x, 0);
        }
    }

    // конст. созд. объекта через массив объектов
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[values.length + 5];

        double step = (rightX - leftX) / (values.length - 1);

        for (int i = 0; i < values.length; i++) {
            double x = leftX + step * i;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    // значение ф-и в точке x
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        //интервал содерж. x
        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();

            if (x >= x1 && x <= x2) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();

                // линейная интерполяция
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        if (x == points[pointsCount - 1].getX()) {
            return points[pointsCount - 1].getY();
        }

        return Double.NaN;
    }

    // кол-во точек
    public int getPointsCount() {
        return pointsCount;
    }

    // копия точки
    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= pointsCount) return null;
        return new FunctionPoint(points[index]);
    }

    // замена точки
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) return;

        if (index > 0 && point.getX() <= points[index-1].getX()) { return; }
        if (index < (pointsCount-1) && point.getX() >= points[index+1].getX()) { return; }

        points[index] = new FunctionPoint(point);
    }

    // значение абсциссы(Ох) точки с заданным номером
    public double getPointX(int index) {
        if (index < 0 || index >= pointsCount) return Double.NaN;
        return points[index].getX();
    }

    // изменение значение абсциссы(Ох) точки с заданным номером
    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) return;

        if (index > 0 && x <= points[index-1].getX()) { return; }
        if (index < (pointsCount-1) && x >= points[index+1].getX()) { return; }

        points[index].setX(x);
    }

    // значение ординаты(Оу) точки с некоторым номером
    public double getPointY(int index) {
        if (index < 0 || index >= pointsCount) return Double.NaN;
        return points[index].getY();
    }

    // изменение значение ординаты(Оу) точки с некоторым номером
    public void setPointY(int index, double y) {
        if (index < 0 || index >= pointsCount) return;
        points[index].setY(y);
    }

    // удаление заданной точки табулированной функции
    public void deletePoint(int index) {
        if (pointsCount <= 2 || index < 0 || index >= pointsCount) return;

        System.arraycopy(points, index+1, points, index, pointsCount-index-1);
        pointsCount--;
    }

    // доб. новую точку табулированной функции
    public void addPoint(FunctionPoint point) {
        // проверка на необходимость расширения
        if (pointsCount >= points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        int pos = 0;
        while (pos < pointsCount && points[pos].getX() < point.getX()) {
            pos++;
        }

        // сдвиг вправо
        System.arraycopy(points, pos, points, pos + 1, pointsCount - pos);

        // добавл новой точки
        points[pos] = new FunctionPoint(point);
        pointsCount++;
    }
}