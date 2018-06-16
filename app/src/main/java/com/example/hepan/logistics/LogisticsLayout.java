package com.example.hepan.logistics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LogisticsLayout extends RelativeLayout {

    Paint linePaint;
    Paint circlePaint;
    // 左侧绘制范围的宽度,此值作为基准,
    float totalWidth;
    float totalHeight;
    float radius;
    float centerX;
    float marginTop;
    float dottedLen;

    public LogisticsLayout(Context context) {
        this(context, null);
    }

    public LogisticsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogisticsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LogisticsLayout);
        try {
            totalWidth = array.getDimension(R.styleable.LogisticsLayout_left_margin, 20);
            centerX = totalWidth * 0.5f;
            radius = totalWidth * 0.1f;
            marginTop = totalWidth * 0.3f;
            dottedLen = totalWidth / 15;
        } finally {
            array.recycle();
        }
        init();
    }


    private void init() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeWidth(3);
        linePaint.setColor(Color.GRAY);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.GRAY);

        // 清除标记,因为 ViewGroup 作为容器,默认不会触发 onDraw() 方法
        setWillNotDraw(false);
        //关闭硬件加速 这里非常重要, 不然虚线,shape等没有效果
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        totalHeight = getHeight();
        System.out.println(totalWidth + " " + totalHeight + "hp");
        // 画虚线
        drawLine(canvas);
        // 画圆点
        drawCircle(canvas);
    }


    private void drawLine(Canvas canvas) {
        linePaint.setPathEffect(new DashPathEffect(new float[]{dottedLen, dottedLen}, 2));
        if (state == State.STATE_HEADER) {
            canvas.drawLine(centerX, marginTop, centerX, totalHeight, linePaint);
        } else if (state == State.STATE_FOOTER) {
            canvas.drawLine(centerX, 0, centerX, marginTop, linePaint);
        } else {
            canvas.drawLine(centerX, 0, centerX, totalHeight, linePaint);
        }
    }

    private void drawCircle(Canvas canvas) {
        if (state == State.STATE_HEADER) {
            circlePaint.setColor(Color.RED);
            canvas.drawCircle(centerX, marginTop, radius, circlePaint);
        } else {
            canvas.drawCircle(centerX, marginTop, radius, circlePaint);
        }
    }

    @State
    private int state = State.STATE_NORMAL;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({State.STATE_HEADER, State.STATE_NORMAL, State.STATE_FOOTER})
    public @interface State {
        int STATE_HEADER = 0;
        int STATE_NORMAL = 1;
        int STATE_FOOTER = 2;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;

    }
}
