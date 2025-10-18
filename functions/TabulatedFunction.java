package functions;

public class TabulatedFunction {
    public FunctionPoint[] points;
    public int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.points = new FunctionPoint[pointsCount];
        this.pointsCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step; 
            points[i] = new FunctionPoint(x, 0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.points = new FunctionPoint[values.length];
        this.pointsCount = values.length;
    
        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
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
    public double linearInterpolation(FunctionPoint p1, FunctionPoint p2, double x) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }
    public double getFunctionValue(double x){
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()){
            return Double.NaN;
        } 
        for(int i = 0; i < pointsCount - 1; i++){
            double curX = points[i].getX();
            double nextX = points[i+1].getX();
            if(x == curX){
                return points[i].getY();
            }
            if(x > curX && x < nextX){
                return linearInterpolation(points[i], points[i+1], x);
            }
            if(x == nextX){
                return points[i+1].getY();
            }
        }
        return points[pointsCount - 1].getY();
    }
    
    
    public int getPointsCount() {
        return pointsCount;
    }
    public FunctionPoint getPoint(int index){
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point){
        if(index > 0 && points[index-1].getX() < point.getX()){
            return;
        }
        if(index < pointsCount - 1 && points[index+1].getX() > point.getX()){
            return;
        }
        points[index] = new FunctionPoint(point);
    }
    
    public double getPointX(int index){
        return points[index].getX();
    }

    public void setPointX(int index, double x){
        if(index > 0 && points[index-1].getX() < x){
            return;
        }
        if(index < pointsCount - 1 && points[index+1].getX() > x){
            return;
        }
        points[index].setX(x);
    }

    public double getPointY(int index){
        return points[index].getY();
    }

    public void setPointY(int index, double y){
        points[index].setY(y);
    }

    public void deletePoint(int index){
        if (pointsCount - 1 - index >= 0) {
            System.arraycopy(points, index + 1, points, index, pointsCount - 1 - index);
        }
        pointsCount--;
        points[pointsCount] = null;
    }

    public void addPoint(FunctionPoint point) {
        FunctionPoint newPoint = new FunctionPoint(point);
        double newX = newPoint.getX();
        
        // Проверяем, не существует ли уже точка с таким X
        for (int i = 0; i < pointsCount; i++) {
            if (Math.abs(points[i].getX() - newX) < 1e-10) {
                // Точка с таким X уже существует - заменяем ее
                points[i] = newPoint;
                return;
            }
        }
        
        // Создаем новый массив на 1 элемент больше
        FunctionPoint[] newArray = new FunctionPoint[pointsCount + 1];
        
        // Находим позицию для вставки
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < newX) {
            insertIndex++;
        }
        
        // Копируем элементы до позиции вставки с помощью System.arraycopy
        if (insertIndex > 0) {
            System.arraycopy(points, 0, newArray, 0, insertIndex);
        }
        
        // Вставляем новую точку
        newArray[insertIndex] = newPoint;
        
        // Копируем элементы после позиции вставки с помощью System.arraycopy
        if (pointsCount - insertIndex > 0) {
            System.arraycopy(points, insertIndex, newArray, insertIndex + 1, pointsCount - insertIndex);
        }
        
        // Заменяем старый массив новым
        points = newArray;
        pointsCount++;
    }
}
    