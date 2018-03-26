package com.example.mohammedalam.mortgagecalculateor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{
    //declaring global objects
    EditText purchasePrice, downPayment, interestRate;
    SeekBar years;
    TextView period, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        purchasePrice = (EditText) findViewById(R.id.editText1);
        downPayment = (EditText) findViewById(R.id.editText2);
        interestRate = (EditText) findViewById(R.id.editText3);
        years = (SeekBar) findViewById(R.id.seekBar);
        period = (TextView) findViewById(R.id.textView4);
        results = (TextView) findViewById(R.id.textView5);


        period.setText("Mortgage period ("+ years.getProgress()*10 +" years) :");

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                try {

                    int inputPurchasePrice = Integer.parseInt(purchasePrice.getText().toString().replaceAll("[$ ]", ""));
                    int inputDownPayment = Integer.parseInt(downPayment.getText().toString().replaceAll("[$ ]", ""));
                    double inputInterestRate = Double.parseDouble(interestRate.getText().toString());

                    double r =((inputInterestRate/100)/12);
                    int n = ((years.getProgress()*10)*12);
                    int loan = (inputPurchasePrice-inputDownPayment);
                    double MonthlyPayment = loan*(r*(Math.pow(1+r,n)))/((Math.pow(1+r,n)-1));

                    if(years.getProgress()==0 ||inputPurchasePrice < inputDownPayment){
                        results.setText("Monthly payments : N/A");
                    }

                    else{
                        DecimalFormat round = new DecimalFormat("##.00");
                        results.setText("Monthly payments : $"+round.format(MonthlyPayment));
                    }
                }
                catch (Exception e){
                    results.setText("Monthly payments : N/A");
                }

            }
        };
        purchasePrice.addTextChangedListener(watcher);
        downPayment.addTextChangedListener(watcher);
        interestRate.addTextChangedListener(watcher);


        SeekBar.OnSeekBarChangeListener SeekBar = new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                period.setText("Mortgage period ("+ years.getProgress()*10 +" years) :");

                try {
                    int inputPurchasePrice = Integer.parseInt(purchasePrice.getText().toString().replaceAll("[$ ]", ""));
                    int inputDownPayment = Integer.parseInt(downPayment.getText().toString().replaceAll("[$ ]", ""));
                    double inputInterestRate = Double.parseDouble(interestRate.getText().toString());

                    double r =((inputInterestRate/100)/12);
                    int n = ((years.getProgress()*10)*12);
                    int loan = (inputPurchasePrice-inputDownPayment);
                    double MonthlyPayment = loan*(r*(Math.pow(1+r,n)))/((Math.pow(1+r,n)-1));

                    if(years.getProgress()==0 ||inputPurchasePrice < inputDownPayment){
                        results.setText("Monthly payments : N/A");
                    }

                    else{
                        DecimalFormat round = new DecimalFormat("##.00");
                        results.setText("Monthly payments : $"+round.format(MonthlyPayment));
                    }
                }
                catch (Exception e){
                    results.setText("Monthly payments : N/A");
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        years.setOnSeekBarChangeListener(SeekBar);

    }
}
