package com.example.volumetomassconverter.Converter;

import android.media.CamcorderProfile;

public class WaterConverter extends Converter {
    private static WaterConverter c=null;
    @Override
    public  double litresToKilograms(double v) {
        return v*1;
    }

    @Override
    public  double litresToPounds(double v) {
        return v*2.204622622;
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
            c=new WaterConverter();
        }
         return c;
    }


}
