package com.example.triangledialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * thanks https://github.com/michaelye/EasyDialog
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TriangleViewWidget  view = (TriangleViewWidget) findViewById(R.id.triangle_view);

        System.out.println("shiming "+ StatusBarUtils.getHeight(this));
        System.out.println("shiming "+ StatusBarUtils.isFlymeOs4x());


//        view.setColor(Color.parseColor("#8BC34A"));
//        mTriangleView.setColor(mBubbleColor);
//        mTriangleView.setGravity(mGravity == Gravity.TOP ? Gravity.BOTTOM : Gravity.TOP);
    }
}
