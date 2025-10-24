package functions;

public class FunctionPoint{

    private double coorX, coorY; // Координаты X, Y

    public FunctionPoint(double x, double y){ // Конструктор с двумя параметрами
        this.coorX = x;
        this.coorY = y;
    }

    public FunctionPoint(FunctionPoint point){ // Коструктор с аргументом точки
        this.coorX = point.getCoorX();
        this.coorY = point.getCoorY();
    }

    public FunctionPoint(){ // Конструктор по умолчанию
        this.coorX = 0;
        this.coorY = 0;
    }
    // Сеттерый
    public void setCoorX(double x){this.coorX = x;} 
    public void setCoorY(double y) {this.coorY = y;}

    // Геттеры
    public double getCoorX(){return this.coorX;}
    public double getCoorY() {return this.coorY;}
}