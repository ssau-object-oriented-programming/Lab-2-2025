package functions;

public class TabulatedFunction {
    private FunctionPoint[] points; 
    private int pointsCount; 

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {

        this.pointsCount = pointsCount;
        this.points = new FunctionPoint[pointsCount];
        
        double step = (rightX - leftX) / (pointsCount - 1);
       
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {

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
            return Double.NaN;
        }

        for (int i = 0; i < pointsCount - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();
            
            if (x >= x1 && x <= x2) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }  
        return 0;
    }

    public int getPointsCount() {
        return pointsCount;
    }
 
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        double newX = point.getX();
        
        if (index == 0) {
            if (newX >= points[1].getX()) {
                return;
            }
        }
        else if (index == pointsCount - 1) {
            if (newX <= points[pointsCount - 2].getX()) {
                return;
            }
        }
        else {
            if (newX <= points[index - 1].getX() || newX >= points[index + 1].getX()) {
                return;
            }
        }
        
        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        return points[index].getX();
    }
    
    public double getPointY(int index) {
        return points[index].getY();
    }
    
    public void setPointX(int index, double x) {
        if (index == 0) {
            if (x >= points[1].getX()) {
                return; 
            }
        }
        else if (index == pointsCount - 1) {
            if (x <= points[pointsCount - 2].getX()) {
                return;
            }
        }
        else {
            if (x <= points[index - 1].getX() || x >= points[index + 1].getX()) {
                return;
            }
        }
        
        points[index].setX(x);
    }

    public void setPointY(int index, double y) {
        if (index == 0) {
            if (y >= points[1].getY()) {
                return; 
            }
        }
        else if (index == pointsCount - 1) {
            if (y <= points[pointsCount - 2].getY()) {
                return;
            }
        }
        else {
            if (y <= points[index - 1].getY() || y >= points[index + 1].getY()) {
                return;
            }
        }
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) {
        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length + points.length / 2 + 1];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        int insertIndex = 0;
        while (insertIndex < pointsCount && point.getX() > points[insertIndex].getX()) {
            insertIndex++;
        }

        if (insertIndex < pointsCount && point.getX() == points[insertIndex].getX()) {
            return;
        }

        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}