package functions;

public class TabulatedFunction {
    private FunctionPoint[] massiveOfPoints;
    private int amountOfElements;

    /**
     * Создает табличную функцию с равномерно распределенными точками
     * @param leftX левая граница области определения
     * @param rightX правая граница области определения
     * @param pointsCount количество точек
     */
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.amountOfElements = pointsCount;
        this.massiveOfPoints = new FunctionPoint[Math.max(amountOfElements, 0)]; // Избегаю отрицательных значений

        if (amountOfElements == 1) {
            this.massiveOfPoints[0] = new FunctionPoint(((leftX + rightX) / 2), 0);
        }

        else {
            double distance = (rightX - leftX) / (amountOfElements - 1); // Поиск интервала
            for(int i = 0; i < amountOfElements; i++) {
                double x = leftX + i * distance;
                this.massiveOfPoints[i] = new FunctionPoint(x, 0);
            }
        }
    }

    /**
     * Создает табличную функцию с равномерно распределенными точками и заданными значениями функции
     * @param leftX левая граница области определения
     * @param rightX правая граница области определения
     * @param values массив значений функции в точках
     */
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.amountOfElements = values.length;
        this.massiveOfPoints = new FunctionPoint[Math.max(amountOfElements, 0)];
        if (amountOfElements == 1) {
            this.massiveOfPoints[0] = new FunctionPoint(((leftX + rightX) / 2), values[0]);
        }
        else {
            double distance = (rightX - leftX) / (amountOfElements - 1); // Поиск интервала
            for(int i = 0; i < amountOfElements; i++) {
                double x = leftX + i * distance;
                this.massiveOfPoints[i] = new FunctionPoint(x, values[i]);
            }
        }
    }

    /**
     * Возвращает левую границу области определения функции
     * @return значение левой границы области определения
     */
    public double getLeftDomainBorder() {
        return this.massiveOfPoints[0].getX();
    }

    /**
     * Возвращает правую границу области определения функции
     * @return значение правой границы области определения
     */
    public double getRightDomainBorder() {
        return this.massiveOfPoints[this.amountOfElements - 1].getX();
    }

    /**
     * Вычисляет значение функции в заданной точке с помощью линейной интерполяции
     * @param x координата, в которой вычисляется значение функции
     * @return значение функции в точке x или Double.NaN, если x вне области определения
     */
    public double getFunctionValue(double x) {
        // Поиск интервала, содержащего x
        for (int i = 0; i < amountOfElements - 1; i++) {
            double x1 = massiveOfPoints[i].getX();
            double x2 = massiveOfPoints[i + 1].getX();

            if (x >= x1 && x <= x2) {
                // Линейная интерполяция между точками i и i+1
                double y1 = massiveOfPoints[i].getY();
                double y2 = massiveOfPoints[i + 1].getY();

                // Уравнение прямой: y = y1 + (y2 - y1) * (x - x1) / (x2 - x1)
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        // Если x точно совпадает с последней точкой
        if (x == massiveOfPoints[amountOfElements - 1].getX()) {
            return massiveOfPoints[amountOfElements - 1].getY();
        }

        return Double.NaN;
    }

    /**
     * Возвращает количество точек в табличной функции
     * @return количество точек
     */
    public int getPointsCount() {
        return this.amountOfElements;
    }

    /**
     * Возвращает копию точки по указанному индексу
     * @param index индекс точки (от 0 до pointsCount-1)
     * @return копия объекта FunctionPoint
     */
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(massiveOfPoints[index].getX(), massiveOfPoints[index].getY());
    }

    /**
     * Заменяет точку по указанному индексу на новую
     * @param index индекс заменяемой точки
     * @param point новая точка (не может быть null)
     */
    public void setPoint(int index, FunctionPoint point) {
        if (index <= 0 || index > amountOfElements || point == null) return;
        if (index > 0 && point.getX() <= massiveOfPoints[index - 1].getX()) {
            return;
        }
        if (index < amountOfElements - 1 && point.getX() >= massiveOfPoints[index + 1].getX()) {
            return;
        }  // Проверки на сохранение очередности X координат
        massiveOfPoints[index] = new FunctionPoint(point.getX(), point.getY());
    }

    /**
     * Возвращает координату X точки по указанному индексу
     * @param index индекс точки
     * @return координата X точки
     */
    public double getPointX(int index) {
        return this.massiveOfPoints[index].getX();
    }

    /**
     * Устанавливает новую координату X для точки по указанному индексу
     * @param index индекс точки
     * @param x новая координата X
     */
    public void setPointX(int index, double x) {
        if (index < 0 || index >= amountOfElements) {
            return;
        }
        if (index > 0 && x <= massiveOfPoints[index - 1].getX()) {
            return;
        }
        if (index < amountOfElements - 1 && x >= massiveOfPoints[index + 1].getX()) {
            return;
        }  // Проверки на сохранение очередности X координат
        massiveOfPoints[index] = new FunctionPoint(x, massiveOfPoints[index].getY());
    }

    /**
     * Возвращает координату Y (значение функции) точки по указанному индексу
     * @param index индекс точки
     * @return координата Y точки
     */
    public double getPointY(int index) {
        return this.massiveOfPoints[index].getY();
    }

    /**
     * Устанавливает новое значение функции (координату Y) для точки по указанному индексу
     * @param index индекс точки
     * @param y новое значение функции
     */
    public void setPointY(int index, double y) {
        if (index < 0 || index >= amountOfElements) {
            return;
        }
        massiveOfPoints[index] = new FunctionPoint(massiveOfPoints[index].getX(), y);
    }

    /**
     * Удаляет точку по индексу
     * @param index индекс удаляемой точки
     */
    public void deletePoint(int index) {
        if (amountOfElements <= 2 || index < 0 || index >= amountOfElements) {
            return;
        }

        // Сдвигаем все элементы после удаляемого влево
        for (int i = index; i < amountOfElements - 1; i++) {
            massiveOfPoints[i] = massiveOfPoints[i + 1];
        }
        massiveOfPoints[amountOfElements - 1] = null;
        amountOfElements--;
    }

    /**
     * Добавляет точку по индексу
     * @param point новая точка
     */
    public void addPoint(FunctionPoint point) {
        if (point == null) {
            return;
        }

        // Увеличиваем массив если нужно
        if (amountOfElements == massiveOfPoints.length) {
            FunctionPoint[] newPoints = new FunctionPoint[massiveOfPoints.length * 2 + 1];
            for (int i = 0; i < amountOfElements; i++) {
                newPoints[i] = massiveOfPoints[i];
            }
            massiveOfPoints = newPoints;
        }

        // Ищем место для вставки
        int pos = 0;
        while (pos < amountOfElements && massiveOfPoints[pos].getX() < point.getX()) {
            pos++;
        }

        // Проверяем на дубликат
        if (pos < amountOfElements && massiveOfPoints[pos].getX() == point.getX()) {
            return;
        }

        // Сдвигаем элементы вправо
        for (int i = amountOfElements; i > pos; i--) {
            massiveOfPoints[i] = massiveOfPoints[i - 1];
        }

        // Вставляем новую точку
        massiveOfPoints[pos] = new FunctionPoint(point.getX(), point.getY());
        amountOfElements++;
    }
}

