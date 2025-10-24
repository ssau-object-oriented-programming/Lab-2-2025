package functions;


public class TabulatedFunction {
    private FunctionPoint[] points; //массив точек
 
    // Конструктор 1:
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
     
     this.points = new FunctionPoint[pointsCount];
     double step = (rightX - leftX) / (pointsCount - 1); //вычисляем шаг между точками
     
     // Заполняем массив точками с координатой x и с y=0
     for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }

  // Конструктор 2:
  public TabulatedFunction(double leftX, double rightX, double[] values) {
     this.points = new FunctionPoint[values.length];
     double step = (rightX - leftX) / (values.length - 1);
     
     //заполняем массив заданным значением y
     for (int i = 0; i < values.length; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

//Области определения
  public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }

//Вычисляем значение функции
  public double getFunctionValue(double x) {
        
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        
        for (int i = 0; i < points.length - 1; i++) {
            double x1 = points[i].getX();
            double x2 = points[i + 1].getX();
            double y1 = points[i].getY();
            double y2 = points[i + 1].getY();
            
            
            if (x == x1) {
                return y1;
            }
            if (x == x2) {
                return y2;
            }
            
            
            if (x > x1 && x < x2) {
                return y1 + (y2 - y1) / (x2 - x1) * (x - x1);
            }
        }
        
       
        return points[points.length - 1].getY();
    }
 // Количество точек
  public int getPointsCount() {
        return points.length;
    }
  
  public FunctionPoint getPoint(int index){
        return new FunctionPoint(points[index]);
    }

//Копируем точку 
  public void setPoint(int index, FunctionPoint point){
        
        if (index > 0 && point.getX() <= points[index - 1].getX()){
            return;
        }
        if (index < points.length - 1 && point.getX() >= points[index + 1].getX()){
            return;
        }
        points[index] = new FunctionPoint(point);
    }

  public double getPointX(int index){
        return points[index].getX();
    }
  
  public void setPointX(int index, double x){
        if (index > 0 && x <= points[index - 1].getX()){
            return;
        }
        if (index < points.length - 1 && x >= points[index + 1].getX()){
            return;
        }
        points[index].setX(x);
    }
 
  public double getPointY(int index){
        return points[index].getY();
    }

   
  public void setPointY(int index, double y){
        points[index].setY(y);
    }

// Удаление точки
  public void deletePoint(int index){
	if (points.length <= 2) { // Нельзя удалить, если точек меньше 3
		return;
	}
        FunctionPoint[] newPoints = new FunctionPoint[points.length - 1]; 
        System.arraycopy(points, 0, newPoints, 0, index); 
        System.arraycopy(points, index + 1, newPoints, index, points.length - index - 1);
	points = newPoints;
    }

// Добавление точки
  public void addPoint(FunctionPoint point) {
        FunctionPoint[] newPoints = new FunctionPoint[points.length+ 1];
        int i= 0;
        while (i< points.length && point.getX() > points[i].getX()) {
            i++;
        }
	// Если точка с таким X уже существует - выходим
	if (i < points.length && point.getX() == points[i].getX()){
            return;
        }
	// Копируем старый массив, вставляя новую точку в нужную позицию
	System.arraycopy(points, 0, newPoints, 0, i);
        newPoints[i] = new FunctionPoint(point);
        System.arraycopy(points, i, newPoints, i + 1, points.length-i);
        
        points = newPoints;
   }
	
  
  
  
}
