package functions;

public class TabulatedFunction {
    private FunctionPoint[] points; //Массив для хранения точек
    private int pointsCount; //Счетчик точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount) { //Конструктор по количеству точек
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount + 10]; //Запас места
        double step = (rightX - leftX) / (pointsCount - 1); //Задаем шаг
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values) { //Конструктор по значениям функции в виде массива
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount + 10]; //Запас места
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }
    public double getLeftDomainBorder() { //Метод для определения левой границы
        return points[0].getX();
    }

    public double getRightDomainBorder() { //Метод для определения правой границы
        return points[pointsCount - 1].getX();
    }
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder() || pointsCount == 0) {
            return Double.NaN;
        }
        for (int i = 0; i < pointsCount - 1; i++) { //Ищем интервал, в котором содержится х
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();

            if (x >= x1 && x <= x2) { // Линейная интерполяция
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }
    public int getPointsCount() { //Получение количества точек
        return pointsCount;
    }
    public FunctionPoint getPoint(int index) { // Возврат копии точки
        if (index >= 0 && index < pointsCount) {
            return new FunctionPoint(points[index]);
        }
        else{
            return null;
        }
    }
    public void setPoint(int index, FunctionPoint point) { //Замена значения абсциссы с указанным номером
        if (index < 0 || index >= pointsCount) {
            return;
        }
        double newX = point.getX();
        if (pointsCount == 1) { // Если всего 1 точка
            points[0] = new FunctionPoint(point);
            return;
        }
        if (index == 0) { // Проверка для первого элемента
            if (newX < points[1].getX()) { // строго меньше следующего
                points[0] = new FunctionPoint(point);
            }
        }
        else if (index == pointsCount - 1) { // Проверка для последнего элемента
            if (newX > points[pointsCount - 2].getX()) { // строго больше предыдущего
                points[pointsCount - 1] = new FunctionPoint(point);
            }
        }
        else { // Проверка для средних элементов
            if (newX > points[index - 1].getX() && newX < points[index + 1].getX()) {
                points[index] = new FunctionPoint(point);
            }
        }
    }
    public double getPointX(int index) { //Возврат значения абсциссы с указанным номером
        return points[index].getX();
    }

    public void setPointX(int index, double x) { //Замена значения абсциссы с указанным номером
        points[index].setX(x);
    }

    public double getPointY(int index) { //Возврат значения ординаты с указанным номером
        return points[index].getY();
    }

    public void setPointY(int index, double y) { //Замена значения ординаты с указанным номером
        points[index].setY(y);
    }
    public void deletePoint(int index) {
        if (index >= 0 && index < pointsCount) { // Сдвиг точек влево, начиная с удаляемой
            System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
            pointsCount--;
        }
    }
    public void addPoint(FunctionPoint point) {
        if (pointsCount >= points.length) { // Если массив заполнен, увеличиваю его
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }
        int insertIndex = 0; // Нахожу позицию для вставки
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }
        if (insertIndex < pointsCount && // Проверяю, не существует ли уже точка с таким X
                points[insertIndex].getX() == point.getX()) {
            return; // Точка с таким X уже существует
        }
        // Сдвиг точек вправо
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
    public void printTabulatedFunction() {

        int pointsCount = getPointsCount();
        for (int i = 0; i < pointsCount; i++) {
            double x = getPointX(i);
            double y = getPointY(i);
            System.out.println("x = " + x + ", y = " + y);
        }
    }
}
