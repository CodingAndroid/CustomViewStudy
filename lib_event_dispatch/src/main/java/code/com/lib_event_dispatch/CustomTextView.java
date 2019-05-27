package code.com.lib_event_dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Author: lihui1
 * Date: 2019/5/24
 * Desc:
 */

public class CustomTextView extends TextView{

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "View dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "View dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "View dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "View dispatchTouchEvent ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "View onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "View onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "View onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "View onTouchEvent ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
