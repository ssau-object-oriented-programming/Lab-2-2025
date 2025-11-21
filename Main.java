import functions.*;

public class Main {
    public static void main(String[] args) {
        // Создаем табулированную функцию для x^2 на интервале [0, 4]
        double[] values = {0, 1, 4, 9, 16}; // создаем массив y
        TabulatedFunction function = new TabulatedFunction(0, 4, values);
        
        System.out.println("Исходная функция:");
        printFunctionInfo(function); // выдаст ввсе xy 
        
        // Проверяем значения функции в разных точках
        System.out.println("\nЗначения функции в разных точках:");
        double[] testPoints = {-1, 0, 0.5, 1, 1.5, 2, 3, 4, 5};
        for (double x : testPoints) { // по порядку из тест поинт
            double y = function.getFunctionValue(x); // лин инт-я
            System.out.printf("f(%.1f) = %s\n", x, Double.isNaN(y) ? "NaN" : String.format("%.2f", y));
        }
        
        // Изменяем точки
        System.out.println("\nПосле изменения точек:");
        function.setPointY(2, 5); // Меняем y в точке с индексом 2
        function.setPoint(3, new FunctionPoint(3.7, 12.3)); // Меняем точку с индексом 3
        printFunctionInfo(function);
        
        // Добавляем точку
        System.out.println("\nПосле добавления точки (2.5, 6.3):");
        function.addPoint(new FunctionPoint(2.5, 6.3));
        printFunctionInfo(function);
        
        // Удаляем точку
        System.out.println("\nПосле удаления точки с индексом 1:");
        function.deletePoint(1);
        printFunctionInfo(function);
        
        // Проверяем значения после изменений
        System.out.println("\nЗначения после изменений:");
        for (double x : testPoints) {
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.1f) = %s\n", x, Double.isNaN(y) ? "NaN" : String.format("%.2f", y));
        }
    }
     private static void printFunctionInfo(TabulatedFunction function) {
        System.out.printf("Область определения: [%.1f, %.1f]\n", 
            function.getLeftDomainBorder(), function.getRightDomainBorder());
        System.out.println("Точки функции:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            FunctionPoint point = function.getPoint(i);
            System.out.printf("  (%4.1f; %6.2f)\n", point.getX(), point.getY());
        }
    }
}