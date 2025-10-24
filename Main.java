import functions.*;

public class Main {
    public static void main(String[] args) {

        // Создание табулированной функции (например, y = x²)
        System.out.println("1. Создание функции y = x² на отрезке [0, 4] с 5 точками:");

        TabulatedFunction function = new TabulatedFunction(0, 4, 5);

        // Вывод начальных точек
        System.out.println("Начальные точки:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("(%.2f, %.2f) ", function.getPointX(i), function.getPointY(i));
        }
        System.out.println("\n");

        // Установка реальных значений y = x²
        System.out.println("2. Установка значений y = x²:");

        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            function.setPointY(i, x * x);
            System.out.printf("Точка %d: (%.2f, %.2f)\n", i, x, x * x);
        }
        System.out.println();

        // Тестирование вычисления значений в разных точках
        System.out.println("3. Вычисление значений функции:");
        double[] testPoints = { -1, 0, 0.5, 1, 1.5, 2, 3.5, 4, 5 };

        for (double x : testPoints) {
            double y = function.getFunctionValue(x);

            if (Double.isNaN(y)) {
                System.out.printf("x = %.2f -> ВНЕ области определения\n", x);
            } else {
                System.out.printf("x = %.2f -> y = %.2f\n", x, y);
            }
        }
        System.out.println();

        // Добавление новой точки
        System.out.println("4. Добавление точки (2.5, 6.25):");
        try {
            function.addPoint(new FunctionPoint(2.5, 6.25));
            System.out.println("Точка добавлена успешно");
            System.out.println("Теперь точек: " + function.getPointsCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // 5. Проверка интерполяции после добавления
        System.out.println("5. Проверка интерполяции после добавления:");
        for (double x = 2.2; x <= 2.8; x += 0.2) {
            double y = function.getFunctionValue(x);
            System.out.printf("x = %.2f -> y = %.2f\n", x, y);
        }
        System.out.println();

        // 6. Удаление точки
        System.out.println("6. Удаление точки с индексом 2:");
        try {
            function.deletePoint(2);

            System.out.println("Точка удалена успешно");
            System.out.println("Теперь точек: " + function.getPointsCount());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();

        // 7. Проверка границ после изменений
        System.out.println("7. Новые границы области определения:");
        System.out.printf("Левая граница: %.2f\n", function.getLeftDomainBorder());
        System.out.printf("Правая граница: %.2f\n", function.getRightDomainBorder());
        System.out.println();

        // 8. Попытка добавить точку с существующим X
        System.out.println("8. Попытка добавить точку с существующим X:");

        try {
            function.addPoint(new FunctionPoint(3.0, 100)); // Должен быть отказ
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 9. Финальный вывод всех точек
        System.out.println("\n9. Финальный набор точек:");

        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("Точка %d: (%.2f, %.2f)\n", i,function.getPointX(i), function.getPointY(i));

        }

    }
}
