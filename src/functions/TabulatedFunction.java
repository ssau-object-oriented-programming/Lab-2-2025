// Задание 3
package functions;

public class TabulatedFunction {
    private int size; // количество точек
    private FunctionPoint[] points; // упорядоченный массив точек

    // Табулирование на интервале [leftX; rightX] с заданным числом точек
    // Первый конструктор
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.size = pointsCount;
        this.points = new FunctionPoint[pointsCount];

        // Постоянный шаг по x
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }

    // Табулирование на [leftX; rightX] по массиву значений values
    //Второй конструктор
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.size = values.length;  //Количество точек
        this.points = new FunctionPoint[size];

        double step = (rightX - leftX) / (size - 1);
        for (int i = 0; i < size; ++i) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    // Задание 4

    // Левая граница области определения x
    public double getLeftDomainBorder() {
        if (size == 0) {
            return Double.NaN; // Если точек нет
        }
        return points[0].getX();
    }

    // Правая граница области определения x
    public double getRightDomainBorder() {
        if (size == 0) {
            return Double.NaN;
        }
        return points[size - 1].getX();
    }


    // Метод, возвращающий значение функции в точке x с использованием линейной интерполяции
    public double getFunctionValue(double x) {
        double result = Double.NaN; // значение по умолчанию вне области определения

        double left = points[0].getX();
        double right = points[size - 1].getX();

        // Должно выполняться условие что  x внутри отрезка [left, right]
        if (x >= left && x <= right) {

            // Если х совпадает с абсциссой из таблицы
            for (int i = 0; i < size; ++i) {
                if (x == points[i].getX()) {
                    result = points[i].getY();
                    break; // если нашли, то выходим из цикла
                }
            }

            // Если точного совпадения нет, тогда интерполируем
            if (Double.isNaN(result)) {
                for (int i = 0; i < size - 1; ++i) {
                    double x0 = points[i].getX();
                    double x1 = points[i + 1].getX();
                    if (x >= x0 && x <= x1) {
                        double y0 = points[i].getY();
                        double y1 = points[i + 1].getY();
                        result = y0 + (y1 - y0) * (x - x0) / (x1 - x0); // линейная интерполяция
                        break;
                    }
                }
            }
        }
        return result;
    }


// Задание 5

    // Метод возвращает количество точек
    public int getPointsCount() {
        return size;
    }

    // Метод возвращает копию точки, соответствующей переданному индексу
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    // Сеттер меняющий точки с табулированной функции на переданную
    public void setPoint(int index, FunctionPoint point) {
        if (point == null)
            return;

        double newX = point.getX();

        if (index > 0) {
            double leftX = points[index - 1].getX();
            if (!(newX > leftX))
                return;
        }

        if (index < size - 1) {
            double rightX = points[index + 1].getX();
            if (!(newX < rightX))
                return;
        }

        //  Сохраняем копию переданной точки
        points[index] = new FunctionPoint(point);
    }

    // Метод, возвращающий абсциссу точки по индексу
    public double getPointX(int index) {

        if (index >= 0 && index < size) {
            return points[index].getX();
        } else {
            return Double.NaN; // если некорректный индекс
        }
    }

    // Метод, изменяющий абсциссу точки по индексу
    public void setPointX(int index, double x) {
    boolean canSet;

    if (index < 0 || index >= size) {
        return; // за пределами массива — выходим
    }

    if (size == 1) {
        canSet = true;
    } else if (index == 0) {
        double nextX = points[1].getX();
        canSet = (x < nextX);
    } else if (index == size - 1) {
        double prevX = points[size - 2].getX();
        canSet = (x > prevX);
    } else {
        double leftX  = points[index - 1].getX();
        double rightX = points[index + 1].getX();
        canSet = (x > leftX && x < rightX);
    }

    if (canSet) {
        points[index].setX(x);
    }
}


    public double getPointY(int index) {return points[index].getY();}
    public void setPointY(int index, double y) {points[index].setY(y);}

// Задание 6

    // Удаляет точку по индексу со сдвигом влево
   public void deletePoint(int index) {
    if (index < 0 || index >= size) return;   // если индекс вне диапазона — выходим
    System.arraycopy(points, index + 1, points, index, size - index - 1);
    points[--size] = null;
}
    // Добавление новой точки с сохранением порядка по x
   public void addPoint(FunctionPoint point) {
    if (point == null) return;

    double x = point.getX();

    // Находим первый индекс, где points[i].x >= x и  запрещаем дубликат x
    int insertIndex = 0;
    while (insertIndex < size && points[insertIndex].getX() < x) {
        insertIndex++;
    }
    if (insertIndex < size && points[insertIndex].getX() == x) {
        return;}

    //  Расширение буфера
    if (size >= points.length) {
        int newCapacity = (points.length > 0) ? (points.length * 3 / 2 + 1) : 2; // min = 2
        FunctionPoint[] newArray = new FunctionPoint[newCapacity];
        System.arraycopy(points, 0, newArray, 0, size);
        points = newArray;
    }

    if (insertIndex < size) {
        System.arraycopy(points, insertIndex, points, insertIndex + 1, size - insertIndex);
    }
    points[insertIndex] = new FunctionPoint(point);
    size++;
}
}

