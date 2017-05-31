package com.example.triangledialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * @author shiming
 * @time 2017/5/31 11:25
 * @desc  用笔画一个三角形的形状 而且是闭合的三角形，但是这个箭头 目前我就画了两个方向  一个向上  一个向下
 */

public class TriangleViewWidget extends View {


    private Paint mPaint;
    private Path mPath;
    private int mColorBg;
    private int mGravity;

    /**
     * 为什么自定空间必须重写了2个方法？
     * @param context  代码中new
     */
    public TriangleViewWidget(Context context) {
        this(context,null);
    }

    /**
     *    * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * *从XML膨胀视图时调用的构造函数。这就是所谓的
     *从XML文件构造视图时，提供属性
     *在xml文件中指定。此版本使用默认样式
     * 0，所以应用的唯一属性值是上下文中的主题
     *和给定的attributeset。
     * @param context
     * @param attrs
     */
    public TriangleViewWidget(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * AttributeSet attrs: 属性值的集合.
     int[] attrs: 我们自定义属性集合在R类中生成的int型数组.这个数组中包含了自定义属性的资源ID.
     int defStyleAttr: 这是当前Theme中的包含的一个指向style的引用.当我们没有给自定义View设置declare-styleable资源集合时,默认从这个集合里面查找布局文件中配置属性值.传入0表示不向该defStyleAttr中查找默认值.
     int defStyleRes: 这个也是一个指向Style的资源ID,但是仅在defStyleAttr为0或者defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用.
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TriangleViewWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //一个笔，抗锯齿，好bi
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //路径
        mPath = new Path();
    }
    public void setBg(int color){
        mColorBg = color;
        invalidate();
    }
    public void setGravity(int gravity){
        mGravity = gravity;
        invalidate();
    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Return the width of the your view.
        int width = getWidth();
        int height = getHeight();
//    Integer.parseInt(String s, int radix)就是将整数字符串s（radix用来指明s是几进制）转换成10进制的整数,显然前提是s为整数字符串。
//    java.lang.Long.parseLong(String s, int radix) 方法解析一个指定的第二个参数基数为基数有符号的long的字符串参数s。在这里可以看到一些例子：
        int i = Color.parseColor("#BBff0000");
        System.out.println("shiming" + i);
        mPaint.setColor(mColorBg);
        /** Restores the paint to its default settings. */
        mPaint.reset();
//        /*设置paint的　style　为STROKE：空心*/
//        mPaint.setStyle(Paint.Style.STROKE);

        /*设置paint 的style为 FILL：实心*/
        mPaint.setStyle(Paint.Style.FILL);
        if (mGravity== Gravity.TOP) {
            //画三角形的头部,确定路径
            mPath.moveTo(width / 2, 0);
            mPath.lineTo(0, height);
            mPath.lineTo(width, height);
            //*关闭当前的轮廓。if the current（is not equal to the
            //*第一点of the轮廓，线段是自动添加的。
            mPath.close();
        }else if(mGravity==Gravity.BOTTOM){
            mPath.moveTo(0,0);
            mPath.lineTo(width, 0);
            mPath.lineTo(width / 2, height);
            mPath.close();
        }
        canvas.drawPath(mPath,mPaint);
    }
}
