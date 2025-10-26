import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
        public static void main(String[] args) {
        System.out.println("Тестировае табулированной ф-ции \n");
        
        // Создание функции f(x) = x^2
        System.out.println("1. Создание функции f(x) = x^2 на интервале [0, 4] с 5 точками:");
        TabulatedFunction function = new TabulatedFunction(0, 4, 5);
        
        // Устанавливаем значения y = x^2 для каждой точки
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            function.setPointY(i, x * x);  // y = x²
        }
        
        // Выводим информацию о функции
        printFunctionInfo(function);
        
        // Вычисление значений в разных точках
        System.out.println("\n2. Вычисление значений функции в различных точках:");
        double[] testPoints = {-1, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5};
        
        for (double x : testPoints) {
            double y = function.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = вне области определения\n", x);
            } else {
                System.out.printf("f(%.1f) = %.4f\n", x, y);
            }
        }
        
        // Изменение точки
        System.out.println("\n3. Изменение точки с индексом 2:");
        System.out.println("До изменения: (" + function.getPointX(2) + ", " + function.getPointY(2) + ")");
        function.setPoint(2, new FunctionPoint(2.0, 3.0));
        System.out.println("После изменения: (" + function.getPointX(2) + ", " + function.getPointY(2) + ")");
        
        // Добавление новой точки
        System.out.println("\n4. Добавление точки (1.5, 2.25):");
        function.addPoint(new FunctionPoint(1.5, 2.25));
        printFunctionInfo(function);
        
        // Удаление точки
        System.out.println("\n5. Удаление точки с индексом 1:");
        function.deletePoint(1);
        printFunctionInfo(function);
        
        // Проверка значений после изменений
        System.out.println("\n6. Проверка значений после всех изменений:");
        for (double x : new double[]{0.5, 1.0, 1.5, 2.0}) {
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.1f) = %.4f\n", x, y);
        }
        
        System.out.println("\n Тестирование завершено");
    }
    
 
    private static void printFunctionInfo(TabulatedFunction function) {
        System.out.println("Область определения: [" + function.getLeftDomainBorder() + 
                          ", " + function.getRightDomainBorder() + "]");
        System.out.println("Количество точек: " + function.getPointsCount());
        System.out.println("Точки функции:");
        
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.printf("  %d: (%.2f, %.4f)\n", i, 
                            function.getPointX(i), function.getPointY(i));
        }
    }
}