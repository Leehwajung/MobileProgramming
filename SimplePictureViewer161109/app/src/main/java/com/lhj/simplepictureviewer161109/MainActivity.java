package com.lhj.simplepictureviewer161109;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS
            = { Manifest.permission.READ_EXTERNAL_STORAGE };    // MainActivity의 Permission
    private static final int PERMISSION_REQUEST = 0;            // Permission 요청 코드
    private int curNum = 0;                                     // 현재 그림 번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permission을 가지고 있는지 확인
        boolean hasPermission = true;
        for (String permission : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false;
                break;
            }
        }

        // Permission을 가지고 있으면 그림을 보여줌
        if (hasPermission) {
            processPictureViewer();
        }

        // Permission을 가지고 있지 않으면 사용자에게 Permission 요청한 다음에, 그림을 보여줌
        else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST);
        }
    }

    /**
     * 그림을 가져와 화면에 띄움
     */
    private void processPictureViewer() {
        // 위젯 가져오기
        final Button btnPrev = (Button) findViewById(R.id.btnPrev);
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final PictureView picUser = (PictureView) findViewById(R.id.picUser);

        // 그림 파일 목록을 가져옴
        final File[] picFiles = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).listFiles();

        // 그림 파일이 있으면
        if (picFiles != null && picFiles.length > curNum) {
            // 첫 번째 그림 파일을 가져와 띄움
            picUser.setPicturePath(picFiles[curNum].getPath());
            picUser.invalidate();

            // 이전 그림 버튼을 누르면
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 이전 그림을 가져와 띄움
                    try {
                        picUser.setPicturePath(picFiles[--curNum].getPath());
                        picUser.invalidate();
                    }

                    // 이전 그림이 없다면 오류 토스트 출력 (원래 그림이 첫 번째 그림)
                    catch (ArrayIndexOutOfBoundsException e) {
                        curNum++;
                        Toast.makeText(getApplicationContext(),
                                R.string.errFirst, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // 다음 그림 버튼을 누르면
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 다음 그림을 가져와 띄움
                    try {
                        picUser.setPicturePath(picFiles[++curNum].getPath());
                        picUser.invalidate();
                    }

                    // 다음 그림이 없다면 오류 토스트 출력 (원래 그림이 마지막 그림)
                    catch (ArrayIndexOutOfBoundsException e) {
                        curNum--;
                        Toast.makeText(getApplicationContext(),
                                R.string.errLast, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // 그림 파일이 없으면 오류 토스트 출력
        else {
            Toast.makeText(getApplicationContext(),
                    R.string.errNoFile, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Permission 획득 여부에 따라 동작 수행 (요청 이후 수행)<p>
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
            @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            // permission 요청
            case PERMISSION_REQUEST:
                // 사용자로부터 Permission을 획득했으면, 그림을 보여줌
                try {
                    if (grantResults[getPermissionIndex(Manifest.permission.READ_EXTERNAL_STORAGE)]
                            == PackageManager.PERMISSION_GRANTED) {
                        processPictureViewer();
                        return;
                    }
                }

                // 사용자로부터 Permission을 획득하지 못했으면, 오류 토스트를 출력하고 앱 종료
                catch (ArrayIndexOutOfBoundsException e) {
                    // 아래(outside of catch block)에서 처리
                }
                Toast.makeText(getApplicationContext(),
                        R.string.errPermission, Toast.LENGTH_LONG).show();
                finish();   // 권한을 획득하지 못하면 앱 종료
                return;

            // permission 요청이 아니면 기본 동작
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 이 엑티비티의 Permission에 해당하는 인덱스를 가져옴
     */
    private static int getPermissionIndex(String permission) {
        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return 0;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
