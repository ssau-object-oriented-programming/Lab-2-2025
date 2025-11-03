import functions.*;

public class Main {
    public static void main(String[] arg){
        TabulatedFunction func = new TabulatedFunction(1, 5.6, 8);
        for (int i =0; i < func.getPointsCount(); i++)
            func.setPointY(i, func.getPointX(i)*1.2);

        System.out.println("Функиця: y=1.2x");
        func.outFunction();

        System.out.println("Проверка удаления существующей точки");
        func.deletePoint(3);
        func.outFunction();

        System.out.println("Проверка удаления несуществующей точки");
        func.deletePoint(8);
        func.outFunction();

        System.out.println("Проверка добавления точки");
        func.addPoint(new FunctionPoint(3.0, 4.0));
        func.outFunction();

        System.out.println("Проверка установки некорректной для интерполяции точки");
        func.setPoint(2, new FunctionPoint(1.2, 5));
        func.outFunction();

        System.out.println("Проверка установки некорректной для интерполяции точки");
        func.setPoint(-3, new FunctionPoint(-10, 5));
        func.outFunction();

        System.out.println("Проверка установки корректной для интерполяции точки");
        func.setPoint(0, new FunctionPoint(1.3, 5));
        func.outFunction();

        System.out.println("Проверка установки корректной для интерполяции точки");
        func.setPoint(2, new FunctionPoint(2.2, 5));
        func.outFunction();

        System.out.println("Проверка getFunctionValue");
        for (double i = -1; i < 3; i += 0.8) {
            System.out.printf("(%.2f, %.2f) ", i, func.getFunctionValue(i));
            System.out.println();
        }

    }
}
