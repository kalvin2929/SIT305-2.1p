package com.techroid.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String unit;
    EditText value;
    double mValue;
    TextView valueOne,valueTwo,valueThree;
    TextView unitOne,unitTwo,unitThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spnOptions);
        value   =  findViewById(R.id.edtValue);
        valueOne = findViewById(R.id.tvValueOne);
        valueTwo = findViewById(R.id.tvValueTwo);
        valueThree = findViewById(R.id.tvValueThree);
        unitOne = findViewById(R.id.tvUnitOne);
        unitTwo = findViewById(R.id.tvUnitTwo);
        unitThree = findViewById(R.id.tvUnitThree);


        //getting option from string array meter celsius kilograms
        ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(this,R.array.options,R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting data to spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        unit = spinner.getSelectedItem().toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        unit = spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onMeasurementClick(View view) {
       if(unit.equals("Meter"))
       {
           if(value.getText().toString().equals(""))
               Toast.makeText(this,"Please Enter a value",Toast.LENGTH_SHORT).show();
           else {
               clearFields();
               mValue = Double.parseDouble(value.getText().toString());
               double CENTIMETERS_IN_METER = 100;
               double FOOTS_IN_METER = 3.28084;
               double INCHES_IN_METER = 39.3701;
               valueOne.setText(String.format("%.2f", mValue * CENTIMETERS_IN_METER));
               unitOne.setText("Centimeter");
               valueTwo.setText(String.format("%.2f", mValue * FOOTS_IN_METER));
               unitTwo.setText("Foot");
               valueThree.setText(String.format("%.2f", mValue * INCHES_IN_METER));
               unitThree.setText("Inch");
           }
       }
       else {
           showPopup();
       }
    }

    public void onTemperatureClick(View view) {
        if(unit.equals("Celsius")) {
            if (value.getText().toString().equals(""))
                Toast.makeText(this, "Please Enter a value", Toast.LENGTH_SHORT).show();
            else {
                clearFields();
                mValue = Double.parseDouble(value.getText().toString());
                double CELSIUS_TO_KELVIN = 273.15;
                valueOne.setText(String.format("%.2f", ((mValue * 9/5)+32)));
                unitOne.setText("Fahrenheit");
                valueTwo.setText(String.format("%.2f", mValue + CELSIUS_TO_KELVIN));
                unitTwo.setText("Kelvin");
            }
        }
        else {
            showPopup();
        }
    }

    public void onWeightClick(View view) {
        if(unit.equals("Kilograms"))
        {
            if(value.getText().toString().equals(""))
                Toast.makeText(this,"Please Enter a value",Toast.LENGTH_SHORT).show();
            else {
                clearFields();
                mValue = Double.parseDouble(value.getText().toString());
                double GRAMS_IN_KILOGRAMS = 1000;
                double POUNDS_IN_KILOGRAMS = 2.20462;
                double OUNCES_IN_KILOGRAMS = 35.274;
                valueOne.setText(String.format("%.2f", mValue * GRAMS_IN_KILOGRAMS));
                unitOne.setText("Grams");
                valueTwo.setText(String.format("%.2f", mValue * OUNCES_IN_KILOGRAMS));
                unitTwo.setText("Ounce(Oz)");
                valueThree.setText(String.format("%.2f", mValue * POUNDS_IN_KILOGRAMS));
                unitThree.setText("Pounds(lb)");
            }
        }
        else {
            showPopup();
        }
    }

    public void showPopup()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Invalid")
                .setMessage("please select correct conversion icon")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                    }
                }).show();
    }
    public void clearFields()
    {
        valueOne.setText("");
        unitOne.setText("");
        valueTwo.setText("");
        unitTwo.setText("");
        valueThree.setText("");
        unitThree.setText("");

    }

}