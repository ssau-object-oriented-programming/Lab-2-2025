package functions;

public class TabulatedFunction {
    private FunctionPoint[] pointValue;
    private int sizeValue;

    public TabulatedFunction(double leftX, double rightX, int pointCount) {
        if (pointCount <= 0) {
            throw new IllegalArgumentException("Количество точек должно быть > 0");
        }
        sizeValue = pointCount;
        pointValue = new FunctionPoint[2 * sizeValue];

        if (pointCount == 1) {
            pointValue[0] = new FunctionPoint(leftX, 0);
        } else {
            double step = (rightX - leftX) / (sizeValue - 1);
            for (int i = 0; i < sizeValue; i++) {
                // Гарантируем, что последняя точка = rightX
                double x = (i == sizeValue - 1) ? rightX : leftX + step * i;
                pointValue[i] = new FunctionPoint(x, 0);
            }
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] value) {
        if (value == null || value.length <= 0) {
            throw new IllegalArgumentException("Массив значений не должен быть пустым");
        }
        sizeValue = value.length;
        pointValue = new FunctionPoint[2 * sizeValue];

        if (sizeValue == 1) {
            pointValue[0] = new FunctionPoint(leftX, value[0]);
        } else {
            double step = (rightX - leftX) / (sizeValue - 1);
            for (int i = 0; i < sizeValue; i++) {
                double x = (i == sizeValue - 1) ? rightX : leftX + step * i;
                pointValue[i] = new FunctionPoint(x, value[i]);
            }
        }
    }

    public double getLeftDomainBorder(){
        return pointValue[0].getX();
    }

    public double getRightDomainBorder(){
        return pointValue[sizeValue - 1].getX();
    }

    public double getFunctionValue(double x) {
        // Проверка на выход за границы
        if (x < pointValue[0].getX() || x > pointValue[sizeValue - 1].getX()) {
            return Double.NaN;
        }

        // Проверка на точное совпадение с существующей точкой
        for (int i = 0; i < sizeValue; i++) {
            if (pointValue[i].getX() == x) {
                return pointValue[i].getY();
            }
        }

        // Поиск интервала для интерполяции
        int index = 0;
        while (index < sizeValue && pointValue[index].getX() < x) {
            index++;
        }

        // Теперь index >= 1 гарантированно
        double x1 = pointValue[index - 1].getX();
        double y1 = pointValue[index - 1].getY();
        double x2 = pointValue[index].getX();
        double y2 = pointValue[index].getY();

        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    public int getPointCount(){
        return sizeValue;
    }

    public FunctionPoint getPoint(int index){
        return new FunctionPoint(pointValue[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        if (sizeValue == 1) {
            // При одной точке нет соседей — можно менять свободно
            pointValue[0] = new FunctionPoint(point);
            return;
        }

        // Для средних точек: X должен быть между соседями
        if ((index != 0) && (index != sizeValue - 1)) {
            if ((pointValue[index - 1].getX() < point.getX()) && (point.getX() < pointValue[index + 1].getX())) {
                pointValue[index] = new FunctionPoint(point);
            }
        }
        // Для первой точки: X должен быть меньше следующей
        else if ((index == 0) && (point.getX() < pointValue[1].getX())) {
            pointValue[index] = new FunctionPoint(point);
        }
        // Для последней точки: X должен быть больше предыдущей
        else if ((index == sizeValue - 1) && (pointValue[index - 1].getX() < point.getX())) {
            pointValue[index] = new FunctionPoint(point);
        }
        // В остальных случаях — ничего не делаем
    }

    public double getPointX(int index){
        return pointValue[index].getX();
    }

    public double getPointY(int index){
        return pointValue[index].getY();
    }

    public void setPointX(int index, double x){setPoint(index, new FunctionPoint(x, pointValue[index].getY()));
    }

    public void setPointY(int index, double y){
        pointValue[index].setY(y);
    }

    public void deletePoint(int index){
        pointValue[index] = null;
        for (int i = index; i < sizeValue-1; i++){
            pointValue[i] = pointValue[i+1];
        }
        --sizeValue;
    }

    public void addPoint(FunctionPoint point){
        if(pointValue.length < sizeValue + 1){
            FunctionPoint[] newPointValue = new FunctionPoint[sizeValue + (sizeValue / 2)];
            System.arraycopy(pointValue, 0, newPointValue, 0, pointValue.length);
            pointValue = null;
            pointValue = newPointValue;
            newPointValue = null;
        }
        int index;

        for (index = 0; index < sizeValue && pointValue[index].getX() < point.getX(); index++);

        if(index != sizeValue && pointValue[index].getX() == point.getX()){
            System.out.println("This 'x' value already exists, set is used");
            setPoint(index, point);
        }
        else {
            System.arraycopy(pointValue, index, pointValue, index+1, sizeValue-index);
            pointValue[index] = new FunctionPoint(point);
            ++sizeValue;
        }
    }


    public void printTabFun(){
        for(int i = 0; i < sizeValue; i++){
            System.out.println("№" + (i+1) + " x:" + pointValue[i].getX() + " y:" + pointValue[i].getY());
        }
    }
}