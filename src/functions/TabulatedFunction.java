package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int size;

    // Конструктор, принимающий следующие параметры: левая граница leftX, правая граница rightX, количество точек pointsCount на промежутке [leftX ; rightX], создающий массив точек, где расстояние между иксами - равные интервалы, а y = 0
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        size = pointsCount;
        points = new FunctionPoint[size];
        double step = (rightX - leftX) / (size - 1);
        // Цикл создаёт точки с равным шагом и y = 0
        for (int i = 0; i < (size - 1); i++) {
            points[i] = new FunctionPoint(leftX + i*step, 0);
        }
        // Последняя точка задаётся явно, чтобы избежать проблемы, когда шаг является дробью в периоде (1.66667). В этом случае последняя точка будет правой границей
        points[size-1] = new FunctionPoint(rightX, 0);
    }

    // Конструктор, принимающий следующие параметры: левая граница leftX, правая граница rightX, значения функции values на промежутке [leftX ; rightX], создающий массив точек, где расстояние между иксами - равные интервалы, а y - значения из массива values
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        size = values.length;
        points = new FunctionPoint[size];
        double step = (rightX - leftX) / (size - 1);
        // Цикл создаёт точки с равным шагом и заданными значениями y
        for (int i = 0; i < (size - 1); i++) {
            points[i] = new FunctionPoint(leftX + i*step, values[i]);
        }
        // Последняя точка задаётся явно, чтобы избежать проблемы, когда шаг является дробью в периоде (1.66667). Последняя точка всегда является правой границей
        points[size-1] = new FunctionPoint(rightX, values[size-1]);
    }

    //Метод возвращает координату X первой точки (левая граница области определения)
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    //Метод возвращает координату X последней точки (правая граница области определения)
    public double getRightDomainBorder() {
        return points[size - 1].getX();
    }

    //Метод возвращает значение функции в точке X
    public double getFunctionValue(double x) {
        // Проверяется, что X лежит в области определения функции
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            for (int i = 1; i < size; i++) {
                // Если X левее, чем X точки points[i], то рассматривается формула прямой от двух точек points[i-1] и points[i]
                if (x <= points[i].getX()) {
                    double y1 = points[i-1].getY();
                    double y2 = points[i].getY();
                    double x1 = points[i-1].getX();
                    double x2 = points[i].getX();
                    return y1 + (y2-y1) * (x-x1) / (x2-x1);
                }
            }
        }
        return Double.NaN;
    }

    //Метод возвращает размер функции (количество точек)
    public int getPointsCount() {
        return size;
    }

    // Метод возвращает копию точки, соответствующую переданному индексу
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    // Метод заменяет указанную точку табулированной функции на переданную
    public void setPoint(int index, FunctionPoint point) {
        if (index > 0 && index < (size-1) && point.getX() > points[index-1].getX() && point.getX() < points[index+1].getX()) {
            points[index] = new FunctionPoint(point);
        }
    }

    // Метод возвращает значение абсциссы точки с указанным номером
    public double getPointX(int index) {
        return points[index].getX();
    }

    // Метод изменяет значение абсциссы точки с указанным номером
    public void setPointX(int index, double x) {
        if (index > 0 && index < (size-1) && x > points[index-1].getX() && x < points[index+1].getX()) {
            points[index].setX(x);
        }
    }

    // Метод возвращает значение ординаты точки с указанным номером
    public double getPointY(int index) {
        return points[index].getY();
    }

    // Метод изменяет значение ординаты точки с указанным номером
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    // Метод удаляет заданную точку табулированной функции по индексу index
    public void deletePoint(int index) {
        if (size > 2) {
            System.arraycopy(points, index+1, points, index, size - index - 1);
            size--;
        }
    }

    // Метод добавляет заданную точку табулированной функции
    public void addPoint(FunctionPoint point) {
        // Если количество точек равно размеру массива, создаётся новый массив размером в 2 раза больше, в него копируются элементы массива points, а после этого points приравнивается к нему
        if (size == points.length) {
            FunctionPoint[] tempPoints = new FunctionPoint[size * 2];
            System.arraycopy(points, 0, tempPoints, 0, size);
            points = tempPoints;
        }
        // Если координата X больше правой границы области определения, то точка point просто «кладётся» в points[size]
        if (point.getX() > getRightDomainBorder()) {
            points[size] = new FunctionPoint(point);
        } else {
            for (int i = 0; i < size; i++) {
                // Иначе перебираются все точки массива и проверяется, левее ли точка point, чем рассматриваемая точка points[i]
                if (point.getX() < points[i].getX()) {
                    // Если это так, то все элементы массива правее точки points[i] и сама эта точка копируются со сдвигом вправо в этот же массив. На место точки points[i] ставится новый объект с координатами точки points
                    System.arraycopy(points, i, points, i+1, size-i);
                    points[i] = new FunctionPoint(point);
                    break;
                }
            }
        }
        size++;
    }
}