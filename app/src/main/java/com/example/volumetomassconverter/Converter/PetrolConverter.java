package com.example.volumetomassconverter.Converter;

public class PetrolConverter extends Converter{

    private static PetrolConverter c=null;

    @Override
    public double litresToKilograms(double v) {
        return v*.740;
    }

    @Override
    public double litresToPounds(double v) {
        return v * 1.63142;
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
            c=new PetrolConverter();
        }
        return c;
    }
}
/*
    public static double convert(String cFrom,String cTo,double v){
        double result=0;
        switch (cFrom){// deciding type of volume to convert from litres
            case "Litres":
                switch (cTo){//convert to
                    case "Kilograms":
                        result= litresToKilograms(v);
                        break;
                    case "Pounds":
                        result= litresToPounds(v);
                        break;
                }
                break;

        }
        return result;
    }

    private static double litresToKilograms(double v) {
        return v * .740;
    }
    private static double litresToPounds(double v) {
        return v * 1.63142;
    }*/