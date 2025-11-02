import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТ 1: СОЗДАНИЕ ФУНКЦИИ ===");
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction func = new TabulatedFunction(0, 4, values);

        // Выводим все точки после создания
        System.out.println("Точки после создания функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        System.out.println("\n=== ТЕСТ 2: ВЫЧИСЛЕНИЕ ЗНАЧЕНИЙ ===");
        double[] testX = {-1, 0, 1.5, 4, 5};
        for (double x : testX) {
            double y = func.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.println("f(" + x + ") = не определено");
            } else {
                System.out.println("f(" + x + ") = " + y);
            }
        }

        System.out.println("\n=== ТЕСТ 3: ДОБАВЛЕНИЕ И УДАЛЕНИЕ ===");

        // Добавление точки
        System.out.println("Добавляем точку (2.5, 6.25)");
        func.addPoint(new FunctionPoint(2.5, 6.25));
        System.out.println("Точки после добавления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
        System.out.println("Количество точек: " + func.getPointsCount());

        // Удаление точки
        System.out.println("\nУдаляем точку с индексом 1");
        func.deletePoint(1);
        System.out.println("Точки после удаления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
        System.out.println("Количество точек: " + func.getPointsCount());

        // Изменение Y точки
        System.out.println("\nИзменяем Y точки с индексом 2 на 10.0");
        func.setPointY(2, 10.0);
        System.out.println("Точки после изменения Y:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Попытка добавить точку с существующим X
        System.out.println("\nПытаемся добавить точку (2.0, 100.0) - должна быть отклонена");
        func.addPoint(new FunctionPoint(2.0, 100.0));
        System.out.println("Точки после попытки добавления дубликата:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Изменение X точки
        System.out.println("\n=== ТЕСТ 4: ИЗМЕНЕНИЕ X ТОЧЕК ===");

        System.out.println("Изменяем X точки с индексом 1 с " + func.getPointX(1) + " на 1.8");
        func.setPointX(1, 1.8);
        System.out.println("Точки после изменения X точки 1:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Попытка изменить X на некорректное значение (должно быть отклонено)
        System.out.println("\nПытаемся изменить X точки 0 на -1.0 (должно быть отклонено)");
        func.setPointX(0, -1.0);
        System.out.println("Точки после НЕУДАЧНОЙ попытки изменения X:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Замена точки целиком
        System.out.println("\n=== ТЕСТ 5: ЗАМЕНА ТОЧЕК ===");

        System.out.println("Заменяем точку с индексом 2 на (2.2, 15.0)");
        func.setPoint(2, new FunctionPoint(2.2, 15.0));
        System.out.println("Точки после замены точки 2:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Попытка заменить точку на некорректную (должно быть отклонено)
        System.out.println("\nПытаемся заменить точку 1 на (3.5, 5.0) - X выходит за границы (должно быть отклонено)");
        func.setPoint(1, new FunctionPoint(3.5, 5.0));
        System.out.println("Точки после НЕУДАЧНОЙ попытки замены:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        System.out.println("\n=== ТЕСТ 6: ГРАНИЦЫ И ФИНАЛЬНОЕ СОСТОЯНИЕ ===");
        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("Финальное количество точек: " + func.getPointsCount());
        System.out.println("Финальные точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
    }
}