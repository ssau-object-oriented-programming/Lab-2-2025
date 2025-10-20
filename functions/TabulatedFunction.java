package functions;

public class TabulatedFunction {
    private FunctionPoint[] point;
    private int pointsCount;
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        this.pointsCount = pointsCount;
        point = new FunctionPoint[pointsCount];
        double prom = (rightX-leftX)/(pointsCount-1);
        for(int i = 0; i < pointsCount; i++){
            double x = leftX + prom * i;
            point[i] = new FunctionPoint(x,0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.pointsCount = values.length;
        point = new FunctionPoint[pointsCount];
        double prom = (rightX-leftX)/(pointsCount-1);
        for(int i = 0; i < pointsCount; i++) {
            double x = leftX + prom * i;
            double y = values[i];
            point[i] = new FunctionPoint(x, y);
        }
    }
    public double getLeftDomainBorder(){
        return point[0].getX();
    }
    public double getRightDomainBorder(){
        return point[point.length - 1].getX();
    }

    public double getFunctionValue(double x){
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        for(int i = 0; i < pointsCount; i++){
            if(point[i].getX() == x){
                return point[i].getY();
            }
        }
        for (int i = 0; i < pointsCount - 1; i++){
            double x1 = point[i].getX();
            double y1 = point[i].getY();
            double x2 = point[i + 1].getX();
            double y2 = point[i + 1].getY();
            if(x1 < x && x < x2){
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }
    public int getPointsCount(){
        return pointsCount;
    }
    public FunctionPoint getPoint(int index){
        return new FunctionPoint(point[index]);
    }
    public void setPoint(int index, FunctionPoint p){
        double x = p.getX();
        if ((index > 0 && x <= point[index - 1].getX()) || (index < pointsCount - 1 && x >= point[index + 1].getX())){
            return;
        }
        point[index] = new FunctionPoint(p);
    }
    public double getPointX(int index){
        return point[index].getX();
    }
    public void setPointX(int index, double x){
        if ((index > 0 && x <= point[index -1].getX()) || (index < pointsCount -1 && x >= point[index +1].getX())){
            return;
        }
        point[index].setX(x);
    }
    public double getPointY(int index){
        return point[index].getY();
    }
    public void setPointY(int index, double y){
        point[index].setY(y);
    }
    public void deletePoint(int index){
        if (pointsCount <=1){
            System.out.println("Нельзя удалить последнюю точку");
            return;
        }
        if (index < 0 || index >= pointsCount){
            System.out.println("Неверный индекс");
            return;
        }
        FunctionPoint[] newPoint = new FunctionPoint[pointsCount - 1];
        System.arraycopy(point, 0, newPoint, 0, index);
        System.arraycopy(point, index + 1, newPoint, index, pointsCount - index - 1);
        point = newPoint;
        pointsCount--;
    }
    public void addPoint(FunctionPoint p){
        double x =p.getX();
        FunctionPoint[] newPoint = new FunctionPoint[pointsCount + 1];
        int i = 0;
        while (i < pointsCount && point[i].getX() < x){
            newPoint[i] = point[i];
            i++;
        }
        newPoint[i] = new FunctionPoint(p);
        for(int j = i; j < pointsCount; j++){
            newPoint[j + 1] = point[j];
        }
        point=newPoint;
        pointsCount++;
    }

}
