import functions.*;

class lab_2{
    static public void main(String[] args){
        TabulatedFunction f1 = new TabulatedFunction(0,10,15);
        double[] y_value = new double[15];
        System.out.println("Функция root(x)");
        System.out.println("Область определения: [" + f1.getLeftDomainBorder() + "; " + f1.getRightDomainBorder() + "]");
        System.out.println("Количество точек: " + f1.getPointsCount());

        System.out.println("\nТочки функции:");
        for (int i=0;i<15;i++){
            f1.setPointY(i,Math.sqrt(f1.getPointX(i)));
            System.out.printf("f%d: (%.1f; %.3f)%n", i + 1, f1.getPointX(i), f1.getPointY(i));
            y_value[i]=Math.sqrt(f1.getPointX(i));
        }

        System.out.println("\nПроверка создания экземляра класса через второй конструктор: ");
        TabulatedFunction f = new TabulatedFunction(0,10,y_value);
        System.out.println("Точки функции:");
        for (int i=0;i<f.getPointsCount();i++){
            System.out.printf("f'%d: (%.1f; %.3f)%n", i + 1, f.getPointX(i), f.getPointY(i));
        }

        System.out.println("\nЗначения f'(x)  разных точках: ");
        double[] test={1,2.1,11,-35,632,453.1,5,6,7,0};
        for (double x:test){
            if(Double.isNaN(f.getFunctionValue(x))){
                System.out.printf("f'(%.1f): не определенно%n",x);
            }
            else{
                System.out.printf("f'(%.1f) = %.3f%n",x,f.getFunctionValue(x));
            }
        }

        System.out.println("\nДобавление точки (5.55, sqrt(5.55)): ");
        FunctionPoint test_point = new FunctionPoint(5.55,Math.sqrt(5.55));
        f.addPoint(test_point);
        System.out.println("Точки функции:");
        for (int i=0;i<f.getPointsCount();i++){
            System.out.printf("f'%d: (%.1f; %.3f)%n", i + 1, f.getPointX(i), f.getPointY(i));
        }

        System.out.println("\nДобавление точки (9, sqrt(9)):");
        test_point = new FunctionPoint(9,Math.sqrt(9));
        f.addPoint(test_point);
        System.out.println("Точки функции:");
        for (int i=0;i<f.getPointsCount();i++){
            System.out.printf("f'%d: (%.1f; %.3f)%n", i + 1, f.getPointX(i), f.getPointY(i));
        }

        System.out.println("\nУдаление точки с номером 5:");
        System.out.printf("Точка f5 = (%.1f, %.3f)%n", f.getPointX(4),f.getPointY(4));
        f.deletePoint(5);
        System.out.println("Точки функции:");
        for (int i=0;i<f.getPointsCount();i++){
            System.out.printf("f'%d: (%.1f; %.3f)%n", i + 1, f.getPointX(i), f.getPointY(i));
        }

        System.out.println("\nЗамена точки с номером 6 на точку f = (4,sqrt(4))");
        test_point= new FunctionPoint(4,Math.sqrt(4));
        System.out.printf("Исходная точка: f6'= (%.1f; %.3f)%n", f.getPointX(5), f.getPointY(5));
        f.setPoint(5,test_point);
        System.out.printf("Измененная точка: f6'= (%.1f; %.3f)%n", f.getPointX(5), f.getPointY(5));
        System.out.println("Точки функции:");
        for (int i=0;i<f.getPointsCount();i++){
            System.out.printf("f'%d: (%.1f; %.3f)%n", i + 1, f.getPointX(i), f.getPointY(i));
        }

        System.out.println("\nЗамена точки с номером 10 по значению x = 6");
        System.out.printf("Исходная точка: f10'= (%.1f; %.3f)%n", f.getPointX(9), f.getPointY(9));
        f.setPointX(9,6);
        f.setPointY(9,Math.sqrt(6));
        System.out.printf("Измененная точка: f10'= (%.1f; %.3f)%n", f.getPointX(9), f.getPointY(9));
        System.out.println("Точки функции:");
        for (int i=0;i<f.getPointsCount();i++){
            System.out.printf("f'%d: (%.1f; %.3f)%n", i + 1, f.getPointX(i), f.getPointY(i));
        }
    }
}