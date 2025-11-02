package functions;

//Класс описывает табулированную функцию
public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    //Конструктор, создающий табулированную функцию с равномерными интервалами по X. Значения функции по умолчанию равны 0.
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {

            // Инициализация массива точек
            this.pointsCount = pointsCount;
            this.points = new FunctionPoint[pointsCount];

            // Вычисление шага по X между точками
            double step = (rightX - leftX) / (pointsCount - 1);

            // Заполнение массива точек, Y = 0 по умолчанию
            for (int i = 0; i < pointsCount; i++) {
                double x = leftX + i * step;
                points[i] = new FunctionPoint(x, 0.0);
            }
        }


    // Создаёт табулированную функцию с заданными значениями, X распределяются равномерно между leftX и rightX.
    public TabulatedFunction(double leftX, double rightX, double[] values) {

        // Инициализация массива точек
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount];

        // Вычисление шага по X между точками
        double step = (rightX - leftX) / (pointsCount - 1);

        // Заполнение массива точек, Y = 0 по умолчанию
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    //Возвращает значение левой границы области определения функции.
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    //Возвращает значение правой границы области определения функции.
    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    //Возвращает значение функции в точке x.
    public double getFunctionValue(double x) {
        // Если x вне диапазона — возвращаем неопределённость
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        // Если x совпадает с одной из табличных точек
        for (int i = 0; i < pointsCount; i++) {
            if (x == points[i].getX()) {
                return points[i].getY();
            }
        }

        // Ищем интервал, в который попадает x
        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double y1 = points[i].getY();
            double x2 = points[i + 1].getX();
            double y2 = points[i + 1].getY();

            if (x > x1 && x < x2) {
                // Линейная интерполяция:
                // y = y1 + (y2 - y1) * ((x - x1) / (x2 - x1))
                return y1 + (y2 - y1) * ((x - x1) / (x2 - x1));
            }
        }

        // На всякий случай, если ничего не нашли (не должно случиться)
        return Double.NaN;
    }

    //Возвращает количество точек табулированной функции.
    public int getPointsCount() {
        return pointsCount;
    }


    //Возвращает копию точки с указанным индексом. Используется конструктор копирования для сохранения принципа инкапсуляции
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    //Заменяет точку по индексу на переданную.
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        double newX = point.getX();

        // Для индексa = 0 или index = pointsCount-1 соответствующая проверка просто пропускается.
            if (index > 0 && newX <= points[index - 1].getX()) {
                return; // нарушит порядок слева
            }
            if (index < pointsCount - 1 && newX >= points[index + 1].getX()) {
                return; // нарушит порядок справа
            }

        points[index] = new FunctionPoint(point);
        }

    //Возвращает X точки с указанным индексом.
    public double getPointX(int index) {
        return points[index].getX();
    }


    //Изменяет абсциссу точки с указанным индексом.
    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (pointsCount > 1) {
            if (index > 0 && x <= points[index - 1].getX()) {
                return; // нарушит порядок слева
            }
            if (index < pointsCount - 1 && x >= points[index + 1].getX()) {
                return; // нарушит порядок справа
            }
        }

        points[index].setX(x);
    }

    //Возвращает ординату точки с указанным индексом.
    public double getPointY(int index) {
        return points[index].getY();
    }

    //Устанавливает новое значение ординаты точки с указанным индексом.
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    //Удаляет точку табулированной функции по индексу.
    public void deletePoint(int index) {
        if (pointsCount <= 2) {
            throw new IllegalStateException("Function must have at least two points");
        }
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        // Сдвигаем все элементы после удаляемого на одну позицию влево
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);

        pointsCount--; // уменьшаем счётчик точек
    }

    //Добавляет новую точку в таблицу, сохраняя при этом порядок по X.
    public void addPoint(FunctionPoint point) {
        double newX = point.getX();

        // Проверяем, нет ли уже такой точки
        for (int i = 0; i < pointsCount; i++) {
            if (points[i].getX() == newX) {
                return; // такая точка уже есть — ничего не делаем
            }
        }

        // Если массив заполнен, создаём новый массив большего размера
        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        // Найдём, куда вставить новую точку, чтобы сохранить порядок по X
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < newX) {
            insertIndex++;
        }

        // Сдвигаем элементы вправо, освобождая место
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);

        // Вставляем копию точки (инкапсуляция!)
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }

}
