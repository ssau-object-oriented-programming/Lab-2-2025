import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {

        System.out.println("Шаг 1. Создаём таблицу для y = 5*x + 10 на отрезке [0; 10] с шагом 1 (11 точек).");

        double left = 0.0, right = 10.0;
        int n = 11; // x = 0,1,2,...,10
        double[] values = new double[n];
        for (int i = 0; i < n; ++i) {
            double x = left + i;     // шаг 1
            values[i] = 5 * x + 10;  // y = 5x + 10
        }
        TabulatedFunction f = new TabulatedFunction(left, right, values);

        System.out.println("\n— Границы области определения и количество точек —");
        System.out.println("Количество точек: " + f.getPointsCount());
        System.out.println("Левая граница:    " + f.getLeftDomainBorder());
        System.out.println("Правая граница:   " + f.getRightDomainBorder());

        System.out.println("\n— Исходные точки таблицы —");
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i); // возвращается копия
            System.out.println("точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }

        System.out.println("\nШаг 2. Проверяем вычисления f(x) в ряде точек (включая вне области):");
        double[] xs1 = { -1.0, 0.0, 0.25, 1.0, 3.2, 3.6, 7.5, 10.0, 10.5 };
        for (double x : xs1) {
            double y = f.getFunctionValue(x);
            System.out.print("f(" + x + ") = ");
            System.out.println(Double.isNaN(y) ? "не определена (вне области)" : y);
        }

        System.out.println("\nШаг 3. Тестируем геттеры/сеттеры точек.");
        System.out.println("Берём x[2] и y[2] через getPointX/getPointY:");
        System.out.println("x[2] = " + f.getPointX(2) + ", y[2] = " + f.getPointY(2));

        System.out.println("Меняем абсциссу точки с индексом 3 на 3.3 (строго между соседями 2 и 4).");
        f.setPointX(3, 3.3);
        System.out.println("После setPointX: x[3] = " + f.getPointX(3) + ", y[3] = " + f.getPointY(3));

        System.out.println("Меняем ординату точки с индексом 4 на 55.0 (локально ломаем прямую).");
        f.setPointY(4, 55.0);
        System.out.println("После setPointY: x[4] = " + f.getPointX(4) + ", y[4] = " + f.getPointY(4));

        System.out.println("Заменяем точку с индексом 5 на (5.5; 37.5) через setPoint (между 5 и 6).");
        f.setPoint(5, new FunctionPoint(5.5, 5 * 5.5 + 10));
        System.out.println("После setPoint:  x[5] = " + f.getPointX(5) + ", y[5] = " + f.getPointY(5));

        System.out.println("\n— Точки таблицы после правок setPointX/setPointY/setPoint —");
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i);
            System.out.println("точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }

        System.out.println("\nШаг 4. Добавляем новую точку (7.5; 47.5), а затем удаляем самую левую точку (индекс 0).");
        f.addPoint(new FunctionPoint(7.5, 47.5));
        System.out.println("Теперь точек: " + f.getPointsCount());
        f.deletePoint(0);

        System.out.println("\n— Итоговое состояние точек —");
        for (int i = 0; i < f.getPointsCount(); i++) {
            FunctionPoint p = f.getPoint(i);
            System.out.println("точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }

        System.out.println("\nШаг 5. Контрольные вычисления после всех изменений:");
        double[] xs2 = { -1.0, 0.0, 1.0, 2.5, 3.5, 4.0, 4.5, 7.4, 7.5, 7.6, 10.0, 11.0 };
        for (double x : xs2) {
            double y = f.getFunctionValue(x);
            System.out.print("f(" + x + ") = ");
            System.out.println(Double.isNaN(y) ? "не определена (вне области)" : y);
        }
    }
}
