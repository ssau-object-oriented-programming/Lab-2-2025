import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {

        double left = 0;
        double right = 4;
        double[] values = {0, 1, 4, 9, 16};  // y = x^2

        TabulatedFunction f = new TabulatedFunction(left, right, values);

        System.out.println("Исходная табулированная функция:");
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("(" + f.getPoint(i).getX() + ", " + f.getPoint(i).getY() + ")");
        }

        System.out.println("\nПроверка значений функции:");
        double[] testX = {-1, 0, 1.5, 2, 2.5, 3.7, 5};
        for (double x : testX) {
            System.out.println("f(" + x + ") = " + f.getFunctionValue(x));
        }

        System.out.println("\nДобавляем новую точку (2.5, 6.25):");
        f.addPoint(new FunctionPoint(2.5, 6.25));
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("(" + f.getPoint(i).getX() + ", " + f.getPoint(i).getY() + ")");
        }

        System.out.println("\nУдаляем точку с индексом 1:");
        f.deletePoint(1);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("(" + f.getPoint(i).getX() + ", " + f.getPoint(i).getY() + ")");
        }

        System.out.println("\nПовторная проверка после изменений:");
        for (double x : testX) {
            System.out.println("f(" + x + ") = " + f.getFunctionValue(x));
        }
    }
}
