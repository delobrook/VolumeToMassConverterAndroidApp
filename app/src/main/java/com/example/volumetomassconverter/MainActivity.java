package com.example.volumetomassconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.volumetomassconverter.Converter.BloodConverter;
import com.example.volumetomassconverter.Converter.Converter;
import com.example.volumetomassconverter.Converter.PetrolConverter;
import com.example.volumetomassconverter.Converter.WaterConverter;
import com.example.volumetomassconverter.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    int t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        Spinner typeOfLiquidSpinner=activityMainBinding.typeOfLiquidSpinner;
        ArrayAdapter<CharSequence> typeOfLiquidSpinnerAdapter= ArrayAdapter.createFromResource(this,R.array.liquids, android.R.layout.simple_spinner_item);
        typeOfLiquidSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfLiquidSpinner.setAdapter(typeOfLiquidSpinnerAdapter);

        typeOfLiquidSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activityMainBinding.setLiquid(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                activityMainBinding.setLiquid(adapterView.getItemAtPosition(0).toString());
            }
        });
        // Type of Volume spinner
        Spinner typeOfVolumeSpinner=activityMainBinding.unitofVolumeSpinner;
        ArrayAdapter<CharSequence> typeOfVolumeSpinnerAdapter=ArrayAdapter.createFromResource(this,R.array.convert_from, android.R.layout.simple_spinner_item);
        typeOfVolumeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfVolumeSpinner.setAdapter(typeOfVolumeSpinnerAdapter);

        typeOfVolumeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sVolume=adapterView.getItemAtPosition(i).toString();
                switch (sVolume){
                    case "Litres":
                        activityMainBinding.volumeUnit.setText("L");
                        break;
                    case "Pints":
                        activityMainBinding.volumeUnit.setText("Pt");
                        break;
                }
                activityMainBinding.setConvertFrom(sVolume);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                activityMainBinding.setConvertFrom(adapterView.getItemAtPosition(0).toString());
            }
        });
        //Type of Mass spinner
        Spinner typeOfMassSpinner= activityMainBinding.unitOfMassSpinner;
        ArrayAdapter<CharSequence> typeOfMassSpinnerAdapter=ArrayAdapter.createFromResource(this,R.array.convert_to, android.R.layout.simple_spinner_item);
        typeOfMassSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfMassSpinner.setAdapter(typeOfMassSpinnerAdapter);

        typeOfMassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sMass= adapterView.getItemAtPosition(i).toString();
                switch (sMass){
                    case "Kilograms":
                        activityMainBinding.resultMassUnit.setText("Kg");
                        break;
                    case "Pounds":
                        activityMainBinding.resultMassUnit.setText("lbs");
                        break;
                }
                activityMainBinding.setConvertTo(sMass);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                activityMainBinding.setConvertTo(adapterView.getItemAtPosition(0).toString());
            }
        });

        //volume edit text
        activityMainBinding.volumeEditTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (t==0){
                    t=1;
                }else{
                    convert();
                }
            }
        });
        activityMainBinding.convertimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });

    }
    private double volumeToMassConverter(String liquid,String convertFrom, String convertTo, double volume){
        double result=volume;
        Converter cConverter=null;
        switch(liquid){
            case "Water":
                cConverter=WaterConverter.getInstance();
                break;
            case "Petrol":
                cConverter=PetrolConverter.getInstance();
                break;
            case "Blood":
                cConverter= BloodConverter.getInstance();
            default:
                result=0.0;
        }
        result= cConverter.convert(convertFrom,convertTo,volume);
        return result;
    }
    private void convert(){
        activityMainBinding.setVolume(Double.parseDouble(activityMainBinding.volumeEditTextNumber.getText().toString()));

        activityMainBinding.setMass(volumeToMassConverter(activityMainBinding.getLiquid(), activityMainBinding.getConvertFrom(), activityMainBinding.getConvertTo(), activityMainBinding.getVolume()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}