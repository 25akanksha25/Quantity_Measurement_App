package Quantity_Measurement_App;

public class UC2 {
    public static class Feet
    {
        private double val;
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
            return Double.compare(this.val, a.val) == 0;
        }

    }

    public static class Inches
    {
        private double val;
        Inches(double val)
        {
            this.val = val;
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
            {
                return true;
            }
            if(obj == null || !(obj instanceof Inches))
            {
                return false;
            }
            Inches b = (Inches)obj;
            return Double.compare(this.val, b.val) == 0;

        }


    }

    public static void demonstrateFeetEquality()
    {
        double feet1 = 1.0;
        double feet2 = 1.0;

        UC2.Feet x = new UC2.Feet(feet1);
        UC2.Feet y = new UC2.Feet(feet2);

        boolean ans = x.equals(y);
        System.out.println("Is Equal(Feet)? "+ans);

    }
    public static void demonstrateInchesEquality()
    {
        double inch1 = 1;
        double inch2 = 1;

        UC2.Inches a = new UC2.Inches(inch1);
        UC2.Inches b = new UC2.Inches(inch2);

        boolean ans = a.equals(b);
        System.out.println("Is Equal(Inches)? "+ans);
    }
    
    
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
    
}
