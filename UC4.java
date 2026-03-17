package Quantity_Measurement_App;

class Length
{
    private double val;
    private LengthUnit unit;


    public enum LengthUnit
    {
        FEET(12.0),
        INCHES(1.0),
        YARD(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor()
        {
            return conversionFactor;
        }
    }

    public Length(double val,LengthUnit unit) {
        this.val = val;
        this.unit = unit;
    }

    public double convertTobaseUnit()
    {
        return val * unit.getConversionFactor();
    }

    public boolean compare(Length thaLength)
    {
        return Double.compare(this.convertTobaseUnit(), thaLength.convertTobaseUnit()) == 0;
    }

    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        Length a = (Length)o;
        return this.compare(a);
    }
    
}

public class UC4 {
    public static boolean demonstrateLengthEquality(Length length1,Length length2)
    {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double v1,Length.LengthUnit l1,double v2,Length.LengthUnit l2)
    {
        Length a = new Length(v1, l1);
        Length b = new Length(v2, l2);

        return demonstrateLengthEquality(a, b);

    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthComparison(1.0, Length.LengthUnit.YARD,36.0, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARD));       
    }

    
}
