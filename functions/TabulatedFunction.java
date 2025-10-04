package functions;

public class TabulatedFunction {

    private FunctionPoint[] points;

    //создаёт объект табулированной функции по заданным левой и правой границе области определения, а также количеству точек для табулирования
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.points = new FunctionPoint[pointsCount];
        if (pointsCount == 1) points[0] = new FunctionPoint((rightX-leftX)/2, 0);
        else {
            //вычисляем шаг между точками и заполняем массив точек
            double step = (rightX - leftX) / (pointsCount - 1);
            for (int i = 0; i < pointsCount; i++) {
                points[i] = new FunctionPoint(leftX + i * step, 0);
            }
        }
    }

    //создаёт объект табулированной функции по заданным левой и правой границе области определения, а также значениям функции
    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.points = new FunctionPoint[values.length];
        if (values.length == 1) points[0] = new FunctionPoint((rightX-leftX)/2, values[0]);
        else {
            //вычисляем шаг между точками и заполняем массив точек
            double step = (rightX - leftX) / (values.length - 1);
            for (int i = 0; i < values.length; i++) {
                points[i] = new FunctionPoint(leftX + i * step, values[i]);
            }
        }
    }

    //возвращает левую границу области определения функции
    public double getLeftDomainBorder(){
        return points[0].getX();
    }

    //возвращает правую границу области определения функции
    public double getRightDomainBorder(){
        return points[points.length-1].getX();
    }

    //вычисляет значение функции в заданной точке x с помощью линейной интерполяции
    //если x вне области определения, то возвращаем Double.NaN (Not-a-Number)
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) return Double.NaN;

        for (int i = 0; i< points.length-1; i++) {
            if (points[i].getX() <= x && x <= points[i + 1].getX()) {
                double x1 = points[i].getX();
                double x2 = points[i + 1].getX();
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }

    //возвращает количество точек табулирования
    public int getPointsCount(){return points.length;}

    //возвращает копию точки по указанному индексу
    public FunctionPoint getPoint(int index){return new FunctionPoint(points[index]);}

    //устанавливает точку по указанному индексу (с проверкой порядка по X)
    public void setPoint(int index, FunctionPoint point){
        double newX = point.getX();
        if (index > 0 && newX<=points[index-1].getX()) return;
        if (index < points.length-1 && newX >= points[index + 1].getX()) return;
        points[index] = new FunctionPoint(point);
    }

    //возвращает координату X точки по указанному индексу
    public double getPointX(int index){return points[index].getX();}

    //устанавливает координату X точки по указанному индексу (с проверкой порядка)
    public void setPointX(int index, double x){
        if (index > 0 && x <= points[index - 1].getX()) return;
        if (index<points.length-1 && x >= points[index + 1].getX()) return;
        points[index].setX(x);
    }

    //возвращает координату Y точки по указанному индексу
    public double getPointY(int index){return points[index].getY();}

    //устанавливает координату Y точки по указанному индексу
    public void setPointY(int index, double y){
        if (index < points.length && index >= 0) points[index].setY(y);
    }

    //удаляет точку по указанному индексу
    public void deletePoint(int index){
        if (index < 0 || index >= points.length) return;
        FunctionPoint[] newPoints = new FunctionPoint[points.length-1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index+1, newPoints, index, newPoints.length-index);
        points  = newPoints;
    }

    //добавляет новую точку в массив (с сохранением порядка по X)
    public void addPoint(FunctionPoint point){
        //находим позицию для вставки (точки должны быть отсортированы по X)
        int insertIndex = 0;
        while (insertIndex < points.length && points[insertIndex].getX() < point.getX()) insertIndex++;

        //если такая точка уже существует - ничего не добавляем
        if (insertIndex < points.length && points[insertIndex].getX() == point.getX()) return;

        FunctionPoint[] newPoints = new FunctionPoint[points.length+1];
        System.arraycopy(points, 0, newPoints, 0, insertIndex);
        newPoints[insertIndex] = new FunctionPoint(point);
        System.arraycopy(points, insertIndex, newPoints, insertIndex + 1, points.length - insertIndex);
        points = newPoints;
    }
}
