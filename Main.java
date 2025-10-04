import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {
        //создаём экземпляр класса табулированной функции и заполняем его значениями для y=x^2
        TabulatedFunction function = new TabulatedFunction(-6.0, 6.0, 9);

        for (int i = 0; i < function.getPointsCount(); i++){
            double x = function.getPointX(i);
            function.setPointY(i, function.getPointX(i)*function.getPointX(i));
        }

        //демонстрация изначально заданной функции
        System.out.println("Функция y=x^2 из "+function.getPointsCount()+" точек на отрезке [-6.0, 6.0]: ");
        printFunction(function);
        System.out.println();

        //демонстрация значений функции в конкретных точках
        double[] testValues = {2.0, -10.0, 5.7};
        for (double testValue : testValues) {
            System.out.println("Значение функции в точке f(" + testValue + "): " + function.getFunctionValue(testValue));
        }
        System.out.println();

        //демонстрация границ области определения функции
        System.out.println("Функция определена на отрезке ["+function.getLeftDomainBorder()+", "+function.getRightDomainBorder()+"]");
        System.out.println();

        //демонстрация функции после удаления точки
        function.deletePoint(7);
        System.out.println("Функция после удаления 8ой точки: ");
        printFunction(function);
        System.out.println();

        //демонстрация функции после вставки точки
        FunctionPoint testPoint1 = new FunctionPoint(-5.8,21);
        function.addPoint(testPoint1);
        System.out.println("Функция после вставки точки c координатами (-5.8, 18.33):");
        printFunction(function);
        System.out.println();

        //демонстрация функции после замены точки
        FunctionPoint testPoint2 = new FunctionPoint(2.8,100);
        function.setPoint(6, testPoint2);
        System.out.println("Функция после замены 7ой точки:");
        printFunction(function);
        System.out.println();

        //демонстрация функции после замены конкретных координат (отдельно x и отдельно y)
        function.setPointX(2, -4.0);
        function.setPointY(5, 12.345);
        System.out.println("Функция после замены координат X у 3 точки и Y у 6 точки: ");
        printFunction(function);
        System.out.println();
    }
    public static void printFunction(TabulatedFunction function){
        for (int i = 0; i < function.getPointsCount(); i++){
            System.out.println((i+1)+" точка: ("+function.getPointX(i)+", "+function.getPointY(i)+")");
        }
    }
}