import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
	public static void main(String[] args) {
		double leftX = -3;
		double rightX = 4;
		int pointsCount = 15;
		double[] values = new double[pointsCount];

		for (double i = leftX, j = 0; i <= rightX; i += (rightX - leftX) / (pointsCount - 1), j++) {
			values[(int)j] = Math.pow(i,2) + 2; // y = x**2 + 2
		}

		TabulatedFunction tabFunct = new TabulatedFunction (leftX, rightX, values);	

		// Выводим в консоль значения функции на ряде точек
		System.out.println("Значения функции на ряде точек");
		functionValues(tabFunct);

		// Изменяем точки
		System.out.println("Изменяем точки");
		FunctionPoint point = new FunctionPoint(-10, 0);
		tabFunct.setPoint(0, point);		
		tabFunct.setPoint(3, point);		

		tabFunct.setPointX(2, -2.3);
		tabFunct.setPointY(2, 6.333);

		tabFunct.setPointX(4, 100);
		tabFunct.setPointY(4, 123);
		
		functionValues(tabFunct);				

		// Удаляем точки
		System.out.println("Удаляем точку");	
		tabFunct.deletePoint(0);		

		functionValues(tabFunct);

		// Добавляем точки
		System.out.println("Добавляем точки");	
		point = new FunctionPoint(3.12, 12.1);
		tabFunct.addPoint(point);

		point = new FunctionPoint(6, 38); // создаст новый массив, тк превысит размер изначального
		tabFunct.addPoint(point);

		functionValues(tabFunct);

		// Вернем значение функции в точке x
		System.out.println("Вернем значение функции в точке x");
		System.out.println(tabFunct.getFunctionValue(-2.5));
		System.out.println(tabFunct.getFunctionValue(2.5));
		System.out.println(tabFunct.getFunctionValue(5.0));
		System.out.println(tabFunct.getFunctionValue(7.0));
	}

	private static void functionValues(TabulatedFunction _tabFunct) {
		System.out.println("x: \t y:");
		for (int i = 0; i < _tabFunct.getPointsCount(); i++) {
			System.out.print(_tabFunct.getPointX(i) + "\t" + _tabFunct.getPointY(i) + "\n");
		}
	}

}






















