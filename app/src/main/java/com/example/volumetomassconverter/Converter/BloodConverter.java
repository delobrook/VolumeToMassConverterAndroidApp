package com.example.volumetomassconverter.Converter;

public class BloodConverter extends Converter{

    private static BloodConverter c=null;

    @Override
    public double litresToKilograms(double v) {
        return v*1.05;
    }

    @Override
    public double litresToPounds(double v) {
        return 2.314854;


    }

    @Override
    public double pintsToKilograms(double v) {
        return litresToKilograms(v*0.473176);
    }

    @Override
    public double pintsToPounds(double v) {
        return litresToPounds(v*0.473176);
    }

    public static Converter getInstance() {
        if(c==null){
            c=new BloodConverter();
        }
        return c;
    }
}
