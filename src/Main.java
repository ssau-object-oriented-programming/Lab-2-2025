import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание функции y=x^2 на отрезке [0, 10]:");
        double[] values = new double[11];
        for (int i = 0; i < values.length; ++i) {
            values[i] = i * i;
        }
        TabulatedFunction function = new TabulatedFunction(0, 10, values);
        printFunction(function);

        System.out.println("Проверка значений функции:");
        System.out.println("Значение в точке f(2.0) = " + function.getFunctionValue(2.0));
        System.out.println("Значение между точками f(2.5) = " + function.getFunctionValue(2.5));
        System.out.println("Значение вне области определения f(-1.0) = " + function.getFunctionValue(-1.0));
        System.out.println();

        System.out.println("Изменение точки:");
        function.setPoint(5, new FunctionPoint(5.5, 30.25));
        printFunction(function);

        System.out.println("Удаление точки:");
        function.deletePoint(3);
        printFunction(function);
        
        System.out.println("Добавление новой точки:");
        function.addPoint(new FunctionPoint(8.5, 72.25));
        printFunction(function);
    }
    public static void printFunction(TabulatedFunction func) {
        System.out.println("Табулированная функция из " + func.getPointsCount() + " точек:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint p = func.getPoint(i);
            System.out.println("Точка " + i + ": (" + p.getX() + "; " + p.getY() + ")");
        }
        System.out.println();
    }
}
