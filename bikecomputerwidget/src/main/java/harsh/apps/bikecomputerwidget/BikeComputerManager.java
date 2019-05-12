package harsh.apps.bikecomputerwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;

import harsh.apps.bikecomputerwidget.draw.DrawManager;

/**
 * Created by Harsh Rastogi on 2/4/19.
 */
class BikeComputerManager {
    private DrawManager drawManager;

    BikeComputerManager(@NonNull Context context, @NonNull TypedArray a) {
        drawManager = new DrawManager(context, a);
    }

    DrawManager drawer() {
        return drawManager;
    }

}
