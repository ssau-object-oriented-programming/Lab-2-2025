package functions;

public class TabulatedFunction {

    private FunctionPoint[] points; //массив типа FunctionPoint
    private int pointsCount;


    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount; //сохраняем колво точек
        this.points = new FunctionPoint[pointsCount]; //и создаём массив нужного размера

        double step = (rightX - leftX) / (pointsCount - 1); //расстояние между точками
        for (int i = 0; i < pointsCount; i++) { //проходимс по всем точкам
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0); //новая точка в масив
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[values.length];

        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].get_x();  //х первой точки
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].get_x();  //х последней точки
    }

    public double getFunctionValue(double x) {
        //если x норм - ищем интервал и интерполирум
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            for (int i = 0; i < pointsCount - 1; i++) {
                double x1 = points[i].get_x();
                double x2 = points[i + 1].get_x();

                if (x >= x1 && x <= x2) {
                    double y1 = points[i].get_y();
                    double y2 = points[i + 1].get_y();
                    return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
                }
            }
        }
        return Double.NaN;
    }

    //возвращает количество точек
    public int getPointsCount() {
        return pointsCount;
    }

    // возвращает копию точки по индексу
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    //замена точку по индексу
    public void setPoint(int index, FunctionPoint point) {
        // проверка что x между соседними точками
        if (index > 0 && point.get_x() <= points[index - 1].get_x()) {
            return;  //x меньше предыдущей точки
        }
        if (index < pointsCount - 1 && point.get_x() >= points[index + 1].get_x()) {
            return;  //x больше следующей точки
        }

        points[index] = new FunctionPoint(point);
    }

    //возвращаем x точки по индексу
    public double getPointX(int index) {
        return points[index].get_x();
    }

    //изменяет х точки по индексу
    public void setPointX(int index, double x) {
        //если х правильный
        if (index > 0 && x <= points[index - 1].get_x()) {
            return;  //x меньше предыдущей точки
        }
        if (index < pointsCount - 1 && x >= points[index + 1].get_x()) {
            return;  //x больше следующей точки
        }

        points[index].set_x(x);  // изменяем х
    }


    public double getPointY(int index) {
        return points[index].get_y(); //получает у точки по индексу
    }

    public void setPointY(int index, double y) {
        points[index].set_y(y);  //изменяем у точки по индексу
    }

    //удаление точки по индексу
    public void deletePoint(int index) {
        for (int i = index; i < pointsCount - 1; i++) {
            points[i] = points[i + 1]; //сдвиг точек влево
        }
        pointsCount--;  //уменьшаем количество точек
        points[pointsCount] = null;  //очищаем последнюю ссылку
    }

    //добавление точки
    public void addPoint(FunctionPoint point) {
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].get_x() < point.get_x()) {
            insertIndex++; //позиция для вставки
        }

        //проверка что точка с таким X еще не существует
        if (insertIndex < pointsCount && points[insertIndex].get_x() == point.get_x()) {
            return;  // точка с таким X уже есть
        }

        //увеличиваем массив в двое
        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        //сдвг точки
        for (int i = pointsCount; i > insertIndex; i--) {
            points[i] = points[i - 1];
        }

        //вставка новой точки
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}