package functions;
import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №2 ===");

        TabulatedFunction func = new TabulatedFunction(0, 4, new double[]{0, 1, 4, 9, 16});

        System.out.println("Границы: [" + func.getLeftDomainBorder() + ", " + func.getRightDomainBorder() + "]");
        System.out.println("Точек: " + func.getPointsCount());

        System.out.println("Тестирование вычислений:");
        double[] testPoints = {-1, 0, 1, 2, 3, 4, 5};
        for (double x : testPoints) {
            double y = func.getFunctionValue(x);
            System.out.println("f(" + x + ") = " + y);
        }

        System.out.println("Добавление точки (1.5, 2.25):");
        func.addPoint(new FunctionPoint(1.5, 2.25));
        System.out.println("Точек после добавления: " + func.getPointsCount());

        System.out.println("Удаление точки с индексом 1:");
        func.deletePoint(1);
        System.out.println("Точек после удаления: " + func.getPointsCount());

        System.out.println("Финальные точки:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("(" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
    }
}