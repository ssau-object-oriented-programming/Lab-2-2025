package functions;

/**
 * Класс табулированной функции... неясно, для чего
 */
public class TabulatedFunction {
    private FunctionPoint[] points; // массив точек
    private int amountOfPoints; // реальное кол-во точек в данном массиве
    /**
     * Конструктор табулированной функции по кол-ву точек
     * @param leftX левая граница <s>страданий</s> по значению X
     * @param rightX правая граница <s>страданий</s> по значению X
     * @param pointsCount кол-во точек данной табулированной функции
     */
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.points = new FunctionPoint[Math.max(pointsCount, 0)];
        this.amountOfPoints = pointsCount;
        if (pointsCount == 1) {
            this.points[0] = new FunctionPoint((leftX + rightX) / 2,0);
        } else {
            double step = (rightX - leftX) / (pointsCount - 1);
            for (int i = Math.max(pointsCount, 0) - 1; i >= 0; i--) {
                this.points[i] = new FunctionPoint(leftX + i * step, 0);
            }
        }
    }
    /**
     * Конструктор табулированной функции по заданным значениям Y(массив)
     * @param leftX левая граница <s>страданий</s> по значению X
     * @param rightX правая граница <s>страданий</s> по значению X
     * @param values точки данной табулированной функции
     */
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this(leftX, rightX, values.length);
        for (int i = this.amountOfPoints - 1; i >= 0; i--) {
            this.points[i].setY(values[i]);
        }
    }
    /**
     * Метод получения левой границы по X
     * @return левая граница X
     */
    public double getLeftDomainBorder() {
        return (this.amountOfPoints > 0) ? (this.points[0].getX()) : (Double.NaN);
    }
    /**
     * Метод получения правой границы по X
     * @return правая граница X
     */
    public double getRightDomainBorder() {
        return (this.amountOfPoints > 0) ? (this.points[this.amountOfPoints - 1].getX()) : (Double.NaN);
    }
    /**
     * Получение значения в данной точке
     * @param x точка по X
     * @return интерполированное линейно значение в данной точке(Y)
     */
    public double getFunctionValue(double x) {
        if (x < this.getLeftDomainBorder() || this.getRightDomainBorder() < x) return Double.NaN;
        int index;
        for (index = this.amountOfPoints - 2; index >= 0; index--) { // нет смысла проверять правую точку, скипаем автоматом
            if (this.points[index].getX() < x) break; // простите Александр Викторович, но вы это не должны видеть.
            if (this.points[index].getX() == x) return this.points[index].getY(); // нет смысла интерполировать
        } // здесь используется функция (y1-y0)/(x1-x0)*(x-x0) + y0
        return (this.points[index+1].getY() - this.points[index].getY()) /
                (this.points[index+1].getX() - this.points[index].getX()) *
                (x - this.points[index].getX()) + this.points[index].getY();
    }
    /**
     * Метод получения кол-ва точек в данном классе(объекте)
     * @return кол-во точек в данном классе(объекте)
     */
    public int getPointsCount() { return this.amountOfPoints; }
    /**
     * Метод получения точки через индекс
     * @param index индекс получаемой точки
     * @return копия точки
     */
    public FunctionPoint getPoint(int index) {
        return (index < 0 || index >= this.amountOfPoints) ?
                (null) : (new FunctionPoint(this.points[index]));
    }
    /**
     * Метод установки точки по заданному индексу
     * @param index индекс точки
     * @param point сама точка. Будет сохранена ее копия
     */
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= this.amountOfPoints) return;
        double leftX = (index == 0) ? (Double.NEGATIVE_INFINITY) : (this.points[index-1].getX());
        double rightX = (index == this.amountOfPoints - 1) ? (Double.POSITIVE_INFINITY) : (this.points[index+1].getX());
        if (leftX < point.getX() && point.getX() < rightX) this.points[index] = new FunctionPoint(point);
    }
    /**
     * Метод получения X координаты точки на index позиции
     * @param index индекс получаемой точки
     * @return значение точки по координате X
     */
    public double getPointX(int index) {
        return (index < 0 || index >= this.amountOfPoints) ? (Double.NaN) : (this.points[index].getX());
    }

    /**
     * Метод изменения у точки X координаты
     * @param index индекс, чьей точки мы меняем X
     * @param x значение X координаты
     */
    public void setPointX(int index, double x) {
        if (index >= 0 && index < this.amountOfPoints)
            this.setPoint(index, new FunctionPoint(x, this.points[index].getY()));
    }
    /**
     * Метод получения Y координаты точки на index позиции
     * @param index индекс получаемой точки
     * @return значение точки по координате Y
     */
    public double getPointY(int index) {
        return (index < 0 || index >= this.amountOfPoints) ? (Double.NaN) : (this.points[index].getY());
    }

    /**
     * Метод изменения у точки Y координаты
     * @param index индекс, чьей точки мы меняем Y
     * @param y значение Y координаты
     */
    public void setPointY(int index, double y) {
        if (index >= 0 && index < this.amountOfPoints) this.points[index].setY(y);
    }

    /**
     * Метод удаления точки по заданному индексу
     * @param index индекс удаляемой точки
     */
    public void deletePoint(int index) {
        if (index < 0 || index >= this.amountOfPoints) return;
        for (; index < this.amountOfPoints - 2; index++) {
            this.points[index] = this.points[index + 1];
        }
        this.amountOfPoints--;
        this.points[index + 1] = null; /* стираем ссылку на последний элемент, т.к. если удалим последний элемент,
        то из памяти он не исчезнет(ссылка останется на неиспользуемой части массива) */
    }

    /**
     * Метод добавления точки в список
     * @param point сама собственно точка
     */
    public void addPoint(FunctionPoint point) {
        double x = point.getX();
        int index = 0;
        for (; index < this.amountOfPoints; index++) {
            if (this.points[index].getX() == x) return; // если X совпадает - не добавляем точку.(идет она нафиг)
            if (this.points[index].getX() > x) break;
        }
        if (this.points.length == this.amountOfPoints) {
            FunctionPoint[] newArray = new FunctionPoint[this.points.length * 2 + 1];
            System.arraycopy(this.points, 0, newArray, 0, index);
            newArray[index] = new FunctionPoint(point);
            System.arraycopy(this.points, index, newArray, index + 1, this.amountOfPoints - index - 2);
            this.points = newArray; // по идее массив удалится, т.к. нету на него ссылок
        } else {
            for (int i = this.amountOfPoints; i > index; i--) {
                this.points[i] = this.points[i - 1];
            }
            this.points[index] = new FunctionPoint(point);
        }
        this.amountOfPoints++;
    }
}