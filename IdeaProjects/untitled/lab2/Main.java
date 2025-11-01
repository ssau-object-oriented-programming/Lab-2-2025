import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double left = -2;
        double right = 3;
        int count = 6;
        TabulatedFunction func = new TabulatedFunction(left, right, count);
        System.out.println("Функция f(x) = 2x² + 3x - 1");
        System.out.println("Левая граница функции: " + func.getLeftDomainBorder());
        System.out.println("Правая граница функции: " + func.getRightDomainBorder());

        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            func.setPointY(i, 2*x*x + 3*x - 1);
        }

        System.out.println("\nПроверка значений функции:");
        for (double x = -3; x <= 4; x += 0.5) {
            System.out.println("f(" + x + ") = " + func.getFunctionValue(x));
        }

        double[] values = {1, -2, -1, 4, 13, 26};
        TabulatedFunction func1 = new TabulatedFunction(-2, 3, values);

        System.out.println("\nФункция создана с массивом значений:");
        for (int i = 0; i < func1.getPointsCount(); i++) {
            System.out.println("x=" + func1.getPointX(i) + ", y=" + func1.getPointY(i));
        }

        System.out.println("\nИзменяем значение y третьей точки:");
        func.setPoint(2, new FunctionPoint(0, -1));
        System.out.println("Новая точка 2: x = " + func.getPointX(2) + ", y = " + func.getPointY(2));

        System.out.println("\nДобавляем новую точку (x=1.5, y=8):");
        func.addPoint(new FunctionPoint(1.5, 8));
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("(" + func.getPointX(i) + "; " + func.getPointY(i) + ")");
        }

        System.out.println("\nНаходим значение y для 2.5 с помощью линейной интерполяции:");
        double valueAt25 = func.getFunctionValue(2.5);
        System.out.println("f(2.5) = " + valueAt25);
        func.addPoint(new FunctionPoint(2.5, valueAt25));
        System.out.println("Добавлена новая точка (2.5; " + valueAt25 + ")");

        System.out.println("\nУдаляем четвертую точку:");
        func.deletePoint(3);
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("(" + func.getPointX(i) + "; " + func.getPointY(i) + ")");
        }
    }
}