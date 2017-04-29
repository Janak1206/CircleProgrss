package com.samplecircleprogrss;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.circleProgress.CircleProgressBar;


public class MainActivity extends Activity {

    int progress = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleProgressBar circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgress);

        //circleProgressBar.setMainStrokeWidth(60);
        //circleProgressBar.setCircleStrokeWidth(60);
        //circleProgressBar.setCircleBgStrokeWidth(60);

        //circleProgressBar.setMaincircleColor(getResources().getColor(R.color.clg_top_bottom_dark));
        //circleProgressBar.setcircleColor(getResources().getColor(R.color.clg_per_green_dark));
        //circleProgressBar.setcircleBgColor(getResources().getColor(R.color.clg_white));

        //circleProgressBar.setMin(10);
        //circleProgressBar.setMax(100);

        //Without Animation
        circleProgressBar.setProgress(progress);
        //With Animation
        //circleProgressBar.setProgressWithAnimation(progress);

        TextView tv_precentage = (TextView) findViewById(R.id.tv_precentage);
        tv_precentage.setText(progress + "%");
    }
}
