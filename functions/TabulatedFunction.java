package functions;
public class TabulatedFunction {
    
    private FunctionPoint points[];
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }

    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        pointsCount = values.length;
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount-1].getX();
    }

    public double getFunctionValue(double x)
     {
        double epcilon=1e-9;
        double leftX=points[0].getX();
        double rightX=points[pointsCount-1].getX();
        if (x < leftX|| x > rightX)
            return Double.NaN;
        if(Math.abs(x-leftX)<epcilon)
        {
            return points[0].getY();
        }
        if(Math.abs(x-rightX)<epcilon)
        {
            return points[pointsCount-1].getY();
        }
        int i = 0;
        double value=0;
        for (i=0;x > points[i].getX();++i)
        {
        value = points[i].getY() + (points[i + 1].getY() - points[i].getY()) * (x - points[i].getX()) / (points[i + 1].getX() - points[i].getX());
        }
        return value;

    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        if(index<0||index>=pointsCount)
        {
            return null;
        }
        return points[index];
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount)
            return;
        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {

        if (index < 0 || index >= pointsCount)
            return Double.NaN;
        return points[index].getX();
    }


    public void setPointX(int index, double x) {
        if (index < 0 || index > pointsCount)
            return;
        points[index].setX(x);
        
    }

    public double getPointY(int index) {
        if (index < 0 || index > pointsCount)
            return Double.NaN;
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

  public void deletePoint(int index){
    if (index < 0 || index >= pointsCount){
            return;
        } 
        
        if(index == pointsCount - 1){
            pointsCount--;
            points[pointsCount] = null;
        }
        else{ 
            System.arraycopy(points, index + 1, points, index, pointsCount - 1 - index);
            pointsCount--;
            points[pointsCount] = null;
        }
        
    
        }

    public void addPoint(FunctionPoint point) {
        int i = 0;
    while (i < pointsCount && point.getX() > points[i].getX()) {
        i++;
    }

    // Если точка с таким же x уже есть, обновляем y
    if (i < pointsCount && points[i].getX() == point.getX()) {
        setPointY(i, point.getY());
        return;
    }

    
    System.arraycopy(points, i, points, i + 1, pointsCount - i);
    points[i] = new FunctionPoint(point);
    pointsCount++;
}


}