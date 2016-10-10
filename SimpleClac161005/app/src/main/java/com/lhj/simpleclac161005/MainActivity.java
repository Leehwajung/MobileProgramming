package com.lhj.simpleclac161005;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText editOperand1, editOperand2;
    private Button btnAdd, btnSub, btnMul, btnDiv, btnMod;
    private TextView textResult;
    private Double operand1, operand2;
    private Double result;

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
        btnMod = (Button) findViewById(R.id.btnMod);

        textResult = (TextView) findViewById(R.id.textResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 + operand2;
                setResult();
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 - operand2;
                setResult();
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 * operand2;
                setResult();
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 / operand2;
                if (result.isInfinite()) {
                    messageDividedByZeroError();
                    return;
                }
                setResult();
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 % operand2;
                if (result.isNaN()) {
                    messageDividedByZeroError();
                    return;
                }
                setResult();
            }
        });
    }

    private boolean getOperands() {
        String strOperand1 = editOperand1.getText().toString();
        String strOperand2 = editOperand2.getText().toString();
        if (strOperand1.isEmpty() || strOperand2.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.errBlank, Toast.LENGTH_SHORT).show();
            return true;
        }
        operand1 = Double.parseDouble(strOperand1);
        operand2 = Double.parseDouble(strOperand2);
        return false;
    }

    private void setResult() {
        textResult.setText(getText(R.string.strResult) + ": " + result);
    }

    private void messageDividedByZeroError() {
        Toast.makeText(getApplicationContext(), R.string.errDiv0, Toast.LENGTH_SHORT).show();
    }
}
