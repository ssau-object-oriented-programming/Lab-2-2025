import functions.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(" ТЕСТИРОВАНИЕ КЛАССА FunctionPoint \n");
        
        // 1. Тестирование конструкторов
        testConstructors();
        
        // 2. Тестирование геттеров и сеттеров
        testGettersAndSetters();
        
        // 3. Тестирование методов в различных сценариях
        testMethods();
        
        // 4. Тестирование с TabulatedFunction
        testWithTabulatedFunction();
        
        System.out.println("\n ВСЕ ТЕСТЫ ЗАВЕРШЕНЫ ");
    }
    
    public static void testConstructors() {
        System.out.println("1. ТЕСТИРОВАНИЕ КОНСТРУКТОРОВ:");
        
        // Конструктор с параметрами
        FunctionPoint point1 = new FunctionPoint(3.5, 7.2);
        System.out.println(" FunctionPoint(3.5, 7.2) = " + point1);
        
        // Конструктор копирования
        FunctionPoint point2 = new FunctionPoint(point1);
        System.out.println(" FunctionPoint(copy) = " + point2);
        
        // Конструктор по умолчанию
        FunctionPoint point3 = new FunctionPoint();
        System.out.println(" FunctionPoint() = " + point3);
        
        // Проверка, что копия независима от оригинала
        point1.setX(10.0);
        System.out.println(" После изменения оригинала, копия не изменилась: " + point2);
        
        System.out.println();
    }
    
    public static void testGettersAndSetters() {
        System.out.println("2. ТЕСТИРОВАНИЕ ГЕТТЕРОВ И СЕТТЕРОВ:");
        
        FunctionPoint point = new FunctionPoint(2.0, 4.0);
        
        // Тестирование геттеров
        System.out.println(" getX() = " + point.getX());
        System.out.println(" getY() = " + point.getY());
        
        // Тестирование сеттеров
        point.setX(5.0);
        point.setY(25.0);
        System.out.println(" После setX(5.0) и setY(25.0) = " + point);
        
        // Тестирование с отрицательными значениями
        point.setX(-3.0);
        point.setY(-9.0);
        System.out.println(" С отрицательными значениями = " + point);
        
        // Тестирование с нулевыми значениями
        point.setX(0.0);
        point.setY(0.0);
        System.out.println(" С нулевыми значениями = " + point);
        
        // Тестирование с дробными значениями
        point.setX(2.75);
        point.setY(3.14159);
        System.out.println(" С дробными значениями = " + point);
        
        System.out.println();
    }
    
    public static void testMethods() {
        System.out.println("3. ТЕСТИРОВАНИЕ МЕТОДОВ В РАЗЛИЧНЫХ СЦЕНАРИЯХ:");
        
        // Создание нескольких точек для сравнения
        FunctionPoint pointA = new FunctionPoint(1.0, 1.0);
        FunctionPoint pointB = new FunctionPoint(1.0, 1.0); // Такая же как A
        FunctionPoint pointC = new FunctionPoint(2.0, 4.0); // Другая
        FunctionPoint pointD = new FunctionPoint(pointA);   // Копия A
        
        System.out.println(" pointA = " + pointA);
        System.out.println(" pointB = " + pointB + " (такая же как A)");
        System.out.println(" pointC = " + pointC + " (другая)");
        System.out.println(" pointD = " + pointD + " (копия A)");
        
        // Тестирование работы с массивом точек
        FunctionPoint[] pointsArray = {
            new FunctionPoint(0, 0),
            new FunctionPoint(1, 1),
            new FunctionPoint(2, 4),
            new FunctionPoint(3, 9)
        };
        
        System.out.println("\nМассив точек:");
        for (int i = 0; i < pointsArray.length; i++) {
            System.out.println("  points[" + i + "] = " + pointsArray[i]);
        }
        
        // Изменение точек в массиве
        pointsArray[1].setX(1.5);
        pointsArray[1].setY(2.25);
        System.out.println(" После изменения points[1] = " + pointsArray[1]);
        
        System.out.println();
    }
    
    public static void testWithTabulatedFunction() {
        System.out.println("4. ТЕСТИРОВАНИЕ TabulatedFunction:");
        
        // Создание объекта типа TabulatedFunction
        TabulatedFunction func = new TabulatedFunction(0.0, 10.0, 6);
        
        // Заполнение функции с использованием FunctionPoint
        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            FunctionPoint point = new FunctionPoint(x, x * x); // f(x) = x²
            func.setPoint(i, point);
        }
        
        System.out.println("Массив типа квадратичной функции f(x) = x²:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint point = func.getPoint(i);
            System.out.println("  " + point);
        }
        
        // Тестирование добавления новых точек
        System.out.println("\nДобавление новых точек:");
        
        FunctionPoint newPoint1 = new FunctionPoint(1.5, 2.25);
        FunctionPoint newPoint2 = new FunctionPoint(3.5, 12.25);
        FunctionPoint newPoint3 = new FunctionPoint(7.5, 56.25);
        
        func.addPoint(newPoint1);
        func.addPoint(newPoint2);
        func.addPoint(newPoint3);
        
        System.out.println("После добавления 3 точек:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  " + func.getPoint(i));
        }
        
        // Тестирование изменения существующей точки
        System.out.println("\nИзменение точки с индексом 2:");
        FunctionPoint modifiedPoint = new FunctionPoint(2.0, 8.0); // Было (2.0, 4.0)
        func.setPoint(2, modifiedPoint);
        System.out.println("  Новая точка: " + func.getPoint(2));
        
        // Тестирование граничных значений
        System.out.println("\nТестирование граничных значений:");
        FunctionPoint edgePoint1 = new FunctionPoint(func.getLeftDomainBorder(), 0);
        FunctionPoint edgePoint2 = new FunctionPoint(func.getRightDomainBorder(), 100);
        
        System.out.println("  Левая граница: " + edgePoint1);
        System.out.println("  Правая граница: " + edgePoint2);
        
        // Тестирование специальных значений
        System.out.println("\nТестирование специальных значений:");
        FunctionPoint specialPoint1 = new FunctionPoint(Double.MAX_VALUE, Double.MAX_VALUE);
        FunctionPoint specialPoint2 = new FunctionPoint(Double.MIN_VALUE, Double.MIN_VALUE);
        
        System.out.println("  MAX_VALUE: " + specialPoint1);
        System.out.println("  MIN_VALUE: " + specialPoint2);
        
        System.out.println();
    }
}