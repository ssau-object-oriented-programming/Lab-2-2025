import functions.*;

public class Main {
    public static void main(String[] args) {

        // Тест 1: Создание функции и вывод значений
        System.out.println("1. Создание функции y = 2x+1 на интервале [0, 10] с 6 точками");
        TabulatedFunction func = new TabulatedFunction(0, 10, 6);
        // Устанавливаем значения функции y = 2x+1
        for (int i = 0; i < func.getPointsCount(); i++) {
            double x = func.getPointX(i);
            func.setPointY(i, 2*x+1);
        }
        // Выводим значения функции в различных точках
        System.out.println("\n Вычисление значений функции в разных точках:");
        double[] testPoints = {-1, 0, 2, 2,4 , 6, 8,10 };
        for (int i=0;i<testPoints.length;i++) {
            if ((i==testPoints.length-1)||(testPoints[i]<testPoints[i+1] && i<(testPoints.length-1))){
            	double y = func.getFunctionValue(testPoints[i]);
            	System.out.print("   f( "+testPoints[i]+") =");
            	if (Double.isNaN(y)) {
                	System.out.println("не определена (вне области определения)");
            	} else {
                	System.out.println(y);
            	}
	    }
	    else{
	    	System.out.println("Такое значение X уже было ");
	    }
        }

        System.out.println("\n Границы области определения:");
        System.out.println("   Левая граница: " + func.getLeftDomainBorder());
        System.out.println("   Правая граница: " + func.getRightDomainBorder());
        System.out.println("\n Исходные точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint point = func.getPoint(i);
            System.out.println("   Точка "+ i+":("+point.getX()+" "+point.getY()+")");
        }
	System.out.println("\n2. Изменение точек функции:");
        // Изменяем первую точку (индекс 0)
        System.out.println("   Изменение первой точки (индекс 0) на (0,100):");
        func.setPoint(0, new FunctionPoint(0, 100)); // Меняем первую точку на (0, 100)
	for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint point = func.getPoint(i);
            System.out.println("   Точка "+ i+":("+point.getX()+" "+point.getY()+")");
        }
        // Добавление точки
        System.out.println("\n3. Добавление новой точки (6.6, 9.9):");
        System.out.println("   Количество точек до добавления: " + func.getPointsCount());
        func.addPoint(new FunctionPoint(6.6, 9.9));
        System.out.println("   Количество точек после добавления: " + func.getPointsCount());
	System.out.println("\n Новые точки функции после добавления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint point = func.getPoint(i);
            System.out.println("   Точка "+ i+":("+point.getX()+" "+point.getY()+")");
        }
	// Удаляем точку из середины (индекс 3)
        System.out.println("\n4.   Удаляем точку с индексом 3: " );
        func.deletePoint(3);
	for (int i = 0; i < func.getPointsCount(); i++) {
            FunctionPoint point = func.getPoint(i);
            System.out.println("   Точка "+ i+":("+point.getX()+" "+point.getY()+")");
        }


    }
}
