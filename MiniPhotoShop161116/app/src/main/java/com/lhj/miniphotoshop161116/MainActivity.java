package com.lhj.miniphotoshop161116;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private GraphicView gpcGraphic;

    private static float scale = 1f;
    private static float angle = 0f;
    private static float color = 1f;
    private static float satur = 1f;

    private static final float SCALE_FACTOR = 0.2f;
    private static final float ANGLE_FACTOR = -20f;
    private static final float COLOR_FACTOR = 0.2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutCanvas = (LinearLayout) findViewById(R.id.layoutCanvas);
        gpcGraphic = new GraphicView(this);
        layoutCanvas.addView(gpcGraphic);
        clickIcons();
    }

    private void clickIcons() {
        final ImageButton btnZoomIn = (ImageButton) findViewById(R.id.btnZoomIn);
        final ImageButton btnZoomOut = (ImageButton) findViewById(R.id.btnZoomOut);
        final ImageButton btnRotate = (ImageButton) findViewById(R.id.btnRotate);
        final ImageButton btnBright = (ImageButton) findViewById(R.id.btnBright);
        final ImageButton btnDark = (ImageButton) findViewById(R.id.btnDark);
        final ImageButton btnGray = (ImageButton) findViewById(R.id.btnGray);

        // 확대 버튼을 클릭하면 확대
        btnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scale += SCALE_FACTOR;
                gpcGraphic.invalidate();
            }
        });

        // 축소 버튼을 클릭하면 축소
        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scale -= SCALE_FACTOR;
                if (scale < SCALE_FACTOR) { // SCALE_FACTOR보다 배율이 작아지지 않도록
                    scale = SCALE_FACTOR;
                }
                gpcGraphic.invalidate();
            }
        });

        // 회전 버튼을 클릭하면 반시계 방향 회전
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle += ANGLE_FACTOR;
                gpcGraphic.invalidate();
            }
        });

        // 밝게 버튼을 클릭하면 밝게
        btnBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color += COLOR_FACTOR;
                gpcGraphic.invalidate();
            }
        });

        // 어둡게 버튼을 클릭하면 어둡게
        btnDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color -= COLOR_FACTOR;
                if (color < 0f) {   // 음수가 나오지 않도록
                    color = 0f;
                }
                gpcGraphic.invalidate();
            }
        });

        // 회색조 버튼
        btnGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (satur == 0f) {  // 기본 색상
                    satur = 1f;
                } else {            // 회색조
                    satur = 0f;
                }
                gpcGraphic.invalidate();
            }
        });
    }


    /**
     * 리스트 뷰에 추가할 그림 출력 뷰
     */
    private static class GraphicView extends View {

        private ColorMatrix matrix;
        private Paint paint;
        private Bitmap picture;

        public GraphicView(Context context) {
            super(context);

            matrix = new ColorMatrix();
            paint = new Paint();
            picture = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (picture != null) {
                // 버튼 선택에 따른 값 조정
                manipulateCanvas(canvas);
                paint.setColorFilter(createFilter());

                // 화면 중앙에 그림 출력
                int picX = (this.getWidth() - picture.getWidth()) / 2;
                int picY = (this.getHeight() - picture.getHeight()) / 2;
                canvas.drawBitmap(picture, picX, picY, paint);
            }
        }

        /**
         * 캔버스 조작 (확대/축소 및 회전)
         */
        private void manipulateCanvas(Canvas canvas) {
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            // 확대 및 축소
            canvas.scale(scale, scale, cenX, cenY);

            // 회전
            canvas.rotate(angle, cenX, cenY);
        }

        /**
         * 색상 조작 (밝기 및 회색조)
         */
        private ColorMatrixColorFilter createFilter() {
            // 밝게 및 어둡게
            matrix.reset();
            float[] colorArr = matrix.getArray();
            colorArr[0] = color;
            colorArr[6] = color;
            colorArr[12] = color;

            // 회색조
            if (satur != 1) {
                matrix.setSaturation(satur);
            }

            return new ColorMatrixColorFilter(matrix);
        }

        @Override
        protected void finalize() throws Throwable {
            if (picture != null && !picture.isRecycled()) {
                picture.recycle();
            }
            super.finalize();
        }
    }
}
