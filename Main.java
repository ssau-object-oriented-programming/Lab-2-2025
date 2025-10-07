import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] s) {
        TabulatedFunction fun = new TabulatedFunction(1, 10, 5); //задаем функцию на интервале 1 10, 5 точек

        for (int i=0; i < fun.getPointsCount(); i++) { //для каждого x устанавливаем значение y=2x
            fun.setPointY(i,2 * fun.getPointX(i)); //по индексу получаем x, умножаем на 2 и записываем в y
        }
        System.out.println("Конструктор 1: ");
        for (int i=0; i < fun.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun.getPointX(i) + " " + fun.getPointY(i));
        }
        System.out.println("Конструктор 2: ");
        double[] val ={1, 2, 3, 5, 9}; //Проверка второго конструктора
        TabulatedFunction fun1 = new TabulatedFunction(1, 10, val); //для каждого x устанавливаем значение из массива
        for (int i=0; i < fun1.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun1.getPointX(i) + " " + fun1.getPointY(i));
        }

        System.out.println("Левая граница первой функции(y=2x) " + fun.getLeftDomainBorder());
        System.out.println("Правая граница первой функции(y=2x) " + fun.getRightDomainBorder());

        System.out.println("Значение функции(y=2x) в точке x = 1.25, входящей в интервал: " + fun.getFunctionValue(1.25));
        System.out.println("Значение функции(y=2x) в точке x = 8 входящей в интервал: " + fun.getFunctionValue(8));
        System.out.println("Значение функции(y=2x) в точке x = 0 не входящей в интервал: " + fun.getFunctionValue(0));
        System.out.println("Значение функции(y=2x) в точке x = 14 не входящей в интервал: " +fun.getFunctionValue(14));

        System.out.println("Функция, которая возвращает копию второй точки(X, Y): " + fun.getPoint(1).getx() + " " + fun.getPoint(1).gety());

        System.out.println("Заменим вторую точку в начальной функции(она не изменится так как вне интервала)");
        FunctionPoint p1 = new FunctionPoint();
        fun.setPoint(1, p1);
        for (int i=0; i < fun.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun.getPointX(i) + " " + fun.getPointY(i));
        }

        System.out.println("Заменим вторую точку в начальной функции");
        FunctionPoint p2 = new FunctionPoint(2, 7);
        fun.setPoint(1, p2);
        for (int i=0; i < fun.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun.getPointX(i) + " " + fun.getPointY(i));
        }

        System.out.println("Заменим у третьей точки абсциссу");
        fun.setPointX(2, 6.6);
        for (int i=0; i < fun.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun.getPointX(i) + " " + fun.getPointY(i));
        }

        System.out.println("Удалим третью точку");
        fun.deletePoint(2);
        for (int i=0; i < fun.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun.getPointX(i) + " " + fun.getPointY(i));
        }

        System.out.println("Добавим три новых точки");
        fun.addPoint(p1);
        FunctionPoint p3 = new FunctionPoint(11, 9);
        FunctionPoint p4 = new FunctionPoint(4, 8);
        fun.addPoint(p3);
        fun.addPoint(p4);
        for (int i=0; i < fun.getPointsCount(); i++) { //Вывод значений x и y
            System.out.println("(X, Y) "+ fun.getPointX(i) + " " + fun.getPointY(i));
        }
    }
}
