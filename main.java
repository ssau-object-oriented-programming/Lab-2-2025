//7 (проверка)
import functions.*;

public class Main {

    public static void main(String[] args) {

		//y = x^2  на [-3;3]
        double[] vals = {9, 4, 1, 0, 1, 4, 9};
        TabulatedFunction f = new TabulatedFunction(-3, 3, vals);

        System.out.println("test getFunctionValue");
        double[] xs = {-4, -3, -2.5, -1.2, 0, 1.7, 3, 4};
        for (double x : xs)
            System.out.println("f(" + x + ") = " + f.getFunctionValue(x));

        // модификации таблицы
        f.setPointY(1, 5);                              // изменение ординаты
        f.addPoint(new FunctionPoint(2.2, 5));          // добавление точки
        f.deletePoint(0);                               // удаление крайней левой

        System.out.println("\nafter modification");
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i);
            System.out.println(i + ": (" + p.getX() + "; " + p.getY() + ')');
        }
    }
}