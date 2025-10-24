import functions.*;

public class Main {
    public static void main(String[] args) {
        // Создаем массив значений для функции квадратного корня y=√x
        double[] sqrtValues = new double[11];
        float x;
        for (int i = 0; i < 11; i++) {
            x = (float)i / 10;
            sqrtValues[i] = Math.sqrt(x);
        }

        TabulatedFunction sqrtFunction = new TabulatedFunction(0.0, 1.0, sqrtValues);
    
        
        // Выводим информацию о функции
        System.out.println("Левая граница области определения: " + sqrtFunction.getLeftDomainBorder());
        System.out.println("Правая граница области определения: " + sqrtFunction.getRightDomainBorder());
        System.out.println("Количество точек: " + sqrtFunction.getPointsCount());
        
        // Выводим все точки функции
        System.out.println("\nВсе точки функции:");
        for (int i = 0; i < sqrtFunction.getPointsCount(); i++) {
            System.out.println("Точка " + i + ": x=" + sqrtFunction.getPointX(i) + 
                             ", y=" + sqrtFunction.getPointY(i));
        }
        
        // Различные точки
        System.out.println("\n   ВЫЧИСЛЕНИЕ ЗНАЧЕНИЙ ФУНКЦИИ   ");
        double[] testPoints = {-0.5, 0.0, 0.05, 0.15, 0.25, 0.35, 0.55, 0.75, 0.95, 1.0, 1.5};
        
        for (double point : testPoints) {
            double value = sqrtFunction.getFunctionValue(point);
            System.out.println("f(" + point + ") = " + value);
        }
        
        // Изменяем точки
        System.out.println("\n   ИЗМЕНЯЕМ ТОЧКИ   ");
        System.out.println("До изменения - Точка 5: x=" + sqrtFunction.getPointX(5) + 
                         ", y=" + sqrtFunction.getPointY(5));
        sqrtFunction.setPointY(5, 0.8);
        System.out.println("После изменения Y - Точка 5: x=" + sqrtFunction.getPointX(5) + 
                         ", y=" + sqrtFunction.getPointY(5));
        
        // Попробуем изменить X (сработает если не нарушается порядок)
        double oldX = sqrtFunction.getPointX(3);
        sqrtFunction.setPointX(3, 0.32);
        System.out.println("После попытки изменения X точки 3: " + sqrtFunction.getPointX(3));
        
        // Добавляем точку
        System.out.println("\n ДОБАВЛЯЕМ ТОЧКИ ");
        System.out.println("Количество точек до добавления: " + sqrtFunction.getPointsCount());
        FunctionPoint newPoint = new FunctionPoint(0.45, Math.sqrt(0.45));
        sqrtFunction.addPoint(newPoint);
        System.out.println("Количество точек после добавления: " + sqrtFunction.getPointsCount());
        
        // Найдем добавленную точку
        for (int i = 0; i < sqrtFunction.getPointsCount(); i++) {
            if (Math.abs(sqrtFunction.getPointX(i) - 0.45) < 0.001) {
                System.out.println("Координаты добавленной точки: x=" + sqrtFunction.getPointX(i) + 
                                 ", y=" + sqrtFunction.getPointY(i));
            }
        }
        
        // Тестируем удаление точки
        System.out.println("\n   УДАЛЯЕМ ТОЧКУ   ");
        System.out.println("Количество точек до удаления: " + sqrtFunction.getPointsCount());
        sqrtFunction.deletePoint(2);
        System.out.println("Количество точек после удаления: " + sqrtFunction.getPointsCount());
      
        
        // Проверяем граничные случаи
        System.out.println("\n. ГРАНИЦЫ  ");
        System.out.println("f(-0.1) = " + sqrtFunction.getFunctionValue(-0.1));
        System.out.println("f(1.1) = " + sqrtFunction.getFunctionValue(1.1));
        
        // Проверяем копирование точки
        System.out.println("\n ПРОВЕРКА КОПИРОВАНИЯ ТОЧКИ   ");
        FunctionPoint original = new FunctionPoint(0.5, 0.707);
        FunctionPoint copy = new FunctionPoint(original);
        System.out.println("Оригинал: x=" + original.getX() + ", y=" + original.getY());
        System.out.println("Копия: x=" + copy.getX() + ", y=" + copy.getY());
    }
}