package harsh.apps.bikecomputerwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import harsh.apps.bikecomputerwidget.animation.AnimationManager;
import harsh.apps.bikecomputerwidget.animation.AnimationValue;


/**
 * Created by Harsh Rastogi on 8/3/19.
 */
public class BikeComputer extends View implements AnimationManager.AnimationListener {

    private BikeComputerManager computerManager;
    private boolean animateOnInit;

    public BikeComputer(Context context) {
        this(context, null);
    }

    public BikeComputer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BikeComputer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        computerManager = new BikeComputerManager(context, context.obtainStyledAttributes(attrs,
                R.styleable.BikeComputer, defStyleAttr, 0), this);
//        computerManager.drawer().setIsInEditMode();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        computerManager.drawer().draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        int defaultDiameter = 200;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = defaultDiameter;

            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = defaultDiameter;

            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }

        int diameter = Math.max(width, height);
        computerManager.drawer().setDimensions(diameter);
        setMeasuredDimension(diameter, diameter);
    }

    public void animateOnInitialization(boolean animate) {
        animateOnInit = animate;
        if (animateOnInit) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerManager.animate();
                }
            }, 500);
        }
    }


    @Override
    public void onAnimationUpdated(@NonNull AnimationValue value) {
        invalidate();
    }
}