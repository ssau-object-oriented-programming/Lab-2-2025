import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction func = new TabulatedFunction(0, 4, values);

        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("Количество точек: " + func.getPointsCount());

        double x = 2.5;
        System.out.println("Значение функции в точке " + x + ": " + func.getFunctionValue(x));

        func.setPointY(2, 10);
        System.out.println("Новое значение Y в точке 2: " + func.getPointY(2));

        func.deletePoint(1);
        func.addPoint(new FunctionPoint(2.2, 5));
        System.out.println("Количество точек после изменений: " + func.getPointsCount());
    }
}