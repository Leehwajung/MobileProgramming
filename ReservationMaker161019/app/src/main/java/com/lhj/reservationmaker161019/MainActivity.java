package com.lhj.reservationmaker161019;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Chronometer crnTakenTime;
    private Button btnStart, btnFinish;
    private RadioButton rdbCal, rdbTime;
    private CalendarView calReservation;
    private TimePicker tpcReservation;
    private TextView textInitYear, textInitMonth, textInitDay, textInitHour, textInitMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("시간 예약");

        // 크로노미터
        crnTakenTime = (Chronometer) findViewById(R.id.crnTakenTime);

        // 버튼
        btnStart = (Button) findViewById(R.id.btnStart);
        btnFinish = (Button) findViewById(R.id.btnFinish);

        // 라디오버튼 2개
        rdbCal = (RadioButton) findViewById(R.id.rdbCal);
        rdbTime = (RadioButton) findViewById(R.id.rdbTime);

        // FrameLayout의 2개 위젯
        calReservation = (CalendarView) findViewById(R.id.calReservation);
        tpcReservation = (TimePicker) findViewById(R.id.tpcReservation);

        // 텍스트 뷰 중에서 연, 월, 일, 시, 분 숫자
        textInitYear = (TextView) findViewById(R.id.textInitYear);
        textInitMonth = (TextView) findViewById(R.id.textInitMonth);
        textInitDay = (TextView) findViewById(R.id.textInitDay);
        textInitHour = (TextView) findViewById(R.id.textInitHour);
        textInitMinute = (TextView) findViewById(R.id.textInitMinute);

        // 처음에는 2개를 안보이게 설정
        tpcReservation.setVisibility(View.INVISIBLE);
        calReservation.setVisibility(View.INVISIBLE);


        rdbCal.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                tpcReservation.setVisibility(View.INVISIBLE);
                calReservation.setVisibility(View.VISIBLE);
            }
        });

        rdbTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tpcReservation.setVisibility(View.VISIBLE);
                calReservation.setVisibility(View.INVISIBLE);
            }
        });

        // 타이머 설정
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crnTakenTime.setBase(SystemClock.elapsedRealtime());
                crnTakenTime.start();
                crnTakenTime.setTextColor(Color.RED);
            }
        });

        // 버튼을 클릭하면 날짜,시간을 가져옴
        btnFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crnTakenTime.stop();
                crnTakenTime.setTextColor(Color.BLUE);

                java.util.Calendar curDate = java.util.Calendar.getInstance();
                curDate.setTimeInMillis(calReservation.getDate());

                textInitYear.setText(Integer.toString(curDate.get(Calendar.YEAR)));
                textInitMonth.setText(Integer.toString(1 + curDate.get(Calendar.MONTH)));
                textInitDay.setText(Integer.toString(curDate.get(Calendar.DATE)));

                textInitHour.setText(Integer.toString(tpcReservation.getCurrentHour()));
                textInitMinute.setText(Integer.toString(tpcReservation.getCurrentMinute()));

            }
        });
    }
}
