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

    private double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor()
    {
        return conversionFactor;
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


enum VolumeUnit implements IMeasurable
{
    MILLILITRE(0.001),
    LITRE(1.0),
    GALLON(3.78541);

    private double conversionFactor;

    VolumeUnit(double conversionFactor) {
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



public class UC11 {
    public static void main(String[] args) {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println(v1.equals(v2));
        System.out.println(v1.equals(v3));

        System.out.println("litre to milli"+v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("gallon to litre"+v3.convertTo(VolumeUnit.LITRE));

        System.out.println(v1.add(v2));
        



        
    }

    
}
