package functions;

public class TabulatedFunction{
    private FunctionPoint[] points; // массив точек
    private int pointsCount; // количество точек

    // создание табулированной функции
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        this.pointsCount = pointsCount;
        points = new FunctionPoint[pointsCount];
        double step = Math.abs(rightX-leftX)/(pointsCount-1);
        for (int i=0; i < pointsCount;i++) {
            points[i] = new FunctionPoint(leftX + i*step, 0);
        }
    }

    // создание табулированной функции с заданными значениями по оси ординат
    public TabulatedFunction(double leftX, double rightX, double[] value){
        int pointsCount = value.length;
        this.pointsCount = pointsCount;
        points = new FunctionPoint[pointsCount];
        double step = Math.abs(rightX-leftX)/(pointsCount-1);
        for (int i=0; i < pointsCount;i++) {
            points[i] = new FunctionPoint(leftX + i*step, value[i]);
        }
    }

    // возвращение левой границы области определения
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    // возвращение правой границы области определения
    public double getRightDomainBorder() {
        return points[this.pointsCount - 1].getX();
    }

    // возвращение у, если точка лежит в области определения функции
    public double getFunctionValue(double x){
        double leftX = getLeftDomainBorder();
        double rightX = getRightDomainBorder();

        if (x>= leftX && x <= rightX){
            // ищем соседние точки
            for (int i = 0; i < this.pointsCount - 1; i++) {
                double x1 = points[i].getX();
                double x2 = points[i + 1].getX();

                if (x >= x1 && x <= x2) {
                    if (x == x1) return points[i].getY();
                    if (x == x2) return points[i + 1].getY();

                    double y1 = points[i].getY();
                    double y2 = points[i + 1].getY();
                    return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
                }
            }

        }

        return Double.NaN;
    }

    // возвращения количества точек
    public int getPointsCount(){
        return pointsCount;
    }

    // возвращение точки
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    // изменение указанной точки на заданную
    public void setPoint (int index, FunctionPoint point){
        if ((index < 0 || index >= pointsCount) || ( index != 0 && point.getX() <= points[index - 1].getX()) ||
                (index != pointsCount-1 &&  point.getX() >= points[index + 1].getX()))

            return;

        points[index]= new FunctionPoint(point);

    }

    // возвращение координаты х
    public double getPointX(int index){
        return points[index].getX();
    }

    // изменение значения х
    public void setPointX(int index, double x){
        if ((index < 0 || index >= pointsCount) || ( index != 0 && x <= points[index - 1].getX()) ||
                (index != pointsCount-1 &&  x >= points[index + 1].getX()))
            return;

        points[index].setX(x);
    }

    // возвращения координаты у
    public double getPointY (int index){
        return points[index].getY();
    }

    // изменения значения у
    public void setPointY(int index, double y){
        if (index < 0 || index >= pointsCount)
            return;

        points[index].setY(y);
    }

    // удаление точки
    public void deletePoint(int index){
        if (index>= 0 && index < pointsCount) {
            System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
            points[--pointsCount] = null;
        }
        return;
    }

    // добавление точки
    public void addPoint(FunctionPoint point) {
        int index= 0;

        double newX = point.getX();
        // находим позицию для вставки точки
        while (index < pointsCount && newX > points[index].getX())
            index++;

        // проверяем нужно ли увеличивать размер массива
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

    // вывод массива точек
    public void outFunction(){
        for (int i = 0; i<pointsCount; i++)
            System.out.printf("(%.2f, %.2f) ", points[i].getX(), points[i].getY());

        System.out.println();
    }
}























































