package code.com.customviewstudy.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author: lihui1
 * Date: 2019/2/20
 * Desc:
 */

public class SquareView extends View{

    private Paint mPaint;

    public SquareView(Context context) {
        this(context, null);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("TAG", "onMeasure");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST){
            widthSize = heightSize = 300;
        } else if (widthMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.EXACTLY){

        }
        setMeasuredDimension(widthSize, heightSize);
        Log.d("TAG", "测量后的宽度和高度:"+getMeasuredWidth()+","+getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("TAG", "onDraw");
        Rect rect = new Rect();
        rect.left = (int) mPaint.getStrokeWidth();
        rect.top = (int) mPaint.getStrokeWidth();
        rect.right = 300 - (int) mPaint.getStrokeWidth();
        rect.bottom = 300 - (int) mPaint.getStrokeWidth();
        canvas.drawRect(rect, mPaint);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d("TAG", "draw");
    }

    /**
     * 最终的宽度和高度
     * 单一View仅计算View本身的位置
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.d("TAG", "layout");
        // 人为设置getWidth() / getHeight()获得的宽/高 总比 getMeasuredWidth() / getMeasuredHeight()获取的宽/高大100px
        Log.d("TAG", "最终的宽度和高度:"+getWidth()+","+getHeight());
    }

    /**
     * onLayout()在View中就是个空实现，由于单一的View没有子View，因此不需要确定子View的布局，所以onLayout()也无需实现。
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("TAG", "onLayout");
    }
}
