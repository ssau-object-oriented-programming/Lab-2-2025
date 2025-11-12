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
        
        // ТЕСТ 4: Проверка операций с точками
        System.out.println("\n4. Тестирование операций с точками:");
        
        // Исходное состояние
        System.out.println("\nИсходные точки:");
        printAllPoints(func1);
        
        // Добавление точки
        System.out.println("\n4.1. Добавление точки (2.5, 6.25):");
        func1.addPoint(new FunctionPoint(2.5, 6.25));
        printAllPoints(func1);
        
        // Добавление еще одной точки
        System.out.println("\n4.2. Добавление точки (1.5, 2.25):");
        func1.addPoint(new FunctionPoint(1.5, 2.25));
        printAllPoints(func1);
        
        // Замена точки
        System.out.println("\n4.3. Замена точки с индексом 3 на (2.8, 7.84):");
        func1.setPoint(3, new FunctionPoint(2.8, 7.84));
        printAllPoints(func1);
        
        // Замена координат
        System.out.println("\n4.4. Замена x координаты точки с индексом 2 на 1.8:");
        func1.setPointX(2, 1.8);
        printAllPoints(func1);
        
        System.out.println("\n4.5. Замена y координаты точки с индексом 4 на 20.0:");
        func1.setPointY(4, 20.0);
        printAllPoints(func1);
        
        // Удаление точки
        System.out.println("\n4.6. Удаление точки с индексом 1:");
        func1.deletePoint(1);
        printAllPoints(func1);
        
        // Проверка некорректных операций
        System.out.println("\n4.7. Проверка некорректных операций:");
        System.out.println("Попытка удалить точку с неверным индексом (-1):");
        int pointsBefore = func1.getPointsCount();
        func1.deletePoint(-1);
        int pointsAfter = func1.getPointsCount();
        System.out.println("Количество точек до: " + pointsBefore + ", после: " + pointsAfter + " (не изменилось)");
        
        System.out.println("Попытка заменить точку с нарушением порядка x:");
        double originalX = func1.getPointX(2);
        func1.setPoint(2, new FunctionPoint(0.5, 1.0)); // Должно отказать
        double newX = func1.getPointX(2);
        System.out.println("x точки с индексом 2 до: " + originalX + ", после: " + newX + " (не изменился)");
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
    
    public static void printAllPoints(TabulatedFunction func) {
        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.println("Точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.printf("    [%d] (%.1f; %.2f)\n", 
                i, func.getPointX(i), func.getPointY(i));
        }
    }
}