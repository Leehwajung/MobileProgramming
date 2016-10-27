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
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Chronometer crnTakenTime;
    private CalendarView calReservation;
    private TimePicker tpcReservation;
    private TextView textYear, textMonth, textDay, textHour, textMinute;
    private int year, month, day;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.activityMain);

        // 상단 크로노미터
        crnTakenTime = (Chronometer) findViewById(R.id.crnTakenTime);

        // 상단 예약 시작 버튼
        Button btnStart = (Button) findViewById(R.id.btnStart);

        // 날짜 및 시간 선택 라디오 버튼 그룹
        RadioButton rdbCal = (RadioButton) findViewById(R.id.rdbCal);
        RadioButton rdbTime = (RadioButton) findViewById(R.id.rdbTime);

        // 캘린더 및 타임 피커
        calReservation = (CalendarView) findViewById(R.id.calReservation);
        tpcReservation = (TimePicker) findViewById(R.id.tpcReservation);

        // 하단 예약 완료 버튼
        Button btnFinish = (Button) findViewById(R.id.btnFinish);

        // 하단 텍스트 뷰 (년, 월, 일, 시, 분 )
        textYear = (TextView) findViewById(R.id.textYear);
        textMonth = (TextView) findViewById(R.id.textMonth);
        textDay = (TextView) findViewById(R.id.textDay);
        textHour = (TextView) findViewById(R.id.textHour);
        textMinute = (TextView) findViewById(R.id.textMinute);

        // 현재 날짜로 날짜 초기화
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH) + 1;
        day = today.get(Calendar.DATE);

        // 처음에는 캘린더 뷰와 타임 피커가 안 보이게 설정
        tpcReservation.setVisibility(View.INVISIBLE);
        calReservation.setVisibility(View.INVISIBLE);

        // 예약 시작 버튼을 클릭하면 타이머 시작
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crnTakenTime.setBase(SystemClock.elapsedRealtime());
                crnTakenTime.start();
                crnTakenTime.setTextColor(Color.RED);
            }
        });

        // 날짜 설정 라디오 버튼을 클릭하면 날짜 선택 활성화
        rdbCal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calReservation.setVisibility(View.VISIBLE);
                tpcReservation.setVisibility(View.INVISIBLE);
            }
        });

        // 시간 설정 라디오 버튼을 클릭하면 시간 선택 활성화
        rdbTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calReservation.setVisibility(View.INVISIBLE);
                tpcReservation.setVisibility(View.VISIBLE);
            }
        });

        // 캘린더 뷰의 날짜를 선택하면 그 날짜를 가져움
        calReservation.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d) {
                year = y;
                month = m + 1;
                day = d;
            }
        });

        // 예약 완료 버튼을 클릭하면 타이머를 중지하고 날짜와 시간을 텍스트 뷰에 나타냄
        btnFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crnTakenTime.stop();
                crnTakenTime.setTextColor(Color.BLUE);

                textYear.setText(adaptForText(year));
                textMonth.setText(adaptForText(month));
                textDay.setText(adaptForText(day));

                textHour.setText(adaptForText(tpcReservation.getHour()));
                textMinute.setText(adaptForText(tpcReservation.getMinute()));
            }
        });
    }

    private String adaptForText(int i) {
        return String.format(Locale.getDefault(), "%d", i);
    }
}
