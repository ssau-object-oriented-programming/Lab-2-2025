// functions/TabulatedFunction.java
package functions;

public class TabulatedFunction {

    private FunctionPoint[] points;  // массив-буфер
    private int pointsCount;         // фактическое число точек

    //3.1  leftX-rightX + количество точек (y = 0)
    public TabulatedFunction(double leftX, double rightX, int count) {
        if(count<2||leftX>=rightX) throw new IllegalArgumentException();
        points=new FunctionPoint[count];   // излишний запас не нужен – пока 1:1
        pointsCount=count;

        double step=(rightX-leftX)/(count-1);
        for(int i=0;i<count;i++)
            points[i]=new FunctionPoint(leftX+i*step,0);
    }

    //3.2  leftX-rightX + массив значений функции
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        if(values==null||values.length<2||leftX>=rightX)
            throw new IllegalArgumentException();
        points=new FunctionPoint[values.length];
        pointsCount=values.length;

        double step=(rightX-leftX)/(values.length-1);
        for(int i=0;i<values.length;i++)
            points[i]=new FunctionPoint(leftX+i*step,values[i]);
    }

    //4  (границы области + значение функции в x через линейную интерполяцию)
    public double getLeftDomainBorder() {return points[0].getX();}
    public double getRightDomainBorder() {return points[pointsCount-1].getX();}

    public double getFunctionValue(double x) {

        if(x<getLeftDomainBorder()||x>getRightDomainBorder())
            return Double.NaN;

        // точное совпадение с узлом
        for(int i=0;i<pointsCount;i++)
            if(x==points[i].getX()) return points[i].getY();

        // поиск интервала [xi , xi+1]
        int i=0;
        while(i<pointsCount-2&&x>points[i+1].getX()) i++;

        double x1=points[i].getX(),y1=points[i].getY();
        double x2=points[i+1].getX(),y2=points[i+1].getY();

        // линейная интерполяция
        return y1+(y2-y1)*(x-x1)/(x2-x1);
    }

    //5  (работа с отдельными точками)
    public int getPointsCount() {return pointsCount;}

    public FunctionPoint getPoint(int index) {
        checkIndex(index);
        return new FunctionPoint(points[index]);            // копия!
    }

    public void setPoint(int index, FunctionPoint p) {
        checkIndex(index);
        double newX=p.getX();
        if(
			(index>0&&newX<=points[index-1].getX())||
			(index<pointsCount-1&&newX>=points[index+1].getX())
		)
            return;                                         // нарушит порядок
        points[index]=new FunctionPoint(p);               // копия!
    }

    public double getPointX(int i) {checkIndex(i);return points[i].getX();}
    public double getPointY(int i) {checkIndex(i);return points[i].getY();}

    public void setPointX(int i, double x) {
        checkIndex(i);
        if( //проверка порядка
			(i>0&&x<=points[i-1].getX())|| //x не больше предыдущей
			(i<pointsCount-1&&x>=points[i+1].getX()) //x не меньше следующей
		)
            return;
        points[i].setX(x);
    }

    public void setPointY(int i, double y) {checkIndex(i);points[i].setY(y);}

    //6  (изменение количества точек: удаление + добавление)
    public void deletePoint(int index) {
        if(pointsCount<=2) return;           // не позволяем < 2 точек
        checkIndex(index);

        for(int i=index;i<pointsCount-1;i++)
            points[i]=points[i+1];

        points[--pointsCount]=null;           // помощь GC
    }

    public void addPoint(FunctionPoint p) {

        // запрет совпадающих X
        for(int i=0;i<pointsCount;i++)
            if(p.getX()==points[i].getX()) return;

        // расширяем массив при необходимости
        if(pointsCount==points.length) {
            int newLen=points.length*2;
            if(newLen==0) newLen=4;
			FunctionPoint[] tmp=new FunctionPoint[newLen];
			for(int i=0;i<pointsCount;i++) tmp[i]=points[i];
			points=tmp;
        }

        // позиция вставки для сохранения сортировки 
        int pos=0;
        while(pos<pointsCount&&points[pos].getX()<p.getX()) pos++;

        for(int i=pointsCount;i>pos;i--)
            points[i]=points[i-1];

        points[pos]=new FunctionPoint(p);
        pointsCount++;
    }

    //  ДОП
    private void checkIndex(int i) {
        if(i<0||i>=pointsCount) throw new IndexOutOfBoundsException();
    }
}