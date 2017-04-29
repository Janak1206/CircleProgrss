package com.circleProgress;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;


public class CircleProgressBar extends View {

    /**
     * ProgressBar's line thickness
     */
    private float progress = 0;
    private int min = 0;
    private int max = 100;
    /**
     * Start the progress at 12 o'clock
     */
    private int startAngle = -90;
    private RectF rectF;

    private Paint maincirclePaint; // Background of Progress(both circle)
    private Paint circleBgPaint; // Progress Arc Background
    private Paint circlePaint; //Progrss Arc

    private float mainStrokeWidth = 50;
    private float CircleBgStrokeWidth = 10;
    private float CircleStrokeWidth = 10;

    private int maincircleColor = Color.BLACK;
    private int circleBgColor = Color.WHITE;
    private int circleColor = Color.GREEN;

    public float getMainStrokeWidth() {
        return mainStrokeWidth;
    }

    public void setMainStrokeWidth(float mainStrokeWidth) {
        this.mainStrokeWidth = mainStrokeWidth;
        maincirclePaint.setStrokeWidth(mainStrokeWidth);
        invalidate();
        requestLayout();//Because it should recalculate its bounds
    }

    public float getCircleBgStrokeWidth() {
        return CircleBgStrokeWidth;
    }

    public void setCircleBgStrokeWidth(float circleBgStrokeWidth) {
        CircleBgStrokeWidth = circleBgStrokeWidth;
        circleBgPaint.setStrokeWidth(circleBgStrokeWidth);
        invalidate();
        requestLayout();//Because it should recalculate its bounds
    }

    public float getCircleStrokeWidth() {
        return CircleStrokeWidth;
    }

    public void setCircleStrokeWidth(float circleStrokeWidth) {
        CircleStrokeWidth = circleStrokeWidth;
        circlePaint.setStrokeWidth(circleStrokeWidth);
        invalidate();
        requestLayout();//Because it should recalculate its bounds
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }


    public int getMaincircleColor() {
        return maincircleColor;
    }

    public void setMaincircleColor(int maincircleColor) {
        this.maincircleColor = maincircleColor;
        maincirclePaint.setColor(maincircleColor);
        invalidate();
        requestLayout();
    }

    public int getcircleBgColor() {
        return circleBgColor;
    }

    public void setcircleBgColor(int circleBgColor) {
        circleBgColor = circleBgColor;
        circleBgPaint.setColor(circleBgColor);
        invalidate();
        requestLayout();
    }

    public int getcircleColor() {
        return circleColor;
    }

    public void setcircleColor(int circleColor) {
        circleColor = circleColor;
        circlePaint.setColor(circleColor);
        invalidate();
        requestLayout();
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rectF = new RectF();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleProgressBar,
                0, 0);
        //Reading values from the XML layout
        try {
            mainStrokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_mainThickness, mainStrokeWidth);
            CircleBgStrokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circleBgThickness, CircleBgStrokeWidth);
            CircleStrokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circleThickness, CircleStrokeWidth);

            progress = typedArray.getFloat(R.styleable.CircleProgressBar_progress, progress);
            min = typedArray.getInt(R.styleable.CircleProgressBar_min, min);
            max = typedArray.getInt(R.styleable.CircleProgressBar_max, max);

            maincircleColor = typedArray.getInt(R.styleable.CircleProgressBar_mainColor, maincircleColor);
            circleBgColor = typedArray.getInt(R.styleable.CircleProgressBar_cirlceBgColor, circleBgColor);
            circleColor = typedArray.getInt(R.styleable.CircleProgressBar_cirlceColor, circleColor);

        } finally {
            typedArray.recycle();
        }

        maincirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maincirclePaint.setColor(maincircleColor);
        maincirclePaint.setStyle(Paint.Style.STROKE);
        maincirclePaint.setStrokeWidth(mainStrokeWidth);

        circleBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleBgPaint.setColor(circleBgColor);
        circleBgPaint.setStyle(Paint.Style.STROKE);
        circleBgPaint.setStrokeWidth(CircleBgStrokeWidth);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(CircleStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(rectF, maincirclePaint);
        canvas.drawOval(rectF, circleBgPaint);
        float angle = 360 * progress / max;
        canvas.drawArc(rectF, startAngle, angle, false, circlePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        rectF.set(0 + mainStrokeWidth / 2, 0 + mainStrokeWidth / 2, min - mainStrokeWidth / 2, min - mainStrokeWidth / 2);
    }

    /**
     * Lighten the given color by the factor
     *
     * @param color  The color to lighten
     * @param factor 0 to 4
     * @return A brighter color
     */
    public int lightenColor(int color, float factor) {
        float r = Color.red(color) * factor;
        float g = Color.green(color) * factor;
        float b = Color.blue(color) * factor;
        int ir = Math.min(255, (int) r);
        int ig = Math.min(255, (int) g);
        int ib = Math.min(255, (int) b);
        int ia = Color.alpha(color);
        return (Color.argb(ia, ir, ig, ib));
    }

    /**
     * Transparent the given color by the factor
     * The more the factor closer to zero the more the color gets transparent
     *
     * @param color  The color to transparent
     * @param factor 1.0f to 0.0f
     * @return int - A transplanted color
     */
    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Set the progress with an animation.
     * Note that the {@link ObjectAnimator} Class automatically set the progress
     * so don't call the {@link CircleProgressBar#setProgress(float)} directly within this method.
     *
     * @param progress The progress it should animate to it.
     */
    public void setProgressWithAnimation(float progress) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(1500);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }
}

