import functions.*;

public class Main {
    public static void main(String[] args) {

        // Создаем табулированную функцию
        double[] values = {0, 1, 4, 9, 16, 25};
        TabulatedFunction func = new TabulatedFunction(0, 10, values);

        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(" x = " + func.getPointX(i) + ", y = " + func.getPointY(i));
        }

        //  Проверяем значение функции в различных точках
        double[] testX = {-1, 0, 3.5, 5, 8.5, 10, 12};
        for (int i = 0; i < testX.length; i++) {
            double x = testX[i];
            double y = func.getFunctionValue(x);
            System.out.println("f(" + x + ") = " + y);
        }

        //  Изменяем значение точки
        func.setPoint(1, new FunctionPoint(2, 10));
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(" x = " + func.getPointX(i) + ", y = " + func.getPointY(i));
        }

        //  Добавляем новую точку
        func.addPoint(new FunctionPoint(11, 30));
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(" x = " + func.getPointX(i) + ", y = " + func.getPointY(i));
        }

        // Удаляем точку
        func.deletePoint(2);
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println(" x = " + func.getPointX(i) + ", y = " + func.getPointY(i));
        }

    }
}