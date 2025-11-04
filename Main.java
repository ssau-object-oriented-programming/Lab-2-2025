import functions.*;

public class Main {
        public static void main(String[] arg){
            TabulatedFunction func = new TabulatedFunction(-3.5, 5, 5);
            for (int i=0; i< func.getPointsCount(); i++)
                func.setPointY(i, func.getPointX(i)*2 +2);

            System.out.println("\nФункция: y = 2x + 2 на отрезке [-3.5, 5] из "+func.getPointsCount()+" точек.");
            func.printFunc();

            System.out.println("\nУдаление существующей точки");
            func.deletePoint(4);
            func.printFunc();

            System.out.println("Удаление несуществующей точки");
            func.deletePoint(11);
            func.printFunc();

            System.out.println("\nДобавление точки");
            func.addPoint(new FunctionPoint(1.5, 5));
            func.printFunc();

            System.out.println("\nУстановка некорректной точки для интерполяции");
            func.setPoint(1, new FunctionPoint(-0.5, 3));
            func.printFunc();

            System.out.println("Установка некорректной точки для интерполяции");
            func.setPoint(3, new FunctionPoint(-2.7, -1));
            func.printFunc();

            System.out.println("\nУстановка корректной точки для интерполяции");
            func.setPoint(1, new FunctionPoint(-3, 3));
            func.printFunc();

            System.out.println("Установка корректной точки для интерполяции");
            func.setPoint(3, new FunctionPoint(1, 3));
            func.printFunc();

            System.out.println("\ngetFunctionValue");
            for(double i = -5; i < 1; i ++) {
                System.out.printf("(%.2f, %.2f) ", i, func.getFunctionValue(i));
                System.out.println();
            }

        }
}
