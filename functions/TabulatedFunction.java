package functions;

public class TabulatedFunction{
	private FunctionPoint[] points;
	private int pointsCount;
	
	public TabulatedFunction(double leftX, double rightX, int pointsCount){
	
		this.pointsCount = pointsCount;
		this.points = new FunctionPoint[pointsCount];
		
		double step = (rightX - leftX) / (pointsCount - 1);
		
		for(int i = 0; i < pointsCount; ++i){
			double x = leftX + i*step;
			points[i] = new FunctionPoint(x, 0.0);
		}
	}
	
	public TabulatedFunction(double leftX, double rightX, double[] values){
	
		this.pointsCount = values.length;
		this.points = new FunctionPoint[pointsCount];
		
		double step = (rightX - leftX) / (pointsCount - 1);
		
		for (int i = 0; i < pointsCount; i++){
			double x = leftX + i * step;
			points[i] = new FunctionPoint(x, values[i]);
		}
	}
	
	public double getLeftDomainBorder(){
		return points[0].getX();
	}
	
	public double getRightDomainBorder(){
		return points[pointsCount - 1].getX();
	}
	
	public double getFunctionValue(double x){
		if (x < getLeftDomainBorder() || x > getRightDomainBorder()){
			return Double.NaN;
		}
		
		for (int i = 0; i < pointsCount; i++){
			
			double x1 = points[i].getX();
			double x2 = points[i + 1].getX();
			
			if (x >= x1 && x <= x2){
				double y1 = points[i].getY();
				double y2 = points[i + 1].getY();
				
				
				return y1 + (y2 - y1) * (x - x1) / (x2 -x1);
			}
		}
		return 0;
	}
	
	public int getPointsCount(){
		return pointsCount;
	}
	
	public FunctionPoint getPoint(int index){
		return new FunctionPoint(points[index]);
	}
	
	public void setFirstPoint(FunctionPoint point){
	
		if(point.getX() < points[1].getX()){
			points[0] = new FunctionPoint(point);
		} else {
			throw new IllegalArgumentException("First point must be left of second point");
		}
	}
	
	public void setLastPoint(FunctionPoint point){
	
		if(point.getX() > points[pointsCount - 2].getX()){
			points[pointsCount - 1] = new FunctionPoint(point);
		} else {
			throw new IllegalArgumentException(" Lust point must be left of previous point");
		}
	}
	
	public void setMiddlePoint(int index,  FunctionPoint point){
	
		double x1 = points[index - 1].getX();
		double x2 = points[index + 1].getX();
		double x = point.getX();
			
			if( x1 < x && x < x2){
				points[index] = new FunctionPoint(point);
			} else {
				throw new IllegalArgumentException(" Middle point must be betwen " + x1 + " and " + x2);
			}
	}

	
	public void setPoint(int index, FunctionPoint point){
	
		if(index == 0){
			setFirstPoint(point);
		}
		
		else if(index == pointsCount - 1){
			setLastPoint(point);
		}
		
		else{
			setMiddlePoint(index, point);
		}
	}
	
	public double getPointX(int index){
		if(0 <= index && index <= pointsCount - 1){
			return points[index].getX();
		} else {
			throw new IllegalArgumentException(" Point must be betwen " + 0 + " and lust element");
		}
	}
	
	public void setFirstPointX(double x){
		if(x < points[1].getX()){
			points[0].setX(x);
		} else {
			throw new IllegalArgumentException("First point must be left of second point");
		}
	}
	
	public void setLustPointX(double x){
		if(x > points[pointsCount - 2].getX()){
			points[pointsCount - 1].setX(x);
		} else {
			throw new IllegalArgumentException(" Lust point must be left of previous point");
		}
	}
	
	public void setMiddlePointX(int index, double x){
		if(x > points[index - 1].getX() && x < points[index + 1].getX()){
			points[index].setX(x);
		}
	}
	
	public void setPointX(int index, double x){
		if(index == 0){
			setFirstPointX(x);
		} else if(index == pointsCount - 1){
			setLustPointX(x);
		} else{
			setMiddlePointX(index, x);
		}
	}
	

	public double getPointY(int index){
		if(0 <= index && index <= pointsCount - 1){
			return points[index].getY();
		} else {
			throw new IllegalArgumentException(" Point must be betwen " + 0 + " and lust element");
		}
	}
	
	public void setPointY(int index, double y){
		if(0 <= index && index <= pointsCount - 1){
			points[index].setY(y);
		} else {
			throw new IllegalArgumentException(" Point must be betwen " + 0 + " and lust element");
		}
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

	public void deletePoint(int index){
		System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
		pointsCount--;
	}

}
