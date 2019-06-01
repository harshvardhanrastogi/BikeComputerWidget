package harsh.apps.bikecomputerwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import harsh.apps.bikecomputerwidget.animation.AnimationManager;
import harsh.apps.bikecomputerwidget.animation.AnimationValue;
import harsh.apps.bikecomputerwidget.draw.DrawManager;

/**
 * Created by Harsh Rastogi on 2/4/19.
 */
class BikeComputerManager implements AnimationManager.AnimationListener {
    private DrawManager drawManager;
    private AnimationManager animationManager;
    private AnimationManager.AnimationListener listener;

    BikeComputerManager(@NonNull Context context, @NonNull TypedArray a, @Nullable AnimationManager.AnimationListener listener) {
        drawManager = new DrawManager(context, a);
        animationManager = new AnimationManager(drawManager.computer(), this);
        this.listener = listener;
    }

    DrawManager drawer() {
        return drawManager;
    }

    void animate() {
        animationManager.animate();
    }

    @Override
    public void onAnimationUpdated(@NonNull AnimationValue value) {
        drawManager.updateValue(value);
        if (listener != null) {
            listener.onAnimationUpdated(value);
        }
    }
}
