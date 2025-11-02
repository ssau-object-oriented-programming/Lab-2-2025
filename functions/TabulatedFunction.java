package functions;

public class TabulatedFunction
{
    private FunctionPoint[] points;
    private int amountOfElements;
    private static final double EPSILON = 1e-10;

    // констурктор для границ координат и количества точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        this.points = new FunctionPoint[pointsCount];
        this.amountOfElements = pointsCount;

        double step = (rightX - leftX) / (pointsCount - 1);

        for(int i = 0; i < pointsCount; i++)
        {
            this.points[i] = new FunctionPoint(leftX + step * i, 0);
        }
    }

    // конструктор с граничными значениями Х и массивом значений в точках
    public TabulatedFunction(double leftX, double rightX, double[] values)
    {
        this.points = new FunctionPoint[values.length];
        this.amountOfElements = values.length;

        double step = (rightX - leftX) / (values.length - 1);

        for(int i = 0; i < values.length; i++)
        {
            this.points[i] = new FunctionPoint(leftX + step * i, values[i]);
        }
    }

    // геттер левой границы по Х
    public double getLeftDomainBorder()
    {
        return this.points[0].getX();
    }

    // геттер правой границы по Х
    public double getRightDomainBorder()
    {
        return (this.amountOfElements == 0) ? Double.NaN : this.points[this.amountOfElements - 1].getX();
    }


    // получение значения функции по заданной координате
    public double getFunctionValue(double x)
    {
        if(this.amountOfElements == 0) return Double.NaN;

        // вывод в случае выхода за границы
        if(x < this.points[0].getX() || x > this.points[amountOfElements - 1].getX())
        {
            return Double.NaN;
        }

        // пробегаемся по всем значениями Х функции, пока не найдем необходимый
        // либо ищем примерное смежное значение
        for(int i = 0; i < this.amountOfElements - 1; i++)
        {
            double x1 = this.points[i].getX();
            double x2 = this.points[i + 1].getX();

            // сравниваем с машинным эпсилоном для корректного результата
            if(Math.abs(x - x1) < EPSILON) return this.points[i].getY();
            if(Math.abs(x - x2) < EPSILON) return this.points[i + 1].getY();

            if(x > x1 && x < x2)
            {
                double y1 = this.points[i].getY();
                double y2 = this.points[i + 1].getY();
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }
        return Double.NaN;
    }

    // получение количества точек в функции
    public int getPointsCount()
    {
        return this.amountOfElements; // возвращаем реальное количество
    }

    // получаем точку по индексу
    public FunctionPoint getPoint(int index)
    {
        if(index < 0 || index >= this.amountOfElements)
        {
            return null;
        }

        return new FunctionPoint(this.points[index]);
    }

    // замена точки по заданному индексу
    public void setPoint(int index, FunctionPoint point)
    {
        if(index < 0 || index >= this.amountOfElements) return;

        // использование приближенного сравнения для граничных условий
        if (index == 0 && this.amountOfElements > 1 &&
                point.getX() >= points[1].getX() - EPSILON) return;

        if (index == this.amountOfElements - 1 &&
                point.getX() <= points[index - 1].getX() + EPSILON) return;

        if (index > 0 && index < this.amountOfElements - 1 &&
                (point.getX() <= points[index - 1].getX() + EPSILON ||
                        point.getX() >= points[index + 1].getX() - EPSILON))
        {
            return;
        }

        points[index] = new FunctionPoint(point);
    }

    // получение координаты Х точки по индексу
    public double getPointX(int index)
    {
        if(index < 0 || index >= amountOfElements)
        {
            return Double.NaN;
        }

        return this.points[index].getX();
    }

    // получение координаты У точки по индексу
    public double getPointY(int index)
    {
        if(index < 0 || index >= amountOfElements)
        {
            return Double.NaN;
        }

        return this.points[index].getY();
    }

    // замена значения Х в точке с заданным индексом
    public void setPointX(int index, double x)
    {
        if(index < 0 || index >= this.amountOfElements) return;

        // проверки из setPoint() адаптированные для setPointX()(для избежания избыточности кода)
        if (index == 0 && this.amountOfElements > 1 && x >= points[1].getX() - EPSILON) return;

        if (index == this.amountOfElements - 1 && x <= points[index - 1].getX() + EPSILON) return;

        if (index > 0 && index < this.amountOfElements - 1 &&
                (x <= points[index - 1].getX() + EPSILON || x >= points[index + 1].getX() - EPSILON))
        {
            return;
        }

        // если все проверки пройдены - меняем X
        this.points[index].setX(x);
    }

    // замена значения Y в точке с заданным индексом
    public void setPointY(int index, double y)
    {
        if(index < 0 || index >= this.amountOfElements) return;

        this.points[index].setY(y);
    }

    // удаление точки
    public void deletePoint(int index)
    {
        if(index < 0 || index >= this.amountOfElements) return;

        // сдвигаем элементы влево
        for(int i = index; i < this.amountOfElements - 1; i++)
        {
            this.points[i] = this.points[i + 1];
        }

        this.points[this.amountOfElements - 1] = null; // очищаем последнюю ссылку
        this.amountOfElements--; // уменьшаем счетчик
    }

    // добавление точки с автоматическим выбором места для подстановки
    public void addPoint(FunctionPoint point)
    {
        double pointX = point.getX();
        int insertIndex = 0;

        // находим позицию для вставки
        for(; insertIndex < this.amountOfElements; insertIndex++)
        {
            // сравниваем с машинным эпсилоном для избежания погрешности представления
            if(Math.abs(this.points[insertIndex].getX() - pointX) < EPSILON)
            {
                return; // точка с таким X уже существует
            }

            if(this.points[insertIndex].getX() > pointX)
            {
                break;
            }
        }

        // проверяем, нужно ли расширять массив
        if (this.amountOfElements == this.points.length)
        {
            // умножаем на 2 для оптимизации
            FunctionPoint[] newArray = new FunctionPoint[this.points.length * 2 + 1];
            System.arraycopy(this.points, 0, newArray, 0, this.amountOfElements);

            this.points = newArray;
        }

        // сдвигаем элементы вправо для освобождения места
        for (int i = this.amountOfElements; i > insertIndex; i--)
        {
            this.points[i] = this.points[i - 1];
        }

        // вставляем новую точку
        this.points[insertIndex] = new FunctionPoint(point);
        this.amountOfElements++;
    }
}