package com.lhj.activities161123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.activityMain);

        // 위젯
        final RadioGroup rdgSelectAct = (RadioGroup) findViewById(R.id.rdgSelectAct);
        final Button btnNewAct = (Button) findViewById(R.id.btnNewAct);

        // 버튼을 누르면 선택한 엑티비티 시작
        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 선택한 엑티비티 확인하기
                Class<?> nextActClass;
                switch (rdgSelectAct.getCheckedRadioButtonId()) {
                    case R.id.rdbSecondAct: // 두 번째 엑티비티 선택
                        nextActClass = SecondActivity.class;
                        break;
                    case R.id.rdbThirdAct:  // 세 번째 엑티비티 선택
                        nextActClass = ThirdActivity.class;
                        break;
                    default:                // 엑티비티를 선택하지 않으면 선택 유도 토스트 메시지 띄움
                        Toast.makeText(getApplicationContext(),
                                R.string.errNoSelect, Toast.LENGTH_SHORT).show();
                        return;
                }

                // 선택한 엑티비티 시작
                Intent intent = new Intent(getApplicationContext(), nextActClass);
                startActivity(intent);
            }
        });
    }
}
