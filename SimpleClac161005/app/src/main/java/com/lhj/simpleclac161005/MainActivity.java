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

        // 위젯을 변수에 대입
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMod = (Button) findViewById(R.id.btnMod);

        textResult = (TextView) findViewById(R.id.textResult);

        // 더하기 버튼 클릭
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

        // 빼기 버튼 클릭
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

        // 곱하기 버튼 클릭
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

        // 나누기 버튼 클릭
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 / operand2;
                if (result.isInfinite()) {  // 0으로 나누면
                    messageDividedByZeroError();
                    return;
                }
                setResult();
            }
        });

        // 나머지 값 버튼 클릭
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOperands()) {
                    return;
                }
                result = operand1 % operand2;
                if (result.isNaN()) {   // 0으로 나누면
                    messageDividedByZeroError();
                    return;
                }
                setResult();
            }
        });
    }

    // EditText로부터 입력값을 가져옴
    // 제대로 입력 받은 경우 false, 입력값이 비어있을 경우 true를 반환
    private boolean getOperands() {
        String strOperand1 = editOperand1.getText().toString();
        String strOperand2 = editOperand2.getText().toString();

        // EditText가 비어 있을 경우 토스트 메시지 출력 후 종료
        if (strOperand1.isEmpty() || strOperand2.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.errBlank, Toast.LENGTH_SHORT).show();
            return true;
        }

        operand1 = Double.parseDouble(strOperand1);
        operand2 = Double.parseDouble(strOperand2);

        return false;
    }

    // TextView에 결과값을 출력 (화면에 출력)
    private void setResult() {
        textResult.setText(getText(R.string.strResult) + ": " + result);
    }

    // 0으로 나눌 때 출력할 에러 토스트 메시지
    private void messageDividedByZeroError() {
        Toast.makeText(getApplicationContext(), R.string.errDiv0, Toast.LENGTH_SHORT).show();
    }
}
