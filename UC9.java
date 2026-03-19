package Quantity_Measurement_App;

enum WeightUnit
{
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }


    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }


    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}


class Weight
{
    private double val;
    private WeightUnit unit;

    public Weight(double val, WeightUnit unit) {
        if (!Double.isFinite(val)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.val = val;
        this.unit = unit;
    }

    private boolean compare(Weight that)
    {
        double ep = 1e-9;

        double thisBase = this.unit.convertToBaseUnit(this.val);
        double thatBase = that.unit.convertToBaseUnit(that.val);

        return Math.abs(thisBase - thatBase) < ep;
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

        Weight i = (Weight) o;
        return this.compare(i);
    }

    public Weight convertTo(WeightUnit target)
    {
        double base = unit.convertToBaseUnit(val);
        double ans = target.convertFromBaseUnit(base);
        return new Weight(ans, target);
    }

    public Weight add(Weight that)
    {
        double val1 = unit.convertToBaseUnit(val);
        double val2 = that.unit.convertToBaseUnit(that.val);

        double sum = val1 + val2;
        double ans = unit.convertFromBaseUnit(sum);

        return new Weight(ans, unit);
    }

    public Weight addAndConvert(Weight that, WeightUnit target)
    {
        if(that == null)
        {
            throw new IllegalArgumentException("Can't be null");
        }
        if(target == null)
        {
            throw new IllegalArgumentException("Unit can't be null");
        }

        double val1 = unit.convertToBaseUnit(val);
        double val2 = that.unit.convertToBaseUnit(that.val);

        double sum = val1 + val2;
        double ans = target.convertFromBaseUnit(sum);

        return new Weight(ans, target);
    }

    public String toString()
    {
        String ans = String.format("%.2f", val);
        return ans + " " + unit;
    }
}


public class UC9 {
    public static void main(String[] args) {

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w3 = new Weight(2.20462, WeightUnit.POUND);

        System.out.println(w1.convertTo(WeightUnit.GRAM));


        System.out.println(w1.equals(w2)); 
        System.out.println(w1.equals(w3)); 

        System.out.println(w1.add(w2)); 


        System.out.println(w1.addAndConvert(w2, WeightUnit.GRAM)); 
      
    }
    
}
