package com.lhj.simplewebbrowser161019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtUrl;
    private WebView webMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯
        edtUrl = (EditText) findViewById(R.id.edtUrl);
        Button btnGo = (Button) findViewById(R.id.btnGo);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        webMainView = (WebView) findViewById(R.id.webMainView);

        // 웹 뷰 클라이언트 설정
        webMainView.setWebViewClient(new MainWebViewClient());

        // 줌 컨트롤 활성화
        WebSettings webSettings = webMainView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        // 이동 버튼
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webMainView.loadUrl(edtUrl.getText().toString());
            }
        });

        // 이전 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webMainView.goBack();
            }
        });
    }

    // 커스텀 웹 뷰 클라이언트
    private static class MainWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
