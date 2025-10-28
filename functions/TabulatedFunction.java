package functions;

public class TabulatedFunction{
    private FunctionPoint[] points;
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        points = new FunctionPoint[pointsCount + 2];
        this.pointsCount = pointsCount;
        double distance = (rightX - leftX)/(pointsCount - 1);

        for (int i = 0; i < pointsCount; i++){
            double x = leftX + i * distance;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.pointsCount = values.length;
        points = new FunctionPoint[pointsCount + 2];
        double distance = (rightX - leftX)/(pointsCount - 1);

        for (int i = 0; i < pointsCount; i++){
            double x = leftX + i * distance;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder(){
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x){
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        for (int i = 0; i < pointsCount - 1; i++){
            double x_1 = points[i].getX();
            double x_2 = points[i + 1].getX();

            if (x == x_1){
                return points[i].getY();
            }

            if (x == x_2){
                return points[i + 1].getY();
            }

            if (x > x_1 && x < x_2){
                double y_1 = points[i].getY();
                double y_2 = points[i + 1].getY();

                return (x - x_1) * (y_2 - y_1) / (x_2 - x_1) + y_1;
            }
        }
        return Double.NaN;
    }

    public int getPointsCount(){
        return pointsCount;
    }

    public FunctionPoint getPoint(int index){
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point){
        if (index < 0 || index >= pointsCount) {
            return;
        }
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            return; 
        }
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) {
            return; 
        }
        
        points[index] = new FunctionPoint(point);
    }
    
    public double getPointX(int index){
        return points[index].getX();
    }

    public void setPointX(int index, double x){
        if (index < 0 || index >= pointsCount) {
            return;
        }
        if (index > 0 && x <= points[index - 1].getX()) {
            return; 
        }
        if (index < pointsCount - 1 && x >= points[index + 1].getX()) {
            return; 
        }
        
        points[index].setX(x);
    }

    public double getPointY(int index){
        return points[index].getY();
    }

     public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        if (pointsCount <= 2) {
            return; 
        }
        if (index < 0 || index >= pointsCount) {
            return;
        }

        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;
        points[pointsCount] = null;
    }

    public void addPoint(FunctionPoint point) {
        FunctionPoint newPoint = new FunctionPoint(point);
        int newIndex = 0;
        while (newIndex < pointsCount && points[newIndex].getX() < newPoint.getX()) {
         newIndex++;
        }
        if  (newIndex < pointsCount && points[newIndex].getX() == newPoint.getX()) {
            return; 
        }
        if (pointsCount == points.length) {
            increaseArraySize();
        }
        
        System.arraycopy(points, newIndex, points, newIndex + 1, pointsCount - newIndex);
        points[newIndex] = newPoint;
        pointsCount++;
    }

    private void increaseArraySize() {
        FunctionPoint[] newArray = new FunctionPoint[points.length * 2 + 2];
        System.arraycopy(points, 0, newArray, 0, pointsCount);
        points = newArray;
    }
}