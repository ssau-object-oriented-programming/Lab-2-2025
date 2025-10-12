import functions.*;

public class Main {
    public static void main(String[] args) {
        double[] values = {1, 6, 11, 16, 21, 26, 31, 36, 41, 46};
        TabulatedFunction f = new TabulatedFunction(5, 50, values);
        System.out.printf("левая граница области определения f: %.2f\n", f.getLeftDomainBorder());
        System.out.printf("правая граница области определения f: %.2f\n", f.getRightDomainBorder());
        System.out.println("Значения функции:");
         for (double i = 3; i < 63; i += 10) {
            System.out.printf("f(%.2f) = %.2f\n", i, f.getFunctionValue(i));
        }
         System.out.println();
        System.out.printf("Кол-во точек: %d\n", f.getPointsCount());
        System.out.println("Точки:");
        f.outClass();
        System.out.println();
        System.out.println("Точки после изменения:");
        f.setPoint(3, new FunctionPoint(50, 50));
        f.setPoint(5, new FunctionPoint(28, 50));
        f.outClass();
        System.out.println();
        System.out.println("Точки после изменения x:");
        f.setPointX(6, 100);
        f.setPointX(2, 18);
        f.outClass();
        System.out.println();
        System.out.println("Точки после изменения y:");
        f.setPointY(6, 100);
        f.outClass();
        System.out.println();
        System.out.printf("x элемента c индексом %d: %.2f\n", 3, f.getPointX(3));
        System.out.printf("y элемента c индексом %d: %.2f\n", 4, f.getPointY(4));
        System.out.println();
        System.out.println("Точки после добавления и удаления точек:");
        f.addPoint(new FunctionPoint(1,1));
        f.deletePoint(2);
        f.outClass();
    }
}