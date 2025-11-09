import functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №2: Табулированные функции ===\n");
        
        // Тест 1: Создание функции с количеством точек
        System.out.println("1. Создание функции f(x) = x^2 на [0, 4] с 5 точками:");
        TabulatedFunction func1 = new TabulatedFunction(0, 4, 5);
        
        // Устанавливаем значения y = x^2
        for (int i = 0; i < func1.getPointsCount(); i++) {
            double x = func1.getPointX(i);
            func1.setPointY(i, x * x);
        }
        
        printFunctionInfo(func1, "func1");
        
        // Тест 2: Создание функции с массивом значений
        System.out.println("\n2. Создание функции с массивом значений:");
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction func2 = new TabulatedFunction(0, 4, values);
        
        printFunctionInfo(func2, "func2");
        
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
        
        // Тест 4: Добавление и удаление точек
        System.out.println("\n4. Тестирование добавления и удаления точек:");
        System.out.println("До добавления точек: " + func1.getPointsCount() + " точек");
        
        func1.addPoint(new FunctionPoint(2.5, 6.25));
        func1.addPoint(new FunctionPoint(1.5, 2.25));
        
        System.out.println("После добавления 2 точек: " + func1.getPointsCount() + " точек");
        
        func1.deletePoint(2);
        System.out.println("После удаления одной точки: " + func1.getPointsCount() + " точек");
        
        System.out.println("\nФинальные точки функции:");
        for (int i = 0; i < func1.getPointsCount(); i++) {
            System.out.printf("(%4.1f; %4.1f) ", 
                func1.getPointX(i), func1.getPointY(i));
        }
        System.out.println();
    }
    
    public static void printFunctionInfo(TabulatedFunction func, String name) {
        System.out.println("Функция " + name + ":");
        System.out.println("  Область определения: [" + 
            func.getLeftDomainBorder() + ", " + 
            func.getRightDomainBorder() + "]");
        System.out.println("  Количество точек: " + func.getPointsCount());
        System.out.println("  Точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.printf("    [%d] (%4.1f; %4.1f)\n", 
                i, func.getPointX(i), func.getPointY(i));
        }
    }
}
