package harsh.apps.bikecomputerwidget.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

import harsh.apps.bikecomputerwidget.draw.Computer;

public class AnimationManager {

    private static final String ANGLE_SPEED_ARC = "speed_arc_angle";
    private static final String ANGLE_MAX_SPEED_ARC = "max_speed_arc_angle";
    private static final String ANGLE_BOUNDARY_ARC = "boundary_arc_angle";
    public static final int START_ANIM_DURATION = 800;
    private AnimationValue animationValue;

    public interface AnimationListener {
        void onAnimationUpdated(@NonNull AnimationValue value);
    }

    private Computer computer;
    private AnimatorSet animatorSet;
    private AnimationListener listener;

    public AnimationManager(@NonNull Computer computer, @NonNull AnimationListener listener) {
        this.computer = computer;
        this.animatorSet = new AnimatorSet();
        this.listener = listener;
        this.animationValue = new AnimationValue();
    }


    public void animate() {
        this.animatorSet.playTogether(createAnimatorList());
        this.animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.playTogether(createReverseAnimatorList());
                animatorSet1.start();
            }
        });
        this.animatorSet.start();
    }

    private List<Animator> createAnimatorList() {
        List<Animator> list = new ArrayList<>();
        list.add(speedAnimator());
        list.add(boundaryAnimator());
        list.add(maxSpeedAnimator());
        return list;
    }

    private List<Animator> createReverseAnimatorList() {
        List<Animator> list = new ArrayList<>();
        list.add(reverseSpeedAnimator());
        list.add(reverseMaxSpeedAnimator());
        return list;
    }


    private Animator speedAnimator() {
        ValueAnimator speedAnimator = new ValueAnimator();
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(ANGLE_SPEED_ARC, 0, 270);
        speedAnimator.setValues(propertyValuesHolder);
        speedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimationManager.this.onAnimationUpdate(animation);
            }
        });
        speedAnimator.setDuration(START_ANIM_DURATION);
        speedAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return speedAnimator;
    }

    private Animator boundaryAnimator() {
        ValueAnimator borderAnimator = new ValueAnimator();
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(ANGLE_BOUNDARY_ARC, 0, 270);
        borderAnimator.setValues(propertyValuesHolder);
        borderAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimationManager.this.onAnimationUpdate(animation);
            }
        });
        borderAnimator.setDuration(START_ANIM_DURATION);
        borderAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return borderAnimator;
    }

    private Animator maxSpeedAnimator() {
        ValueAnimator maxSpeedAnimator = new ValueAnimator();
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(ANGLE_MAX_SPEED_ARC, 0, 270);
        maxSpeedAnimator.setValues(propertyValuesHolder);
        maxSpeedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimationManager.this.onAnimationUpdate(animation);
            }
        });
        maxSpeedAnimator.setDuration(START_ANIM_DURATION - 100);
        maxSpeedAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return maxSpeedAnimator;
    }

    private Animator reverseSpeedAnimator() {
        ValueAnimator reverseSpeedAnimator = new ValueAnimator();
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(ANGLE_SPEED_ARC, 270, 0);
        reverseSpeedAnimator.setValues(propertyValuesHolder);
        reverseSpeedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimationManager.this.onAnimationUpdate(animation);
            }
        });
        reverseSpeedAnimator.setDuration(START_ANIM_DURATION);
        reverseSpeedAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return reverseSpeedAnimator;
    }

    private Animator reverseMaxSpeedAnimator() {
        ValueAnimator reverseMaxSpeedAnimator = new ValueAnimator();
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(ANGLE_MAX_SPEED_ARC, 270, 0);
        reverseMaxSpeedAnimator.setValues(propertyValuesHolder);
        reverseMaxSpeedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimationManager.this.onAnimationUpdate(animation);
            }
        });
        reverseMaxSpeedAnimator.setDuration(START_ANIM_DURATION + 100);
        reverseMaxSpeedAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return reverseMaxSpeedAnimator;
    }


    private void onAnimationUpdate(@Nullable ValueAnimator animator) {

        if (animator == null || listener == null) {
            return;
        }

        if (animator.getAnimatedValue(ANGLE_SPEED_ARC) != null) {

            animationValue.setSpeedSweepAngle((float) animator.getAnimatedValue(ANGLE_SPEED_ARC));
        }

        if (animator.getAnimatedValue(ANGLE_BOUNDARY_ARC) != null) {

            animationValue.setBoundarySweepAngle((float) animator.getAnimatedValue(ANGLE_BOUNDARY_ARC));
        }
        if (animator.getAnimatedValue(ANGLE_MAX_SPEED_ARC) != null) {

            animationValue.setMaxSpeedSweepAngle((float) animator.getAnimatedValue(ANGLE_MAX_SPEED_ARC));
        }

        listener.onAnimationUpdated(animationValue);
    }
}
