public class Main {
    public static void main(String[] args) {
        // Создание массива значений функции (квадратичная функция y = x²)
        double[] values = {0.0, 1.0, 4.0, 9.0, 16.0, 25.0};

        // создание объекта табулированной функции
        functions.TabulatedFunction func = new functions.TabulatedFunction(0.0, 5.0, values);

        System.out.println("=== Начальное состояние функции ===");
        func.printPoints();

        // Массив тестовых точек для вычисления значений функции
        // Включает точки внутри и вне области определения
        double[] testPoints = {-2.0, -1.0, 0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 6.0};

        // Цикл по всем тестовым точкам
        for (double x : testPoints) {
            // Получение значения функции в точке x
            double y = func.getFunctionValue(x);

            // Вывод результата в формате: f(x) = y
            System.out.printf("f(%4.1f) = ", x);

            // Проверка, является ли результат числом (не NaN)
            if (!Double.isNaN(y)) {
                // Если точка в области определения - выводим значение
                System.out.printf("%6.3f%n", y);
            } else {
                // Если точка вне области определения - выводим "undefined"
                System.out.println("undefined");
            }
        }

        System.out.println("\n=== ТЕСТИРОВАНИЕ ИЗМЕНЕНИЯ ТОЧЕК ===");

        System.out.println("1. Тест setPoint:");
        System.out.println("До: f(1.5) = " + func.getFunctionValue(1.5));
        func.setPoint(2, new functions.FunctionPoint(2.0, 10.0)); // Меняем (2,4) на (2,10)
        System.out.println("После: f(1.5) = " + func.getFunctionValue(1.5));
        func.printPoints();
        
        System.out.println("2. Тест setPointX:");
        System.out.println("До: f(1.2) = " + func.getFunctionValue(1.2));
        func.setPointX(1, 1.5); // Меняем x=1 на x=1.5
        System.out.println("После: f(1.2) = " + func.getFunctionValue(1.2));
        func.printPoints();
        
        System.out.println("3. Тест addPoint:");
        System.out.println("До: f(2.5) = " + func.getFunctionValue(2.5));
        func.addPoint(new functions.FunctionPoint(2.5, 6.25));
        System.out.println("После: f(2.5) = " + func.getFunctionValue(2.5));
        func.printPoints();
        
        System.out.println("4. Тест deletePoint:");
        System.out.println("До: f(0.5) = " + func.getFunctionValue(0.5));
        func.deletePoint(0); // Удаляем первую точку (0,0)
        System.out.println("После: f(0.5) = " + func.getFunctionValue(0.5));
        func.printPoints();
    }
}