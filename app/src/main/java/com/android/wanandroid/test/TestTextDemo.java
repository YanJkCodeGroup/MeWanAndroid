package com.android.wanandroid.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestTextDemo extends View {

   private Paint mPaint;

   public TestTextDemo(Context context) {
      super(context);
   }

   public TestTextDemo(Context context, AttributeSet attrs) {
      super(context, attrs);
      mPaint = new Paint();
      mPaint.setTextSize(15 * 3);

   }

   public TestTextDemo(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      canvas.drawText("asdasghj中国自行车三阿萨德高危儿", 500, 500, mPaint);
      Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
   }
}
