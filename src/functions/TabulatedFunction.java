package functions;


public class TabulatedFunction {
    private FunctionPoint[] points;
    private int size;

    // Конструкторы
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        points = new FunctionPoint[pointsCount + 10];
        size = pointsCount;
        if (leftX > rightX){
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }
        double step = (rightX - leftX)/(size - 1);
        for (int i = 0;i < size;i++){
            points[i] = new FunctionPoint(leftX + i*step,0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        points = new FunctionPoint[values.length + 5];
        size = values.length;
        if(leftX > rightX){
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }
        double step = (rightX - leftX)/ (size -1);
        for(int i = 0; i < size;i++){
            points[i] = new FunctionPoint(leftX + i*step,values[i]);
        }
    }

    // Метод, возвращающий значение левой границы
    public double getLeftDomainBorder(){
        if (size ==0){
            return Double.NaN;
        }
        return points[0].getX();
    }

    // Метод, возвращающий значение правой границы
    public double getRightDomainBorder(){
        if (size ==0){
            return Double.NaN;
        }
        return points[size -1 ].getX();
    }

    // Метод, возвращающий значение функции в точке x
    public double getFunctionValue(double x){
        if ((x < getLeftDomainBorder() || x > getRightDomainBorder()) || (size ==0)){
            return Double.NaN;
        }
        for (int i = 0; i < size - 1; i++) {
            if (x >= points[i].getX() && x <= points[i + 1].getX()) {

                double x1 = points[i].getX();
                double y1 = points[i].getY();
                double x2 = points[i + 1].getX();
                double y2 = points[i + 1].getY();


                return (y1 + (x - x1) * (y2 - y1) / (x2 - x1));
            }
        }
        return Double.NaN;
    }

    // Метод, возвращающий количество точек
    public int getPointsCount(){
        return size;
    }

    // Метод, возвращающий длину массива с учетом запаса
    public int getRealLength() {

        return points.length;
    }
     public FunctionPoint getPoint(int index){
        if (index >= 0 && index < size) {
            return new FunctionPoint(points[index]);
        }
        else{
            return null;
        }
    }

    // Метод, заменяющий точку на заданную
    public void setPoint(int index, FunctionPoint point){
        if (index < 0 || index >= size)
            return;
        double newX = point.getX();
        if ((index > 0 && newX <= points[index - 1].getX()) || (index < size -1  && newX >=points[index +1].getX())) {
            return;
        }
        points[index] = new FunctionPoint(point);

    }

    // Метод, возвращающий значение абсциссы точки с указанным номером
    public double getPointX(int index){
        if (index < 0 || index >= size){
            return Double.NaN;
        }

        return points[index].getX();
    }

    // Метод, изменяющий значение абсциссы точки с указанным номером
    public void setPointX(int index,double x){
        if (index < 0 || index >= size){
            return;
        }
        if ((index > 0 && x <= points[index - 1].getX()) || (index < size-1 && x >=points[index +1].getX())) {
            return;
        }
        points[index] = new FunctionPoint(x,points[index].getY());
    }

    // Метод, возвращающий значение ординаты точки с указанным номером
    public double getPointY(int index ){
        if (index < 0 || index >= size){
            return Double.NaN;
        }
        return points[index].getY();
    }

    // Метод, изменяющий значение ординаты точки с указанным номером
    public void setPointY(int index, double y){
        if (index < 0 || index >= size){
            return;
        }
        points[index] = new FunctionPoint(points[index].getX(),y);
    }

    // Удаление точки с указанным номером
    public void deletePoint(int index){
        if (index >= 0 && index < size){
            --size;
            System.arraycopy(points,index+1, points, index,size - index);
        }
    }

    // добавление точки
    public void addPoint(FunctionPoint point){
        double x = point.getX();

        // если за пределами левой границы
        if (x < getLeftDomainBorder()){
            // если запас длины  в массиве еще есть
            if (size < points.length){
                System.arraycopy(points, 0, points, 1,size);
                points[0] = point;
                ++size;
            }
            else {
                FunctionPoint[] newPoints = new FunctionPoint[size + 5];
                System.arraycopy(points, 0, newPoints, 1, points.length);
                newPoints[0] = point;
                points = newPoints;
                ++size;
            }
        }
        // если за пределами правой границы
        else if (x > getRightDomainBorder()){
            if (size < points.length){
                points[size] = point;
                ++size;
            }
            else{
                FunctionPoint[] newPoints = new FunctionPoint[size + 5];
                System.arraycopy(points, 0, newPoints,0,points.length);
                newPoints[size] = point;
                points = newPoints;
                ++size;
            }
        }
        else{
            if (size < points.length){
                int index = 0;
                while(index < size && x > points[index].getX()){
                    ++index;
                }
                System.arraycopy(points, index,points,index + 1,size -index);
                points[index] = point;
                ++size;
            }
            else{
                FunctionPoint[] newPoints = new FunctionPoint[size + 5];
                int index = 0;
                while(index < size && x > points[index].getX()){
                    ++index;
                }
                System.arraycopy(points,index,newPoints,index + 1, size - index);
                newPoints[index] = point;
                points = newPoints;
                ++size;
            }
        }

    }


}
