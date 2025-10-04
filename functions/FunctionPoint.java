package functions;

public class FunctionPoint {
    private double x;
    private double y;

    // Создание объекта по заданным точкам
    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Создание копии объекта
    public FunctionPoint(FunctionPoint point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    // Конструктор с параметрами заданными по умолчанию
    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
