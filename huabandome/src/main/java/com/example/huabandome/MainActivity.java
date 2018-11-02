package com.example.huabandome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/*
 *三个自定义控件
 *   第一个是画板         canvas
 *   第二个是画笔         paint
 *   第三个是手势适配器
 *
 *  实现画图效果的思路是：根据图片作画  实际是对图片进行修改，起到画图的效果
 *  1.  原图，白纸，画板，画笔
 *  2.  用手势识别进行作画
 */
public class MainActivity extends AppCompatActivity {


    private ImageView img;
    Bitmap bitmap1 = null;
    private Canvas mCanvas;
    int startX =0;
    int startY =0;
    int stopX = 0;
    int stopY = 0;
    Paint paint=null;
    Paint paint1=null;
    Bitmap bitmap=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //加载原图
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //创建白纸   复制这张图片  设置宽高图片等参数
        bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        //创建画板
        mCanvas = new Canvas(bitmap1);
        //创建画笔
        paint = new Paint();
        paint1 = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        //在纸上画画，白纸在画板上    ， 开始作画
        mCanvas.drawBitmap(bitmap,new Matrix(),paint);
       // mCanvas.drawLine(10,90,310,90,paint);
       /* mCanvas.drawText("画线及圆弧",10,80,paint);
        mCanvas.drawLine(10,90,310,90,paint);//直线
        mCanvas.drawLine(330,90,430,150,paint);//斜线*/

        //手势识别器和画笔的结合使用

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch(event.getAction()){
                    //按下时的
                    case MotionEvent.ACTION_DOWN:
                        //获取用户按下时的坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        //Log.e("zhx","startX:   "+startX+"    startY:   "+startY);
                        break;
                    //移动
                    case MotionEvent.ACTION_MOVE:
                        stopX = (int) event.getX();
                        stopY = (int) event.getY();
                        mCanvas.drawLine(startX,startY,stopX,stopY,paint);
                        startX = stopX;
                        startY = stopY;
                        img.setImageBitmap(bitmap1);
                       //Log.e("zhx","x:   "+x1+"    y:   "+y1);
                        break;
                    //抬起
                    case MotionEvent.ACTION_UP:

                        stopX = (int) event.getX();
                        stopY = (int) event.getY();
                        Log.e("zhx","stopX:   "+stopX+"    stopY:   "+stopY);

                        break;

                }



                return true;
            }
        });

    }
    private void initView() {
        img = findViewById(R.id.img);
    }

    public void red(View view) {
        paint.setColor(Color.RED);
    }

    public void green(View view) {
        paint.setColor(Color.GREEN);
    }

    public void brush(View view) {
       // paint.setColor(Color.WHITE);
       /* mCanvas.drawColor(0,PorterDuff.Mode.CLEAR);*/
       // img.setImageBitmap(bitmap1);
        paint1.setAlpha(0);
        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint1.setAntiAlias(true);
        paint1.setDither(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeJoin(Paint.Join.ROUND);
        paint1.setStrokeWidth(30);
        mCanvas.drawBitmap(bitmap,new Matrix(),paint1);
        img.setImageBitmap(bitmap1);
    }

    public void save(View view) {

    }


}
