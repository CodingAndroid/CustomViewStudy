package code.com.qqstepview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: lihui1
 * Date: 2018/12/14
 * Desc: 自定义View-QQ步数
 */

public class QQStepView extends View{

    private int fixedArcColor = Color.BLUE;
    private int dynamicArcColor = Color.RED;
    private int arcBorderWidth = 20;
    private int stepTextSize = 20;
    private int stepTextColor = Color.YELLOW;

    //固定弧长画笔
    private Paint mFixedPaint;
    //运动弧长画笔
    private Paint mDynamicPaint;
    //文字画笔
    private Paint mTextPaint;

    private float maxStep;
    private float currentStep;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = getResources().obtainAttributes(attrs, R.styleable.QQStepView);
        fixedArcColor = array.getColor(R.styleable.QQStepView_fixed_arc_color, fixedArcColor);
        dynamicArcColor = array.getColor(R.styleable.QQStepView_dynamic_arc_color, dynamicArcColor);
        arcBorderWidth = (int) array.getDimension(R.styleable.QQStepView_arc_border_width, arcBorderWidth);
        stepTextSize = (int) array.getDimension(R.styleable.QQStepView_step_text_size, stepTextSize);
        stepTextColor = array.getColor(R.styleable.QQStepView_step_text_color, stepTextColor);
        array.recycle();
        initView();
    }

    private void initView(){
        mFixedPaint = new Paint();
        mFixedPaint.setAntiAlias(true);
        mFixedPaint.setColor(fixedArcColor);
        mFixedPaint.setStrokeWidth(arcBorderWidth);
        mFixedPaint.setStyle(Paint.Style.STROKE);
        mFixedPaint.setStrokeCap(Paint.Cap.ROUND);

        mDynamicPaint = new Paint();
        mDynamicPaint.setAntiAlias(true);
        mDynamicPaint.setColor(dynamicArcColor);
        mDynamicPaint.setStrokeWidth(arcBorderWidth);
        mDynamicPaint.setStyle(Paint.Style.STROKE);
        mDynamicPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(stepTextColor);
        mTextPaint.setTextSize(stepTextSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * onMeasure根据MeasureSpec确定View的宽和高
     * MeasureSpec 代表32位int值, 高2位代表SpecMode, 低30位代表SpecSize, SpecMode指的是测量模式, SpecSize指的是测量大小。
     * SpecMode有如下3种模式:
     * UNSPECIFIED: 未指定模式, View想多大就多大, 父容器不做限制, 一般用于系统内部的测量;
     * AT_MOST: 最大模式, 对应于wrap_parent属性, 子View的最终大小是父View指定的SpecSize值, 并且子View的大小不能大于这个值;
     * EXACTLY: 精确模式, 对应于match_parent属性和具体的数值, 父容器测量出View所需要的大小, 也就是SpecSize的值;
     * MeasureSpec实现为int以减少对象内存分配, 通过使用二进制, 将测量模式&测量大小打包成int值, 并提供打包和解包的方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST){
            widthSize = heightSize = 450;
        } else if (widthMode == MeasureSpec.EXACTLY){
            widthSize = heightSize = Math.min(widthSize, heightSize);
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rect = new RectF(arcBorderWidth/2, arcBorderWidth/2, getWidth()-arcBorderWidth/2, getHeight());
        canvas.drawArc(rect, 135, 270, false, mFixedPaint);
        canvas.drawArc(rect, 135, getCurrentStep()/getMaxStep() * 270, false, mDynamicPaint);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseLine = rect.centerY() + distance;
        canvas.drawText(String.valueOf((int)getCurrentStep()),  rect.centerX(), baseLine, mTextPaint);
    }

    public float getMaxStep() {
        return maxStep;
    }

    public void setMaxStep(float maxStep) {
        this.maxStep = maxStep;
    }

    public float getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(float currentStep) {
        this.currentStep = currentStep;
        invalidate();
    }
}
/**
 * Invalidate the whole view. If the view is visible,
 * onDraw(android.graphics.Canvas) will be called at some point in the future.
 * <p>
 * This must be called from a UI thread. To call from a non-UI thread, call
 * postInvalidate().
 */
