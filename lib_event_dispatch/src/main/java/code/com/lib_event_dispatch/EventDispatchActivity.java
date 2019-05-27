package code.com.lib_event_dispatch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDispatchActivity extends Activity{

    @BindView(R2.id.custom_tv)
    CustomTextView mCustomTextView;
    @BindView(R2.id.custom_vp)
    CustomViewGroup mCustomViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        ButterKnife.bind(this);

        mCustomTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "View onClick");
            }
        });
        mCustomTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "View onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("TAG", "View onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "View onTouch ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("TAG", "View onTouch ACTION_CANCEL");
                        break;
                }
                return false;
            }
        });

        mCustomViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "ViewGroup onClick");
            }
        });
        mCustomViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "ViewGroup onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("TAG", "ViewGroup onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "ViewGroup onTouch ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("TAG", "ViewGroup onTouch ACTION_CANCEL");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "Activity dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "Activity dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "Activity dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "Activity dispatchTouchEvent ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "Activity onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "Activity onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "Activity onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "Activity onTouchEvent ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
