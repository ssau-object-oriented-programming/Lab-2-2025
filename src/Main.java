
import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double left = 0;
        double right = 4;
        int count = 5;
        TabulatedFunction func = new TabulatedFunction(left, right, count);
        System.out.println("Функция х^3");
        System.out.println("Границы функции:");
        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            func.setPointY(i, x * x * x);
        }

        System.out.println("Проверка значений функции:");
        for (double x = -1; x <= 5; x += 1.0) {
            System.out.println("f(" + x + ") = " + func.getFunctionValue(x));
        }
        double[] values = {0, 1, 8, 27, 64};
        TabulatedFunction func1 = new TabulatedFunction(left, right, values);

        System.out.println("Функция создана с массивом значений:");
        for (int i = 0; i < func1.getPointsCount(); i++) {
            System.out.println("x=" + func1.getPointX(i) + ", y=" + func1.getPointY(i));
        }

        System.out.println("\nИзменяем значение y второй точки:");
        func.setPoint(1, new FunctionPoint(1.5, 3.375));
        System.out.println("Новая точка 1: x = " + func.getPointX(1) + ", y = " + func.getPointY(1));

        System.out.println("\nДобавляем новую точку (x=2.5, y=15.625):");
        func.addPoint(new FunctionPoint(2.5, 15.625));
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("(" + func.getPointX(i) + "; " + func.getPointY(i) + ")");
        }
        System.out.println("Находим значение Y для 3,5 с помощью линейной интерполяциии:");
        func.addPoint(new FunctionPoint(3.5, func.getFunctionValue(3.5))); // добавляем как новую точку
        System.out.println("Добавлена новая точка (3.5; " +func.getFunctionValue(3.5) + ")");

        System.out.println("\nУдаляем третью точку:");
        func.deletePoint(2);
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("(" + func.getPointX(i) + "; " + func.getPointY(i) + ")");
        }
    }
}
