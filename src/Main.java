import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction func = new TabulatedFunction(0, 4, values);

        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("Исходные точки функции:");
        printPoints(func);

        double x = 2.5;
        System.out.println("Значение функции в точке " + x + ": " + func.getFunctionValue(x));
        System.out.println();

        func.setPointY(2, 10);
        System.out.println("После изменения Y в точке 2:");
        printPoints(func);

        func.deletePoint(1);
        System.out.println("После удаления точки 1:");
        printPoints(func);

        func.addPoint(new FunctionPoint(2.2, 5));
        System.out.println("После добавления точки (2.2, 5):");
        printPoints(func);
    }

    private static void printPoints(TabulatedFunction func) {
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("Point " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
        System.out.println();
    }
}

