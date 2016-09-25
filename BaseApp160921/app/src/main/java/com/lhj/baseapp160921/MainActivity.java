package com.lhj.baseapp160921;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button button;  // 버튼 참조 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);            // 버튼 가져오기

        button.setOnClickListener(new View.OnClickListener() {  // 버튼 터치 확인을 위한 리스너 생성 및 설정

            @Override
            public void onClick(View view) {                    // 버튼 터치 시 동작
                Toast.makeText(getApplicationContext(), R.string.strMsg, Toast.LENGTH_SHORT).show();
                                                                // 토스트 메시지 출력
            }
        });

    }
}
