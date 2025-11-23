package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    // Конструктор с количеством точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX || pointsCount < 2) {
            throw new IllegalArgumentException();
        }
        
        this.points = new FunctionPoint[pointsCount];
        this.pointsCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);
        
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0); // создает новую точку добавляет ее в массив поинтс
        }
    }

    // Конструктор с массивом значений
    public TabulatedFunction(double leftX, double rightX, double[] values) { // задает точки когда есть y vallues массив y
        if (leftX >= rightX || values.length < 2) {
            throw new IllegalArgumentException();
        }
        
        this.points = new FunctionPoint[values.length];
        this.pointsCount = values.length;
        double step = (rightX - leftX) / (values.length - 1);
        
        for (int i = 0; i < values.length; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    // Методы области определения
    public double getLeftDomainBorder() { //левая граница
        return points[0].getX();
    }

    public double getRightDomainBorder() { // правая
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

         // Сначала проверяем, не совпадает ли x с одной из существующих точек
        for (int i = 0; i < pointsCount; i++) {
            if (Math.abs(points[i].getX() - x) < 1e-9) { // Используем машинный эпсилон для сравнения double
                return points[i].getY(); // Возвращаем соответствующий y
            }
        }

        // Если не совпадает ни с одной точкой, ищем интервал для интерполяции
        for (int i = 0; i < pointsCount - 1; i++) { // поиск промежутка в котором лежит x
            if (x >= points[i].getX() && x <= points[i + 1].getX()) { 
                double x1 = points[i].getX();
                double y1 = points[i].getY();
                double x2 = points[i + 1].getX();
                double y2 = points[i + 1].getY();
                
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        
        return Double.NaN;
    }

    // Методы работы с точками
    public int getPointsCount() { // возвращает количество точек
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) { // возврат точки для соблюдения инкапсуляции
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException(); //индекс вне массива
        }
        return new FunctionPoint(points[index]); // возвращает копию точки на позиции индекса
    }

    public void setPoint(int index, FunctionPoint point) { // замена точки по индексу
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        
        if (point.getX() <= points[index - 1].getX() || point.getX() >= points[index + 1].getX()) {
            throw new IllegalArgumentException(); // неправильный аргумент x
        }
        
        points[index] = new FunctionPoint(point); // ставится новая точка ху поинт
    }

    public double getPointX(int index) { // получает коорд х по индексу
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        return points[index].getX(); // возвращает точку х
    }

    public void setPointX(int index, double x) { // заменяет коорд х
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        
        if (x <= points[index - 1].getX() || x >= points[index + 1].getX()) { // проверка упорядоченности точки
            throw new IllegalArgumentException();
        }
        
        points[index].setX(x); // на позицию идекса заменяет х у точки стоящей на позиции индекс
    }

    public double getPointY(int index) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        return points[index].getY(); // возврат y
    }

    public void setPointY(int index, double y) { //проверка индекса
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        points[index].setY(y); // замена y 
    }

    // Методы изменения количества точек
    public void deletePoint(int index) { // удаление точки
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException();
        }
        if (pointsCount < 3) {
            throw new IllegalStateException();
        }
        // сдвигает массив влево
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);// 1(откуда копируем),2(копирование точек с позицции индекс +1)
        pointsCount--;   // удаляем точку                                                 // 3(то куда копируе), 4(то откуда начинает вставлять)
        points[pointsCount] = null;  // зануляет точку на позиции поинтс каунт            // 5(то сколько эл ов нужно скопировать)
    }

    public void addPoint(FunctionPoint point) { // добавляет точку
        // Поиск позиции для вставки
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) { //ищет место куда вставить точку
            insertIndex++;
        }
        
        // Проверка на дублирование x
        if (insertIndex < pointsCount && Math.abs(points[insertIndex].getX() - point.getX()) < 1e-9) {//нельзя добавлять ту же точку выдаст ошибку
            throw new IllegalArgumentException();     // конечное число в 10 может быть не конечным в 2 сс
        }
        
        // Проверка необходимости расширения массива
        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length + 10];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }
        
        // Сдвиг элементов вправо и вставка
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}