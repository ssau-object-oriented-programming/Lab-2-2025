import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // создаем табулированную функцию на интервале от 0 до 10 с 5 точками
        TabulatedFunction quadraticFunc = new TabulatedFunction(0, 10, 5);
        // устанавливаем значения y как x в квадрате для каждой точки
        for (int i = 0; i < quadraticFunc.getPointsCount(); i++) {
            double x = quadraticFunc.getPointX(i);
            quadraticFunc.setPointY(i, x*x);
        }

        System.out.println("Квадратичная функция y = x^2:");
        // выводим все точки функции
        for (int i = 0; i < quadraticFunc.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f) ", quadraticFunc.getPointX(i), quadraticFunc.getPointY(i));
        }
        System.out.println();

        // добавляем дополнительные точки в функцию
        quadraticFunc.addPoint(new FunctionPoint(1.5, 2.25));
        quadraticFunc.addPoint(new FunctionPoint(3.5, 12.25));

        System.out.println("После добавления точек:");
        // снова выводим все точки чтобы увидеть изменения
        for (int i = 0; i < quadraticFunc.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f) ", quadraticFunc.getPointX(i), quadraticFunc.getPointY(i));
        }
        System.out.println();

        // удаляем точку с индексом 3
        quadraticFunc.deletePoint(3);

        System.out.println("После удаления точки:");
        // выводим точки после удаления
        for (int i = 0; i < quadraticFunc.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f) ", quadraticFunc.getPointX(i), quadraticFunc.getPointY(i));
        }
        System.out.println();

        // изменяем точку с индексом 2 на новые координаты
        quadraticFunc.setPoint(2, new FunctionPoint(3.0, 4.0));

        System.out.println("После изменения точки:");
        // выводим точки после изменения
        for (int i = 0; i < quadraticFunc.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f) ", quadraticFunc.getPointX(i), quadraticFunc.getPointY(i));
        }
        System.out.println();

        // тестируем интерполяцию в различных точках
        System.out.println("Интерполяция:");
        double[] testX = {0.5, 1.2, 2.5, 3.2, 6.8};
        for (double x : testX) {
            // вычисляем значение функции в каждой тестовой точке
            System.out.printf("f(%.2f) = %.2f\n", x, quadraticFunc.getFunctionValue(x));
        }

        // выводим границы области определения
        System.out.printf("Область определения: [%.2f, %.2f]\n", quadraticFunc.getLeftDomainBorder(), quadraticFunc.getRightDomainBorder());

        // проверяем поведение функции вне области определения
        System.out.println("Вне области определения:");
        System.out.printf("f(-1) = %.2f\n", quadraticFunc.getFunctionValue(-1));
        System.out.printf("f(11) = %.2f\n", quadraticFunc.getFunctionValue(11));

        System.out.println("Финальное состояние:");
        // выводим конечное состояние всех точек
        for (int i = 0; i < quadraticFunc.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f) ", quadraticFunc.getPointX(i), quadraticFunc.getPointY(i));
        }
        System.out.println();
    }
}
