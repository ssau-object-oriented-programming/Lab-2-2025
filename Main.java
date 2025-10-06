import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // пример f(x) = 2x + 1 на интервале [0, 5]
        double[] val = {1, 3, 5, 7, 9, 11};
        TabulatedFunction fuf = new TabulatedFunction(0, 5, val);

        // info
        System.out.println("Функция: f(x) = 2x + 1");
        System.out.printf("Область: [%.1f; %.1f]\n", fuf.getLeftDomainBorder(), fuf.getRightDomainBorder());
        System.out.println("Кол-во точек: " + fuf.getPointsCount());

        // Точки
        System.out.println("Исходные точки");
        for (int i = 0; i < fuf.getPointsCount(); i++) {
            FunctionPoint point = fuf.getPoint(i);
            System.out.printf("%d --- (%.1f; %.1f)\n", i+1, point.getX(), point.getY());
        }

        // Вычисление значений при разных точках
        double[] test = {-2, 0, 0.5, 1, 2.5, 3, 4, 5, 6};
        System.out.println("\nВычисление значений функции:");
        for (double x : test) {
            double value = fuf.getFunctionValue(x);
            System.out.printf("f(%.1f) = %.1f\n", x, value);
        }

        // Изменение точки
        fuf.setPointY(2,6);
        System.out.printf("\n%d --- (%.1f; %.1f)\n", 3, fuf.getPoint(2).getX(), fuf.getPoint(2).getY());
        fuf.setPointX(5,2);
        System.out.printf("\n%d --- (%.1f; %.1f)\n", 6, fuf.getPoint(5).getX(), fuf.getPoint(5).getY());

        // Добавка точки
        System.out.println("\nКоличество точек до: " + fuf.getPointsCount());
        fuf.addPoint(new FunctionPoint(1.5, 4));
        System.out.println("Количество точек после: " + fuf.getPointsCount());
        System.out.printf("f(1.5) = %.1f\n\n", fuf.getFunctionValue(1.5));

        // Удаляем точку
        fuf.deletePoint(1);
        System.out.println("Количество точек после: " + fuf.getPointsCount());

        // Демонстрация интерполяции
        System.out.println();
        double[] interpolationPoints = {-0.2, 0.7, 1.2, 2.8, 4.3, 5.5};
        for (double x : interpolationPoints) {
            double value = fuf.getFunctionValue(x);
            double expected = 2 * x + 1; // Теоретическое значение
            if (x > fuf.getRightDomainBorder() ||  x < fuf.getLeftDomainBorder()) {
                System.out.printf("f(%.1f) = %.3f (ожидалось: NaN)\n", x, value);
            } else {
                System.out.printf("f(%.1f) = %.3f (ожидалось: %.3f)\n", x, value, expected);
            }
        }

        // Проверка граничных условий
        System.out.printf("\nЛевая граница f(%.1f) = %.1f\n", fuf.getLeftDomainBorder(), fuf.getFunctionValue(fuf.getLeftDomainBorder()));
        System.out.printf("Правая граница f(%.1f) = %.1f\n", fuf.getRightDomainBorder(), fuf.getFunctionValue(fuf.getRightDomainBorder()));
    }
}