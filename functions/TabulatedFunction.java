package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    // Конструктор создания объекта табулированной функции через кол-во точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount*2];

        double step = (rightX - leftX) / (pointsCount - 1); // Задание шага

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            points[i] = new FunctionPoint(x, 0);    // создание точек с равным шагом step через интервал x
        }
    }

    // Конструктор создания объекта табулированной функции через массив значений
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[values.length*2];

        double step = (rightX - leftX) / (values.length - 1);   // Задание шага

        for (int i = 0; i < values.length; i++) {
            double x = leftX + step * i;
            points[i] = new FunctionPoint(x, values[i]);    // создание точек с равным шагом step через интервал x
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    // интерпол
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) { return Double.NaN; } // Вне графика
        if (x == getLeftDomainBorder()) { return points[0].getY(); }    // на левом конце
        if (x == getRightDomainBorder()) { return points[pointsCount - 1].getY(); }     // на правом конце

        // внутри графика
        for (int i = 0; i < pointsCount; i++) {
            double x1 = points[i].getX();
            double x2 = points[i+1].getX();

            if (x >= x1 && x <= x2) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();

                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }

    // кол-во точек
    public int getPointsCount() {
        return pointsCount;
    }

    // копия точки
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    // замена точки
    public void setPoint(int index, FunctionPoint point) {
        // если индекс > 0 и новая точка находится вне левой точки, то скип
        if (index > 0 && point.getX() <= points[index-1].getX()) { return; }

        // если индекс < кол-во точек и новая точка находится вне правой точки, то скип
        if (index < (pointsCount-1) && point.getX() >= points[index+1].getX()) { return; }

        points[index] = new FunctionPoint(point);   // если все норм, то меняем точку
    }

    // значение абсциссы точки с указанным номером
    public double getPointX(int index) {
        return points[index].getX();
    }

    // изменение значение абсциссы точки с указанным номером
    public void setPointX(int index, double x) {
        // если индекс > 0 и новая точка находится вне левой точки, то скип
        if (index > 0 && x <= points[index-1].getX()) { return; }

        // если индекс < кол-во точек и новая точка находится вне правой точки, то скип
        if (index < (pointsCount-1) && x >= points[index+1].getX()) { return; }

        points[index].setX(x);
    }

    // значение ординаты точки с указанным номером
    public double getPointY(int index) {
        return points[index].getY();
    }

    // изменение значение ординаты точки с указанным номером.
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    // удаление заданной точки табулированной функции.
    public void deletePoint(int index) {
        if (pointsCount <= 2) { return; }   // если точек всего 2 - выйти
        System.arraycopy(points, index+1,points, index, pointsCount-index-1);
        pointsCount--;
    }

    // добавить новую точку табулированной функции
    public void addPoint(FunctionPoint point) {
        FunctionPoint[] newPoints = new FunctionPoint[pointsCount*2];

        // ищем позицию между точками
        int pos = 0;
        while (pos < pointsCount && points[pos].getX() < point.getX()) {
            pos++;
        }

        // копируем до нужной позиции
        System.arraycopy(points, 0, newPoints, 0, pos);

        // вставляем точку
        newPoints[pos] = point;

        // копируем остаток
        System.arraycopy(points, pos, newPoints, pos+1, pointsCount - pos);
        // меняем обратно и увеличиваем кол-во точек
        points = newPoints;
        pointsCount++;
    }
}