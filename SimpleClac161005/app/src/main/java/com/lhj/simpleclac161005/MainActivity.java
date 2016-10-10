package com.lhj.simpleclac161005;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText editOperand1, editOperand2;
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private TextView textResult;
    private String operand1, operand2;
    private Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        editOperand1 = (EditText) findViewById(R.id.editOperand1);
        editOperand2 = (EditText) findViewById(R.id.editOperand2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        textResult = (TextView) findViewById(R.id.textResult);

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                operand1 = editOperand1.getText().toString();
                operand2 = editOperand2.getText().toString();
                result = Integer.parseInt(operand1) + Integer.parseInt(operand2);
                textResult.setText(getText(R.string.strResult) + ": " + result.toString());
                return false;
            }
        });

        btnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                operand1 = editOperand1.getText().toString();
                operand2 = editOperand2.getText().toString();
                result = Integer.parseInt(operand1) - Integer.parseInt(operand2);
                textResult.setText(getText(R.string.strResult) + ": " + result.toString());
                return false;
            }
        });

        btnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                operand1 = editOperand1.getText().toString();
                operand2 = editOperand2.getText().toString();
                result = Integer.parseInt(operand1) * Integer.parseInt(operand2);
                textResult.setText(getText(R.string.strResult) + ": " + result.toString());
                return false;
            }
        });

        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                operand1 = editOperand1.getText().toString();
                operand2 = editOperand2.getText().toString();
                result = Integer.parseInt(operand1) / Integer.parseInt(operand2);
                textResult.setText(getText(R.string.strResult) + ": " + result.toString());
                return false;
            }
        });
    }
}
