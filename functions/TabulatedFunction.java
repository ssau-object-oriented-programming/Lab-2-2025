package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;
    
    // Конструктор с количеством точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2) {
            pointsCount = 2; // Минимум 2 точки
        }
        if (leftX >= rightX) {
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }
        
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount + 5]; // Запас места
        
        double step = (rightX - leftX) / (pointsCount - 1);
        
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0); // Значения по умолчанию 0
        }
    }
    
    // Конструктор с массивом значений
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount + 5];
        
        double step = (rightX - leftX) / (pointsCount - 1);
        
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    // Методы для работы с функцией
    public double getLeftDomainBorder() {
        return points[0].getX();
    }
    
    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }
    
 public double getFunctionValue(double x) {
    final double EPSILON = 1e-10; // Машинный эпсилон для сравнения double
    
    if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
        return Double.NaN;
    }
    
    for (int i = 0; i < pointsCount - 1; i++) {
        double x1 = points[i].getX();
        double x2 = points[i + 1].getX();
        
        // Проверяем точное совпадение с x1
        if (Math.abs(x - x1) < EPSILON) {
            return points[i].getY();
        }
        
        // Проверяем точное совпадение с x2
        if (Math.abs(x - x2) < EPSILON) {
            return points[i + 1].getY();
        }
        
        // Проверяем попадание в интервал (x1, x2)
        if (x > x1 && x < x2) {
            double y1 = points[i].getY();
            double y2 = points[i + 1].getY();
            return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
        }
    }
    
    return Double.NaN;
}

    // Методы для работы с точками
    public int getPointsCount() {
        return pointsCount;
    }
    
    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= pointsCount) {
            return null;
        }
        return new FunctionPoint(points[index]); // Возвращаем копию
    }
    
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) {
            return;
        }
        
        // Проверка что x находится между соседями
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            return;
        }
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) {
            return;
        }
        
        points[index] = new FunctionPoint(point); // Сохраняем копию
    }
    
    public double getPointX(int index) {
        if (index < 0 || index >= pointsCount) {
            return Double.NaN;
        }
        return points[index].getX();
    }
    
    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) {
            return;
        }
        // Используем существующий метод setPoint для проверок
        FunctionPoint temp = new FunctionPoint(x, points[index].getY());
        setPoint(index, temp);
    }
    
    public double getPointY(int index) {
        if (index < 0 || index >= pointsCount) {
            return Double.NaN;
        }
        return points[index].getY();
    }
    
    public void setPointY(int index, double y) {
        if (index < 0 || index >= pointsCount) {
            return;
        }
        points[index].setY(y);
    }

    // Методы изменения количества точек
    public void deletePoint(int index) {
        if (index < 0 || index >= pointsCount || pointsCount <= 2) {
            return; // Нельзя удалить если точек меньше 2
        }
        
        // Сдвигаем точки влево
        for (int i = index; i < pointsCount - 1; i++) {
            points[i] = points[i + 1];
        }
        pointsCount--;
    }
    
    public void addPoint(FunctionPoint point) {
        // Поиск позиции для вставки (сохраняем порядок по x)
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }
        
        // Проверяем нужно ли расширять массив
        if (pointsCount >= points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length + 10];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }
        
        // Сдвигаем точки вправо
        for (int i = pointsCount; i > insertIndex; i--) {
            points[i] = points[i - 1];
        }
        
        // Вставляем новую точку (копию)
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}
