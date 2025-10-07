import functions.*;

class Main {
    public static void main(String[] args) {
        // Функция y = x^2 - 1 на [-5; 5]
        double[] values = {24, 15, 8, 3, 0, -1, 0, 3, 8, 15, 24};
        TabulatedFunction func = new TabulatedFunction(-5, 5, values);

        System.out.printf("%-21s %s%n", "Функция:", "y = x^2 - 1");
        System.out.printf("%-21s [%.2f; %.2f]%n%n", "Область определения:", func.getLeftDomainBorder(), func.getRightDomainBorder());

        System.out.println("До модификаций:");
        // Точки вне области определения
        System.out.printf("f(-6) = %.2f%n", func.getFunctionValue(-6));
        System.out.printf("f(10) = %.2f%n", func.getFunctionValue(10));

        // Точки внутри
        System.out.printf("f(0.1) = %.2f%n", func.getFunctionValue(0.1));
        System.out.printf("f(0.2) = %.2f%n", func.getFunctionValue(0.2));
        System.out.printf("f(0.3) = %.2f%n", func.getFunctionValue(0.3));
        System.out.printf("f(-2.5) = %.2f%n", func.getFunctionValue(-2.5));
        System.out.printf("f(0.3) = %.2f%n", func.getFunctionValue(0.3));
        System.out.printf("f(3.7) = %.2f%n", func.getFunctionValue(3.7));

        // Модификации
        func.deletePoint(3);
        func.addPoint(new FunctionPoint(-2, 10));
        func.setPointY(5, 100);

        System.out.println("\nПосле модификаций:");
        System.out.printf("f(-2.5) = %.2f%n", func.getFunctionValue(-2.5));
        System.out.printf("f(0.3) = %.2f%n", func.getFunctionValue(0.3));
        System.out.printf("f(3.7) = %.2f%n", func.getFunctionValue(3.7));
    }
}