package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;  //массив точек
    private int  pointsCount; // количество точек
    private int pointsLimit;  // вместимость массива

    //Конструкторы
    //Создает объект табул. функции по заданным параметрам
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {

        this.pointsCount = pointsCount;
        this.pointsLimit = pointsCount + 10;
        this.points = new FunctionPoint[pointsLimit];

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }
    //Создает объект табул. функции по заданным параметрам
    public TabulatedFunction(double leftX, double rightX, double[] values) {

        this.pointsCount = values.length;
        this.pointsLimit = values.length + 10;
        this.points = new FunctionPoint[pointsLimit];

        double step = (rightX - leftX) / (values.length - 1);

        for (int i = 0; i < values.length; i++) {
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

    //Возвращает значение функции в точке х, если точка лежит в обл. определения
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (int i = 0; i < pointsCount - 1; i++) {
            if (x >= points[i].getX() && x <= points[i + 1].getX()) {
                if (x == points[i].getX()) {
                    return points[i].getY();
                }
                else if (x == points[i + 1].getX()) {
                    return points[i + 1].getY();
                }
                else {
                    return interpolation(x, points[i], points[i + 1]);
                }
            }
        }
        return Double.NaN;
    }

    // Линейная интерполяция
    private double interpolation(double x, FunctionPoint p1, FunctionPoint p2) {
        return p1.getY() + (x - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    //Метод возвращает количество точек
    public int getPointsCount() {
        return pointsCount;
    }

    //Метод возвращает копию точки, соответствующей данному индексу
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    //Метод заменяет указанную точку на переданную
    public void setPoint(int index, FunctionPoint point) {
        double newX = point.getX();

        if (index > 0) {
            //если newX меньше левой границы
            if (newX <= points[index - 1].getX()) {
                return;
            }
        }
        if (index < pointsCount - 1) {
            //если newX больше правой границы
            if (newX >= points[index + 1].getX()) {
                return;
            }
        }
        //если Х лежит в нужном интервале, создаем новую точку
        points[index] = new FunctionPoint(point);
    }
    //Метод возвращает значение абсциссы точки с указанным номером
    public double getPointX(int index) {
        return points[index].getX();
    }
    //Метод возвращает значение ординаты точки с указанным номером
    public double getPointY(int index) {
        return points[index].getY();
    }
    //Метод изменяет значение абсциссы точки с указанным номером
    public void setPointX(int index, double x) {
        if (index > 0) {
            //если x меньше левой границы
            if (x <= points[index - 1].getX()) {
                return;
            }
        }
        if (index < pointsCount - 1) {
            //если x больше правой границы
            if (x >= points[index + 1].getX()) {
                return;
            }
        }
        //создаем новую точку с новым Х и старым Y
        points[index] = new FunctionPoint(x, getPointY(index));
    }

    //Метод изменяет значение ординаты точки с указанным номером
    public void setPointY(int index, double y) {
        points[index] = new FunctionPoint(getPointX(index), y);
    }

    //Метод удаления точки функции
    public void deletePoint(int index) {
        if (pointsCount <= 2) {
            return; // нельзя удалять, т.к. мало точек
        }
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;
        points[pointsCount] = null;
    }
    //Метод добавления новой точки
    public void addPoint(FunctionPoint point) {

        int searchIndex = 0;
        while (searchIndex < pointsCount && point.getX() > points[searchIndex].getX()) {
            searchIndex++;
        }

        if (pointsCount == pointsLimit) {
            pointsLimit = pointsLimit * 2;
            FunctionPoint[] newPoints = new FunctionPoint[pointsLimit];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }
        System.arraycopy(points, searchIndex, points, searchIndex + 1, pointsCount - searchIndex);
        points[searchIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}
