package harsh.apps.bikecomputerwidget.draw;

/**
 * Created by Harsh Rastogi on 1/4/19.
 */
public class Computer {

    private int size;
    private float padding;
    private float speedTextSize;
    private float maxSpeedTextSize;
    private float distanceTextSize;
    private float durationTextSize;
    private float speedUnitTextSize;
    private float distanceUnitTextSize;
    private float durationUnitTextSize;
    private float boundaryStrokeWidth;
    private float speedStrokeWidth;
    private float maxSpeedStrokeWidth;


    int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }

    float getPadding() {
        return padding;
    }

    void setPadding(float padding) {
        this.padding = padding;
    }

    float getSpeedTextSize() {
        return speedTextSize;
    }

    void setSpeedTextSize(float speedTextSize) {
        this.speedTextSize = speedTextSize;
    }

    float getMaxSpeedTextSize() {
        return maxSpeedTextSize;
    }

    void setMaxSpeedTextSize(float maxSpeedTextSize) {
        this.maxSpeedTextSize = maxSpeedTextSize;
    }

    float getDistanceTextSize() {
        return distanceTextSize;
    }

    void setDistanceTextSize(float distanceTextSize) {
        this.distanceTextSize = distanceTextSize;
    }

    float getDurationTextSize() {
        return durationTextSize;
    }

    void setDurationTextSize(float durationTextSize) {
        this.durationTextSize = durationTextSize;
    }

    float getSpeedUnitTextSize() {
        return speedTextSize * 0.6f;
    }

    void setSpeedUnitTextSize(float speedUnitTextSize) {
        this.speedUnitTextSize = speedUnitTextSize;
    }

    float getDistanceUnitTextSize() {
        return distanceUnitTextSize * 0.66f;
    }

    void setDistanceUnitTextSize(float distanceUnitTextSize) {
        this.distanceUnitTextSize = distanceUnitTextSize;
    }

    float getDurationUnitTextSize() {
        return durationTextSize * 0.4f;
    }

    void setDurationUnitTextSize(float durationUnitTextSize) {
        this.durationUnitTextSize = durationUnitTextSize;
    }

    float getBoundaryStrokeWidth() {
        return boundaryStrokeWidth;
    }

    void setBoundaryStrokeWidth(float boundaryStrokeWidth) {
        this.boundaryStrokeWidth = boundaryStrokeWidth;
    }

    float getSpeedStrokeWidth() {
        return speedStrokeWidth;
    }

    void setSpeedStrokeWidth(float speedStrokeWidth) {
        this.speedStrokeWidth = speedStrokeWidth;
    }

    float getMaxSpeedStrokeWidth() {
        return maxSpeedStrokeWidth;
    }

    void setMaxSpeedStrokeWidth(float maxSpeedStrokeWidth) {
        this.maxSpeedStrokeWidth = maxSpeedStrokeWidth;
    }

    float getViewCenter() {
        return size / 2;
    }
}
