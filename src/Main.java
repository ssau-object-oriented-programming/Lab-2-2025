import functions.*;

public class Main {
    public static void main(String[] args) {
        double value[] = {1,3,5,2,6,7,26,8};
        TabulatedFunction tab = new TabulatedFunction(2, 9, value);

        System.out.println("ТОЧКИ ДО ИЗМЕНЕНИЙ");
        tab.printTabFun();
        System.out.println();

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
        System.out.println("\nПРОВЕРКА СЕТТЕРОВ");

        double testVals[] = {0, 4, 8};
        TabulatedFunction testFunc = new TabulatedFunction(0, 4, testVals);

        System.out.println("Исходные точки:");
        testFunc.printTabFun();

// 1. Корректное изменение Y (всегда разрешено)
        testFunc.setPointY(1, 10);
        System.out.println("\nПосле setPointY(1, 10):");
        testFunc.printTabFun(); // y[1] должно стать 10

// 2. Корректное изменение X (вписывается в интервал)
        testFunc.setPointX(1, 1.5); // между 0 и 4 → допустимо
        System.out.println("\nПосле setPointX(1, 1.5):");
        testFunc.printTabFun(); // x[1] = 1.5

// 3. Некорректное изменение X (нарушает порядок)
        testFunc.setPointX(1, 5); // 5 > 4 (следующая точка) → должно игнорироваться
        System.out.println("\nПосле попытки setPointX(1, 5) (некорректно):");
        testFunc.printTabFun(); // x[1] останется 1.5

// 4. Попытка заменить первую точку на x=3 (должно игнорироваться)
        testFunc.setPoint(0, new FunctionPoint(3, -1));
        System.out.println("\nПосле попытки setPoint(0, (3,-1)) (некорректно):");
        testFunc.printTabFun(); // первая точка останется (0,0)

    }
}