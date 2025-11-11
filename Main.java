import functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №2: Табулированные функции ===\n");
        
        // Тест 1: Создание функции f(x) = x^2
        System.out.println("1. Создание функции f(x) = x^2 на [0, 4] с 5 точками:");
        TabulatedFunction func1 = new TabulatedFunction(0, 4, 5);
        
        for (int i = 0; i < func1.getPointsCount(); i++) {
            double x = func1.getPointX(i);
            func1.setPointY(i, x * x);
        }
        
        printFunctionInfo(func1, "func1");
        
        // Тест 2: Проверка точных совпадений с узлами табуляции
        System.out.println("\n2. Проверка точных совпадений с узлами табуляции:");
        System.out.println("f(0.0) = " + func1.getFunctionValue(0.0));
        System.out.println("f(1.0) = " + func1.getFunctionValue(1.0)); 
        System.out.println("f(2.0) = " + func1.getFunctionValue(2.0));
        System.out.println("f(3.0) = " + func1.getFunctionValue(3.0));
        System.out.println("f(4.0) = " + func1.getFunctionValue(4.0));
        
        // Тест 3: Проверка интерполяции
        System.out.println("\n3. Проверка интерполяции в разных точках:");
        double[] testPoints = {-1, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5};
        
        for (double x : testPoints) {
            double y = func1.getFunctionValue(x);
            System.out.printf("f(%4.1f) = ", x);
            if (Double.isNaN(y)) {
                System.out.println("не определена");
            } else {
                System.out.printf("%6.3f\n", y);
            }
        }
    }
    
    public static void printFunctionInfo(TabulatedFunction func, String name) {
        System.out.println("Функция " + name + ":");
        System.out.println("  Область определения: [" + func.getLeftDomainBorder() + ", " + func.getRightDomainBorder() + "]");
        System.out.println("  Количество точек: " + func.getPointsCount());
        System.out.println("  Точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.printf("    [%d] (%4.1f; %4.1f)\n", i, func.getPointX(i), func.getPointY(i));
        }
    }
}