package harsh.apps.bikecomputerwidget.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;

import java.util.Locale;

import harsh.apps.bikecomputerwidget.R;
import harsh.apps.bikecomputerwidget.animation.AnimationValue;

/**
 * Created by Harsh Rastogi on 1/4/19.
 */
class DrawController {
    private static final String UNIT_SPEED = "kmph";
    private static final String TAG = "DrawController";
    private static final String DISTANCE_UNIT = "kms";
    private static final String HOUR = "h";
    private static final String MINUTE = "m";

    private final Context context;
    private final Computer computer;
    private Paint speedArcPaint;
    private Paint maxSpeedArcPaint;
    private Paint boundaryArcPaint;
    private TextPaint speedTextPaint;
    private TextPaint distanceUnitTextPaint;
    private TextPaint maxSpeedTextPaint;
    private TextPaint durationTextPaint;
    private TextPaint durationUnitTextPaint;
    private TextPaint durationSecondsTextPaint;
    private TextPaint distanceTextPaint;
    private RectF speedArcRectF = new RectF();
    private RectF maxSpeedArcRectF = new RectF();
    private RectF boundaryArcRectF = new RectF();
    private float speedArcPadding;
    private float speedArcSweepAngle;
    private float boundaryArcSweepAngle = 0f;
    private float speed;
    private float maxSpeed;
    private float speedTextX;
    private float speedTextY;
    private float speedUnitTextX;
    private float speedUnitTextY;
    private float maxSpeedTextX;
    private float maxSpeedTextY;
    private float maxSpeedArcStartAngle = 90;
    private float maxSpeedArcSweepAngle;
    private long rideStartTime;
    private float durationHourUnitX;
    private float durationHourUnitY;
    private float durationHourX;
    private float durationHourY;
    private float durationMinX;
    private float durationMinY;
    private float durationMinUnitX;
    private float durationMinUnitY;
    private float durationSecX;
    private float durationSecY;
    private int hour;
    private int min;
    private int sec;
    private boolean startDurationUpdate;
    private float distance = 0.0f;
    private float distanceTextX;
    private float distanceTextY;
    private float distanceUnitTextX;
    private float distanceUnitTextY;
    private float computerMaxSpeed = 60f;
    private float defaultArcStartAngle = 90f;
    private AnimationValue value;


    DrawController(@NonNull Context context, @NonNull Computer computer, @Nullable TypedArray a) {
        this.context = context;
        this.computer = computer;
        init(a);
    }

    void draw(@NonNull Canvas canvas) {
        drawArcs(canvas);
        drawText(canvas);
    }

    private void drawText(@NonNull Canvas canvas) {
        canvas.drawText(getFormattedSpeed(), speedTextX, speedTextY, speedTextPaint);
        canvas.drawText(UNIT_SPEED, speedUnitTextX, speedUnitTextY, distanceUnitTextPaint);
        canvas.drawText(String.valueOf((int) maxSpeed), maxSpeedTextX, maxSpeedTextY, maxSpeedTextPaint);
        drawDurationText(canvas);
        canvas.drawText(padDistanceWithZero(distance), distanceTextX, distanceTextY, distanceTextPaint);
        canvas.drawText(DISTANCE_UNIT, distanceUnitTextX, distanceUnitTextY, durationUnitTextPaint);
    }

    @NonNull
    private String getFormattedSpeed() {
        return String.format(Locale.ENGLISH, "%.1f", speed);
    }

    private void drawArcs(@NonNull Canvas canvas) {
        if (value != null) {
            canvas.drawArc(maxSpeedArcRectF, maxSpeedArcStartAngle, maxSpeedArcSweepAngle, false, maxSpeedArcPaint);
            speedArcSweepAngle = value.getSpeedSweepAngle();
            boundaryArcSweepAngle = value.getBoundarySweepAngle();
            maxSpeedArcSweepAngle = value.getMaxSpeedSweepAngle();
            canvas.drawArc(speedArcRectF, defaultArcStartAngle, speedArcSweepAngle, false, speedArcPaint);
            canvas.drawArc(boundaryArcRectF, defaultArcStartAngle, boundaryArcSweepAngle, false, boundaryArcPaint);
            canvas.drawArc(maxSpeedArcRectF, maxSpeedArcStartAngle, maxSpeedArcSweepAngle, false, maxSpeedArcPaint);

        } else {
            canvas.drawArc(maxSpeedArcRectF, maxSpeedArcStartAngle, maxSpeedArcSweepAngle, false, maxSpeedArcPaint);
            canvas.drawArc(speedArcRectF, defaultArcStartAngle, speedArcSweepAngle, false, speedArcPaint);
            canvas.drawArc(boundaryArcRectF, defaultArcStartAngle, boundaryArcSweepAngle, false, boundaryArcPaint);
        }
    }


    private void init(@Nullable TypedArray a) {
        int textColor = 0;
        int speedArcColor = 0;
        int maxSpeedArcColor = 0;
        int boundaryColor = 0;
        try {
            if (a != null) {
                textColor = a.getColor(R.styleable.BikeComputer_textColor, getColor(R.color.textColor));
                speedArcColor = a.getColor(R.styleable.BikeComputer_speedStrokeColor, getColor(R.color.speedArcColor));
                maxSpeedArcColor = a.getColor(R.styleable.BikeComputer_maxSpeedStrokeColor, getColor(R.color.maxSpeedArcColor));
                boundaryColor = a.getColor(R.styleable.BikeComputer_boundaryColor, getColor(R.color.circumArcColor));
                computer.setPadding(a.getDimension(R.styleable.BikeComputer_android_padding, getDimension(R.dimen.bike_computer_widget_padding)));
                computer.setSpeedStrokeWidth(a.getDimension(R.styleable.BikeComputer_speedStrokeWidth, getDimension(R.dimen.speed_arc_stroke_width)));
                computer.setMaxSpeedStrokeWidth(a.getDimension(R.styleable.BikeComputer_maxSpeedStrokeWidth, getDimension(R.dimen.max_speed_arc_stroke_width)));
                computer.setBoundaryStrokeWidth(a.getDimension(R.styleable.BikeComputer_boundaryWidth, getDimension(R.dimen.max_speed_arc_stroke_width)));
                computer.setSpeedTextSize(a.getDimensionPixelSize(R.styleable.BikeComputer_speedTextSize, Math.round(getDimension(R.dimen.default_speed_text))));
                computer.setDistanceTextSize(a.getDimensionPixelSize(R.styleable.BikeComputer_distanceTextSize, Math.round(getDimension(R.dimen.distance_text))));
                computer.setDurationTextSize(a.getDimensionPixelSize(R.styleable.BikeComputer_durationTextSize, Math.round(getDimension(R.dimen.duration_text))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (a != null) {
                a.recycle();
            }
        }

        Typeface light = null;
        Typeface semiBold = null;

        try {
            light = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/open_sans_light.ttf");
            semiBold = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/open_sans_semi_bold.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        speedArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        speedArcPaint.setStyle(Paint.Style.STROKE);
        speedArcPaint.setStrokeWidth(computer.getSpeedStrokeWidth());
        speedArcPaint.setStrokeCap(Paint.Cap.BUTT);
        speedArcPaint.setColor(speedArcColor);

        maxSpeedArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maxSpeedArcPaint.setStyle(Paint.Style.STROKE);
        maxSpeedArcPaint.setStrokeWidth(computer.getMaxSpeedStrokeWidth());
        maxSpeedArcPaint.setStrokeCap(Paint.Cap.BUTT);
        maxSpeedArcPaint.setColor(maxSpeedArcColor);

        boundaryArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boundaryArcPaint.setStyle(Paint.Style.STROKE);
        boundaryArcPaint.setStrokeWidth(computer.getBoundaryStrokeWidth());
        boundaryArcPaint.setStrokeCap(Paint.Cap.BUTT);
        boundaryArcPaint.setColor(boundaryColor);

        speedTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        speedTextPaint.setTextSize(computer.getSpeedTextSize());
        speedTextPaint.setColor(textColor);
        speedTextPaint.setTypeface(light);

        distanceUnitTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        distanceUnitTextPaint.setTextSize(computer.getSpeedUnitTextSize());
        distanceUnitTextPaint.setColor(textColor);
        distanceUnitTextPaint.setTypeface(light);

        maxSpeedTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        maxSpeedTextPaint.setTextSize(getDimension(R.dimen.max_speed_text));
        maxSpeedTextPaint.setColor(textColor);
        maxSpeedTextPaint.setTypeface(semiBold);

        durationTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        durationTextPaint.setTextSize(computer.getDurationTextSize());
        durationTextPaint.setColor(textColor);
        durationTextPaint.setTypeface(light);

        durationUnitTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        durationUnitTextPaint.setTextSize(computer.getDurationUnitTextSize());
        durationUnitTextPaint.setColor(textColor);
        durationUnitTextPaint.setTypeface(light);

        durationSecondsTextPaint = new TextPaint(durationUnitTextPaint);
        durationSecondsTextPaint.setTextSize(computer.getDurationUnitTextSize());
        durationSecondsTextPaint.setTypeface(semiBold);

        distanceTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        distanceTextPaint.setTextSize(computer.getDistanceTextSize());
        distanceTextPaint.setColor(textColor);
        distanceTextPaint.setTypeface(light);
    }

    private int getColor(@ColorRes int res) {
        return ContextCompat.getColor(context, res);
    }

    private float getDimension(@DimenRes int res) {
        return context.getResources().getDimension(res);
    }

    void setDimensions(int size) {
        speedArcPadding = getDimension(R.dimen.speed_arc_padding);

        maxSpeedArcRectF.left = computer.getPadding() + computer.getMaxSpeedStrokeWidth() / 2;
        maxSpeedArcRectF.top = computer.getPadding() + computer.getMaxSpeedStrokeWidth() / 2;
        maxSpeedArcRectF.right = size - maxSpeedArcRectF.left;
        maxSpeedArcRectF.bottom = size - maxSpeedArcRectF.top;

        speedArcRectF.left = maxSpeedArcRectF.left + (computer.getMaxSpeedStrokeWidth() / 2)
                + speedArcPadding + (computer.getSpeedStrokeWidth() / 2);
        speedArcRectF.top = maxSpeedArcRectF.top + (computer.getMaxSpeedStrokeWidth() / 2)
                + speedArcPadding + (computer.getSpeedStrokeWidth() / 2);
        speedArcRectF.right = size - speedArcRectF.left;
        speedArcRectF.bottom = size - speedArcRectF.top;

        boundaryArcRectF.left = speedArcRectF.left + (computer.getSpeedStrokeWidth() / 2)
                + speedArcPadding + (computer.getBoundaryStrokeWidth() / 2);
        boundaryArcRectF.top = speedArcRectF.top + (computer.getSpeedStrokeWidth() / 2)
                + speedArcPadding + (computer.getBoundaryStrokeWidth() / 2);
        boundaryArcRectF.right = size - boundaryArcRectF.left;
        boundaryArcRectF.bottom = size - boundaryArcRectF.top;

        Rect textBounds = new Rect();

        speedTextX = computer.getViewCenter() + getDimension(R.dimen._8dp);
        speedTextY = speedArcRectF.bottom + getTextHeight(getFormattedSpeed(), speedTextPaint) / 2f;

        textBounds.setEmpty();
        distanceUnitTextPaint.getTextBounds(UNIT_SPEED, 0, UNIT_SPEED.length(), textBounds);
        speedUnitTextX = computer.getViewCenter() + getDimension(R.dimen._8dp);
        speedTextY -= textBounds.height() / 2f; // input1 text + unit horizontally align with the input1 arc width
        speedUnitTextY = speedTextY + textBounds.height();


        maxSpeedTextX = getMaxSpeedTextX(size, maxSpeed);
        maxSpeedTextY = getMaxSpeedTextY(size, maxSpeed);

        distanceTextX = computer.getViewCenter() - (getTextWidth(padDistanceWithZero(distance), distanceTextPaint)
                + getTextWidth(DISTANCE_UNIT, durationUnitTextPaint)) / 2f;
        distanceTextY = computer.getPadding() + computer.getMaxSpeedStrokeWidth() + speedArcPadding + computer.getSpeedStrokeWidth() +
                getTextHeight(padDistanceWithZero(distance), distanceTextPaint) + 32;

        distanceTextY = (boundaryArcRectF.top + 2.5f) + ((computer.getViewCenter() - getDurationTextHeight() / 2) -
                (boundaryArcRectF.top + 2.5f) + getTextHeight(padDistanceWithZero(distance), distanceTextPaint)) / 2;

        distanceUnitTextX = distanceTextX + getTextWidth(padDistanceWithZero(distance), distanceTextPaint) + 8;
        distanceUnitTextY = distanceTextY;
    }

    private int getTextWidth(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    private int getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    private String padTimeWithZero(int v) {
        if (v / 10 > 0) {
            return String.valueOf(v);
        }
        return "0" + v;
    }

    private String padDistanceWithZero(float v) {
        String str;
        int characteristics = (int) v;
        if (characteristics / 10 <= 0) {
            str = "0" + characteristics;
        } else {
            str = String.valueOf(characteristics);
        }

        int mantissa = (int) ((v - (int) v) * 100);

        if (mantissa / 10 <= 0) {
            str += ".0" + mantissa;
        } else {
            str += "." + mantissa;
        }

        return str;
    }

    private void calculateDurationTextWidth() {
        String hourStr = padTimeWithZero(hour);
        String minStr = padTimeWithZero(min);
        String secStr = padTimeWithZero(sec);

        float startX = computer.getPadding() + computer.getMaxSpeedStrokeWidth() + speedArcPadding + computer.getSpeedStrokeWidth();
        float endX = computer.getSize() - startX;
        float totalWidthAvail = endX - startX;
        float totalTextWidth = getTextWidth(hourStr, durationTextPaint) + 8
                + getTextWidth(HOUR, durationUnitTextPaint) + 8
                + getTextWidth(minStr, durationTextPaint) + 8
                + getTextWidth(MINUTE, durationUnitTextPaint) + 8
                + getTextWidth(secStr, durationSecondsTextPaint);
        startX = startX + (totalWidthAvail - totalTextWidth) / 2;

        float maxHeight = getDurationTextHeight();

        float endY = computer.getViewCenter() + maxHeight / 2;// + (maxHeight / 2f);

        durationHourX = startX;
        durationHourY = endY;

        durationHourUnitX = durationHourX + getTextWidth(hourStr, durationTextPaint) + 8;
        durationHourUnitY = endY;

        durationMinX = durationHourUnitX + getTextWidth(HOUR, durationUnitTextPaint) + 8;
        durationMinY = endY;

        durationMinUnitX = durationMinX + getTextWidth(minStr, durationTextPaint) + 8;
        durationMinUnitY = endY;

        durationSecX = durationMinUnitX + getTextWidth(MINUTE, durationUnitTextPaint) + 8;
        durationSecY = endY;

    }

    private float getDurationTextHeight() {
        float maxHeight = getTextHeight(String.valueOf(hour), durationTextPaint);
        maxHeight = Math.max(maxHeight, getTextHeight(String.valueOf(min), durationTextPaint));
        return maxHeight;
    }

    private float getMaxSpeedTextX(int diameter, float maxSpeed) {
        float centerX = getX(computer.getViewCenter(), (diameter / 2f) - computer.getPadding() - computer.getMaxSpeedStrokeWidth() / 2, maxSpeedArcSweepAngle + 6);
        float textWidth = getTextWidth(String.valueOf((int) maxSpeed), maxSpeedTextPaint);
        return centerX - textWidth / 2;
    }

    private float getMaxSpeedTextY(int diameter, float maxSpeed) {
        float centerY = getY(computer.getViewCenter(), (diameter / 2f) - computer.getPadding() - computer.getMaxSpeedStrokeWidth() / 2, maxSpeedArcSweepAngle + 6);
        float textHeight = getTextHeight(String.valueOf((int) maxSpeed), maxSpeedTextPaint);
        return centerY + textHeight / 2;
    }

    private float angle(float degrees) {
        return (float) Math.toRadians(degrees);
    }

    private float getX(float x, float length, float angle) {

        return (float) (x - length * Math.sin(angle(angle)));
    }

    private float getY(float y, float length, float angle) {

        return (float) (y + length * Math.cos(angle(angle)));
    }

    private void drawDurationText(Canvas canvas) {
        calculateDurationTextWidth();
        canvas.drawText(HOUR, durationHourUnitX, durationHourUnitY, durationUnitTextPaint);
        canvas.drawText(padTimeWithZero(hour), durationHourX, durationHourY, durationTextPaint);
        canvas.drawText(padTimeWithZero(min), durationMinX, durationMinY, durationTextPaint);
        canvas.drawText(MINUTE, durationMinUnitX, durationMinUnitY, durationUnitTextPaint);
        canvas.drawText(padTimeWithZero(sec), durationSecX, durationSecY, durationUnitTextPaint);
    }

    void setIsInEditMode() {
        maxSpeedArcSweepAngle = 45f;
        speedArcSweepAngle = 35f;
        maxSpeed = 25f;
        speed = 15.5f;
        hour = 0;
        min = 18;
        sec = 57;
        distance = 15.7f;
    }

    void updateValue(AnimationValue value) {
        this.value = value;
    }
}


