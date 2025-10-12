import functions.*;

public class Main {
    public static void main(String[] args) {

        //y = x^2
        double[] values = {2.25, 1,  0.25, 0, 0.25, 1, 2.25, 4, 6.25};
        TabulatedFunction func = new TabulatedFunction(-1.5, 2.5, values);

        double[] arguments = {-2, -1.5, 0.2, 0.3, 1, 1.2, 2.04};
        for (double arg: arguments) {
            System.out.print(func.getFunctionValue(arg));
            System.out.print(' ');
        }
        System.out.println();

        //добавляем точки {0.3;0.09) и {0.1;0,01}
        FunctionPoint p1 = new FunctionPoint(0.3, 0.09);
        FunctionPoint p2 = new FunctionPoint(0.1, 0.01);
        func.addPoint(p1);
        func.addPoint(p2);
        for (double arg: arguments) {
            System.out.print(func.getFunctionValue(arg));
            System.out.print(' ');
        }
        System.out.println();

        // удаляем добавленные точки, под индексами 4 и 5
        func.deletePoint(4);
        func.deletePoint(4);
        for (double arg: arguments) {
            System.out.print(func.getFunctionValue(arg));
            System.out.print(' ');
        }
        System.out.println();

        // изменим значения точки {0.5;0.25) на {0.3;0.09}
        func.setPointX(4, 0.3);
        func.setPointY(4, 0.09);
        for (double arg: arguments) {
            System.out.print(func.getFunctionValue(arg));
            System.out.print(' ');
        }
    }
}
