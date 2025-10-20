import functions.*;

public class Main {
    public static void main(String[] args) {
        double value[] = {1,3,5,2,6,7,26,8};
        TabulatedFunction tab = new TabulatedFunction(2, 9, value);

        System.out.println("ДО ИЗМЕНЕНИЙ");
        System.out.println("f(3.5) = " + tab.getFunctionValue(3.5));
        System.out.println("f(1) = " + tab.getFunctionValue(1));
        System.out.println("f(10) = " + tab.getFunctionValue(10));

        tab.deletePoint(1);
        tab.addPoint(new FunctionPoint(0, -1));

        System.out.println("\nПОСЛЕ ИЗМЕНЕНИЙ");
        System.out.println("f(1) = " + tab.getFunctionValue(1));
        System.out.println("f(3) = " + tab.getFunctionValue(3));
        System.out.println("f(-1) = " + tab.getFunctionValue(-1));

        System.out.println("\nТочки:");
        tab.printTabFun();
    }
}















