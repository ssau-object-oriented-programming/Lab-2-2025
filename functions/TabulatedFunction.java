package functions;

public class TabulatedFunction {

    private FunctionPoint[] points_arr;
    private int pointslength;

    public static boolean compareDouble(double a, double b) {
        final double epsilon = 1e-10;
        double diff = a - b;

        if (diff < -epsilon && diff > epsilon) {
            return false;  
        }
        else {
            return true;   
        }
    }
    
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points_arr = new FunctionPoint[pointsCount];
        pointslength = pointsCount;

        // Вычисляем длину интервала с гарантией положительного значения
        double intervalLength;
        if (rightX >= leftX) {
            intervalLength = (rightX - leftX) / (pointsCount - 1);
        } else {
            intervalLength = (leftX - rightX) / (pointsCount - 1);
        }

        
        for (int i = 0; i < pointsCount; i++) {
            points_arr[i] = new FunctionPoint(leftX + intervalLength * i, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] points) {
        pointslength = points.length;
        points_arr = new FunctionPoint[points.length];
        double intervalLength;
        if (rightX >= leftX) {
            intervalLength = (rightX - leftX) / (points.length - 1);
        } else {
            intervalLength = (leftX - rightX) / (points.length - 1);
        }

        if (pointslength != 0) {
            for (int i = 0; i < points.length; i++) {
                points_arr[i] = new FunctionPoint(leftX + intervalLength * i, points[i]);
            }
        }
    }

    public double getLeftDomainBorder() {
        return points_arr[0].getX();
    }

    public double getRightDomainBorder(){
        return points_arr[pointslength-1].getX();
    }
    public double getFunctionValue(double x){
        double y;
        int findex=0, sindex=0;

        if(x < getLeftDomainBorder() || x > getRightDomainBorder()){return Double.NaN;}
        if(compareDouble(x,getLeftDomainBorder())){return points_arr[0].getY();}
        if(compareDouble(x,getRightDomainBorder())){return points_arr[pointslength-1].getY();}

        for(int i = 0; i < pointslength-1; i++){
            if(points_arr[i].getX() <= x && points_arr[i + 1].getX() >= x){
            findex = i;
            sindex = i + 1;
            i = pointslength-1;
            }
        }
        return ((points_arr[findex].getY()) + (points_arr[sindex].getY() - points_arr[findex].getY()) * (x - points_arr[findex].getX()) / (points_arr[sindex].getX() - points_arr[findex].getX()));
    }

    public int getPointsCount(){
        return pointslength;
    }

    public FunctionPoint getPoint(int index){
        FunctionPoint point = new FunctionPoint(points_arr[index]);
        return point;
    }

    public void setPoint(int index, FunctionPoint point){
        //point = getPoint(index);
        if ((index == 0 || point.getX() > points_arr[index - 1].getX()) &&
                (index == pointslength - 1 || point.getX() < points_arr[index + 1].getX())) {
            points_arr[index] = new FunctionPoint(point);
        };
    }
    public double getPointX(int index){
        return points_arr[index].getX();
    }

    public void setPointX(int index, double x){
        if(x >= this.getLeftDomainBorder() && x <= this.getRightDomainBorder()){
            points_arr[index] = new FunctionPoint(x, points_arr[index].getY());
        }
    }

    public double getPointY(int index){
        return points_arr[index].getY();
    }
    public void setPointY(int index, double y){
        points_arr[index] = new FunctionPoint(points_arr[index].getX(), y);
    }

    public void deletePoint(int index){
        if(index >= 0 && index < pointslength){
            points_arr[index] = null;
            for(int i = index; i < pointslength-1; i++){
            points_arr[i] = points_arr[i+1];
            }
            points_arr[pointslength-1] = null;
            pointslength--;
        }

    }
    public void addPoint(FunctionPoint point){
        int indx = 0;
        if(point.getX() > points_arr[pointslength-1].getX()){
        indx = pointslength;
        }
        for(int i = 0; i < pointslength-1; i++){
            if(point.getX() >=points_arr[i].getX()&& point.getX() <= points_arr[i+1].getX()){
                indx = i+1;
                i=pointslength-1;
            }
        }
        FunctionPoint[] temp_arr = new FunctionPoint[pointslength+1];
        System.arraycopy(points_arr, 0, temp_arr, 0, indx);
        temp_arr[indx] = point;
        System.arraycopy(points_arr, indx, temp_arr, indx+1, pointslength-indx);
        points_arr = temp_arr;
        pointslength++;
    }
}
