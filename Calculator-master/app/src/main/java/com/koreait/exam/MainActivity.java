package com.koreait.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private EditText etVal1;
    private EditText etVal2;
    private EditText etResult;

    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = findViewById(R.id.tvDisplay);
        etVal1 = findViewById(R.id.etVal1);
        etVal2 = findViewById(R.id.etVal2);
        etResult = findViewById(R.id.etResult);
    }

    // Clear 버튼 이벤트
    public void clkClear(View v) {
        operator = "";
        tvDisplay.setText("");
        etVal1.getText().clear();
        etVal2.getText().clear();
        etResult.getText().clear();
        /*
        etVal1.setText("");
        etVal2.setText("");
        etResult.setText("");
        tvDisplay.setText("");

         */
    }

    // Clear 버튼 제외한 나머지 버튼들의 이벤트
    public void clkNumber(View v) {
        TextView tv = (TextView)v;
        String num = tv.getText().toString();
        addTvDisplay(num);
        switch (operator){
            case "":
                String oldVal = etVal1.getText().toString();
                etVal1.setText(oldVal + num);
                break;
            default:
                String oldVal2 = etVal2.getText().toString();
                etVal2.setText(oldVal2 + num);
                break;
        }
    }

    // 사칙연산 버튼 이벤트
    public void clkOperator(View v){
        TextView tv = (TextView)v;
        String oper = tv.getText().toString();
        operator = oper;

        addTvDisplay(oper);
    }

    // equal 버튼 이벤트
    public void clkEqual(View v){
        addTvDisplay("=");
        String strVal1 = etVal1.getText().toString();
        String strVal2 = etVal2.getText().toString();
        int intVal1 = Integer.parseInt(strVal1);
        int intVal2 = Integer.parseInt(strVal2);

        String strResult = "";
        int intResult = 0;
        switch (operator){
            case "+":
                strResult = String.valueOf(intVal1+intVal2);
                break;

            case "-":
                strResult = String.valueOf(intVal1-intVal2);
                break;
            case "/":
                strResult = String.valueOf((float)intVal1/intVal2);
                break;
            case "*":
                strResult = String.valueOf(intVal1*intVal2);
                break;
        }
        addTvDisplay(strResult);
        etResult.setText(strResult);

        /*
        String strVal1 = etVal1.getText().toString();
        String strVal2 = etVal2.getText().toString();
        double num1 = Double.parseDouble(strVal1);
        double num2 = Double.parseDouble(strVal2);
        switch(operator){
            case "+":
                String result = String.valueOf(num1+num2);
                etResult.setText(result);
                addTvDisplay(result);
                break;
            case "-":
                String result2 = String.valueOf(num1-num2);
                etResult.setText(result2);
                addTvDisplay(result2);
                break;
            case "/":
                String result3 = String.valueOf(num1%num2);
                etResult.setText(result3);
                addTvDisplay(result3);
                break;
            case "*":
                String result4 = String.valueOf(num1*num2);
                etResult.setText(result4);
                addTvDisplay(result4);
                break;
        }

         */

    }

    public void addTvDisplay(String val){
        //String oldVal = tvDisplay.getText().toString();
        //tvDisplay.setText(oldVal + val);
        tvDisplay.append(val);
    }
}