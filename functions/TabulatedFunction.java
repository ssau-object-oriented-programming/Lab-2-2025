package functions;

public class TabulatedFunction {
    private FunctionPoint[] points; //массив точек
    private int pointsCount;   //реальное количество точек

    //конструктор 1: leftX, rightX, pointsCount, y = 0
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        points = new FunctionPoint[pointsCount];

        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            double y = 0;
            points[i] = new FunctionPoint(x, y);
        }
    }

    //конструктор 2: leftX, rightX, значения функции values[]
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        points = new FunctionPoint[pointsCount];

        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            double y = values[i];
            points[i] = new FunctionPoint(x, y);
        }
    }

    //4 задание
    public double getLeftDomainBorder() {//левая граница области определения
        return points[0].getX();
    }

    public double getRightDomainBorder() {//правая граница области определения
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {//значение функции в точке x с линейной интерполяцией
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        for (int i = 0; i < pointsCount; i++) { //точная точка
            if (points[i].getX() == x) {
                return points[i].getY();
            }
        }

        for (int i = 0; i < pointsCount - 1; i++) {//линейная интерполяция между соседними точками
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();

            if (x > x1 && x < x2) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN;
    }


    //5 задание
    public int getPointsCount() { //количество точек
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {// получаем копию точки по индексу
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {//заменяем точку на новую если не нарушает порядок х
        double newX = point.getX();

        if (index > 0 && newX <= points[index - 1].getX()) return;
        if (index < pointsCount - 1 && newX >= points[index + 1].getX()) return;

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) { //получаем х точки
        return points[index].getX();
    }

    public void setPointX(int index, double x) {//изменить х точки если не нарушает порядок
        if (index > 0 && x <= points[index - 1].getX()) return;
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) return;

        points[index].setX(x);
    }

    public double getPointY(int index) {//получаем у точки
        return points[index].getY();
    }

    public void setPointY(int index, double y) {//изменить у точки
        points[index].setY(y);
    }
    //6 задание
    public void deletePoint(int index) {
        if (pointsCount <= 1) return; // нельзя удалить последнюю точку

        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);//сдвигаем все элементы справа от index на одну позицию влево

        pointsCount--; //теперь на одну точку меньше
    }

    public void addPoint(FunctionPoint point) {//добавляем новую точку с сохранением порядка х
        if (pointsCount == points.length) {//если массив полон создаём массив большего размера
            FunctionPoint[] newPoints = new FunctionPoint[points.length + 1];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        int insertIndex = 0; //находим позицию вставки сохраняем порядок по х
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);//сдвигаем элементы справа на 1 позицию вправо
        points[insertIndex] = new FunctionPoint(point);//вставляем копию новой точки

        pointsCount++;//увеличиваем количество точек
    }
}