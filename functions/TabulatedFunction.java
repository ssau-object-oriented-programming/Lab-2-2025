package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX) {
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
            System.out.println("Левая граница должна быть меньше правой.Их поменяли местами автоматически");

        }

        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX) {
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
            System.out.println("Левая граница должна быть меньше правой.Их поменяли местами автоматически");

        }


        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }
    
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;//Если x не в диапазоне ,то возвращаем Nan
        }
        for (int i = 0; i < pointsCount - 1; i++) {//Берем каждый минимальный отрезок между обьектами,берем их X
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();

            if (x >= x1 && x <= x2) {//Если исходный x между ними ,то находим для него y по формуле
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();

                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }

    public int getPointsCount(){
        return pointsCount;
    }
    
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }
    
    public void setPoint(int index, FunctionPoint point) {
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            return;
        }
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) {
            return;
        }

        points[index] = new FunctionPoint(point);
    }
    
    public double getPointX(int index) {
        return points[index].getX();
    }
    
    public void setPointX(int index, double x) {
        if (index > 0 && x <= points[index - 1].getX()) {
            return;
        }
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) {
            return;
        }

        points[index].setX(x);
    }
    
    public double getPointY(int index) {
        return points[index].getY();
    }
    
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }
    
    public void deletePoint(int index) {
        for (int i = index; i < pointsCount - 1; i++) {
            points[i] = points[i + 1];
        }
        --pointsCount;
    }
    
    // ИСПРАВЛЕНО: functionPoint -> FunctionPoint (была опечатка)
    public void addPoint(FunctionPoint point){
        int count=0;
        while(count < pointsCount && point.getX()>points[count].getX()){//проверка текущего X обьекта с X обьекта point
            count++;
        }
        if (count < pointsCount && point.getX()==points[count].getX()){//Проверка на равенство X ,если так ,то вставлять элемент не будем 
            return ;
        }
        // Проверяем необходимость увеличения массива
        if (pointsCount >= points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            for (int i = 0; i < pointsCount; i++) {
                newPoints[i] = points[i];
            }
            points = newPoints;
        }
        // Сдвигаем элементы ,Выходит так что на месте  point[count] и points[count+1] стоят два одиннаковых элемента
        for (int i = pointsCount; i > count; i--) {
            points[i] = points[i - 1];
        }
        // Вставляем новую точку
        points[count] = new FunctionPoint(point);

        //  увеличиваем счетчик
        pointsCount++;
    }
}
