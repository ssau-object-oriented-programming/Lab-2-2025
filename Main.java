import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Создаем функцию f(x) = 3x + 2 на интервале [1, 5] с 4 точками
        TabulatedFunction f = new TabulatedFunction(1, 5, 4);
        for (int i = 0; i < f.getPointsCount(); i++) {
            // Для каждого x устанавливаем значение y = 3x + 2
            f.setPointY(i, 3 * f.getPointX(i) + 2);
        }

        System.out.println("Конструктор 1");
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }

        System.out.println("Конструктор 2");
        double[] val = {5, 8, 11, 14}; // Значения y = 3x + 2 для x = 1,2,3,4
        TabulatedFunction fun1 = new TabulatedFunction(1, 4, val);
        for (int i = 0; i < fun1.getPointsCount(); i++) {
            System.out.println("x,y "+ fun1.getPointX(i) + " " + fun1.getPointY(i));
        }

        System.out.println("Левая граница " + f.getLeftDomainBorder());
        System.out.println("Правая граница " + f.getRightDomainBorder());

        System.out.println("Значение функции в точке x = 1.5 входящей в интервал: " + f.getFunctionValue(1.5));
        System.out.println("Значение функции в точке x = 3.5 входящей в интервал: " + f.getFunctionValue(3.5));
        System.out.println("Значение функции в точке x = 0 не входящей в интервал: " + f.getFunctionValue(0));
        System.out.println("Значение функции в точке x = 6 не входящей в интервал: " + f.getFunctionValue(6));

        System.out.println("Возвращение копии второй точки " + f.getPoint(1).getX() + " " + f.getPoint(1).getY());

        System.out.println("Замена второй точки в начальной функции (вне интервала)");
        FunctionPoint p1 = new FunctionPoint(0.5, 10); // x=0.5 вне интервала [1,5]
        f.setPoint(1, p1);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }

        System.out.println("Замена 2 точки в начальной функции");
        FunctionPoint p2 = new FunctionPoint(2.5, 12);
        f.setPoint(1, p2);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }

        System.out.println("Замена абсциссы 3 точки");
        f.setPointX(2, 3.8);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }
        System.out.println("Замена ординаты 2 точки");
        f.setPointY(1, 15);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }

        System.out.println("Удаление 3 точки");
        f.deletePoint(2);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }

        System.out.println("Добавление 2 новых точек");
        FunctionPoint p3 = new FunctionPoint(2.2, 9);
        FunctionPoint p4 = new FunctionPoint(3.5, 13);
        f.addPoint(p3);
        f.addPoint(p4);
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.println("x,y "+ f.getPointX(i) + " " + f.getPointY(i));
        }
    }
}