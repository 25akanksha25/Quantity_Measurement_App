package Quantity_Measurement_App;


class Length
{
    private double val;
    private LengthUnit unit;


    enum LengthUnit
    {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private double conversionFactor;
        LengthUnit(double conversionFactor)
        {
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

    private double convertToBaseUnit()
    {
        return val * unit.getConversionFactor();
    }

    private boolean compare(Length thatLen)
    {
        double ep = 1e-9;
        return Math.abs(this.convertToBaseUnit() - thatLen.convertToBaseUnit()) < ep;
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
        Length i = (Length) o;
        return this.compare(i);
    }

    public double convert(double val,Length.LengthUnit from, Length.LengthUnit to)
    {
        if(!Double.isFinite(val))
        {
            throw new IllegalArgumentException("The Value should be finite");
        }
        if(from == null || to == null)
        {
            throw new IllegalArgumentException("Unit can't br null");
        }

        double base = val * from.getConversionFactor();
        double ans = base / to.getConversionFactor();
        return ans;
    }
    public Length convertTo(LengthUnit targetUnit)
    {
        double ans = convert(this.val,this.unit,targetUnit);
        return new Length(ans, targetUnit);
    }


    public Length add(Length thatLength)
    {
        if(thatLength == null)
        {
            throw new IllegalArgumentException("Can't be null");
        }
        double first = this.val * this.unit.getConversionFactor();
        double sec = thatLength.val * thatLength.unit.getConversionFactor();
        double sum = first + sec;

        double finalV = convertFromBaseToTarget(sum, this.unit);

        return new Length(finalV, this.unit);
    }


    private double convertFromBaseToTarget(double lengthInInches,LengthUnit targetUnit)
    {
        return lengthInInches / targetUnit.getConversionFactor();
    }
    

    public Length addAndConvert(Length thatLength,LengthUnit targetUnit)
    {
        if(thatLength == null)
        {
            throw new IllegalArgumentException("Can't be null");
        }
        if(targetUnit == null)
        {
            throw new IllegalArgumentException("Unit can't be null");

        }
        double first = this.val * this.unit.getConversionFactor();
        double sec = thatLength.val * thatLength.unit.getConversionFactor();
        double sum = first + sec;

        double finalV = sum / targetUnit.getConversionFactor();

        return new Length(finalV, targetUnit);
    }


    public String toString()
    {
        String ans = String.format("%.2f", val);
        return ans + " " + unit;
    }
    
}

public class UC7 {

    public static Length demonstrateLengthConversion(double val,Length.LengthUnit from,Length.LengthUnit to)
    {
        Length l1 = new Length(val, from);
        return l1.convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length l1,Length.LengthUnit to)
    {
        return l1.convertTo(to);
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2)
    {
        return l1.equals(l2);
    }
    public static boolean demonstrateLengthComparison(double v1, Length.LengthUnit u1, double v2,Length.LengthUnit u2)
    {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return l1.equals(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2)
    {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2,Length.LengthUnit target)
    {
        return l1.addAndConvert(l2, target);
    }

    public static void main(String[] args) {

        System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.FEET,Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(2.0, Length.LengthUnit.YARDS,Length.LengthUnit.INCHES));
        

        System.out.println(demonstrateLengthComparison(12.0, Length.LengthUnit.INCHES, 1.0, Length.LengthUnit.FEET));

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("(Feet + Inches): " + demonstrateLengthAddition(l1, l2));

        Length l5 = new Length(36.0, Length.LengthUnit.INCHES);
        Length l6 = new Length(1.0, Length.LengthUnit.YARDS);

        System.out.println(demonstrateLengthAddition(l5, l6, Length.LengthUnit.FEET));

        Length l7 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length l8 = new Length(1.0, Length.LengthUnit.INCHES);

        System.out.println(demonstrateLengthAddition(l7, l8, Length.LengthUnit.CENTIMETERS));
        
    }
}

