import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {
        TabulatedFunction test_fun = new TabulatedFunction(1,10,10);
      //  test_fun.setPoint(3,test_point);
        for(int i=0;i<test_fun.getPointsCount();i++){
            test_fun.setPointY(i,test_fun.getPointX(i)*5);
        }

        System.out.println("Создали функцию через первый конструктор (y=5x)");
        for(int i=0;i<test_fun.getPointsCount();i++){
            System.out.println("X: "+ test_fun.getPointX(i) + " " + "Y: "+ test_fun.getPointY(i));
        }

        System.out.println(" ");

        System.out.println(test_fun.getFunctionValue(4.5) + " Функция определения значения в произвольной точке 4.5");
        System.out.println(" ");

        System.out.println("Добавили у=20 по индексу 3");
        test_fun.setPointY(3,80);
        for(int i=0;i<test_fun.getPointsCount();i++){
            System.out.println("X: "+ test_fun.getPointX(i) + " " + "Y: "+ test_fun.getPointY(i));
        }

        System.out.println(" ");

        System.out.println(test_fun.getPointsCount() + " Количество точек");
        System.out.println(" ");



        FunctionPoint test_point = new FunctionPoint(8.5,17);
        test_fun.addPoint(test_point);

        System.out.println("Добвавили точку test_point 8.5, 17");
        for(int i=0;i<test_fun.getPointsCount();i++){
            System.out.println("Х: "+ test_fun.getPointX(i) + " " + "Y: "+ test_fun.getPointY(i));
        }

        System.out.println(" ");

        test_fun.deletePoint(9);

        System.out.println("Удалили элемент с индексом 9");
        for(int i=0;i<test_fun.getPointsCount();i++){
            System.out.println("Х: "+ test_fun.getPointX(i) + " " + "Y: "+ test_fun.getPointY(i));
        }

        System.out.println(" ");

        double[] testarr = {4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.0};
        TabulatedFunction test_fun2 = new TabulatedFunction(1,5,testarr);

        System.out.println("Проверка второго конструктора");
        for(int i=0;i<test_fun2.getPointsCount();i++){
            System.out.println("Х: "+ test_fun2.getPointX(i) + " " + "Y: "+ test_fun2.getPointY(i));
        }



    }


}