package Quantity_Measurement_App;
import java.util.*;

public class UC1 {
    static class Feet
    {
        private final double val;
        Feet(double val)
        {
            this.val = val;
        }

        @Override
        public boolean equals(Object obj)
        {
            if(obj == this)
            {
                return true;
            }
            if(obj == null || !(obj instanceof Feet))
            {
                return false;
            }

            Feet a = (Feet)obj;
            if(Double.compare(this.val, a.val) == 0)
            {
                return true;
            }
            return false;
        }


    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try
        {
            double num1 = sc.nextDouble();
            double num2 = sc.nextDouble();

            UC1.Feet x = new UC1.Feet(num1);
            UC1.Feet y = new UC1.Feet(num2);
            boolean res = x.equals(y);
            System.out.println("Is Equal ? "+res);
        }
        catch(InputMismatchException e)
        {
            System.out.println("The Input must be numeric");
        }



    }
    
}
