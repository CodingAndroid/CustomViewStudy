package code.com.lib_event_dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Author: lihui1
 * Date: 2019/5/24
 * Desc:
 */

public class CustomViewGroup extends RelativeLayout{

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "ViewGroup dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "ViewGroup dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "ViewGroup dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "ViewGroup dispatchTouchEvent ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "ViewGroup onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "ViewGroup onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "ViewGroup onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "ViewGroup onTouchEvent ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "ViewGroup onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "ViewGroup onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "ViewGroup onInterceptTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "ViewGroup onInterceptTouchEvent ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
