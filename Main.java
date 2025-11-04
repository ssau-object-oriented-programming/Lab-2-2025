import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тестирование класса TabulatedFunction ===\n");

        // 1. Создаем табулированную функцию для y = x^2 на интервале [0, 4]
        System.out.println("1. Создание функции y = x^2 на [0, 4] с 5 точками:");
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction func = new TabulatedFunction(0, 4, values);
        System.out.println("Функция: " + func);
        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.println("Область определения: [" + func.getLeftDomainBorder() + ", " + func.getRightDomainBorder() + "]");

        // 2. Вычисляем значения функции в различных точках
        System.out.println("\n2. Вычисление значений функции:");
        double[] testPoints = {-1, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5};

        for (double x : testPoints) {
            double y = func.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = вне области определения%n", x);
            } else {
                System.out.printf("f(%.1f) = %.2f%n", x, y);
            }
        }

        // 3. Тестируем модификацию точек
        System.out.println("\n3. Модификация точек:");

        // Изменяем значение y в точке x=2
        System.out.println("До изменения: (" + func.getPoint(2).getX() + ", " + func.getPoint(2).getY() + ")");
        func.setPointY(2, 5.0);
        System.out.println("После setPointY(2, 5.0): (" + func.getPoint(2).getX() + ", " + func.getPoint(2).getY() + ")");

        // Пытаемся изменить x точки (должно сохранить упорядоченность)
        try {
            func.setPointX(2, 1.5);
            System.out.println("После setPointX(2, 1.5): (" + func.getPoint(2).getX() + ", " + func.getPoint(2).getY() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при setPointX: " + e.getMessage());
        }

        // 4. Тестируем добавление точек
        System.out.println("\n4. Добавление точек:");

        // Добавляем точку в середину
        func.addPoint(new FunctionPoint(0.7, 0.49));
        System.out.println("После добавления (0.7, 0.49):");
        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.println("Функция: " + func);

        // Добавляем точку в начало
        func.addPoint(new FunctionPoint(-0.5, 0.25));
        System.out.println("После добавления (-0.5, 0.25):");
        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.println("Функция: " + func);

        // 5. Тестируем удаление точек
        System.out.println("\n5. Удаление точек:");

        // Удаляем точку с индексом 2
        System.out.println("Удаляем точку с индексом 2: (" + func.getPoint(2).getX() + ", " + func.getPoint(2).getY() + ")");
        func.deletePoint(2);
        System.out.println("После удаления:");
        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.println("Функция: " + func);

        // 6. Проверяем значения после изменений
        System.out.println("\n6. Проверка значений после изменений:");
        double[] newTestPoints = {-0.5, 0, 0.7, 1, 2, 3, 4};

        for (double x : newTestPoints) {
            double y = func.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = вне области определения%n", x);
            } else {
                System.out.printf("f(%.1f) = %.2f%n", x, y);
            }
        }

        // 7. Тестируем граничные случаи
        System.out.println("\n7. Граничные случаи:");

        // Попытка добавить точку с существующим x
        try {
            func.addPoint(new FunctionPoint(1.0, 100));
            System.out.println("Точка добавлена");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении точки с существующим x: " + e.getMessage());
        }

        // Попытка удалить точку, когда останется только 1 точка
        try {
            // Удаляем точки пока не останется 2
            while (func.getPointsCount() > 2) {
                func.deletePoint(0);
            }
            System.out.println("Осталось точек: " + func.getPointsCount());

            // Пытаемся удалить еще одну (должна быть ошибка)
            func.deletePoint(0);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка при удалении последней точки: " + e.getMessage());
        }

        // 8. Проверка инкапсуляции
        System.out.println("\n8. Проверка инкапсуляции:");
        FunctionPoint testPoint = func.getPoint(0);
        System.out.println("Полученная точка: (" + testPoint.getX() + ", " + testPoint.getY() + ")");

        // Меняем полученную точку (не должна влиять на оригинал)
        testPoint.setX(999);
        testPoint.setY(999);
        System.out.println("После изменения полученной точки:");
        System.out.println("Полученная точка: (" + testPoint.getX() + ", " + testPoint.getY() + ")");
        System.out.println("Оригинальная точка в функции: (" + func.getPoint(0).getX() + ", " + func.getPoint(0).getY() + ")");

        System.out.println("\n=== Тестирование завершено ===");
    }
}