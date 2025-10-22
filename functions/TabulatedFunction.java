package functions;

public class TabulatedFunction{
    private FunctionPoint[] points;
    private double leftX;
    private double rightX;
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        this.points = new FunctionPoint[this.pointsCount];
        double step = (rightX - leftX)/(pointsCount - 1);
        for (int i = 0; i < pointsCount; i++){
            points[i] = new FunctionPoint(leftX + i*step, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.pointsCount = values.length;
        this.points = new FunctionPoint[this.pointsCount];
        double step = (rightX - leftX)/(pointsCount - 1);
        int i;
        for (i = 0; i < pointsCount; i++){
            points[i] = new FunctionPoint(leftX + i*step, values[i]);
        }
    }
    
    public double getLeftDomainBorder(){
        return leftX;
    }
    
    public double getRightDomainBorder(){
        return rightX;
    }
    
    public double getFunctionValue(double x){
        if (x < leftX || x > rightX){
            return Double.NaN;
        }
        if (x == leftX){
            return points[0].getY();
        }
        if (x == rightX){
            return points[pointsCount - 1].getY();
        }
        else{
            int i;
            double value = 0;
            for (i = 0; x > points[i].getX(); i++){
                value = points[i].getY()+(points[i+1].getY() - points[i].getY())*(x-points[i].getX())/(points[i+1].getX()-points[i].getX());
            }
            return value; 
        }
    }
    
    public int getPointsCount(){
        return pointsCount;
    }
    
    public FunctionPoint getPoint(int index){
        if (index < 0 || index >= pointsCount){
            return null;
        }
        return new FunctionPoint(points[index].getX(), points[index].getY());
    }
    
    public void setPoint(int index, FunctionPoint point){
        if (index < 0 || index >= pointsCount){
            return;
        }
        points[index] = new FunctionPoint(point);
        leftX = points[0].getX();
        rightX = points[pointsCount - 1].getX(); 
    }

    public double getPointX(int index){
        if (index < 0 || index >= pointsCount){
            return Double.NaN;
        }
        else{
            return points[index].getX();
        }
    }
    
    public void setPointX(int index, double x){
        if (index < 0 || index >= pointsCount){
            return;
        }
        points[index].setX(x);
        leftX = points[0].getX();
        rightX = points[pointsCount - 1].getX();
    }

    public double getPointY(int index){
        if (index < 0 || index >= pointsCount){
            return Double.NaN;
        }
        else{
            return points[index].getY();
        }
    }

    public void setPointY(int index, double y){
        if (index < 0 || index >= pointsCount){
            return;
        }
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
            leftX = points[0].getX();
            rightX = points[pointsCount - 1].getX();
    }

	public void addPoint(FunctionPoint point){
        int i = 0;
        while (point.getX() > points[i].getX()) ++i;
        if(points[i].getX() == point.getX()){
            setPointY(i, point.getY());
            return;
        }
        System.arraycopy(points, 0, points, 0, i);
        if(i < pointsCount)
            System.arraycopy(points, i, points, i+1, pointsCount - i);
        setPoint (i, point);
        pointsCount++;
        leftX = points[0].getX();
        rightX = points[pointsCount - 1].getX();
    }

}

       
