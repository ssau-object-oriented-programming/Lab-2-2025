import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) { //создаём табулированную функцию y = x^2 на интервале [0, 4] с 5 точками
        double[] values = {0, 1, 4, 9, 16}; //значения функции в точках
        TabulatedFunction f = new TabulatedFunction(0, 4, values);

        System.out.println("изначальная функция y = x^2");
        for (double x = -1; x <= 5; x += 0.5) {
            double y = f.getFunctionValue(x);
            System.out.printf("f(%.1f) = %s\n", x, Double.isNaN(y) ? "NaN" : y);
        }

        System.out.println("\nработа с точками ");//работа с точками
        FunctionPoint point = f.getPoint(2); // копируем третью точку
        System.out.println("копия точки 2: (" + point.getX() + ", " + point.getY() + ")");

        f.setPointY(2, 10); //изменяем у третьей точки
        System.out.println("после изменения Y точки 2: f(1.5) ≈ " + f.getFunctionValue(1.5));

        f.setPointX(2, 2.5); //перемещаем точку по X
        System.out.println("после изменения X точки 2: f(2.5) = " + f.getFunctionValue(2.5));

        f.addPoint(new FunctionPoint(3.2, 11));//добавляем новую точку
        System.out.println("после добавления точки (3.2,11): f(3.2) = " + f.getFunctionValue(3.2));

        f.deletePoint(0);//удаляем точку
        System.out.println("после удаления первой точки: f(0) = " + f.getFunctionValue(0));
    }
}