import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ КЛАССОВ ТАБУЛИРОВАННОЙ ФУНКЦИИ ===\n");

        // Создание табулированной функции f(x) = 10x + 10
        System.out.println("1. СОЗДАНИЕ ФУНКЦИИ f(x) = 10x + 10 НА ИНТЕРВАЛЕ [0, 5]:");
        double[] values = {10, 20, 30, 40, 50, 60}; // значения y = 10x + 10
        TabulatedFunction func = new TabulatedFunction(0, 5, values);

        // Вывод информации о функции
        System.out.printf("Область определения: [%.1f, %.1f]\n",
                func.getLeftDomainBorder(), func.getRightDomainBorder());
        System.out.println("Количество точек: " + func.getPointsCount());

        // Вывод точек функции
        System.out.println("\n2. ТОЧКИ ТАБУЛИРОВАННОЙ ФУНКЦИИ:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("Точка %d: (%.1f; %.1f)\n", i, p.getX(), p.getY());
        }

        // Тестирование вычисления значений (включая точки вне области определения)
        System.out.println("\n3. ВЫЧИСЛЕНИЕ ЗНАЧЕНИЙ ФУНКЦИИ:");
        double[] testX = {-2.0, 0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 6.0};
        for (double x : testX) {
            double y = func.getFunctionValue(x);
            double expected = 10 * x + 10;
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = не определено (вне области определения)\n", x);
            } else {
                System.out.printf("f(%.1f) = %.1f (ожидалось: %.1f)\n", x, y, expected);
            }
        }

        // Тестирование модификации точек - ТЕПЕРЬ С ПРАВИЛЬНЫМИ ЗНАЧЕНИЯМИ Y!
        System.out.println("\n4. ТЕСТИРОВАНИЕ МОДИФИКАЦИИ ТОЧЕК:");

        // Изменение ординаты - теперь на ПРАВИЛЬНОЕ значение
        System.out.println("Изменение ординаты точки с индексом 2 на правильное значение:");
        System.out.printf("До: (%.1f; %.1f) -> ", func.getPointX(2), func.getPointY(2));
        double correctY = 10 * func.getPointX(2) + 10; // Вычисляем правильное Y
        func.setPointY(2, correctY);
        System.out.printf("После: (%.1f; %.1f)\n", func.getPointX(2), func.getPointY(2));

        // Корректное изменение абсциссы с ОБНОВЛЕНИЕМ Y
        System.out.println("\nКорректное изменение абсциссы с обновлением Y:");
        System.out.printf("До: (%.1f; %.1f) -> ", func.getPointX(1), func.getPointY(1));
        func.setPointX(1, 1.2); // Меняем X
        func.setPointY(1, 10 * 1.2 + 10); // ОБНОВЛЯЕМ Y соответственно новой X!
        System.out.printf("После: (%.1f; %.1f)\n", func.getPointX(1), func.getPointY(1));

        // Тестирование добавления точки - с ПРАВИЛЬНЫМ Y
        System.out.println("\n5. ТЕСТИРОВАНИЕ ДОБАВЛЕНИЯ ТОЧКИ:");
        System.out.println("Количество точек до добавления: " + func.getPointsCount());
        double newX = 2.2;
        double newY = 10 * newX + 10; // ПРАВИЛЬНОЕ значение Y
        func.addPoint(new FunctionPoint(newX, newY));
        System.out.println("Количество точек после добавления: " + func.getPointsCount());
        System.out.printf("Значение в добавленной точке: f(%.1f) = %.1f\n", newX, func.getFunctionValue(newX));

        // Вывод всех точек после добавления
        System.out.println("Точки после добавления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("(%.1f; %.1f) ", p.getX(), p.getY());
        }
        System.out.println();

        // Тестирование удаления точки
        System.out.println("\n6. ТЕСТИРОВАНИЕ УДАЛЕНИЯ ТОЧКИ:");
        System.out.println("Количество точек до удаления: " + func.getPointsCount());
        func.deletePoint(2);
        System.out.println("Количество точек после удаления: " + func.getPointsCount());

        // ОБНОВЛЯЕМ ВСЕ ЗНАЧЕНИЯ Y после всех изменений, чтобы соответствовать f(x) = 10x + 10
        System.out.println("\nОбновление всех значений Y в соответствии с f(x) = 10x + 10:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            func.setPointY(i, 10 * x + 10);
            System.out.printf("Точка %d: (%.1f; %.1f)\n", i, x, func.getPointY(i));
        }

        // Проверка интерполяции после ВСЕХ исправлений
        System.out.println("\n7. ПРОВЕРКА ИНТЕРПОЛЯЦИИ ПОСЛЕ ИСПРАВЛЕНИЙ:");
        double[] interpolationX = {0.3, 1.1, 1.8, 2.7, 3.9, 4.6};
        for (double x : interpolationX) {
            double y = func.getFunctionValue(x);
            double expected = 10 * x + 10;
            System.out.printf("f(%.1f) = %.1f (ожидалось: %.1f) %s\n",
                    x, y, expected,
                    Math.abs(y - expected) < 0.1 ? "СОВПАДАЕТ" : "НЕ СОВПАДАЕТ ОШИБКА!");
        }

        // Граничные значения
        System.out.println("\n8. ГРАНИЧНЫЕ ЗНАЧЕНИЯ:");
        System.out.printf("Левая граница: f(%.1f) = %.1f\n",
                func.getLeftDomainBorder(), func.getFunctionValue(func.getLeftDomainBorder()));
        System.out.printf("Правая граница: f(%.1f) = %.1f\n",
                func.getRightDomainBorder(), func.getFunctionValue(func.getRightDomainBorder()));

        System.out.println("\n=== ТЕСТИРОВАНИЕ ЗАВЕРШЕНО ===");
    }
}