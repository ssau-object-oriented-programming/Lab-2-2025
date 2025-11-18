package functions;

public class TabulatedFunction{
    FunctionPoint array[];
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        this.pointsCount = pointsCount;
        this.array = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i<pointsCount; i++) {
            double x = leftX + step*i;
            array[i] = new FunctionPoint(x,0.0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.pointsCount = values.length;
        this.array = new FunctionPoint[values.length];
        double step = (rightX - leftX) / (values.length - 1);

        for (int i = 0; i < values.length; i++) {
            double x = leftX + step * i;
            array[i] = new FunctionPoint(x, 0.0); // ← y всегда 0.0!
        }
    }

    // методы
    public double getLeftDomainBorder(){
        return array[0].getX();
    }
    public double getRightDomainBorder(){
        return array[pointsCount-1].getX();
    }
    public double getFunctionValue(double x) {
        if (x< array[0].getX() || x > array[pointsCount - 1].getX()) {
            return Double.NaN;
        }

        for (int i = 0; i< pointsCount - 1; i++) {
            double x1 = array[i].getX();
            double x2 = array[i + 1].getX();

            if (x >= x1 && x <= x2) {
                if (Math.abs(x - x1) < 1e-10) {
                    return array[i].getY();
                }
                if (Math.abs(x - x2) < 1e-10) {
                    return array[i + 1].getY();
                }

                double slope = (array[i + 1].getY() - array[i].getY()) /
                        (array[i + 1].getX() - array[i].getX());
                return array[i].getY() + slope * (x - array[i].getX());
            }
        }
        return Double.NaN;
    }

    public int getPointsCount(){
        return pointsCount;
    }

    public FunctionPoint getPoint(int index){
        return new FunctionPoint(array[index]);
    }
    public void setPoint(int index, FunctionPoint point) {
        double newX = point.getX();
        if (index>0 && newX <=array[index-1].getX()) return;
        if (index<pointsCount-1 && newX>=array[index+1].getX()) return;

        array[index] = new FunctionPoint(point);
    }

    public void setPointX(int index, double x) {
        if (index>0 && x <= array[index-1].getX()) return;
        if (index<pointsCount-1 && x>=array[index+1].getX()) return;

        array[index].setX(x);
    }
    public void setPointY(int index, double y) {
        array[index].setY(y);
    }
    public double getPointX(int index){
        return array[index].getX();
    }
    public double getPointY(int index) {
        return array[index].getY();
    }

    public void deletePoint(int index){

        System.arraycopy(array, index+1, array, index, pointsCount-index-1);
        pointsCount--;
        array[pointsCount] = null;
    }

    public void addPoint(FunctionPoint point){
        FunctionPoint newPoint = new FunctionPoint(point);
        int newIndex = 0;
        while (newIndex<pointsCount && array[newIndex].getX()< newPoint.getX()){
            newIndex++;
        }
        if (newIndex< pointsCount && array[newIndex].getX() == newPoint.getX()){
            array[newIndex] = newPoint;
            return;
        }
        if (pointsCount >= array.length) {
            FunctionPoint[] newArray = new FunctionPoint[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, pointsCount);
            array = newArray;
        }
        for (int i = pointsCount; i>newIndex; i--) {
            array[i] = array[i - 1];
        }
        array[newIndex] = newPoint;
        pointsCount++;
    }
}
