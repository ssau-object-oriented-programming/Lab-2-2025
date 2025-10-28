package functions;

public class TabulatedFunction {
    private FunctionPoint[] arrPoints; // массив для хранения точек
    private int pointsCount; // текущее количество точек в функции

    // конструктор 1: создает функцию с равномерным распределением точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        this.pointsCount = pointsCount;

        // создаем массив точно по размеру количества точек
        this.arrPoints = new FunctionPoint[pointsCount];
        // вычисляем шаг между точками - расстояние между соседними точками по x
        double x_step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * x_step;
            arrPoints[i] = new FunctionPoint(x, 0);
        }
    }

    // конструктор 2: создает функцию с заданными значениями y
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        // количество точек равно длине массива значений
        this.pointsCount = values.length;
        this.arrPoints = new FunctionPoint[pointsCount];

        // вычисляем шаг между точками
        double x_step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * x_step;
            // используем значение из массива для y-координаты
            arrPoints[i] = new FunctionPoint(x, values[i]);
        }
    }

    // метод возвращает левую границу области определения
    // область определения - интервал от самой левой до самой правой точки
    public double getLeftDomainBorder() {
        return arrPoints[0].getX();
    }

    // метод возвращает правую границу области определения
    public double getRightDomainBorder() {
        return arrPoints[pointsCount - 1].getX();
    }

    // метод вычисляет значение функции в точке x с помощью линейной интерполяции
    // возвращает Double.NaN, если x вне области определения
    public double getFunctionValue(double x) {
        // проверяем, находится ли x в области определения функции
        // если x меньше левой границы или больше правой границы
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN; // возвращаем неопределенность
        }

        // ищем интервал в который попадает x
        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = arrPoints[i].getX();
            double x2 = arrPoints[i + 1].getX();

            // проверяем, попадает ли x в интервал
            if (x >= x1 && x <= x2) {
                double y1 = arrPoints[i].getY();
                double y2 = arrPoints[i + 1].getY();

                // это уравнение прямой, проходящей через две точки(интерполяция)
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN;
    }

    // метод возвращает количество точек в функции
    public int getPointsCount() {
        return pointsCount;
    }

    // метод возвращает копию точки по указанному индексу
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(arrPoints[index]);
    }

    // метод заменяет точку по указанному индексу
    public void setPoint(int index, FunctionPoint point) {

        // если точка не первая, проверяем что ее x больше x предыдущей точки
        if (index > 0 && point.getX() <= arrPoints[index - 1].getX()) {
            return;
        }

        // если точка не последняя, проверяем что ее x меньше x следующей точки
        if (index < pointsCount - 1 && point.getX() >= arrPoints[index + 1].getX()) {
            return;
        }

        // заменяем точку на копию переданной точки
        arrPoints[index] = new FunctionPoint(point);
    }

    // метод возвращает координату x точки по указанному индексу
    public double getPointX(int index) {
        return arrPoints[index].getX();
    }

    // метод изменяет координату x точки по указанному индексу
    public void setPointX(int index, double x) {
        if (index > 0 && x <= arrPoints[index - 1].getX()) {
            return;
        }
        if (index < pointsCount - 1 && x >= arrPoints[index + 1].getX()) {
            return;
        }

        arrPoints[index].setX(x);
    }

    // метод возвращает координату y точки по указанному индексу
    public double getPointY(int index) {
        return arrPoints[index].getY();
    }

    // метод изменяет координату y точки по указанному индексу
    // изменение y не влияет на порядок точек, поэтому проверки не нужны
    public void setPointY(int index, double y) {
        arrPoints[index].setY(y);
    }

    // метод удаляет точку по указанному индексу
    public void deletePoint(int index) {

        // проверяем валидность индекса
        if (index < 0 || index >= pointsCount){
            return;
        }
        int elementsToMove = pointsCount - index - 1;

        // сдвигаем элементы влево, начиная со следующей после удаляемой
        if (elementsToMove > 0) {
            System.arraycopy(arrPoints, index + 1,arrPoints, index, elementsToMove);
        }
        // очищаем последний эл-т массива
        arrPoints[pointsCount - 1] = null;
        // уменьшаем счетчик
        pointsCount--;
    }

    // метод добавляет новую точку в функцию
    public void addPoint(FunctionPoint point) {
        int insertIndex = 0;

        // ищем первую точку, у которой x больше или равен x добавляемой точки
        while (insertIndex < pointsCount && arrPoints[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        // проверяем, не существует ли уже точка с таким x
        // используем сравнение с небольшой погрешностью для вещественных чисел
        if (insertIndex < pointsCount && Math.abs(arrPoints[insertIndex].getX() - point.getX()) < 1e-10) {
            return;
        }

        // проверяем, нужно ли расширять массив
        // если массив заполнен, увеличиваем его в 2 раза
        if (pointsCount >= arrPoints.length) {
            FunctionPoint[] newPoints = new FunctionPoint[arrPoints.length * 2];

            // копируем существующие точки в новый массив
            System.arraycopy(arrPoints, 0, newPoints, 0, pointsCount);
            arrPoints = newPoints;
        }

        // сдвигаем элементы для освобождения места для новой точки
        // сдвигаем все элементы начиная с insertIndex на одну позицию вправо
        System.arraycopy(arrPoints, insertIndex, arrPoints, insertIndex + 1, pointsCount - insertIndex);

        // вставляем новую точку (копию) в освободившееся место
        arrPoints[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}

