import functions.TabulatedFunction;
import functions.FunctionPoint;

public class Main {
    public static void main(String[] args) {
        // y = x^3, x = [0, 5]
        double[] values = {0, 1, 8, 27, 64, 125};
        TabulatedFunction func = new TabulatedFunction(0, 5, values);
        
        System.out.println("Function^ y = x^3, x = [0, 5]");
        printFunctionInfo(func);
        
        System.out.println("\ncalculating values");
        double[] testPoints = {-1, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 5};
        for (double x : testPoints) {
            double result = func.getFunctionValue(x);
            if (Double.isNaN(result)) {
                System.out.printf("f(%.1f) = not defined%n", x);
            } else {
                System.out.printf("f(%.1f) = %.2f%n", x, result);
            }
        }
        
        System.out.println("\nadding points");
        func.addPoint(new FunctionPoint(0.5, 0.125));
        func.addPoint(new FunctionPoint(4, 64));
        func.addPoint(new FunctionPoint(2.5, 15.625));
        System.out.println("result:");
        printFunctionInfo(func);
        
        System.out.println("\ncheck");
        for (double x : new double[]{0.5, 2.5, 4}) {
            System.out.printf("f(%.1f) = %.3f%n", x, func.getFunctionValue(x));
        }
        
        System.out.println("\npoint changing");
        func.setPointY(2, 5.0);
        System.out.println("result:");
        printFunctionInfo(func);
        System.out.printf("f(%.1f) = %.3f%n", func.getPointX(2), func.getFunctionValue(func.getPointX(2)));
        
        System.out.println("\npoint deleting");
        func.deletePoint(3);
        System.out.println("result:");
        printFunctionInfo(func);
        
    }
    
    private static void printFunctionInfo(TabulatedFunction func) {
        System.out.println("Область определения: [" + func.getLeftDomainBorder() + ", " + func.getRightDomainBorder() + "]");
        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.print("Точки: ");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.print("(" + func.getPointX(i) + "; " + func.getPointY(i) + ")");
            if (i < func.getPointsCount() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
