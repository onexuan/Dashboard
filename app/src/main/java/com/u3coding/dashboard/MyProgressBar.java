package com.u3coding.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${U3} on 2016/3/17.
 */
public class MyProgressBar extends View{
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
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        radiu = Math.min(width, height) / 2-5;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radiu, paint);
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radiu - 30, paint);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF oval=new RectF();                     //RectF对象
        oval.left=paddingLeft+15+5;                              //左边
        oval.top=height / 2 - width/2+15+5;                                   //上边
        oval.right=2*radiu+paddingLeft-15+5;                             //右边
        oval.bottom=2*radiu+height / 2 - width/2-15+5;
        canvas.drawArc(oval,360,360,false,paint);
    }
}
