package Quantity_Measurement_App;

class Length
{
    private double val;
    private LengthUnit unit;


    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double conversionFactor()
        {
            return conversionFactor;
        }
        
    }

    public Length(double val,LengthUnit unit) {
        this.val = val;
        this.unit = unit;
    }

    private double convertTobaseUnit()
    {
        return val * unit.conversionFactor();
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

public class UC3 {

    public static boolean demonstrateLengthEquality(Length length1,Length length2)
    {
        return length1.equals(length2);
    }
    
    public static void demonstrateFeetEquality()
    {
        Length a = new Length(1, Length.LengthUnit.FEET);
        Length b = new Length(1, Length.LengthUnit.FEET);

        System.out.println("Is Equals(Feet)? "+demonstrateLengthEquality(a, b));

    }

    public static void demonstrateInchEquality()
    {
        Length a = new Length(1, Length.LengthUnit.INCHES);
        Length b = new Length(1, Length.LengthUnit.INCHES);

        System.out.println("Is Equals(Inches)? "+demonstrateLengthEquality(a, b));

    }

    public static void demonstrateFeetInchesEquality()
    {
        Length a = new Length(1, Length.LengthUnit.FEET);
        Length b = new Length(12, Length.LengthUnit.INCHES);

        System.out.println("Is Equals(Feet & Inches)? "+demonstrateLengthEquality(a, b));

    }
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchEquality();
        demonstrateFeetInchesEquality();
        
    }
    
}
