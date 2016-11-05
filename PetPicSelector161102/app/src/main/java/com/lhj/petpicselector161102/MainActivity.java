package com.lhj.petpicselector161102;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯 불러오기
        final RadioGroup rdgPet = (RadioGroup) findViewById(R.id.rdgPet);
        final Button btnSelect = (Button) findViewById(R.id.btnSelect);

        // 그림 보기 버튼을 클릭할 때
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idRdbPetSelected = rdgPet.getCheckedRadioButtonId();
                RadioButton rdbPetSelected = (RadioButton) findViewById(idRdbPetSelected);

                // 동물 라디오 버튼이 선택되어 있으면, 다이얼로그를 띄어 동물 사진을 보여줌
                if (rdbPetSelected != null) {
                    // 다이얼로그에 등록할 뷰를 설정함
                    View viewPetDlg = View.inflate(MainActivity.this, R.layout.dialog_viewer, null);
                    ImageView imgPet = (ImageView) viewPetDlg.findViewById(R.id.imgPet);
                    switch (idRdbPetSelected) {
                        case R.id.rdbDog:
                            imgPet.setImageResource(R.drawable.dog);
                            break;
                        case R.id.rdbCat:
                            imgPet.setImageResource(R.drawable.cat);
                            break;
                        case R.id.rdbRabbit:
                            imgPet.setImageResource(R.drawable.rabbit);
                            break;
                        case R.id.rdbHorse:
                            imgPet.setImageResource(R.drawable.horse);
                            break;
                    }

                    // 다이얼로그를 설정하고 띄움
                    AlertDialog.Builder dlgViewer = new AlertDialog.Builder(MainActivity.this);
                    dlgViewer.setTitle(rdbPetSelected.getText());           // 제목 설정
                    dlgViewer.setView(viewPetDlg);                          // 다이얼로그에 뷰 등록
                    dlgViewer.setPositiveButton(R.string.strClose, null);   // 닫기 버튼
                    dlgViewer.show();                                       // 다이얼로그 보이기
                }

                // 동물 라디오 버튼이 선택되어 있지 않으면, 선택 유도 토스트 메시지를 띄움
                else {
                    Toast.makeText(getApplicationContext(),
                            R.string.errNotSelected, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
