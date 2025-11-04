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
    public static final double EPSILON = 1e-10;
    //Сравнение чисел с плавающей точкой
    //равенство
    public static boolean doubleEq(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
    //строго меньше
    public static boolean doubleLess(double a, double b) {
        return a < b - EPSILON;
    }
    //сторого больше
    public static boolean doubleGreater(double a, double b) {
        return a > b + EPSILON;
    }
    //меньше или равно
    public static boolean doubleLessOrEq(double a, double b) {
        return doubleLess(a,b) || doubleEq(a,b);
    }
    //больше или равно
    public static boolean doubleGreaterOrEq(double a, double b) {
        return doubleGreater(a,b) || doubleEq(a,b);
    }
    public double getLeftDomainBorder() {
        return points[0].getX();
    }
    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }
    //Возвращает значение функции в точке х, если точка лежит в обл. определения
    public double getFunctionValue(double x) {
        if (doubleLess(x,getLeftDomainBorder()) || doubleGreater(x,getRightDomainBorder())) {
            return Double.NaN;
        }

        for (int i = 0; i < pointsCount - 1; i++) {
            if (doubleGreaterOrEq(x,points[i].getX()) && doubleLessOrEq(x,points[i + 1].getX())) {
                if (doubleEq(x,points[i].getX())) {
                    return points[i].getY();
                }
                else if (doubleEq(x,points[i + 1].getX())) {
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
            if (doubleLessOrEq(newX,points[index - 1].getX())) {
                return;
            }
        }
        if (index < pointsCount - 1) {
            //если newX больше правой границы
            if (doubleGreaterOrEq(newX,points[index + 1].getX())) {
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
            if (doubleLessOrEq(x, points[index - 1].getX())) {
                return;
            }
        }
        if (index < pointsCount - 1) {
            //если x больше правой границы
            if (doubleGreaterOrEq(x, points[index + 1].getX())) {
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
        while (searchIndex < pointsCount && doubleGreater(point.getX(), points[searchIndex].getX())) {
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
