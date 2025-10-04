import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Демонстрация функции sin(x)
        System.out.println("=== Функция sin(x) ===");
        TabulatedFunction sinF = new TabulatedFunction(0, 2 * Math.PI, 7);
        for (int i = 0; i < sinF.getPointsCount(); i++){
            double x = sinF.getPointX(i);
            sinF.setPointY(i, Math.sin(x));
        }

        System.out.println("Исходная функция:");
        for (int i = 0; i < sinF.getPointsCount(); i++) {
            System.out.printf("Точка %d: x=%.2f, y=%.2f%n",
                    i, sinF.getPointX(i), sinF.getPointY(i));
        }

        // Демонстрация добавления точки
        System.out.println("\n=== Добавление точки (1.5, 0.9) ===");
        sinF.addPoint(new FunctionPoint(1.5, 0.9));
        System.out.println("После добавления:");
        for (int i = 0; i < sinF.getPointsCount(); i++) {
            System.out.printf("Точка %d: x=%.2f, y=%.2f%n",
                    i, sinF.getPointX(i), sinF.getPointY(i));
        }

        // Демонстрация удаления точки
        System.out.println("\n=== Удаление точки с индексом 2 ===");
        sinF.deletePoint(2);
        System.out.println("После удаления:");
        for (int i = 0; i < sinF.getPointsCount(); i++) {
            System.out.printf("Точка %d: x=%.2f, y=%.2f%n",
                    i, sinF.getPointX(i), sinF.getPointY(i));
        }

        // Демонстрация изменения точки
        System.out.println("\n=== Изменение точки с индексом 1 ===");
        sinF.setPoint(1, new FunctionPoint(1.0, 0.5));
        System.out.println("После изменения:");
        for (int i = 0; i < sinF.getPointsCount(); i++) {
            System.out.printf("Точка %d: x=%.2f, y=%.2f%n",
                    i, sinF.getPointX(i), sinF.getPointY(i));
        }

        // Демонстрация интерполяции
        System.out.println("\n=== Интерполяция значений ===");
        System.out.printf("sin(%.2f) ≈ %.4f%n", 1.2, sinF.getFunctionValue(1.2));
        System.out.printf("sin(%.2f) ≈ %.4f%n", 2.8, sinF.getFunctionValue(2.8));
        System.out.printf("sin(%.2f) ≈ %.4f%n", 4.1, sinF.getFunctionValue(4.1));

        // Демонстрация границ области определения
        System.out.println("\n=== Границы области определения ===");
        System.out.printf("Левая граница: %.2f%n", sinF.getLeftDomainBorder());
        System.out.printf("Правая граница: %.2f%n", sinF.getRightDomainBorder());

        // Демонстрация работы с отдельными координатами
        System.out.println("\n=== Изменение координат по отдельности ===");
        System.out.println("До изменения:");
        System.out.printf("Точка 0: x=%.2f, y=%.2f%n", sinF.getPointX(0), sinF.getPointY(0));

        sinF.setPointX(0, 0.5);
        sinF.setPointY(0, 0.3);

        System.out.println("После изменения:");
        System.out.printf("Точка 0: x=%.2f, y=%.2f%n", sinF.getPointX(0), sinF.getPointY(0));

        // Итоговое состояние функции
        System.out.println("\n=== Итоговое состояние функции ===");
        for (int i = 0; i < sinF.getPointsCount(); i++) {
            System.out.printf("Точка %d: x=%.2f, y=%.2f%n",
                    i, sinF.getPointX(i), sinF.getPointY(i));
        }
        System.out.println("Всего точек: " + sinF.getPointsCount());
    }
}



