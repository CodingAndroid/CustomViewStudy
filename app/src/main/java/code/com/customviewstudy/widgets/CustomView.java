package code.com.customviewstudy.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import code.com.customviewstudy.Adapter;
import code.com.customviewstudy.R;

/**
 * Author: lihui1
 * Date: 2019/2/21
 * Desc: 自定义ViewGroup-访简书赞赏平放
 */

public class CustomView extends ViewGroup{

    private float mViewMarginRate = 0.5f;

    private boolean mReversed = true;

    private Adapter mAdapter;

    private DataSetObserver mObserver;


    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        mViewMarginRate = array.getFloat(R.styleable.CustomView_lineViewMarginRate, mViewMarginRate);
        mReversed = array.getBoolean(R.styleable.CustomView_lineIsReverse, mReversed);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽、高测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //宽、高测试大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        int width = 0;
        int height = 0;
        for (int i = 0; i < childCount; i++){
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int measureWidth = child.getMeasuredWidth();
            int measureHeight = child.getMeasuredHeight();
            //计算控件的宽度
            if (i == 0){
                width = measureWidth;
            } else {
                width += (int) (mViewMarginRate * width + 0.5f);
            }
            height = Math.max(height, measureHeight);
        }
        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingTop() + getPaddingBottom();
        //设置自己的宽、高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        //摆放
        for (int i = 0; i < childCount; i++){
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (i > 0){
                paddingLeft += (int) (mViewMarginRate * width + 0.5f);
            }
            child.layout(paddingLeft, paddingTop, paddingLeft+width, paddingTop+height);
        }
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (!mReversed){
            return i;
        }
        return childCount - 1 - i;
    }

    public void setAdapter(Adapter adapter){

        if (mAdapter != null && mObserver != null){
            mAdapter.unRegisterDataSetObserver(mObserver);
            mAdapter = null;
            mObserver = null;
        }

        if (adapter == null){
            throw new NullPointerException("FlowBaseAdapter is null");
        }

        mAdapter = adapter;
        resetLayout();
        mObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                resetLayout();
            }
        };
        mAdapter.registerDataSetObserver(mObserver);
    }

    private void resetLayout() {
        removeAllViews();
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = mAdapter.getView(i, this);
            addView(view);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mAdapter != null && mObserver != null) {
            mAdapter.unRegisterDataSetObserver(mObserver);
            mAdapter = null;
            mObserver = null;

        }
        super.onDetachedFromWindow();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
