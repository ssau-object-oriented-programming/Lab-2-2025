package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int size; // Текущее количество точек

    // Конструкторы
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2) {
            throw new IllegalArgumentException("pointsCount must be at least 2");
        }
        if (leftX >= rightX) {
            throw new IllegalArgumentException("leftX must be less than rightX");
        }

        this.points = new FunctionPoint[pointsCount + 2]; // Запас памяти
        this.size = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            this.points[i] = new FunctionPoint(x, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("values array must have at least 2 elements");
        }
        if (leftX >= rightX) {
            throw new IllegalArgumentException("leftX must be less than rightX");
        }

        int pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount + 2]; // Запас памяти
        this.size = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            this.points[i] = new FunctionPoint(x, values[i]);
        }
    }

    /**
     * Удаляет точку с указанным индексом
     * @param index индекс удаляемой точки
     * @throws IllegalStateException если после удаления останется меньше 2 точек
     */
    public void deletePoint(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size <= 2) {
            throw new IllegalStateException("Cannot delete point - function must have at least 2 points");
        }

        // Сдвигаем точки влево, начиная с позиции после удаляемой
        System.arraycopy(points, index + 1, points, index, size - index - 1);
        points[size - 1] = null; // Помогаем сборщику мусора
        size--;

        // Если массив слишком пустой, уменьшаем его размер (но не меньше начального + 2)
        if (points.length > size * 2 && points.length > 4) {
            shrinkArray();
        }
    }

    /**
     * Добавляет новую точку с сохранением упорядоченности
     * @param point добавляемая точка
     */
    public void addPoint(FunctionPoint point) {
        // Находим позицию для вставки (сохраняя упорядоченность по x)
        int insertIndex = 0;
        while (insertIndex < size && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        // Проверяем, что точка с таким x уже не существует
        if (insertIndex < size && Math.abs(points[insertIndex].getX() - point.getX()) < 1e-10) {
            throw new IllegalArgumentException("Point with x=" + point.getX() + " already exists");
        }

        // Проверяем, нужно ли увеличивать массив
        if (size >= points.length) {
            expandArray();
        }

        // Сдвигаем точки вправо, чтобы освободить место для новой точки
        if (insertIndex < size) {
            System.arraycopy(points, insertIndex, points, insertIndex + 1, size - insertIndex);
        }

        // Вставляем копию точки (инкапсуляция)
        points[insertIndex] = new FunctionPoint(point);
        size++;
    }

    /**
     * Увеличивает размер массива при нехватке места
     */
    private void expandArray() {
        int newCapacity = points.length * 3 / 2 + 1; // Увеличиваем на 50% + 1
        FunctionPoint[] newArray = new FunctionPoint[newCapacity];
        System.arraycopy(points, 0, newArray, 0, size);
        points = newArray;
    }

    /**
     * Уменьшает размер массива при избытке места
     */
    private void shrinkArray() {
        int newCapacity = Math.max(size + 2, points.length / 2); // Не уменьшаем слишком сильно
        FunctionPoint[] newArray = new FunctionPoint[newCapacity];
        System.arraycopy(points, 0, newArray, 0, size);
        points = newArray;
    }

    // Обновленные методы доступа с учетом поля size

    public int getPointsCount() {
        return size;
    }

    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (!isValidXPosition(index, point.getX())) {
            throw new IllegalArgumentException(
                    "New x-coordinate " + point.getX() + " at index " + index +
                            " would violate the ordering of points"
            );
        }

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (!isValidXPosition(index, x)) {
            throw new IllegalArgumentException(
                    "New x-coordinate " + x + " at index " + index +
                            " would violate the ordering of points"
            );
        }

        double y = points[index].getY();
        points[index] = new FunctionPoint(x, y);
    }

    public double getPointY(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        double x = points[index].getX();
        points[index] = new FunctionPoint(x, y);
    }

    /**
     * Проверяет, что новое значение x для точки с указанным индексом
     * сохраняет упорядоченность точек
     */
    private boolean isValidXPosition(int index, double newX) {
        if (index > 0 && newX <= points[index - 1].getX()) {
            return false;
        }
        if (index < size - 1 && newX >= points[index + 1].getX()) {
            return false;
        }
        return true;
    }

    // Остальные методы (getLeftDomainBorder, getRightDomainBorder, getFunctionValue и т.д.)
    // должны использовать size вместо points.length

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[size - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (int i = 0; i < size - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();

            if (x == x1) return points[i].getY();
            if (x == x2) return points[i + 1].getY();

            if (x > x1 && x < x2) {
                return linearInterpolation(points[i], points[i + 1], x);
            }
        }

        return Double.NaN;
    }

    private double linearInterpolation(FunctionPoint p1, FunctionPoint p2, double x) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        double k = (y2 - y1) / (x2 - x1);
        return y1 + k * (x - x1);
    }

}