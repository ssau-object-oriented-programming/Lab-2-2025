import functions.*;
public class Main {
    public static void main(String[] args){
        TabulatedFunction func = new TabulatedFunction(0, 4, 3);
        func.setPointY(0, 0);   // (0,0)
        func.setPointY(1, 4);   // (2,4)
        func.setPointY(2, 16);  // (4,16)
        //показываем все точки
        for (int i = 0; i<func.getPointsCount(); i++) {
            System.out.println("  (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        //проверяем значения в разных местах
        System.out.println("f(1) = " + func.getFunctionValue(1));   // между 0 и 2
        System.out.println("f(3) = " + func.getFunctionValue(3));   // между 2 и 4
        System.out.println("f(5) = " + func.getFunctionValue(5));   // справа от 4
        System.out.println("f(-1) = " + func.getFunctionValue(-1)); // слева от 0

        //добавляем новую точку
        func.addPoint(new FunctionPoint(1, 1));
        System.out.println("actual amount of dots: " + func.getPointsCount());

        //снова показываем все точки
        for (int i = 0; i<func.getPointsCount(); i++) {
            System.out.println("  (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        //проверяем значение после добавления точки
        System.out.println("\nf(1) after adding new point = " + func.getFunctionValue(1));

        //удаляем точку
        func.deletePoint(1);
        System.out.println("remaining dots: " + func.getPointsCount());

        //финальные точки
        for (int i = 0; i<func.getPointsCount(); i++) {
            System.out.println("  (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
        //вроде все проверила
    }
}
