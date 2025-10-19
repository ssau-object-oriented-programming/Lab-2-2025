package functions;

class TabulatedFunction
{
    private FunctionPoint[] points;

    // констурктор для границ координат и количества точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        if (pointsCount < 2)
        {
            throw new IllegalArgumentException("pointsCount must be at least 2");
        }

        if(leftX >= rightX)
        {
            throw new IllegalArgumentException("leftX must be less than rightX");
        }

        this.points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);

        for(int i = 0; i < pointsCount; i++)
        {
            this.points[i] = new FunctionPoint(leftX + step * i, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values)
    {
        if (values.length < 2)
        {
            throw new IllegalArgumentException("pointsCount must be at least 2");
        }

        if(leftX >= rightX)
        {
            throw new IllegalArgumentException("leftX must be less than rightX");
        }

        this.points = new FunctionPoint[values.length];
        double step = (rightX - leftX) / (values.length - 1);

        for(int i = 0; i < values.length; i++)
        {
            this.points[i] = new FunctionPoint(leftX + step * i, values[i]);
        }
    }

    public double getLeftDomainBorder()
    {
        return this.points[0].getX();
    }

    public double getRightDomainBorder()
    {
        return this.points[this.points.length - 1].getX();
    }

    public double getFunctionValue(double x)
    {
        if(x < this.points[0].getX() || x > this.points[points.length - 1].getX())
        {
            return Double.NaN;
        }

        for(int i = 0; i < this.points.length - 1; i++)
        {
            double x1 = this.points[i].getX();
            double x2 = this.points[i + 1].getX();

            if(x == x1)
            {
                return this.points[i].getY();
            }

            if(x == x2)
            {
                return  this.points[i + 1].getY();
            }

            if(x > x1 && x < x2)
            {
                double y1 = this.points[i].getY();
                double y2 = this.points[i + 1].getY();

                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN;
    }

    public int pointCount()
    {
        return this.points.length;
    }

    public FunctionPoint getPoint(int index)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        if(index >= this.points.length)
        {
            throw new IndexOutOfBoundsException("Index exceed maximum index");
        }

        return new FunctionPoint(this.points[index]);
    }

    public void setPoint(int index, FunctionPoint point)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        if(index >= this.points.length)
        {
            throw new IndexOutOfBoundsException("Index exceed maximum index");
        }

        if (index == 0)
        {
            if (points.length > 1 && point.getX() >= points[1].getX())
            {
                throw new IllegalArgumentException("First point x must be less than next point x");
            }
        }
        else if (index == points.length - 1)
        {
            if (point.getX() <= points[index - 1].getX())
            {
                throw new IllegalArgumentException("Last point x must be greater than previous point x");
            }
        }
        else
        {
            if (point.getX() <= points[index - 1].getX() || point.getX() >= points[index + 1].getX())
            {
                throw new IllegalArgumentException("Point x must be between neighbors");
            }
        }

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        if(index >= this.points.length)
        {
            throw new IndexOutOfBoundsException("Index exceed maximum index");
        }

        return this.points[index].getX();
    }

    public double getPointY(int index)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        if(index >= this.points.length)
        {
            throw new IndexOutOfBoundsException("Index exceed maximum index");
        }

        return this.points[index].getX();
    }

    public void setPointX(int index, double x)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        if(index >= this.points.length)
        {
            throw new IndexOutOfBoundsException("Index exceed maximum index");
        }

        this.setPoint(index, new FunctionPoint(x, this.points[index].getY()));
    }

    public void setPointY(int index, double y)
    {
        if(index < 0)
        {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        if(index >= this.points.length)
        {
            throw new IndexOutOfBoundsException("Index exceed maximum index");
        }

        this.setPoint(index, new FunctionPoint(this.points[index].getX(), y));
    }
}