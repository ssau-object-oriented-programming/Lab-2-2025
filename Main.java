import functions.*;

public class Main {

    public static void main(String[] args) {
        double[] data = {1, 3, 6, 8, 4, 2};
        FunctionPoint P = new FunctionPoint(3.5, 5);

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
    }
}