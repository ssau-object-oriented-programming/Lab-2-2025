import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ TABULATEDFUNCTION ===\n");
        System.out.println("1. Создание функции y = x^2");

        // создаем табулированную функцию с 5 точками на интервале [0, 4]
        TabulatedFunction function = new TabulatedFunction(0, 4, 5);

        // устанавливаем значения y согласно квадратичной функции x^2
        // проходим по всем точкам и вычисляем y = x*x для каждой
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            function.setPointY(i, x * x);
        }

        // вывод начальных точек
        System.out.println("Начальные точки функции:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f)%n", function.getPointX(i), function.getPointY(i));
        }

        // 2. тестирование интерполяции до изменений
        System.out.println("\n2. Интерполяция на исходной функции");
        double[] testPoints = {0.5, 1.2, 2.5, 3.2, 6.8};

        // вычисляем значение функции в тестовых точках с помощью интерполяции
        for (double x : testPoints) {
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.2f) = %.2f%n", x, y);
        }

        // 3. тестирование границ области определения
        // проверяем корректность определения границ функции
        System.out.println("\n3. Область определения");
        System.out.printf("Левая граница: %.2f%n", function.getLeftDomainBorder());
        System.out.printf("Правая граница: %.2f%n", function.getRightDomainBorder());

        // точки вне области определения
        System.out.println("Проверка точек вне области определения:");
        double[] outsidePoints = {-1.0, 10.0};
        for (double x : outsidePoints) {
            double y = function.getFunctionValue(x);

            // для точек вне области определения должна возвращаться NaN (not a number)
            System.out.printf("f(%.2f) = %s%n", x, Double.isNaN(y) ? "не определено" : String.format("%.2f", y));
        }

        // 4. добавление новых точек
        System.out.println("\n4. Добавление точек");
        function.addPoint(new FunctionPoint(1.5, 2.25));
        function.addPoint(new FunctionPoint(3.5, 12.25));
        System.out.println("После добавления двух точек:");

        // выводим обновленный список точек
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f)%n", function.getPointX(i), function.getPointY(i));
        }

        // 5. удвление точки
        System.out.println("\n5. Удаление точки");
        function.deletePoint(3);
        System.out.println("После удаления точки с индексом 3:");

        // проверяем, что точка действительно удалена
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f)%n", function.getPointX(i), function.getPointY(i));
        }

        // 6. изменение точки
        // тестируем замену существующей точки на новую
        System.out.println("\n6. Изменение точки");
        function.setPoint(2, new FunctionPoint(3.0, 4.0));
        System.out.println("После изменения точки с индексом 2:");

        // проверяем, что точка изменилась корректно
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f)%n", function.getPointX(i), function.getPointY(i));
        }

        // 7. финальная проверка интерполяции
        // повторно тестируем интерполяцию после всех изменений
        System.out.println("\n7. Финальная интерполяция");
        for (double x : testPoints) {
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.2f) = %.2f%n", x, y);
        }

        // 8. итоговое состояние
        // выводим окончательное состояние функции после всех операций
        System.out.println("\n8. Итоговое состояние функции");
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f)%n", function.getPointX(i), function.getPointY(i));
        }
    }
}