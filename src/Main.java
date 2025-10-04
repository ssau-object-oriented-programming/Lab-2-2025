import functions.*;

public class Main {
    public static void main(String[] args) {
        TabulatedFunction cubicFunction = new TabulatedFunction(-2.0, 2.0, 5);
        for (int i = 0; i < cubicFunction.getPointsCount(); i++) {
            double x = cubicFunction.getPointX(i);
            cubicFunction.setPointY(i, x * x * x);
        }
        System.out.println("Исходные точки:"); // Вывожу исходные точки
        cubicFunction.printTabulatedFunction();
        double[] testPoints = {-3.0, -2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0, 3.0}; //Точки для теста

        for (double x : testPoints) {
            double value = cubicFunction.getFunctionValue(x);
            if (Double.isNaN(value)) {
                System.out.println("не определено " + x);
            } else {
                System.out.println(x + "=" + value + " ожидалось = " + x*x*x);
            }
        }
        FunctionPoint newPoint = new FunctionPoint(-1.5, -3.375); //Меняю вторую точку
        cubicFunction.setPoint(1, newPoint);

        System.out.println("После замены точки:");
        cubicFunction.printTabulatedFunction();

        FunctionPoint addedPoint = new FunctionPoint(4, 64); //Добавляю новую точку
        cubicFunction.addPoint(addedPoint);

        System.out.println("После добавления точки:");
        cubicFunction.printTabulatedFunction();

        cubicFunction.deletePoint(3); //Удаляю точку

        System.out.println("После удаления точки:");
        cubicFunction.printTabulatedFunction();
        for (double x : testPoints) {
            double value = cubicFunction.getFunctionValue(x);
            if (Double.isNaN(value)) {
                System.out.println("не определено " + x);
            } else {
                System.out.println(x + "=" + value + " ожидалось = " + x*x*x);
            }
        }
    }

}
