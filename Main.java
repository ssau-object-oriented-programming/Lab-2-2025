import functions.*;

public class Main {
    public static void main(String[] args) {

        TabulatedFunction function = new TabulatedFunction(-2.0, 4.5, 5);

        System.out.println("начальные точки:");
        printFunction(function);

        FunctionPoint newPoint = new FunctionPoint(2.0, 5.0);
        function.setPoint(3, newPoint);

        System.out.println("после замены:");
        printFunction(function);

        FunctionPoint add_Point = new FunctionPoint(-3.5, 0.75);
        function.addPoint(add_Point);

        System.out.println("после добавления:");
        printFunction(function);

        function.deletePoint(3);

        System.out.println("после удаления:");
        printFunction(function);
    }

    private static void printFunction(TabulatedFunction q) {
        int count = q.getPointsCount();
        for (int i = 0; i < count; i++) {
            System.out.println("x = " + q.getPointX(i) + " y = " + q.getPointY(i));
        }
    }
}