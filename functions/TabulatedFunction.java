package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;
    // Конструктор1 : создает объект  фии по заданным  границам области определения и кол-ва точек для табулирования
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2) {
            pointsCount = 2; // Минимум 2 точки
        }
        if (leftX > rightX) {
            // Меняем границы местами, если они в неправильном порядке
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }

        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount];

        // Если после корректировки границы совпадают, делаем небольшой сдвиг
        if (leftX == rightX) {
            rightX = leftX + 1.0; // Сдвигаем правую границу на 1
        }
        // Вычисляем шаг
        double step = (rightX - leftX) / (pointsCount - 1);
        // Создаем точки с равномерным распределением по x
        for (int i = 0; i < pointsCount; i++) {
            double currentX = leftX + i * step;
            points[i] = new FunctionPoint(currentX, 0.0);
        }
    }

    // Конструктор 2: вместо кол-ва точек получает значения фии в виде массива
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        if (values == null) {
            values = new double[]{0.0, 1.0}; // Значения по умолчанию
        }
        if (values.length < 2) {
            // Если точек меньше 2, создаем массив с 2 точками
            double[] newValues = new double[2];
            newValues[0] = (values.length > 0) ? values[0] : 0.0;
            newValues[1] = (values.length > 0) ? values[0] + 1.0 : 1.0;
            values = newValues;
        }
        if (leftX > rightX) {
            // Меняем границы местами
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }

        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount];
        // Если границы совпадают, делаем сдвиг
        if (leftX == rightX) {
            rightX = leftX + 1.0;
        }
        // Вычисляем шаг между точками
        double step = (rightX - leftX) / (pointsCount - 1);
        // Создаем точки с заданными значениями y
        for (int i = 0; i < pointsCount; i++) {
            double currentX = leftX + i * step;
            points[i] = new FunctionPoint(currentX, values[i]);
        }
    }
        public double getLeftDomainBorder() { // возвращает значение левой границы
            return points[0].getX();
        }

        public double getRightDomainBorder() { //возвращает значение правой границы
            return points[pointsCount - 1].getX();
        }

        // Метод для получения значения функции
        public double getFunctionValue(double x) {
            if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
                return Double.NaN;
            }

            // Поиск интервала, в котором находится x
            for (int i = 0; i < pointsCount - 1; i++) {
                double x1 = points[i].getX();
                double x2 = points[i + 1].getX();

                if (x >= x1 && x <= x2) {
                    // Интерполяция
                    double y1 = points[i].getY();
                    double y2 = points[i + 1].getY();

                    return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
                }
            }

            return Double.NaN;
        }

    public int getPointsCount() { // возвращает колво точек
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) { // возвращает копию точки, соответствующей переданному индексу
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) { // замена указанной точки на переданную
        // Проверка корректности позиции
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            return; // Не установить точку левее предыдущей
        }
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) {
            return; // Не установить точку правее следующей
        }

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) { //возвращает значение абсциссы точки
        return points[index].getX();
    }

    public void setPointX(int index, double x) { //изменяет значение абсциссы точки
        // Проверка корректности позиции
        if (index > 0 && x <= points[index - 1].getX()) {
            return;
        }
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) {
            return;
        }

        points[index].setX(x);
    }

    public double getPointY(int index) { //возвращает значение ординаты точки
        return points[index].getY();
    }

    public void setPointY(int index, double y) { //изменияет значение ординаты точки
        points[index].setY(y);
    }

    public void deletePoint(int index) { //удаляет заданную точку
        if (pointsCount <= 2) {
            return; // Нельзя удалить точку, если останется меньше 2 точек
        }

        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) { //добавляет новую точку
        int IndexIns = 0;
        while (IndexIns < pointsCount && points[IndexIns].getX() < point.getX()) {
            IndexIns++;
        }

        // Проверка на дублирование
        if (IndexIns < pointsCount && points[IndexIns].getX() == point.getX()) {
            return; // Точка с таким x уже существует
        }
        // Увеличение массива
        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        // Сдвиг элементов для освобождения места
        System.arraycopy(points, IndexIns, points, IndexIns + 1, pointsCount - IndexIns);

        // Вставка новой точки
        points[IndexIns] = new FunctionPoint(point);
        pointsCount++;
    }
}
