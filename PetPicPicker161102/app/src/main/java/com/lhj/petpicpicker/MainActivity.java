package com.lhj.petpicpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.activityMain);

        // 이미지 뷰
        imgPet = (ImageView) findViewById(R.id.imgPet);
    }

    // 옵션 메뉴 설정
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();  // 메뉴 인플레이터
        inflater.inflate(R.menu.menu_main, menu);   // 메뉴 레이아웃에 연결
        return result;
    }

    // 메뉴 아이템을 선택하면 사진 출력
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemDog:      // 강아지 선택
                imgPet.setImageResource(R.drawable.dog);
                return true;
            case R.id.itemCat:      // 고양이 선택
                imgPet.setImageResource(R.drawable.cat);
                return true;
            case R.id.itemRabbit:   // 토끼 선택
                imgPet.setImageResource(R.drawable.rabbit);
                return true;
        }
        return false;
    }
}
