import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {
        // Создаем функцию log10(x) на интервале [0.1, 10.1] с 6 точками (равномерно)
        TabulatedFunction function = new TabulatedFunction(0.1, 10.1, 6);
        System.out.println("Функция log10(x):");
        System.out.println("Область определения: [" + function.getLeftDomainBorder() + "; " + function.getRightDomainBorder() + "]");
        System.out.println("Количество точек: " + function.getPointsCount());

        // Устанавливаем значения y = log10(x) для равномерно распределенных x
        System.out.println("\nТочки функции:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            double y = Math.log10(x);
            function.setPointY(i, y);
            System.out.printf("Точка %d: (%.1f; %.3f)%n", i + 1, x, y, x, y);
        }

        System.out.println("\nЗначения функции в различных точках:");
        double[] testPoints = {-1, 0, 0.1, 0.3, 0.5, 1, 1.5, 2, 3, 5, 7, 10, 10.1, 11};

        for (double x : testPoints) {
            double y = function.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = не определено%n", x);
            } else {
                double exact = Math.log10(x);
                System.out.printf("f(%.1f) = %.3f (точное: %.3f)%n", x, y, exact);
            }
        }

        System.out.println("\nДобавляем точку (3; 0.477):");
        function.addPoint(new FunctionPoint(3, Math.log10(3)));
        System.out.println("Количество точек после добавления: " + function.getPointsCount());

        System.out.println("\nДобавляем точку (7; 0.845):");
        function.addPoint(new FunctionPoint(7, Math.log10(7)));
        System.out.println("Количество точек после добавления: " + function.getPointsCount());

        System.out.println("\nУдаляем точку с индексом 1:");
        function.deletePoint(1);
        System.out.println("Количество точек после удаления: " + function.getPointsCount());

        System.out.println("\nИтоговая информация о точках:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            double y = function.getPointY(i);
            System.out.printf("Точка %d: (%.1f; %.3f)%n", i + 1, x, y);
        }

        // Проверка особых случаев
        System.out.println("\n--- Проверка особых случаев ---");
        System.out.println("f(0) = " + function.getFunctionValue(0));
        System.out.println("f(-5) = " + function.getFunctionValue(-5));
        System.out.println("f(100) = " + function.getFunctionValue(100));
    }
}