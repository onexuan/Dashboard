package com.u3coding.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by ${U3} on 2016/3/17.
 */
public class MyProgressBar extends View {
    float progress = 360;
    int totalTime;
    Bitmap image;
    Drawable drawable;
    int boundWidth = 5;
    private int progressWidth = 30;
    private int progressColor = Color.GREEN;
    private int progressBackColor = Color.GREEN;


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
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public void setRadiu(float radiu) {
        this.radiu = radiu;
        invalidate();
    }

    public void start(long time) {
        time *= 60000;
        final float step = (float) 360 / (time / 30);
        CountDownTimer mTimer = new CountDownTimer(time, 30) {
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

    private void end(float step) {
        progress -= step;
        invalidate();
        progress = 0;
        invalidate();
    }

    public void setBoundWidth(int width) {
        boundWidth = width;
    }

    public void setProgressWidth(int width) {
        progressWidth = width;
    }

    public void setProgressColor(int color) {
        progressColor = color;
    }

    public void setProgressBackColor(int color) {
        progressBackColor = color;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        invalidate();
    }

    int width;
    int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        width = getWidth() - paddingLeft - paddingRight;
        height = getHeight() - paddingTop - paddingBottom;
        radiu = Math.min(width, height) / 2 - boundWidth;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(boundWidth);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radiu - progressWidth, paint);
        if (drawable != null  ) {
            image = ((BitmapDrawable) drawable).getBitmap();
            Paint paint1 = new Paint();
            Bitmap bitmap = createFramedPhoto(paddingLeft, paddingTop, paddingLeft + 2 * (int) radiu, paddingTop + 2 * (int) radiu);
            paint1.setAntiAlias(true);
            canvas.drawBitmap(bitmap, paddingLeft, height / 2 - width / 2 + progressWidth + boundWidth, paint);
        }
        paint.setStrokeWidth(progressWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF oval = new RectF();
        oval.left = paddingLeft + progressWidth / 2 + boundWidth;
        oval.top = height / 2 - width / 2 + progressWidth / 2 + boundWidth;
        oval.right = 2 * radiu + paddingLeft - progressWidth / 2 + boundWidth;
        oval.bottom = 2 * radiu + height / 2 - width / 2 - progressWidth / 2 + boundWidth;
        paint.setColor(progressBackColor);
        canvas.drawArc(oval, 270, 360, false, paint);

        paint.setColor(progressColor);
        canvas.drawArc(oval, 270, progress, false, paint);
    }

    private Bitmap createFramedPhoto(int left, int top, int right, int bottom) {

        Rect dst = new Rect(left, top, right, bottom);
        Bitmap output = Bitmap.createBitmap(right - left, bottom - top, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF oval = new RectF();
        oval.left = left + progressWidth + boundWidth;
        oval.top = getPaddingTop();
        oval.right = right - progressWidth;
        oval.bottom = 2 * radiu + oval.top - 2 * (boundWidth + progressWidth);
        canvas.drawOval(oval, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(image, null, dst, paint);
        return output;

    }


}
