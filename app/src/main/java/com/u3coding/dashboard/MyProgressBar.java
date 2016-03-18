package com.u3coding.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by ${U3} on 2016/3/17.
 */
public class MyProgressBar extends View{
    float progress = 360;
    int totalTime;
    int boundWidth = 5;
    private int progressWidth = 30;
    private int progressColor = Color.GREEN;


    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
        private float radiu;
    private Paint   paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public void setRadiu(float radiu){
        this.radiu = radiu;
        invalidate();
    }
    public void start(long time) {
        time *= 60000;
        final float step = (float)360/(time/30);
        CountDownTimer mTimer = new CountDownTimer(time,30) {
            public void onTick(long millisUntilFinished) {
                 progress -= step;
                invalidate();
            }

            @Override
            public void onFinish() {
                end(step);
            }

        };
        mTimer.start();
    }
    private void end(float step){
        progress -= step;
        invalidate();
        progress = 0;
        invalidate();
    }
    public void setBoundWidth(int width){
        boundWidth = width;
        invalidate();
    }
    public void setProgressWidth(int width){
        progressWidth = width;
        invalidate();
    }
    public void setProgressColor(int color){
        progressColor = color;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        radiu = Math.min(width, height) / 2-boundWidth;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(boundWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radiu, paint);
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radiu - progressWidth, paint);
        paint.setColor(progressColor);
        paint.setStrokeWidth(progressWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF oval=new RectF();
        oval.left=paddingLeft+progressWidth/2+boundWidth;
        oval.top=height / 2 - width/2+progressWidth/2+boundWidth;
        oval.right=2*radiu+paddingLeft-progressWidth/2+boundWidth;
        oval.bottom=2*radiu + height / 2 - width / 2 - progressWidth/2 + boundWidth;
        canvas.drawArc(oval,270,progress,false,paint);
    }
}
