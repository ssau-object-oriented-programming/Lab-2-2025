import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание экземпляра класса через первый конструктор: ");
        TabulatedFunction f = new TabulatedFunction(0, 6, 10);
        double[] y_values = new double[f.getPointsCount()];
        System.out.println("Функция f(x) = 2x + 1");
        System.out.println("Область определения: [" + f.getLeftDomainBorder() + "; " + f.getRightDomainBorder() + "]");
        System.out.println("Количество точек: " + f.getPointsCount());
        System.out.println("\nТочки функции: ");

        for (int i = 0; i < f.getPointsCount(); i++) {
            f.setPointY(i,2* f.getPointX(i) + 1);
            System.out.printf("%d: (%.2f; %.2f)%n", i+1, f.getPointX(i), f.getPointY(i));
            y_values[i] = f.getPointY(i);
        }
        System.out.println("\nСоздание экземпляра класса через второй конструктор: ");
        System.out.println("Точки функции: ");
        TabulatedFunction f1 = new TabulatedFunction(0, 6, y_values);
        for (int i = 0; i < y_values.length; i++) {
            System.out.printf("%d: (%.2f; %.2f)%n", i+1, f.getPointX(i), f.getPointY(i));
        }
        //Проверка метода getFunctionValue
        System.out.println("\nЗначения функции f(x) в разных x: ");
        double[] x_val = {-1.3, 18, 1, 6, 43, 3.1};
        for (int i = 0; i < x_val.length; i++) {
            if (Double.isNaN(f.getFunctionValue(x_val[i]))) {
                System.out.println("Значение x = " + x_val[i] + " лежит вне области определения функции" );
            }
            else {
                System.out.printf("f(%.2f) = %.2f%n", x_val[i], f.getFunctionValue(x_val[i]));
            }
        }
        //Проверка метода setPoint #1
        System.out.println("\nЗамена точки №3 на точку (7; 15)");
        System.out.printf("Исходная точка %d: (%.2f; %.2f)%n", 3, f.getPointX(2), f.getPointY(2));
        FunctionPoint test1 = new FunctionPoint(7, 15);
        f.setPoint(2, test1);
        if (f.getPointX(2) == test1.getX()) {
            System.out.println("\nЗамена точки №3 прошла успешно");
        }
        else {
            System.out.println("Замена не удалась, так как х не попал в интервал");
        }
        System.out.printf("Точка %d: (%.2f; %.2f)%n", 3, f.getPointX(2), f.getPointY(2));

        //Проверка метода setPoint #2
        System.out.println("\nЗамена точки №8 на точку (5; 11)");
        System.out.printf("Исходная точка %d: (%.2f; %.2f)%n", 8, f.getPointX(7), f.getPointY(7));
        FunctionPoint test2 = new FunctionPoint(5, 11);
        f.setPoint(7, test2);
        if (f.getPointX(7) == test2.getX()) {
            System.out.println("Замена точки №3 прошла успешно");
        }
        else {
            System.out.println("Замена не удалась, так как х не попал в интервал");
        }
        System.out.printf("Точка %d: (%.2f; %.2f)%n", 8, f.getPointX(7), f.getPointY(7));

        //Проверка метода setPointX #1
        System.out.println("\nЗамена точки №2 на точку с координатой х = 1");
        System.out.printf("Исходная точка %d: (%.2f; %.2f)%n", 2, f.getPointX(1), f.getPointY(1));
        f.setPointX(1,1);
        if (f.getPointX(1) == 1) {
            f.setPointY(1,3);
            System.out.println("Замена точки №2 прошла успешно");
        }
        else {
            System.out.println("Замена не удалась, так как х не попал в интервал");
        }
        System.out.printf("Точка %d: (%.2f; %.2f)%n", 2, f.getPointX(1), f.getPointY(1));

        //Проверка метода setPointX #2
        System.out.println("\nЗамена точки №9 на точку с координатой х = 4.9");
        System.out.printf("Исходная точка %d: (%.2f; %.2f)%n", 9, f.getPointX(8), f.getPointY(8));
        f.setPointX(8,4.9);
        if (f.getPointX(8) == 4.9) {
            f.setPointY(8,4.9*2+1);
            System.out.println("Замена точки №2 прошла успешно");
        }
        else {
            System.out.println("Замена не удалась, так как х не попал в интервал");
        }
        System.out.printf("Точка %d: (%.2f; %.2f)%n", 9, f.getPointX(8), f.getPointY(8));

        System.out.println("\nТочки функции после замены: ");
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.printf("%d: (%.2f; %.2f)%n", i+1, f.getPointX(i), f.getPointY(i));
        }
        //Проверка метода deletePoint
        System.out.printf("\nУдаление точки №%d: (%.2f; %.2f)%n", 9, f.getPointX(8), f.getPointY(8));
        f.deletePoint(8);
        System.out.println("Точки функции после удаления: ");
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.printf("%d: (%.2f; %.2f)%n", i+1, f.getPointX(i), f.getPointY(i));
        }
        //Проверка метода addPoint
        System.out.println("\nДобавление точек (7, 15) и (3, 7)");
        FunctionPoint test_add1 = new FunctionPoint(7,15);
        f.addPoint(test_add1);
        FunctionPoint test_add2 = new FunctionPoint(3,7);
        f.addPoint(test_add2);
        System.out.println("Точки функции после добавления: ");
        for (int i = 0; i < f.getPointsCount(); i++) {
            System.out.printf("%d: (%.2f; %.2f)%n", i+1, f.getPointX(i), f.getPointY(i));
        }
    }
}
