import functions.*;

public class Main {

    public static void main(String[] args) {
        double[] data = {1, 3, 6, 8, 4, 2};
        FunctionPoint P = new FunctionPoint(3.5, 5);
        final double EPSILON_DOUBLE = 1e-9;

        TabulatedFunction Function = new TabulatedFunction(3, 20, data);

        System.out.println("Input data:");
        for (int i = 0; i < Function.getPointsCount(); i++) {
            System.out.println("X[" + i + "] = " + Function.getPointX(i) + " and Y[" + i + "] = " + Function.getPointY(i));
        }

        System.out.println("\nDeleting point at index 0 ");
        if (Function.getPointsCount() > 0) {
            Function.deletePoint(0);
            System.out.println("Point at index 0 deleted.");
        } 
        else {
            System.out.println("Cannot delete, function has no points.");
        }

        System.out.println("\nAs a result of deleting:");
        for (int i = 0; i < Function.getPointsCount(); i++) {
            System.out.println("X[" + i + "] = " + Function.getPointX(i) + " and Y[" + i + "] = " + Function.getPointY(i));
        }

        System.out.println("\nAdding point P(3.5, 5)");
        Function.addPoint(P);
        System.out.println("Point P(3.5, 5) added.");

        System.out.println("\nAs a result of adding:");
        for (int i = 0; i < Function.getPointsCount(); i++) {
            System.out.println("X[" + i + "] = " + Function.getPointX(i) + " and Y[" + i + "] = " + Function.getPointY(i));
        }

        System.out.println("\nCalculating function value");
        double ValueX = 7;
        double ValueY = Function.getFunctionValue(ValueX);
        System.out.println("Function value at X = " + ValueX + " is Y = " + ValueY);

        //Проверка геттер и сеттер методов
        System.out.println("\n               Checking the get() and set() methods");
        
        int testIndex = 3;
        if (testIndex > Function.getPointsCount() || testIndex < 0){
            System.out.println("Invalid testIndex");
        }
        else{
            double afterSetPointX = Function.getPointX(testIndex);
            Function.setPointX(testIndex, 6.8);
            if (Math.abs(Function.getPointX(testIndex) - 6.8) > EPSILON_DOUBLE){
                System.out.println("\nProblem with get/set method PointX");
            }
            else{
                System.out.println("\nsetPointX has been successfully installed: X = " + Function.getPointX(testIndex));
                Function.setPointX(testIndex, afterSetPointX);
                System.out.println("\nThe original X has been restored: " + afterSetPointX);
            }
       
            double afterSetPointY = Function.getPointY(testIndex);
            Function.setPointY(testIndex, 6.8);
            if (Math.abs(Function.getPointY(testIndex) - 6.8) > EPSILON_DOUBLE){
                System.out.println("Problem with get/set method PointY");
            }
            else{
                System.out.println("\nsetPointY has been successfully installed: Y = " + Function.getPointY(testIndex));
                Function.setPointY(testIndex, afterSetPointY);
                System.out.println("\nThe original Y has been restored: " + afterSetPointY);
            }
            }

            FunctionPoint afterSetPoint = Function.getPoint(testIndex);
            FunctionPoint testPoint = new FunctionPoint(5, 10);
            Function.setPoint(testIndex, testPoint);
            if(Math.abs(testPoint.getX() - Function.getPointX(testIndex)) > EPSILON_DOUBLE && Math.abs(testPoint.getY() - Function.getPointY(testIndex)) > EPSILON_DOUBLE){
                System.out.println("\nProblem with get/set method Point");
            }
            else{
                System.out.println("\nsetPoint has been successfully installed: X = " + Function.getPointX(testIndex) + " Y = " + Function.getPointY(testIndex));
                Function.setPoint(testIndex, afterSetPoint);
                System.out.println("\nThe original value of the point has been restored: X = " + Function.getPointX(testIndex) + " Y = " + Function.getPointY(testIndex));
        }
    }
}