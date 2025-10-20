import functions.*;

public class Main {
    public static void main(String[] args) {
        FunctionPoint[] points = {
                new FunctionPoint(0, 0),
                new FunctionPoint(1, 1),
                new FunctionPoint(2, 4),
                new FunctionPoint(3, 9)
        };
        TabulatedFunction func = new TabulatedFunction(0, 3, new double[]{0, 1, 4, 9});

        System.out.println("Исходная функция:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(func.getPoint(i));
        }

        System.out.println("\nПроверка значений в разных точках:");
        double[] testX = {-1, 0.5, 1.5, 2.5, 4};
        for (int i = 0; i < testX.length; i++) {
            double x = testX[i];
            System.out.printf("f(%.2f) = %.2f%n", x, func.getFunctionValue(x));
        }

        System.out.println("\nДобавляем точку (1.5, 2.25):");
        func.addPoint(new FunctionPoint(1.5, 2.25));
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(func.getPoint(i));
        }

        System.out.println("\nУдаляем точку с индексом 2:");
        func.deletePoint(2);
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(func.getPoint(i));
        }
    }
}
