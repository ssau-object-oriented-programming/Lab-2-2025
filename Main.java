import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {

    public static void main(String[] args)
    {
        double [] data = {1, 3, 6, 8, 4};
        FunctionPoint Point = new FunctionPoint(7, 5);
        TabulatedFunction Function = new TabulatedFunction(0.0,10.0, data);
        System.out.println("Input data:");
        for (int i = 0; i < Function.getPointsCount(); i++){
            System.out.println("X["+ i + "] = " + Function.getPointX(i) + " and Y["+ i + "] = " + Function.getPointY(i));
        }
        Function.deletePoint(0);
        System.out.println("\n");
        System.out.println("As a result of deleting:");
        for (int i = 0; i < Function.getPointsCount(); i++){
            System.out.println("X["+ i + "] = " + Function.getPointX(i) + " and Y["+ i + "] = " + Function.getPointY(i));
        }
        Function.addPoint(Point);
        System.out.println("\n");
        System.out.println("As a result of adding:");
        for (int i = 0; i < Function.getPointsCount(); i++){
            System.out.println("X["+ i + "] = " + Function.getPointX(i) + " and Y["+ i + "] = " + Function.getPointY(i));
        }
        System.out.println("\n");
        System.out.println("Function value in the given X:6  Y = " + Function.getFunctionValue(6));
    }

}