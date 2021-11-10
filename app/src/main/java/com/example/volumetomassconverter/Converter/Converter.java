package com.example.volumetomassconverter.Converter;

public abstract class Converter{

    public double convert(String cFrom,String cTo,double v){
        double result=0;
        switch (cFrom){// deciding type of volume to convert from litres
            case "Litres":
                result=convertLitresToWeight(cTo,v);
                break;
            case "Pints":
                result=convertPintsToWeight(cTo,v);
                break;

        }
        return result;
    }
    public double convertLitresToWeight(String cTo,double v){
        double result=0;
        switch (cTo){//convert to
            case "Kilograms":
                result=litresToKilograms(v);
                break;
            case "Pounds":
                result= litresToPounds(v);
                break;
        }
        return result;
    }
    public double convertPintsToWeight(String cTo,double v){
        double result=0;
        switch (cTo){//convert to
            case "Kilograms":
                result=pintsToKilograms(v);
                break;
            case "Pounds":
                result= pintsToPounds(v);
                break;
        }
        return result;
    }

    public abstract double litresToKilograms(double v) ;
    public abstract double litresToPounds(double v) ;
    public abstract double pintsToKilograms(double v) ;
    public abstract double pintsToPounds(double v) ;
}
