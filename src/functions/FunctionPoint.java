// Задание 2
package functions;
public class FunctionPoint {
    private double x;
    private double y;
    // Конструктор
    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // конструктор копирования
    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }
    // конструктор по умолчанию (0, 0)
    public FunctionPoint() {
        this(0.0, 0.0);
    }

        // Геттер для координаты x
    public double getX() {
        return x;
    }
        // Геттер для координаты y
    public double getY() {
        return y;
    }
        // Сеттер для координаты x
    public void setX(double x) {
        this.x = x;
    }
        // Сеттер для координаты y
    public void setY(double y) {
        this.y = y;
    }
}