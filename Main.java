import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        
        double[] val = {0.0, 1.0, 4.0, 9.0, 16.0};
        TabulatedFunction function = new TabulatedFunction(0.0, 4.0, val);
        
        System.out.println("- Изначальная функция -");
        printFunctionInfo(function);
        
        System.out.println("\n- Вычисление значений функции -");
        double[] Points = {-1.0, 0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 5.0};
        for (double x : Points) {
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.1f) = %s%n", x, Double.isNaN(y) ? "не определено" : String.format("%.2f", y));
        }
        
        
        System.out.println("\n- Изменение точек -");
        function.setPointY(2, 5.0); 
        System.out.println("После изменения: ");
        printFunctionInfo(function);
        
        
        System.out.println("\n- Добавление точки -");
        FunctionPoint newPoint = new FunctionPoint(1.5, 2.25);
        function.addPoint(newPoint);
        System.out.println("После добавления:");
        printFunctionInfo(function);
        
        
        
        System.out.println("\n- Удаление точки -");
        function.deletePoint(2); // Удаляем точку с индексом 2
        System.out.println("После удаления:");
        printFunctionInfo(function);
        
        
        System.out.println("\n- Значения после всех изменений -");
        for (double x : Points) {
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.1f) = %s%n", x, Double.isNaN(y) ? "не определено" : String.format("%.2f", y));
        }
        
        
        System.out.println("\n- Границы -");
        System.out.println("Левая граница: " + function.getLeftDomainBorder());
        System.out.println("Правая граница: " + function.getRightDomainBorder());
        System.out.println("Количество точек: " + function.getPointsCount());
    }
    
    private static void printFunctionInfo(TabulatedFunction function) {
        System.out.println("Точки функции:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            double y = function.getPointY(i);
            System.out.printf(" %d %.1f %.1f %n", i, x, y);
        }
    }
}