package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int pointsCount;

    public TabulatedFunction(double leftX,double rightX, int pointsCount){
        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount];

        double step = (rightX-leftX)/(pointsCount-1);
        for (int i =0; i<pointsCount; i++){
            points[i]=new FunctionPoint(leftX+i*step,0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.pointsCount = values.length;
        this.points = new FunctionPoint[pointsCount];

        double step = (rightX-leftX)/(pointsCount-1);
        for (int i =0; i<pointsCount; i++){
            points[i]=new FunctionPoint(leftX+i*step,values[i]);
        }
    }

    public double getLeftDomainBorder(){
        return points[0].getX();
    }
    public double getRightDomainBorder(){
        return points[pointsCount-1].getX();
    }
    public double getFunctionValue(double x){
        double LX= points[0].getX(),RX= points[pointsCount-1].getX(); //LX-leftX, RX-rightX
        double LY=points[0].getY(),RY= points[pointsCount-1].getY(); //LY-leftY, RY-rightY
        if(LX>x || RX<x){
            return Double.NaN;
        }
        return ((x-LX)*(RY-LY))/(RX-LX)+LY;
    }

    public int getPointsCount(){
        return pointsCount;
    }

    public FunctionPoint getPoint(int index){
        FunctionPoint point = points[index];
        return point;
    }

    public void setPoint(int index, FunctionPoint point){
        if (points[index-1].getX()<point.getX()&&points[index + 1].getX()>point.getX()){
            points[index]=point;
        }
    }

    public double getPointX(int index){
        return points[index].getX();
    }

    public void setPointX(int index, double x){
        if (points[index-1].getX()<x&&x<points[index+1].getX())
            points[index].setX(x);
    }

    public double getPointY(int index){
        return points[index].getY();
    }

    public void setPointY(int index, double y){
        points[index].setY(y);
    }

    public void deletePoint(int index){
        System.arraycopy(points,index,points,index-1,pointsCount-index);
        points[pointsCount-1]=null;
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) {
        for (int i = 1; i < pointsCount; i++) {
            if (point.getX() < points[i].getX() && point.getX() > points[i - 1].getX()) {
                FunctionPoint[] newPoints = new FunctionPoint[pointsCount + 1];
                System.arraycopy(points, 0, newPoints, 0, i);
                newPoints[i] = point;
                System.arraycopy(points, i, newPoints, i + 1, pointsCount - i);
                points = newPoints;
                i = pointsCount;
                pointsCount+=1;
            }
        }
    }
}
