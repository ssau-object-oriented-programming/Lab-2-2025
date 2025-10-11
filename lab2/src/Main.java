import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double[] values = {0, 1, 128, 2187, 16384};
        TabulatedFunction f = new TabulatedFunction(0, 4, values);

        System.out.println("Исходная функция:");
        printFunction(f);

        FunctionPoint p = f.getPoint(3);
        System.out.println("\nКопия точки с индексом 3: (" + p.getX() + "; " + p.getY() + ")");

        f.setPointY(3, 7);
        System.out.println("\nИзменение координаты У точки с индексом 3:");
        printFunction(f);

        f.addPoint(new FunctionPoint(0.7, 11));
        System.out.println("\nДобавление точки (0.7; 11):");
        printFunction(f);

        f.deletePoint(2);
        System.out.println("\nУдаление точки с индексом 2:");
        printFunction(f);

        double x = -1;
        double y = f.getFunctionValue(x);
        System.out.println("\nЗначение функции в точке x = " + x + ": " + y);

        x = 3.4;
        y = f.getFunctionValue(x);
        System.out.println("Значение функции в точке x = " + x + ": " + y);
    }

    private static void printFunction(TabulatedFunction f) {
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("Point " + i + ": (" + f.getPointX(i) + "; " + f.getPointY(i) + ")");
        }
    }
}
