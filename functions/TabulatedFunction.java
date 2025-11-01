package functions;


public class TabulatedFunction { 
    private double leftDomainBorder, rightDomainBorder; // Левая и правая границы

   private FunctionPoint[] points; // Массив точек
   private int size;

    public TabulatedFunction(double leftX, double rightX, int pointsCount){ // Конструктор с количеством точек

        this.leftDomainBorder = leftX; // Левая граница области определения
        this.rightDomainBorder = rightX; // Правая граница области определения
        this.points = new FunctionPoint[pointsCount]; // Массив точек (FunctionPoint)
        this.size = pointsCount;

        double delta = (rightX - leftX) / (pointsCount - 1); // Считаем дельту, размер каждого из отрезков
        for (int i = 0; i < pointsCount-1; ++i){ // Инициализируем значения точек в цикле кроме последнего
            points[i] = new FunctionPoint(leftX + (i * delta), 0);
        }
        points[pointsCount-1] = new FunctionPoint(rightX, 0);
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){ // Конструктор с значениями функций
        this.leftDomainBorder = leftX; 
        this.rightDomainBorder = rightX;
        this.points = new FunctionPoint[values.length];
        this.size = values.length;

        double delta = (rightX - leftX) / (values.length - 1); 
        for (int i = 0; i < values.length - 1; ++i) { // В цикле инициализируем значения точек, но уже вместе с y
            points[i] = new FunctionPoint(leftX + (i * delta), values[i]);
        }
        points[values.length- 1] = new FunctionPoint(rightX, values[values.length - 1]);
    }

    // Гет методы
    public double getLeftDomainBorder(){return leftDomainBorder;} 
    public double getRightDomainBorder() {return rightDomainBorder;}


    // Метод для получения прямой по двум точкам
    private double interpolate(double x, double x1, double y1, double x2, double y2){
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    // Метод для получения значения функции
    public double getFunctionValue(double x){
        if (x < this.leftDomainBorder || x > this.rightDomainBorder){ // Если x выходит из диапозона возращаем Double.NaN 
            return Double.NaN;
        }


        for (int i = 0; i < size - 1; ++i){ // Перебараем в цикле все отрезки интервала

            if (((x > points[i].getCoorX()) || (Math.abs(x - points[i].getCoorX()) <= FunctionPoint.epsilon)) && x < points[i+1].getCoorX()){ // Если x входит в определённый отрезок
                return interpolate(x, points[i].getCoorX(), points[i].getCoorY(), points[i+1].getCoorX(), points[i+1].getCoorY());
            }
        }

        if (Math.abs(x - points[size-1].getCoorX()) <= FunctionPoint.epsilon){ // Если x является правой границей интервала
            return points[size-1].getCoorY();
        }

        return Double.NaN;
    }

    public int getPointsCount(){ // Возвращает количество точек
        return size;
    }

    public FunctionPoint getPoint(int index) throws IllegalArgumentException{

        if (index < 0 || index >=size){ // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива"); 
        }

        FunctionPoint pointCopy = new FunctionPoint(points[index].getCoorX(), points[index].getCoorY()); // Создаём копию, чтобы не нарушать инкапчуляцию
        return pointCopy;
    }

    public void setPoint(int index, FunctionPoint point) throws IllegalArgumentException{ 
        if (index < 0 || index >= size) { // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива");
        }
        if (index > 0 && point.getCoorX() < points[index-1].getCoorX()){
            throw new IllegalArgumentException("x Нарушает порядок,выберите другой индекс");
        }
        if (index < size - 1 && point.getCoorX() > points[index + 1].getCoorX()){
            throw new IllegalArgumentException("x Нарушает порядок,выберите другой индекс");
        }
        points[index].setCoorX(point.getCoorX());
        points[index].setCoorY(point.getCoorY());
    }

    public double getPointX(int index) throws IllegalArgumentException{
        if (index < 0 || index >= size) { // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива");
        }
        return points[index].getCoorX();
    }

    public double getPointY(int index) throws IllegalArgumentException {
        if (index < 0 || index >= size) { // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива");
        }
        return points[index].getCoorY();
    }

    public void setPointX(int index, double x) throws IllegalArgumentException{ 
        if (index < 0 || index >= size) { // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива");
        }

        if (index > 0 && points[index-1].getCoorX() > x ){
            throw new IllegalArgumentException("x Нарушает порядок,выберите другой индекс");
        }
        if (index < size-1 && points[index+1].getCoorX() < x){
            throw new IllegalArgumentException("x Нарушает порядок,выберите другой индекс");
        }
            points[index].setCoorX(x);   
    }

    public void setPointY(int index, double y) throws IllegalArgumentException {
        if (index < 0 || index >= size) { // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива");
        }
        points[index].setCoorY(y);
    }

    public void deletePoint(int index) throws IllegalArgumentException{
        if (index < 0 || index >= size) { // Если не подходит под условие выбрасываем исключение
            throw new IllegalArgumentException("Выход за пределы массива");
        }
    
        System.arraycopy(points, index + 1, points, index, size - index - 1);
        points[size-1] = null;
        --size;


        if (size > 0) { // Обнавляем границы после удаления
            this.leftDomainBorder = points[0].getCoorX();
            this.rightDomainBorder = points[size - 1].getCoorX();
        }
    }

    public void addPoint(FunctionPoint point) throws IllegalArgumentException{

        final int k = 2; // Коэфф. расширения длины массива

        double point_x = point.getCoorX();
        int point_idx = size; // Индекс новой точки // По умолчанию в конец

        if (point_x < leftDomainBorder){ // Проверяем расширяет ли точка диапазон
            point_idx = 0; 
        }else{
            if (point_x > rightDomainBorder){
                point_idx = size;
            }
        }

        for (int i = 0; i < size; ++i){
            if (Math.abs(point_x - points[i].getCoorX()) <= FunctionPoint.epsilon){ // Если точка по x совпадает с другой точкой
                throw new IllegalArgumentException("Точка должна иметь уникальное значение x");
            }
        }

        for (int i = 0; i < size - 1; ++i){ // Если точка принадлежит интервалу
            if ( points[i].getCoorX() < point_x && point_x < points[i+1].getCoorX()){ // Если входит в один из отрезков интервала
                point_idx = i + 1;
                break;
            }
        }

        if (points.length <= size) { // Проверяем хватает ли длины массива, если нет расширяем массив на 1
            FunctionPoint[] new_points = new FunctionPoint[k*points.length];
            
            System.arraycopy(points, 0, new_points, 0, point_idx);
            new_points[point_idx] = new FunctionPoint(point);
            System.arraycopy(points, point_idx, new_points, point_idx+1, size - point_idx);

            points = new_points;
        }else{
            System.arraycopy(points, point_idx, points, point_idx + 1, size - point_idx);
            points[point_idx] = new FunctionPoint(point);
            
        }
        size++;
        if (point_idx == 0) { // обновляем границы
            this.leftDomainBorder = point_x;
        }
        if (point_idx == size - 1) { 
            this.rightDomainBorder = point_x;
        }
    }
}