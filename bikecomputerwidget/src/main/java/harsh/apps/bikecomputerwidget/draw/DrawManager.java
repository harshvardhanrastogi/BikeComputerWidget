package harsh.apps.bikecomputerwidget.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * Created by Harsh Rastogi on 2/4/19.
 */
public class DrawManager {

    private Computer computer;
    private DrawController controller;

    public DrawManager(@NonNull Context context, @NonNull TypedArray a) {

        computer = new Computer();
        controller = new DrawController(context, computer, a);
    }

    public void draw(@NonNull Canvas canvas) {
        controller.draw(canvas);
    }

    public void setIsInEditMode() {
        controller.setIsInEditMode();
    }

    public Computer computer() {
        return computer;
    }

    public void setDimensions(int size) {
        computer.setSize(size);
        controller.setDimensions(size);
    }


}
