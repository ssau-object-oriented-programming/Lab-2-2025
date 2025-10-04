import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        TabulatedFunction sinF = new TabulatedFunction(0, 2 * Math.PI, 7);
        for (int i = 0; i < sinF.getPointsCount(); i++){
            double x = sinF.getPointX(i);
            sinF.setPointY(i, Math.sin(x));
        }

        System.out.println("Функция sin(x):");
        for (int i = 0; i < sinF.getPointsCount(); i++) {
            System.out.printf("x=%.2f, sin(x)=%.2f%n",
                    sinF.getPointX(i), sinF.getPointY(i));
        }

    }
}

