package com.lhj.reservationmaker161019;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Chronometer crnTakenTime;
    private RadioGroup rdgDateTime;
    private DatePicker dpcReservation;
    private TimePicker tpcReservation;
    private TextView textYear, textMonth, textDay, textHour, textMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("시간 예약");

        // 상단 크로노미터
        crnTakenTime = (Chronometer) findViewById(R.id.crnTakenTime);

        // 날짜 및 시간 선택 라디오 버튼 그룹
        rdgDateTime = (RadioGroup) findViewById(R.id.rdgDateTime);
        RadioButton rdbDate = (RadioButton) findViewById(R.id.rdbDate);
        RadioButton rdbTime = (RadioButton) findViewById(R.id.rdbTime);

        // 날짜 및 시간 피커
        dpcReservation = (DatePicker) findViewById(R.id.dpcReservation);
        tpcReservation = (TimePicker) findViewById(R.id.tpcReservation);

        // 연, 월, 일, 시, 분 텍스트 뷰
        textYear = (TextView) findViewById(R.id.textYear);
        textMonth = (TextView) findViewById(R.id.textMonth);
        textDay = (TextView) findViewById(R.id.textDay);
        textHour = (TextView) findViewById(R.id.textHour);
        textMinute = (TextView) findViewById(R.id.textMinute);


        // 크로노미터를 클릭하면 타이머 시작
        crnTakenTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crnTakenTime.setBase(SystemClock.elapsedRealtime());
                crnTakenTime.start();
                crnTakenTime.setTextColor(Color.RED);
                rdgDateTime.setVisibility(View.VISIBLE);
            }
        });

        // 날짜 선택 활성화
        rdbDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tpcReservation.setVisibility(View.INVISIBLE);
                dpcReservation.setVisibility(View.VISIBLE);
            }
        });

        // 시간 선택 활성화
        rdbTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tpcReservation.setVisibility(View.VISIBLE);
                dpcReservation.setVisibility(View.INVISIBLE);
            }
        });

        // 연도를 롱클릭하면 타이머 중지
        textYear.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                crnTakenTime.stop();
                crnTakenTime.setTextColor(Color.BLUE);

                textYear.setText(adaptForText(dpcReservation.getYear()));
                textMonth.setText(adaptForText(1 + dpcReservation.getMonth()));
                textDay.setText(adaptForText(dpcReservation.getDayOfMonth()));

                textHour.setText(adaptForText(tpcReservation.getHour()));
                textMinute.setText(adaptForText(tpcReservation.getMinute()));

                rdgDateTime.setVisibility(View.INVISIBLE);
                tpcReservation.setVisibility(View.INVISIBLE);
                dpcReservation.setVisibility(View.INVISIBLE);

                return false;
            }
        });
    }

    private String adaptForText(int i) {
        return String.format(Locale.getDefault(), "%d", i);
    }
}
