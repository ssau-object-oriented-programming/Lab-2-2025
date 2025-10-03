import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static final double deltaX = 0.1;
    public static final double initialX = -1;
    public static final double endX = 3;
    public static void main(String[] args) {
        double[] arr = {1,4,9,16,25,36,49,64,81,100,121};
        TabulatedFunction tf = new TabulatedFunction(1, 11, arr);

        System.out.println("Тестируем getFunctionValue...");
        display(tf);

        System.out.println();
        System.out.println("Смотрим, что при замене будет...");
        tf.setPoint(0, new FunctionPoint(-1,1));
        display(tf);

        System.out.println();
        System.out.println("Возвращаем точку обратно...");
        tf.setPoint(0, new FunctionPoint(1,1));
        display(tf);

        System.out.println();
        System.out.println("Изменим одну точку...");
        tf.setPointX(0,-5); // <----
        display(tf);

        System.out.println();
        System.out.println("Вернем обратно...");
        tf.setPointX(0,1);
        display(tf);

        System.out.println();
        System.out.println("Удалим точку...");
        tf.deletePoint(0);
        display(tf);

        System.out.println();
        System.out.println("Вернем обратно точку...");
        tf.addPoint(new FunctionPoint(1,1));
        display(tf);
    }
    public static void display(TabulatedFunction tf) {
        double currentX = initialX;
        for (; currentX <= endX; currentX += deltaX) {
            System.out.println(currentX + " -> " + tf.getFunctionValue(currentX));
        }
    }
}
