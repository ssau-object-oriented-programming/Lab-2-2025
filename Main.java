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
        testFunction(func);

        System.out.println("\nЗаменяем точку с индексом 2:");
        func.setPoint(2, new FunctionPoint(2, 10)); // (2,5) -> (2,10)
        testFunction(func);

        System.out.println("\nДобавляем новую точку (1.5, 4):");
        func.addPoint(new FunctionPoint(1.5, 4));
        testFunction(func);

        System.out.println("\nУдаляем точку с индексом 1:");
        func.deletePoint(1);
        testFunction(func);

        System.out.println("\nПробуем изменить X точки за границами:");
        func.setPointX(0, 5); // Не должно измениться
        testFunction(func);

        System.out.println("\nМеняем Y точки с индексом 0:");
        func.setPointY(0, 0);
        testFunction(func);
    }

    public static void testFunction(TabulatedFunction func)
    {
        // тестируем точки: слева, на границе, внутри, справа
        double[] testPoints = {-1, 0, 1.2, 2, 3.5, 4, 5};

        for (double x : testPoints)
        {
            double y = func.getFunctionValue(x);

            if (Double.isNaN(y))
            {
                System.out.printf("f(%.1f) = не определено%n", x);
            } else
            {
                System.out.printf("f(%.1f) = %.2f%n", x, y);
            }
        }

        System.out.println("Точек в функции: " + func.getPointsCount());
    }
}