import functions.*;  //импортируем все классы из пакета functions
public class Main {    
    //метод для вывода всех точек функции
    private static void printPoints(TabulatedFunction func) {
        System.out.print("Точки функции: [");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.print("(" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
            if (i < func.getPointsCount() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        //создаем массив значений y для ф-ии y=x
        double[] values = {0, 1, 2, 3, 4};  //y=x для x=0,1,2,3,4
        
        //создаем табулированную ф-ию на отрезке[0,4]
        TabulatedFunction func = new TabulatedFunction(0, 4, values);
        
        //выводим информацию о ф-ии
        System.out.println("Исходная функция");
        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("Кол-во точек: " + func.getPointsCount());
        printPoints(func);
        
        //проверяем значения в разных точках
        System.out.println(" Проверка значений функции");
        double[] testPoints = {-1, 0.5, 1.7, 2.2, 3.4, 5.5};
        for (double x : testPoints) {
            double result = func.getFunctionValue(x);
            System.out.println("f(" + x + ") = " + result);
        }
        //проверяем операции с точками(добавление, удаление, изменение)
        System.out.println("\n Проверка операций с точками");
        
        //добавляем новую точку
        System.out.println("1. Добавляем точку (2.2, 2.2):");
        func.addPoint(new FunctionPoint(2.2, 2.2));
        printPoints(func);
        System.out.println("f(2.2) = " + func.getFunctionValue(2.2));
        
        //удаляем точку с индексом 1
        System.out.println("\n2. Удаляем точку с индексом 1:");
        func.deletePoint(1);
        printPoints(func);
        
        //изменяем y у точки с индексом 2
        System.out.println("\n3. Изменяем y точки с индексом 2 на 2.5:");
        func.setPointY(2, 2.5);
        printPoints(func);
        
        //проверяем результат после всех изменений
        System.out.println("\n Итоговый результат");
        System.out.println("f(2.2) = " + func.getFunctionValue(2.2));
        printPoints(func);
    }
}
