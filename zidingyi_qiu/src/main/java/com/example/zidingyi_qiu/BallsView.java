package com.example.zidingyi_qiu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * date:2018/11/2
 * author:赵豪轩(xuan)
 * function:
 *
 *  需求创建一个一个小球,我手按住小球,然后进行滑动,小球就跟着我的手移动,当我手松开,在空白的地方滑动,小球就不在动了
 *  实现思路:
 *  1.创建一个类,继承View(就从普通类变成了一个控件)
 *  2.覆写必须要覆写的三个构造方法
 *  3.在OnMeasure,获取屏幕和控件的宽高,让小球居于屏幕中间的位置
 *  4.在OnDrawer绘制小球
 *  5.触摸事件的监听中,根据用户手指滑动,让小球在对应的坐标重新绘制postInvalidate();
 *  有一个BUG,你会出现当你没有按住小球,在空白地方滑动,小球依然跟着动
 *
 *  自定义控件必须要实现 画纸，画板，onDraw ,画笔
 */
public class BallsView extends View {

    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private int x;
    private int y;
    private int mRadius = 50;
    private int mY;
    private int mX;
    private boolean mOnball;


    public BallsView(Context context) {
        this(context,null);
    }

    public BallsView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BallsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = this.getHeight();
        mWidth = this.getWidth();

        y = mHeight / 2;
        x = mWidth / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(x,y,mRadius,mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                mX =(int) event.getX();
                mY = (int) event.getY();
                mOnball = onball(mX, mY);
                Toast.makeText(getContext(), mOnball+"", Toast.LENGTH_SHORT).show();
                break;
           case MotionEvent.ACTION_MOVE:
               if (mOnball){
                   x = (int)event.getX();
                   y = (int)event.getY();
                   postInvalidate();
               }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    private boolean onball(int mX, int mY) {

        double sqrt = Math.sqrt((x - mX) * (x - mX) + (y - mY) * (y - mY));
        if (sqrt <= mRadius){
            return true;
        }
        return false;
    }
}
