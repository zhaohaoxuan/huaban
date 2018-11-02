package com.example.zidingyi_square;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * date:2018/11/2
 * author:赵豪轩(xuan)
 * function:
 */
public class Square extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private int mHeight;
    private int mWidth;
    private int mx=100;
    private int my=100;
    private int x;
    private int y;
    private boolean mBoolean;

    public Square(Context context) {
        this(context,null);
    }

    public Square(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Square(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    private void initView() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mHeight = mBitmap.getHeight();
        mWidth = mBitmap.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,mx,my,mPaint);
       /* //绘制一个正方形
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawRect(120, 120, 160, 160, mPaint);// 正方形*/
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                mBoolean = onSquare(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mBoolean){
                    mx = (int) event.getX();
                    my = (int) event.getY();
                    postInvalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
    public boolean onSquare(int x,int y){
        if (mx <= x && x <= mx + mWidth && my <= y && y <= my + mHeight) {
            //continue
            return true;
        }
        return false;
    }






}
