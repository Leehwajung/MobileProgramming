package com.lhj.simplepictureviewer161109;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class PictureView extends View {

    private String picturePath = null;  // 그림 파일 경로 및 이름
    private Bitmap bitmap = null;       // 그림 파일에 대한 비트맵

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null && !bitmap.isRecycled()) {
            // 화면 크기에 맞는 스케일 비율 구하기 (AVD의 해상도가 높아서 그냥 쓰면 그림이 너무 작게 보임)
            float wScale = canvas.getWidth() / bitmap.getWidth();
            float hScale = canvas.getHeight() / bitmap.getHeight();
            float scale = wScale < hScale ? wScale : hScale;

            // 캔버스를 스케일하고 비트맵 출력
            canvas.scale(scale, scale);
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    /**
     * @return picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * @param picturePath 그림 파일 경로 및 이름
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        if (this.bitmap != null && !this.bitmap.isRecycled()) {
            this.bitmap.recycle();  // 비트맵 리소스 해제
        }
        this.bitmap = BitmapFactory.decodeFile(picturePath);    // 그림 파일에 접근하여 디코딩
    }
}
