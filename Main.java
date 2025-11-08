import functions.*;  //импортируем все классы из пакета functions
public class Main {
    public static void main(String[] args) {
        //создаем массив значений y для ф-ии y=x
        double[] values = {0, 1, 2, 3, 4};  //y=x для x=0,1,2,3,4
        
        //создаем табулированную ф-ию на отрезке[0,4]
        TabulatedFunction func = new TabulatedFunction(0, 4, values);
        
        //выводим информацию о ф-ии
        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("Количество точек: " + func.getPointsCount());
        
        //проверяем значения в разных точках
        double[] testPoints = {-1, 0.5, 1.7, 2.2, 3.4, 5.5};
        for (double x : testPoints) {
            double result = func.getFunctionValue(x);
            System.out.println("f(" + x + ") = " + result);
        }
        
        //проверяем операции с точками(добавление, удаление, изменение)
        System.out.println("\nПроверка операций с точками:");
        
        //добавляем новую точку
        func.addPoint(new FunctionPoint(2.2, 2.2));
        System.out.println("После добавления точки (2.2, 2.2):");
        System.out.println("f(2.2) = " + func.getFunctionValue(2.2));
        
        //удаляем точку с индексом 1
        func.deletePoint(1);
        System.out.println("После удаления точки с индексом 1:");
        
        //изменяем y у точки с индексом 2
        func.setPointY(2, 2.5);
        System.out.println("После изменения y точки с индексом 2 на 2.5:");
        
        //проверяем результат после всех изменений
        System.out.println("f(2.2) = " + func.getFunctionValue(2.2));
    }
}