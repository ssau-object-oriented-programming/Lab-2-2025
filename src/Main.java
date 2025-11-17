import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Prepare tabulated sine with uniformly spaced points.
        
        TabulatedFunction sinFunction = new TabulatedFunction(0.0, Math.PI, 6);
        fillWithSineValues(sinFunction);

        printTabulatedPoints("Initial sine approximation", sinFunction);

        // Modify one of the base points.
        double newY = sinFunction.getPointY(2) + 0.3;
        sinFunction.setPoint(2, new FunctionPoint(sinFunction.getPointX(2), newY));

        // Insert an additional point inside the domain.
        sinFunction.addPoint(new FunctionPoint(Math.PI * 0.75, Math.sin(Math.PI * 0.75)));

        // Remove the first tabulated point to show how deletion works.
        sinFunction.deletePoint(0);

        printTabulatedPoints("After editing points", sinFunction);
    }

    private static void fillWithSineValues(TabulatedFunction function) {
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            function.setPointY(i, Math.sin(x));
        }
    }

    private static void printTabulatedPoints(String title, TabulatedFunction function) {
        System.out.println(title);
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            double y = function.getPointY(i);
            System.out.printf("Point %d: x=%.4f, y=%.6f%n", i, x, y);
        }
        System.out.println();
    }
}
