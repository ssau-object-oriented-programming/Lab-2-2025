package functions;

 //Класс хранит точки в упорядоченном массиве
    public class TabulatedFunction {
    private FunctionPoint[] points;     // Массив для хранения точек
    private int pointsCount;           // Фактическое количество точек
    private static final double EPSILON = 1e-10; // Машинный эпсилон для сравнений

    // КОНСТРУКТОРЫ:
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        // Проверка параметров
        if (pointsCount < 2) {
            pointsCount = 2;  // Минимум 2 точки для функции
        }

        // Если границы перепутаны - меняем местами
        if (leftX > rightX) {
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }

        this.pointsCount = pointsCount;
        // Создаем массив с запасом места для будущих добавлений
        this.points = new FunctionPoint[pointsCount + 2];

        // Вычисляем шаг между точками
        double step = (rightX - leftX) / (pointsCount - 1);
        
        // Создаем точки с равными интервалами
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;  // Вычисляем x координату
            points[i] = new FunctionPoint(x, 0.0);  // y = 0 по умолчанию
        }
    }

    //Вместо количества точек получает значения функции в виде массива
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount + 2];

        // Проверка на перепутанные границы
        if (leftX > rightX) {
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }

        // Проверка на совпадающие границы
        if (Math.abs(rightX - leftX) < EPSILON) {
            // Если границы совпадают - создаем точки с одинаковым X
            for (int i = 0; i < pointsCount; i++) {
                points[i] = new FunctionPoint(leftX, values[i]);
            }
        } else {
            // Если границы разные - стандартная логика с шагом
            double step = (rightX - leftX) / (pointsCount - 1);
            for (int i = 0; i < pointsCount; i++) {
                double x = leftX + i * step;
                points[i] = new FunctionPoint(x, values[i]);
            }
        }
    }
    // МЕТОДЫ ДЛЯ РАБОТЫ С ФУНКЦИЕЙ
    public double getLeftDomainBorder() {
        return points[0].getX();  // Первая точка - самая левая
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();  // Последняя точка - самая правая
    }

    public double getFunctionValue(double x) {
        // Проверка что x в области определения
        if (x < getLeftDomainBorder() - EPSILON || x > getRightDomainBorder() + EPSILON) {
            return Double.NaN;  // Не число -точка вне области определения
        }

        // Ищем интервал, в который попадает x
        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();
            
            if (x >= x1 - EPSILON && x <= x2 + EPSILON) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                
                //уравнение прямой, проходящей через две точки
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        
        return Double.NaN;
    }

    // МЕТОДЫ ДЛЯ РАБОТЫ С ТОЧКАМИ:
    
    // Количество точек в функции
    public int getPointsCount() {
        return pointsCount;
    }

    //Возвращает копию точки (для инкапсуляции)
    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= pointsCount) {
            return null;
        }
        return new FunctionPoint(points[index]);
    }

    // Заменяет точку на новую
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) {
            return;
        }

        // Проверяем,что новая x координата находится между соседями
        if (index > 0 && point.getX() < points[index - 1].getX() + EPSILON) {
            return; 
        }
        if (index < pointsCount - 1 && point.getX() > points[index + 1].getX() - EPSILON) {
            return;
        }

        // Заменяем координаты точки (создаем копию)
        points[index].setX(point.getX());
        points[index].setY(point.getY());
    }

    // Возвращает координату x точки по индексу
    public double getPointX(int index) {
        if (index < 0 || index >= pointsCount) {
            return Double.NaN;
        }
        return points[index].getX();
    }

    // Устанавливает новую координату x для точки
    public void setPointX(int index, double x) {
        if (index < 0 || index >= pointsCount) {
            return;
        }

        // Проверяем границы
        if (index > 0 && x < points[index - 1].getX() + EPSILON) {
            return;
        }
        if (index < pointsCount - 1 && x > points[index + 1].getX() - EPSILON) {
            return;
        }

        points[index].setX(x);  // Устанавливаем новое значение x
    }

    // Возвращает координату y точки по индексу
    public double getPointY(int index) {
        if (index < 0 || index >= pointsCount) {
            return Double.NaN;
        }
        return points[index].getY();
    }

    // Устанавливает новую координату y для точки
    public void setPointY(int index, double y) {
        if (index < 0 || index >= pointsCount) {
            return;
        }
        points[index].setY(y);
    }

    // МЕТОДЫ ДЛЯ ИЗМЕНЕНИЯ КОЛИЧЕСТВА ТОЧЕК:

    // Удаляет точку по индексу
    public void deletePoint(int index) {
        // Нельзя удалять если точек меньше 3 или индекс неверный
        if (index < 0 || index >= pointsCount || pointsCount <= 2) {
            return;
        }

        // Сдвигаем все элементы после удаляемой точки влево
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;  // Уменьшаем счетчик точек
    }

    // Добавляет новую точку в функцию (с сохранением упорядоченности)
    public void addPoint(FunctionPoint point) {
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX() - EPSILON) {
            insertIndex++;
        }

        // Проверяем, нет ли точки с таким же x
        if (insertIndex < pointsCount && Math.abs(points[insertIndex].getX() - point.getX()) < EPSILON) {
            return;
        }

        // Проверяем нужно ли увеличивать массив
        if (pointsCount >= points.length) {
            // Создаем новый массив в 2 раза больше
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        // Сдвигаем элементы чтобы освободить место для новой точки
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        
        // Вставляем новую точку (создаем копию для инкапсуляции)
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++; 
    }
}