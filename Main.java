import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main
{
    public static void main(String[] args)
    {
        // создаем функцию f(x) = 2x + 1 на интервале [0, 4]
        double[] values = {1, 3, 5, 7, 9};
        TabulatedFunction func = new TabulatedFunction(0, 4, values);

        System.out.println("Исходная функция (f(x) = 2x + 1):");
        printFunctionPoints(func);
        testFunctionValue(func);

        // проверка замены точки по индексу
        System.out.println("\nЗаменяем точку с индексом 2:");
        System.out.println("До: " + pointToString(func.getPoint(2)));
        func.setPoint(2, new FunctionPoint(2, 10));
        System.out.println("После: " + pointToString(func.getPoint(2)));
        printFunctionPoints(func);

        // проверка добавления точки с координатами
        System.out.println("\nДобавляем новую точку (1.5, 4):");
        func.addPoint(new FunctionPoint(1.5, 4));
        printFunctionPoints(func);

        // проверка удаления точки
        System.out.println("\nУдаляем точку с индексом 1:");
        System.out.println("Удаляемая точка: " + pointToString(func.getPoint(1)));
        func.deletePoint(1);
        printFunctionPoints(func);

        // проверка замены координаты У точки по индексу
        System.out.println("\nМеняем Y точки с индексом 0:");
        System.out.println("До: Y = " + func.getPointY(0));
        func.setPointY(0, 0);
        System.out.println("После: Y = " + func.getPointY(0));
        printFunctionPoints(func);
    }

    // вывод всех точек функции через геттеры
    public static void printFunctionPoints(TabulatedFunction func)
    {
        System.out.println("Точки функции:");

        for (int i = 0; i < func.getPointsCount(); i++)
        {
            double x = func.getPointX(i);
            double y = func.getPointY(i);

            System.out.printf("  [%d] (%.2f; %.2f)%n", i, x, y);
        }
    }

    // метод для вывода координат точки
    public static String pointToString(FunctionPoint point)
    {
        if (point == null) return "null";

        return String.format("(%.2f; %.2f)", point.getX(), point.getY());
    }

    // проверка работы getFunctionValue
    public static void testFunctionValue(TabulatedFunction func)
    {
        System.out.println("Проверка getFunctionValue (интерполяция):");
        double[] testPoints = {-1, 0, 1.2, 2, 3.5, 4, 5};

        for (double x : testPoints)
        {
            double y = func.getFunctionValue(x);

            if (Double.isNaN(y))
            {
                System.out.printf("  f(%.1f) = не определено%n", x);
            } else
            {
                System.out.printf("  f(%.1f) = %.2f%n", x, y);
            }
        }
    }
}