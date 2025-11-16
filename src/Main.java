import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Prepare tabulated sine with uniformly spaced points.
        
        TabulatedFunction sinFunction = new TabulatedFunction(0.0, Math.PI, 6);
        fillWithSineValues(sinFunction);

        double[] firstProbePoints = {-0.5, 0.0, Math.PI / 6, Math.PI / 3, Math.PI / 2, Math.PI, Math.PI + 0.5};
        printValues("Initial sine approximation", sinFunction, firstProbePoints);

        // Modify one of the base points.
        double newY = sinFunction.getPointY(2) + 0.3;
        sinFunction.setPoint(2, new FunctionPoint(sinFunction.getPointX(2), newY));

        // Insert an additional point inside the domain.
        sinFunction.addPoint(new FunctionPoint(Math.PI * 0.75, Math.sin(Math.PI * 0.75)));

        // Remove the first tabulated point to show how deletion works.
        sinFunction.deletePoint(0);

        double[] secondProbePoints = {0.2, Math.PI / 3, Math.PI / 2, Math.PI * 0.75, Math.PI + 0.25};
        printValues("After editing points", sinFunction, secondProbePoints);
    }

    private static void fillWithSineValues(TabulatedFunction function) {
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            function.setPointY(i, Math.sin(x));
        }
    }

    private static void printValues(String title, TabulatedFunction function, double[] arguments) {
        System.out.println(title);
        for (int i = 0; i < arguments.length; i++) {
            double x = arguments[i];
            double value = function.getFunctionValue(x);
            System.out.printf("f(%.4f) = %.6f%n", x, value);
        }
        System.out.println();
    }
}
