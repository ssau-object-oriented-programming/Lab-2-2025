import functions.*;

public class Main {

    public static void main(String[] args) {
        double[] x2 = {9, 4, 1, 0, 1, 4, 9};
        TabulatedFunction f = new TabulatedFunction(-3,3,x2);
        for(int i = 0; i<f.getPointsCount(); i++)
        {
            System.out.printzln("X= "+f.getPointX(i)+"  Y= "+f.getPointY(i));
        }

        System.out.println(f.getLeftDomainBorder() +" "+ f.getRightDomainBorder());

        System.out.println("x = -2  "+f.getFunctionValue(-2));
        System.out.println("x = -4  "+f.getFunctionValue(-4));
        System.out.println(f.getPointsCount());
        System.out.println(f.getPointX(5));
        f.deletePoint(5);
        for(int i = 0; i<f.getPointsCount(); i++)
        {
            System.out.println("X= "+f.getPointX(i)+"  Y= "+f.getPointY(i));
        }
        System.out.println("////////////////");
        FunctionPoint p = new FunctionPoint(-2.5,6);
        f.addPoint(p);
        for(int i = 0; i<f.getPointsCount(); i++)
        {
            System.out.println("X= "+f.getPointX(i)+"  Y= "+f.getPointY(i));
        }
        System.out.println("////////////////");
        FunctionPoint o = new FunctionPoint(-2.1,6.2);
        f.setPoint(1,o);
        for(int i = 0; i<f.getPointsCount(); i++)
        {
            System.out.println("X= "+f.getPointX(i)+"  Y= "+f.getPointY(i));
        }
    }
}
