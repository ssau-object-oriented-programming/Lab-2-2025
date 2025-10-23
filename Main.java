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
        int testIndex = 3;
        if (testIndex > Function.getPointsCount() || testIndex < 0){
            System.out.println("Invalid testIndex");
        }
        else{
            double afterSetPointX = Function.getPointX(testIndex);
            Function.setPointX(testIndex, 6.8);
            if (Math.abs(Function.getPointX(testIndex) - 6.8) > EPSILON_DOUBLE){
                System.out.println("Problem with get/set method PointX");
            }
            else{
                Function.setPointX(testIndex, afterSetPointX);
            }
       
            double afterSetPointY = Function.getPointY(testIndex);
            Function.setPointY(testIndex, 6.8);
            if (Math.abs(Function.getPointY(testIndex) - 6.8) > EPSILON_DOUBLE){
                System.out.println("Problem with get/set method PointY");
            }
            else{
                Function.setPointY(testIndex, afterSetPointY);
            }

            FunctionPoint afterSetPoint = Function.getPoint(testIndex);
            FunctionPoint testPoint = new FunctionPoint(5, 10);
            Function.setPoint(testIndex, testPoint);
            if(Math.abs(testPoint.getX() - Function.getPointX(testIndex)) > EPSILON_DOUBLE && Math.abs(testPoint.getY() - Function.getPointY(testIndex)) > EPSILON_DOUBLE){
                System.out.println("Problem with get/set method Point");
            }
            else{
                Function.setPoint(testIndex, afterSetPoint);
            }
        }
    }
}