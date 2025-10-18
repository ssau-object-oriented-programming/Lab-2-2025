import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {
        
        // 1. Создаем табулированную функцию f(x) = x² на отрезке [0, 4]
        System.out.println("1. СОЗДАНИЕ ФУНКЦИИ f(x) = x^2 на [0, 4]");
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction function = new TabulatedFunction(0, 4, values);
        System.out.println("Функция f(x) = x^2");
        System.out.println("Количество точек: " + function.getPointsCount());
        System.out.println("Область определения: [" + function.getLeftDomainBorder() + ", " + function.getRightDomainBorder() + "]");
        
        // 2. Тестируем вычисление значений в различных точках
        System.out.println("\n2. ВЫЧИСЛЕНИЕ ЗНАЧЕНИЙ ФУНКЦИИ");
        double[] testPoints = {-1, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5};
        
        for (double x : testPoints) {
            double y = function.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.println("f(x) = не определено (вне области)");
            } else {
                System.out.println("f(x) = " +  y );
            }
        }
        
        // 3. Тестируем изменение существующих точек
        System.out.println("\n3. ИЗМЕНЕНИЕ СУЩЕСТВУЮЩИХ ТОЧЕК");

        // Меняем значение Y в точке x=2
        function.setPointY(2, 10.0);
        System.out.println("После setPointY(2, 10.0): f(2.0) = " + function.getFunctionValue(2.0));

        // Меняем точку целиком
        function.setPoint(3, new FunctionPoint(3.5, 20.0));
        System.out.println("После setPoint(3, (3.5; 20.0)): f(3.5) = " + function.getFunctionValue(3.5));
        
        // 4. Тестируем добавление новых точек
        System.out.println("\n4. ДОБАВЛЕНИЕ НОВЫХ ТОЧЕК");

        // Добавляем точку в начало
        function.addPoint(new FunctionPoint(-1.0, 1.0));
        System.out.println("После addPoint((-1.0; 1.0)): точек = " + function.getPointsCount() + 
                        ", f(-1.0) = " + function.getFunctionValue(-1.0));

        // Добавляем точку в середину
        function.addPoint(new FunctionPoint(1.5, 2.25));
        System.out.println("После addPoint((1.5; 2.25)): f(1.5) = " + function.getFunctionValue(1.5));

        // Добавляем точку в конец
        function.addPoint(new FunctionPoint(5.0, 25.0));
        System.out.println("После addPoint((5.0; 25.0)): точек = " + function.getPointsCount() + 
                        ", f(5.0) = " + function.getFunctionValue(5.0));
        
        // Пытаемся добавить точку с существующим X (должна заменить)
        function.addPoint(new FunctionPoint(2.0, 100.0));
        System.out.println("После addPoint((2.0; 100.0)) - замена: " + function);
        System.out.println("f(2.0) = " + function.getFunctionValue(2.0));
        
        // 5. Тестируем удаление точек
        System.out.println("\n5. УДАЛЕНИЕ ТОЧЕК");

        // Удаляем первую точку
        function.deletePoint(0);
        System.out.println("После deletePoint(0): точек = " + function.getPointsCount() + 
                        ", левая граница = " + function.getLeftDomainBorder());

        // Удаляем точку из середины
        function.deletePoint(2);
        System.out.println("После deletePoint(2): точек = " + function.getPointsCount());

        // Удаляем последнюю точку
        int lastIndex = function.getPointsCount() - 1;
        function.deletePoint(lastIndex);
        System.out.println("После deletePoint(" + lastIndex + "): точек = " + function.getPointsCount() + 
                        ", правая граница = " + function.getRightDomainBorder());

        }
}
