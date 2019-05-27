package code.com.customviewstudy;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aries.ui.view.radius.RadiusTextView;

import code.com.customviewstudy.widgets.CustomView;
import code.com.lib_event_dispatch.EventDispatchActivity;
import code.com.qqstepview.QQStepView;

public class MainActivity extends Activity {

    private QQStepView mQQStepView;

    private CustomView mCustomView;

    private RadiusTextView mRadiusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadiusTextView = findViewById(R.id.rv_event_dispatch);
        mRadiusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventDispatchActivity.class);
                startActivity(intent);
            }
        });
        mQQStepView = findViewById(R.id.qqStepView);
        Log.d("TAG", "onCreate-mQQStepView的宽/高="+mQQStepView.getWidth()+","+mQQStepView.getHeight());
        mQQStepView.setMaxStep(100);
        //ValueAnimator-Android 属性动画中的核心类
        ValueAnimator animator = ValueAnimator.ofFloat(0, 3000);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mQQStepView.setCurrentStep(Float.valueOf(animation.getAnimatedValue().toString())/3000*100);
            }
        });
        animator.start();

        mCustomView = findViewById(R.id.customView);
        mCustomView.setAdapter(new Adapter() {
            @Override
            public int getCount() {
                return 8;
            }

            @Override
            public View getView(final int position, ViewGroup parent) {
                ImageView imageView = new ImageView(parent.getContext());
                imageView.setImageResource(R.mipmap.ic_launcher_round);
                if (position == 0) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "position--" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return imageView;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart-mQQStepView的宽/高="+mQQStepView.getWidth()+","+mQQStepView.getHeight());
        mQQStepView.post(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "onStart-mQQStepView的宽/高="+mQQStepView.getWidth()+","+mQQStepView.getHeight());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume-mQQStepView的宽/高="+mQQStepView.getWidth()+","+mQQStepView.getHeight());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            Log.d("TAG", "onWindowFocusChanged-mQQStepView的宽/高="+mQQStepView.getWidth()+","+mQQStepView.getHeight());
        }
    }
}
