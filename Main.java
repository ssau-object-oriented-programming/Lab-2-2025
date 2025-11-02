import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Создаём табулированную функцию на интервале [0, 10] с 5 точками
        TabulatedFunction func = new TabulatedFunction(0, 10, 5);

        // Присваиваем функции значения: y = x^2
        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            func.setPointY(i, x * x + 2);
        }

        // Выводим все точки функции
        System.out.println("Исходные точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("(%.1f; %.1f)%n", p.getX(), p.getY());
        }

        // Проверяем работу getFunctionValue с разными x
        System.out.println("\nПроверка getFunctionValue:");
        double[] testX = {-5, 0, 2.5, 5, 7.5, 10, 12};
        for (double x : testX) {
            double y = func.getFunctionValue(x);
            System.out.printf("f(%.1f) = %.1f%n", x, y);
        }

        // Проверяем изменение точки
        System.out.println("\nИзменяем значение точки (вторая точка, Y = 100):");
        func.setPointY(1, 100);
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("(%.1f; %.1f)%n", p.getX(), p.getY());
        }

        // Добавляем новую точку
        System.out.println("\nДобавляем новую точку (5.5; 30.25):");
        func.addPoint(new FunctionPoint(5.5, 30.25));

        // Выводим все точки после добавления
        System.out.println("После добавления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("(%.1f; %.1f)%n", p.getX(), p.getY());
        }

        // Удаляем точку
        System.out.println("\nУдаляем точку с индексом 4:");
        func.deletePoint(4);

        // Выводим все точки после удаления
        System.out.println("После удаления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.printf("(%.1f; %.1f)%n", p.getX(), p.getY());
        }
    }
}
