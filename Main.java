import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {

    public static void main(String[] args)
    {
        double [] data = {2, 3, 6, 8, 4};
        FunctionPoint Point = new FunctionPoint(0.1, 5);
        TabulatedFunction Function = new TabulatedFunction(0.0,1.0, data);
        System.out.println("Input data:");
        for (int i = 0; i < Function.getPointsCount(); i++){
            System.out.println("X["+ i + "] = " + Function.getPointX(i) + " and Y["+ i + "] = " + Function.getPointY(i));
        }
        Function.deletePoint(4);
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
        System.out.println("Function value in the given X:0.3  Y = " + Function.getFunctionValue(0.3));
       
        System.out.println("\n");
        System.out.println("As a result of Setting and getting:");
        for(int i=0; i<Function.getPointsCount();i++)
        {
            Function.setPointX(i,i);
            Function.setPointY(i,i);
        }
        
        for(int i=0; i<Function.getPointsCount();i++)
        {
            System.out.println("X = " + Function.getPointX(i) + " and Y = " + Function.getPointY(i));

        }
        
        

    }

}