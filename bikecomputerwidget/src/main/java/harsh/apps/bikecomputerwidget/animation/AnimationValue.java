package harsh.apps.bikecomputerwidget.animation;

public class AnimationValue {
    float boundarySweepAngle;
    float speedSweepAngle;
    float maxSpeedSweepAngle;


    public float getBoundarySweepAngle() {
        return boundarySweepAngle;
    }

    public void setBoundarySweepAngle(float boundarySweepAngle) {
        this.boundarySweepAngle = boundarySweepAngle;
    }

    public float getSpeedSweepAngle() {
        return speedSweepAngle;
    }

    public void setSpeedSweepAngle(float speedSweepAngle) {
        this.speedSweepAngle = speedSweepAngle;
    }

    public float getMaxSpeedSweepAngle() {
        return maxSpeedSweepAngle;
    }

    public void setMaxSpeedSweepAngle(float maxSpeedSweepAngle) {
        this.maxSpeedSweepAngle = maxSpeedSweepAngle;
    }

    @Override
    public String toString() {
        return "AnimationValue{" +
                "boundarySweepAngle=" + boundarySweepAngle +
                ", speedSweepAngle=" + speedSweepAngle +
                ", maxSpeedSweepAngle=" + maxSpeedSweepAngle +
                '}';
    }
}
