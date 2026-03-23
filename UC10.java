package Quantity_Measurement_App;

interface IMeasurable
{
    public double getConversionFactor();
    default double convertToBaseUnit(double val)
    {
        return val * getConversionFactor();
    }

    default double convertFromBaseUnit(double val)
    {
        return val / getConversionFactor();
    }
}


enum LengthUnit implements IMeasurable
{
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private double conversionfactor;

    LengthUnit(double conversionFactor) {
        this.conversionfactor = conversionFactor;
    }

    public double getConversionFactor()
    {
        return conversionfactor;
    }
    
}


enum WeightUnit implements IMeasurable
{
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592);

    private double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor()
    {
        return conversionFactor;
    }

}


class Quantity<U extends IMeasurable>
{
    private double val;
    private U unit;

    public Quantity(double val,U unit) {
        this.val = val;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U target)
    {
        double base = unit.convertToBaseUnit(val);
        double tar = target.convertFromBaseUnit(base);

        return new Quantity<>(tar, target);
    }

    public Quantity<U> add(Quantity<U> other)
    {
        double base1 = unit.convertToBaseUnit(val);
        double base2 = other.unit.convertToBaseUnit(other.val);

        double ans = base1 + base2;

        double fin = unit.convertFromBaseUnit(ans);

        return new Quantity<>(fin, unit);
    }

    public Quantity<U> add(Quantity<U> other, U target)
    {
        double b1 = unit.convertToBaseUnit(val);
        double b2 = other.unit.convertToBaseUnit(other.val);

        double sum = b1 + b2;

        double ans = target.convertFromBaseUnit(sum);

        return new Quantity<>(ans, target);
    }

    public boolean compare(Quantity<?> th)
    {
        double ep = 1e-9;
        double b1 = unit.convertToBaseUnit(val);
        double b2 = th.unit.convertToBaseUnit(th.val);

        return Math.abs(b1 - b2) < ep;
    }

    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        Quantity<?> i = (Quantity<?>) o;
        return this.compare(i);
    }

    public String toString()
    {
        String ans = String.format("%.2f", val);
        return ans + " " + unit;
    }

    
}

public class UC10 {
    public static void main(String[] args) {
        Quantity<LengthUnit> len1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> len2 = new Quantity<>(120.0, LengthUnit.INCHES);

        System.out.println("lengths equal? : "+len1.equals(len2));

        Quantity<WeightUnit> we1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit> we2 = new Quantity<>(1000.0,WeightUnit.GRAM);

        System.out.println("Weights equal? : "+we1.equals(we2));

        System.out.println("10 feet in inches : "+len1.convertTo(LengthUnit.INCHES));

        System.out.println("total in feet : "+len1.add(len2));
        System.out.println("total in inch : "+len1.add(len2,LengthUnit.INCHES));

        System.out.println("total in kilo : "+we1.add(we2));
        System.out.println("total in gram : "+we1.add(we2,WeightUnit.GRAM));


    }
    
}
