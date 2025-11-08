package functions;

public class TabulatedFunction {
    private FunctionPoint[] points; //массив для хранения точек
    private int pointsCount;        //счетчик кол-ва точек
    private static final double EPSILON = 1e-10;
    
    //Конструктор1(точки от leftX до rightX, y=0)
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        //создаем массив
        this.points = new FunctionPoint[pointsCount];
        this.pointsCount = pointsCount;
        
        //вычисляем расстояние между точками
        double move=(rightX - leftX) / (pointsCount - 1);
        
        //создаем точки с равными интервалами
        for (int i = 0;i < pointsCount;i++) {
            double x = leftX + i * move;
            points[i] = new FunctionPoint(x,0.0);
        }
    }

    //Конструктор2(точки от leftX до rightX, y из values)  
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        //создаем массив
        this.points = new FunctionPoint[values.length];
        this.pointsCount = values.length;
        
        //вычисляем расcтояние между точками
        double move = (rightX - leftX) / (pointsCount - 1);
        
        //создаем точки с заданными значениями y
        for (int i = 0; i < pointsCount;i++) {
            double x = leftX + i * move;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }
    
    //МЕТОДЫ
    //метод для левой границы области определения табулированной ф-ии
    public double getLeftDomainBorder() {
        return points[0].getX();
    }
    
    //метод для правой границы области определения табулированной ф-ии
    public double getRightDomainBorder() {
        return points[pointsCount - 1].getX();
    }
    
    //метод для количества точек
    public int getPointsCount() {
        return pointsCount;
    }
    
    //метод для точки по индексу(возвращаем копию)
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }
    
    //замена точки
    public void setPoint(int index, FunctionPoint point) {
        //создаем копию
        FunctionPoint newPoint = new FunctionPoint(point);
        
        //проверка чтобы не ломалася порядок по возрастанию по x
        //если x новой точки <= предыдущей -> не меняется
        if (index > 0 && newPoint.getX() - points[index-1].getX() <= EPSILON) return;
        //если x новой точки >= следуующей -> не меняется
        if (index < pointsCount-1 && points[index+1].getX() - newPoint.getX() <= EPSILON) return;
        
        points[index] = newPoint;
    }
    
    //получить x точки
    public double getPointX(int index) {
        return points[index].getX();
    }
    
    //изменить x точки
    public void setPointX(int index, double x) {
        //проверка порядка по x
        if (index > 0 && x - points[index-1].getX() <= EPSILON) return;
        if (index < pointsCount-1 && points[index+1].getX() - x <= EPSILON) return;
        
        points[index].setX(x);
    }
    
    //получить y точки
    public double getPointY(int index) {
        return points[index].getY();
    }
    
    //изменить y точки
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }
    
        //удалить точку
    public void deletePoint(int index) {
        //сдвигаем точки влево с помощью System.arraycopy()
        if (index < pointsCount - 1) {
            System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        }
        //очищаем последний элемент
        points[pointsCount-1] = null;
        pointsCount--;
    }
    
    //добавить точку
    public void addPoint(FunctionPoint point) {
        //создаем копию точки
        FunctionPoint newPoint = new FunctionPoint(point);
        
        //если массив полный-увеличиваем на 5 ячек
        if (pointsCount == points.length) {
            FunctionPoint[] newArray = new FunctionPoint[points.length + 5];
            //копируем старые точки с помощью System.arraycopy()
            System.arraycopy(points, 0, newArray, 0, pointsCount);
            points = newArray;
        }
        
        //ищем позицию для вставки(сохранем порядок по x)
        int position = pointsCount;
        for (int i = 0; i < pointsCount; i++) {
            if (newPoint.getX() - points[i].getX() < -EPSILON) {
                position = i;
                break;
            }
        }
        
        //сдвигаем точки вправо с помощью System.arraycopy() чтобы освободить место
        if (position < pointsCount) {
            System.arraycopy(points, position, points, position + 1, pointsCount - position);
        }
        
        //вставляем новую точку
        points[position] = newPoint;
        pointsCount++;
    }
    //метод для вычисления значения ф-ии в точке x 
    public double getFunctionValue(double x) {
        //если x за границами-возвращаем NaN
        if (x - getLeftDomainBorder() < -EPSILON || x - getRightDomainBorder() > EPSILON) {
            return Double.NaN;
        }
        
        //поиск отрзка где находится x
        for (int i = 0; i < pointsCount - 1;i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();
            
            //если x попал в тек. отрезок
            if (x - x1 >= -EPSILON && x - x2 <= EPSILON) {
                double y1 = points[i].getY();
                double y2 = points[i + 1].getY();
                
                //лин. интерполяция
                return y1+ (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        
        return Double.NaN;
    }
}