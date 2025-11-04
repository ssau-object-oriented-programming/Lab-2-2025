package functions;

public class TabulatedFunction {
    private FunctionPoint[] points; //массив точек, задающих функцию
    private int pointsCount;

    //создаёт объект табулированной функции по заданным левой и правой границам области определения, а также количеству точек для табулирования
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.pointsCount = pointsCount;
        points = new FunctionPoint[pointsCount];
        double step = Math.abs(rightX - leftX) / (pointsCount - 1); //шаг между точками
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }
    }

    //создает объект табулированной функции по заданным левой и правой границам области определения, а также значениям функции
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        int pointsCount = values.length;
        this.pointsCount = pointsCount;
        points = new FunctionPoint[pointsCount];
        double step = Math.abs(rightX - leftX) / (pointsCount - 1); //интервал (шаг между точками)
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }


    public double getLeftDomainBorder() {
        return points[0].getX(); //возвращение левой границы области опрделения
    }

    public double getRightDomainBorder() {
        return points[this.pointsCount - 1].getX(); // возвращение правой границы области определения
    }

    //возвращение значения функции в точке x, если эта точка лежит в области определения функции
    public double getFunctionValue(double x) {
        double leftX = getLeftDomainBorder();
        double rightX = getRightDomainBorder();

        if (x >= leftX && x <= rightX) {
            //поиск соседних точек
            for (int i = 0; i < this.pointsCount - 1; i++) {
                double x1 = points[i].getX();
                double x2 = points[i + 1].getX();

                if (x >= x1 && x <= x2) {
                    if (Math.abs(x - x1) < 1e-10) return points[i].getY();
                    if (Math.abs(x - x2) < 1e-10) return points[i + 1].getY();

                    double y1 = points[i].getY();
                    double y2 = points[i + 1].getY();
                    return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
                }
            }

        }

        return Double.NaN;
    }

    //возвращения количество точек
    public int getPointsCount(){
        return pointsCount;
    }

    //возвращает копию точки по указанному индексу
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    //заменяет указанную точку табулированной функции на переданную
    public void setPoint(int index, FunctionPoint point){
        if ((index < 0 || index >= pointsCount) || ( index != 0 && point.getX() <= points[index - 1].getX())
                ||  (index != pointsCount-1 &&  point.getX() >= points[index + 1].getX()))
        return;

        points[index]= new FunctionPoint(point);

    }

    //возвращает значение абсциссы точки с указанным номером
    public double getPointX(int index){
        return points[index].getX();
    }

    //измененяет значения х
    public void setPointX(int index, double x){
        if ((index < 0 || index >= pointsCount) ||  ( index != 0 && x <= points[index - 1].getX())
        || (index != pointsCount-1 &&  x >= points[index + 1].getX()))
        return;

        points[index].setX(x);
    }

    //возвращает координаты у
    public double getPointY (int index){
        return points[index].getY();
    }

    //изменяет значения у
    public void setPointY(int index, double y){
        if (index < 0 || index >= pointsCount)
            return;

        points[index].setY(y);
    }

    //удаление точки по указанному индексу
    public void deletePoint(int index){
        if (index < pointsCount) {
            System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
            points[--pointsCount] = null;
        }
        return;
    }

    //добавление новой точки в массив с сохранением порядка по Х
    public void addPoint(FunctionPoint point) {
        int index= 0;

        double newX = point.getX();
        //находим позицию для вставки точки
        while (index < pointsCount && newX > points[index].getX())
            index++;
        //проверка для увелечение массива
        if (pointsCount >= points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        if (index < pointsCount)
            System.arraycopy(points, index, points, index + 1, pointsCount - index);

        points[index] = new FunctionPoint(point);
        pointsCount++;

    }
    //вывод
    public void printFunc(){
        for(int i = 0; i< pointsCount; i++)
            System.out.printf("(%.2f, %.2f) ", points[i].getX(), points[i].getY());

        System.out.println();
    }

}
