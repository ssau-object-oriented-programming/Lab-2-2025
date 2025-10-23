package functions;

public class TabulatedFunction {

    private FunctionPoint[] points_arr; // Массив точек функции
    private int pointslength; // Текущее количество точек

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points_arr = new FunctionPoint[pointsCount]; // Инициализация массива
        pointslength = pointsCount; // Установка количества точек
        double intervlen= Math.abs((rightX-leftX))/(pointsCount-1); // Вычисление интервала

        for(int i = 0; i < pointsCount; i++){
            points_arr[i] = new FunctionPoint(leftX+intervlen*i, 0); // Создание точек с равномерным X и Y=0
        }
    }
    
    public TabulatedFunction(double leftX, double rightX, double[] points) {
        pointslength = points.length; // Установка количества точек из массива Y
        points_arr = new FunctionPoint[points.length]; // Инициализация массива точек

        double intervlen= Math.abs((rightX-leftX))/(points.length-1); // Вычисление интервала
        if (pointslength != 0) {
            for (int i = 0; i < points.length; i++) {
                points_arr[i] = new FunctionPoint(leftX+intervlen*i / (pointslength - 1), points[i]); // Создание точек с заданными Y
            }
        }
    }

    public double getLeftDomainBorder() {
        return points_arr[0].getX(); // Возврат X первой точки
    }

    public double getRightDomainBorder(){
        return points_arr[pointslength-1].getX(); // Возврат X последней точки
    }
    
    public double getFunctionValue(double x){
        double y; // Не используется, можно удалить
        int findex=0, sindex=0; // Индексы для интерполяции

        if(x < getLeftDomainBorder() || x > getRightDomainBorder()){return Double.NaN;} // Проверка границ
        if(x == getLeftDomainBorder()){return points_arr[0].getY();} // Значение на левой границе
        if(x == getRightDomainBorder()){return points_arr[pointslength-1].getY();} // Значение на правой границе

        for(int i = 0; i < pointslength-1; i++){
            if(points_arr[i].getX() <= x && points_arr[i + 1].getX() >= x){ // Поиск отрезка
            findex = i; // Левая точка отрезка
            sindex = i + 1; // Правая точка отрезка
            i = pointslength-1; // Выход из цикла
            }
        }
        return ((points_arr[findex].getY()) + (points_arr[sindex].getY() - points_arr[findex].getY()) * (x - points_arr[findex].getX()) / (points_arr[sindex].getX() - points_arr[findex].getX())); // Линейная интерполяция
    }

    public int getPointsCount(){
        return pointslength; // Возврат количества точек
    }

    public FunctionPoint getPoint(int index){
        FunctionPoint point = new FunctionPoint(points_arr[index]); // Создание копии точки
        return point; // Возврат копии
    }

    public void setPoint(int index, FunctionPoint point){
        if ((index == 0 || point.getX() > points_arr[index - 1].getX()) && // Проверка порядка X
                (index == pointslength - 1 || point.getX() < points_arr[index + 1].getX())) {
            points_arr[index] = new FunctionPoint(point); // Замена точки
        };
    }
    
    public double getPointX(int index){
        return points_arr[index].getX(); // Возврат X точки
    }

    public void setPointX(int index, double x){
        if(x >= this.getLeftDomainBorder() && x <= this.getRightDomainBorder()){ // Проверка границ
            points_arr[index] = new FunctionPoint(x, points_arr[index].getY()); // Установка нового X
        }
    }

    public double getPointY(int index){
        return points_arr[index].getY(); // Возврат Y точки
    }
    
    public void setPointY(int index, double y){
        points_arr[index] = new FunctionPoint(points_arr[index].getX(), y); // Установка нового Y
    }

    public void deletePoint(int index){
        if(index >= 0 && index < pointslength){ // Проверка индекса
            points_arr[index] = null; // Удаление точки
            for(int i = index; i < pointslength-1; i++){
