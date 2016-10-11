package com.lhj.petpicviewer161005;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView textAsk;
    private CheckBox chkAgree;
    private RadioGroup rdgPet;
    private Button btnOk;
    private ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        // 위젯을 변수에 대입
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);
        textAsk = (TextView) findViewById(R.id.textAsk);
        rdgPet = (RadioGroup) findViewById(R.id.rdgPet);
        btnOk = (Button) findViewById(R.id.BtnOk);
        imgPet = (ImageView) findViewById(R.id.imgPet);

        // 시작 체크박스의 체크 여부가 변경되면
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 체크되면 모두 보이도록 설정
                if (chkAgree.isChecked()) {
                    textAsk.setVisibility(android.view.View.VISIBLE);
                    rdgPet.setVisibility(android.view.View.VISIBLE);
                    btnOk.setVisibility(android.view.View.VISIBLE);
                    imgPet.setVisibility(android.view.View.VISIBLE);
                } else {
                    textAsk.setVisibility(android.view.View.INVISIBLE);
                    rdgPet.setVisibility(android.view.View.INVISIBLE);
                    btnOk.setVisibility(android.view.View.INVISIBLE);
                    imgPet.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });

        // 선택확인 버튼을 클릭하면
        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch (rdgPet.getCheckedRadioButtonId()) {
                    case R.id.rdbDog:
                        imgPet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rdbCat:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rdbRabbit:
                        imgPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),
                                R.string.errSelect, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
