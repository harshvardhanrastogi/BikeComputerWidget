package harsh.apps.bikecomputerwidget.draw;

/**
 * Created by Harsh Rastogi on 1/4/19.
 */
public class Computer {

    private int size;
    private float padding;
    private float input1TextSize;
    private float input2TextSize;
    private float centerInput1TextSize;
    private float centerInput2TextSize;
    private float input1UnitTextSize;
    private float centerInput1UnitTextSize;
    private float centerInput2UnitTextSize;
    private float boundaryStrokeWidth;
    private float input1StrokeWidth;
    private float input2StrokeWidth;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public float getInput1TextSize() {
        return input1TextSize;
    }

    public void setInput1TextSize(float input1TextSize) {
        this.input1TextSize = input1TextSize;
    }

    public float getInput2TextSize() {
        return input2TextSize;
    }

    public void setInput2TextSize(float input2TextSize) {
        this.input2TextSize = input2TextSize;
    }

    public float getCenterInput1TextSize() {
        return centerInput1TextSize;
    }

    public void setCenterInput1TextSize(float centerInput1TextSize) {
        this.centerInput1TextSize = centerInput1TextSize;
    }

    public float getCenterInput2TextSize() {
        return centerInput2TextSize;
    }

    public void setCenterInput2TextSize(float centerInput2TextSize) {
        this.centerInput2TextSize = centerInput2TextSize;
    }

    public float getInput1UnitTextSize() {
        return input1UnitTextSize;
    }

    public void setInput1UnitTextSize(float input1UnitTextSize) {
        this.input1UnitTextSize = input1UnitTextSize;
    }

    public float getCenterInput1UnitTextSize() {
        return centerInput1UnitTextSize;
    }

    public void setCenterInput1UnitTextSize(float centerInput1UnitTextSize) {
        this.centerInput1UnitTextSize = centerInput1UnitTextSize;
    }

    public float getCenterInput2UnitTextSize() {
        return centerInput2UnitTextSize;
    }

    public void setCenterInput2UnitTextSize(float centerInput2UnitTextSize) {
        this.centerInput2UnitTextSize = centerInput2UnitTextSize;
    }

    public float getBoundaryStrokeWidth() {
        return boundaryStrokeWidth;
    }

    public void setBoundaryStrokeWidth(float boundaryStrokeWidth) {
        this.boundaryStrokeWidth = boundaryStrokeWidth;
    }

    public float getInput1StrokeWidth() {
        return input1StrokeWidth;
    }

    public void setInput1StrokeWidth(float input1StrokeWidth) {
        this.input1StrokeWidth = input1StrokeWidth;
    }

    public float getInput2StrokeWidth() {
        return input2StrokeWidth;
    }

    public void setInput2StrokeWidth(float input2StrokeWidth) {
        this.input2StrokeWidth = input2StrokeWidth;
    }

    public float getViewCenter() {
        return size / 2;
    }
}
