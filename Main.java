import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {
        // Конструктор из задания
        double[] values = {0, 1, 4, 9}; // y = x**2 при x = [0, 3]
        TabulatedFunction function = new TabulatedFunction(0, 3, values);

        System.out.println("Точки функции через конструктор:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            FunctionPoint p = function.getPoint(i);
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }

        System.out.println("\nЗначения функции:");
        double[] testX = {-1, 0.5, 1, 1.5, 2, 3, 4};
        for (double x : testX) {
            double y = function.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.println("f(" + x + ") = NaN (вне области)");
            } else {
                System.out.println("f(" + x + ") = " + y);
            }
        }

        // Изменяем точку
        System.out.println("\nМеняем точку с индексом 1 на точку (1, 2):");
        function.setPoint(1, new FunctionPoint(1, 2));
        System.out.println("Точки после изменения:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            FunctionPoint p = function.getPoint(i);
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }

        // Пробуем недопустимое изменение
        System.out.println("\nЗадаем x=2.5 для индекса 1:");
        function.setPointX(1, 2.5); // не должно измениться
        System.out.println("Точка 1: (" + function.getPointX(1) + ", " + function.getPointY(1) + ")");

        // Удаляем точку
        System.out.println("\nУдаляем точку с индексом 1:");
        function.deletePoint(1);
        System.out.println("Точки после удаления:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            FunctionPoint p = function.getPoint(i);
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }

        // Добавляем точку
        System.out.println("\nДобавляем точку (1.5, 2.25):");
        function.addPoint(new FunctionPoint(1.5, 2.25));
        System.out.println("Точки после добавления:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            FunctionPoint p = function.getPoint(i);
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }
    }
}
