package functions;

public class TabulatedFunction
{
    private FunctionPoint[] mas;
    private int count;

    public TabulatedFunction(double leftX, double rightX, int pointsCount){//конструтор с кол ом точек
        count = pointsCount;
        mas = new FunctionPoint[pointsCount];
        double step = (rightX - leftX)/(pointsCount - 1);
        double x = leftX;

        for(int i = 0; i < pointsCount; ++i)
        {
            mas[i] = new FunctionPoint(x, 0);
            x+=step;
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values)//конструктор с массивом значений
    {
        count = values.length;
        mas = new FunctionPoint[values.length];
        double step = (rightX - leftX)/(values.length - 1);
        double x = leftX;

        for(int i = 0; i < values.length; ++i)
        {
            mas[i] = new FunctionPoint(x, values[i]);
            x+=step;
        }
    }

    public double getLeftDomainBorder()//левая граница области определения
    {
        return this.mas[0].getX();
    }

    public double getRightDomainBorder()//правая граница области определения
    {
        return this.mas[count-1].getX();
    }

    public double getFunctionValue(double x) //значение функции в точке
    {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder())
        {
            int left = 0;
            int right = this.count - 1;
            int middle = (left+right)/2;
            while(left<=right)
            {
                middle = (left+right)/2;
                if(this.mas[middle].getX()==x)
                    return mas[middle].getY();
                else
                if(this.mas[middle].getX() < x){
                    left = middle+1;
                }
                else
                    right = middle-1;
            }
            if(mas[middle].getX()<x)
                ++middle;
            double k = (this.mas[middle].getY() - this.mas[middle - 1].getY())/(this.mas[middle].getX() - this.mas[middle - 1].getX());
            double b = this.mas[middle].getY()-k*this.mas[middle].getX();

            return k*x + b;
        }
        else
            return Double.NaN;
    }

    public int getPointsCount()//количество точек
    {
        return this.count;
    }

    public FunctionPoint getPoint(int index)//получение копии точки
    {
        FunctionPoint j = new FunctionPoint(mas[index]);
        return j;
    }

    public void setPoint(int index, FunctionPoint point)//установка точки
    {
        if(point.getX() >= getLeftDomainBorder() && point.getX() <= getRightDomainBorder())
        {
            mas[index].setX(point.getX());
            mas[index].setY(point.getY());
        }
    }
    //геттеры и сеттеры для координат Х и У
    public double getPointX(int index)
    {
        return this.mas[index].getX();
    }

    public  void setPointX(int index, double x)
    {
        if(x >= getLeftDomainBorder() && x <= getRightDomainBorder())
            this.mas[index].setX(x);
    }

    public double getPointY(int index)
    {
        return this.mas[index].getY();
    }

    public void setPointY(int index, double y)
    {
        if(y >= getLeftDomainBorder() && y <= getRightDomainBorder())
            this.mas[index].setY(y);
    }
    public void deletePoint(int index) //удаление точки 
    {
        System.arraycopy(this.mas, index + 1, this.mas, index, this.count - index - 1);
        this.count = this.count - 1;
    }

    public void addPoint(FunctionPoint point) //добавление точки с бинарным посиком позиций
    {
        if(count >= mas.length)
        {
            FunctionPoint arr[]= new FunctionPoint[count+5];
            System.arraycopy(this.mas,0,arr,0,count);
            this.mas = arr;
        }
        int left = 0;
        int right = this.count - 1;
        int middle = (left+right)/2;
        while(left<=right)
        {
            middle = (left+right)/2;
            if(this.mas[middle]==point)
                break;
            else
            if(this.mas[middle].getX() < point.getX()){
                left = middle+1;
            }
            else
                right = middle-1;
        }
        if(mas[middle].getX()<point.getX())
            ++middle;
        System.arraycopy(this.mas, middle,this.mas, middle+1, count- middle);
        this.mas[middle] = point;
        ++count;
    }



}
