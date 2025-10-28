import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {

        System.out.println(" Создание функции f(x) = x + 5 на интервале [0, 10]:");
        double[] values = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TabulatedFunction func = new TabulatedFunction(0, 10, values);

        System.out.printf("Область определения: [%.1f, %.1f]\n",
                func.getLeftDomainBorder(), func.getRightDomainBorder());
        System.out.println("Кол-во точек: " + func.getPointsCount());

        System.out.println("\n Точки табулированной функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("Точка %d: (%.1f; %.1f)\n", i, p.getX(), p.getY());
        }

        System.out.println("\n Вычисленные значения функции:");
        double[] testX = {-5.0, -2.0, 0.0, 1.0, 2.5, 3.0, 4.5, 5.0, 6.5, 7.0, 8.5, 10.0, 12.0, 15.0};
        for (double x : testX) {
            double y = func.getFunctionValue(x);
            double expected = x + 5;
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = не определено (не входит в область определения)\n", x);
            } else {
                System.out.printf("f(%.1f) = %.1f (должно быть: %.1f)\n", x, y, expected);
            }
        }

        System.out.println("\n Проверка модификации точек:");

        System.out.println("Изменение ординаты точки с индексом 2 на правильное значение:");
        System.out.printf("До: (%.1f; %.1f) ", func.getPointX(2), func.getPointY(2));
        double correctY = func.getPointX(2) + 5;
        func.setPointY(2, correctY);
        System.out.printf("После: (%.1f; %.1f)\n", func.getPointX(2), func.getPointY(2));

        System.out.println("\nКорректное изменение абсциссы с обновлением Y:");
        System.out.printf("До: (%.1f; %.1f) ", func.getPointX(4), func.getPointY(4));
        double newX = 4.2;
        func.setPointX(4, newX);
        func.setPointY(4, newX + 5);
        System.out.printf("После: (%.1f; %.1f)\n", func.getPointX(4), func.getPointY(4));

        System.out.println("\n Добавление точек:");
        System.out.println("Количество точек до добавления: " + func.getPointsCount());
        double addX = 3.3;
        double addY = addX + 5;
        func.addPoint(new FunctionPoint(addX, addY));
        System.out.println("Количество точек после добавления: " + func.getPointsCount());
        System.out.printf("Значение в добавленной точке: f(%.1f) = %.1f\n", addX, func.getFunctionValue(addX));

        System.out.println("Точки после добавления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("(%.1f; %.1f) ", p.getX(), p.getY());
        }
        System.out.println();

        System.out.println("\n Удаление точек:");
        System.out.println("Количество точек до удаления: " + func.getPointsCount());
        func.deletePoint(2);
        System.out.println("Количество точек после удаления: " + func.getPointsCount());

        System.out.println("\nОбновление всех значений Y в соответствии с f(x) = x + 5:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            func.setPointY(i, x + 5);
            System.out.printf("Точка %d: (%.1f; %.1f)\n", i, x, func.getPointY(i));
        }

        System.out.println("\n Интерполяция:");
        double[] interpolationX = {0.3, 1.1, 2.8, 3.9, 5.2, 6.7, 8.1, 9.4};
        for (double x : interpolationX) {
            double y = func.getFunctionValue(x);
            double expected = x + 5;
            System.out.printf("f(%.1f) = %.1f (должно быть: %.1f) %s\n",
                    x, y, expected,
                    Math.abs(y - expected) < 0.1 ? "правильно" : "не правильно!");
        }

        System.out.println("\n Границы:");
        System.out.printf("Левая граница: f(%.1f) = %.1f\n",
                func.getLeftDomainBorder(), func.getFunctionValue(func.getLeftDomainBorder()));
        System.out.printf("Правая граница: f(%.1f) = %.1f\n",
                func.getRightDomainBorder(), func.getFunctionValue(func.getRightDomainBorder()));
        
    }
}