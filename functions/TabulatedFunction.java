package functions;

public class TabulatedFunction {
    private FunctionPoint[] array;
    private int capacity;


    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        array = new FunctionPoint[pointsCount];
        for (int t = 0; t < pointsCount; t++) {
            array[t] = new FunctionPoint();
        }
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int t = 0; t < pointsCount - 1; t++) {
            array[t].setX(leftX + t * step);
        }
        array[pointsCount - 1].setX(rightX);
        capacity = pointsCount;
    }
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        array = new FunctionPoint[values.length];
        for (int t = 0; t < values.length; t++) {
            array[t] = new FunctionPoint();
        }
        double step = (rightX - leftX) / (values.length - 1);
        for (int t = 0; t < values.length - 1; t++) {
            array[t].setX(leftX + t * step);
            array[t].setY(values[t]);
        }
        array[values.length  - 1].setX(rightX);
        array[values.length  - 1].setY(values[values.length  - 1]);
        capacity = values.length;
    }


    public double getLeftDomainBorder() { return array[0].getX();}
    public double getRightDomainBorder() { return array[capacity - 1].getX();}
    public double getFunctionValue(double x) {
        if (x < array[0].getX() || x > array[capacity - 1].getX()) {return Double.NaN;}
        for (int t = 0; t < capacity; t++) {
            if (x == array[t].getX()) {return array[t].getY();}
        }
        int t = 0;
        while (x > array[t].getX()) {
            t++;
        }
        double x0 = array[t - 1].getX();
        double x1 = array[t].getX();
        double y0 = array[t - 1].getY();
        double y1 = array[t].getY();
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }


    public int getPointsCount() {return capacity;}
    public FunctionPoint getPoint(int index) {return new FunctionPoint(array[index]);}
    public void setPoint(int index, FunctionPoint point) {
        if (point.getX() <= array[index - 1].getX() || point.getX() >= array[index + 1].getX()) {
            return;
        }
        array[index] = new FunctionPoint(point);
    }
    public double getPointX(int index) {return array[index].getX();}
    public void setPointX(int index, double x) {
        if (x <= array[index - 1].getX() || x >= array[index + 1].getX()) {
            return;
        }
        array[index].setX(x);
    }
    public double getPointY(int index) {return array[index].getY();}
    public void setPointY(int index, double y) {array[index].setY(y);}


    public void deletePoint(int index) {
        System.arraycopy(array, index + 1, array, index, capacity - index - 1);
        array[capacity - 1] = null;
        capacity--;
    }

    public void addPoint(FunctionPoint point) {
        FunctionPoint copy = new FunctionPoint(point);
        int pose = 0;
        while (pose < capacity && array[pose].getX() < copy.getX()) {
            pose++;
        }
        if (capacity == array.length) {
            FunctionPoint[] temp = new FunctionPoint[capacity+1];
            for (int t = 0; t < capacity+1; t++) {
                temp[t] = new FunctionPoint();
            }
            for (int t = 0; t < capacity; t++) {
                temp[t] = new FunctionPoint(array[t]);
            }
            array = temp;
            capacity++;
        }
        System.arraycopy(array, pose, array, pose + 1, capacity - pose - 1);
        array[pose] = copy;
    }
}
