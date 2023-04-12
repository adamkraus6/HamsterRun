package edu.sdsmt.Hamster_Run_Kraus_Adam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Bitmap hamsterImage;
    private Paint hamsterPaint;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        hamsterImage = BitmapFactory.decodeResource(getResources(), R.drawable.hamster);
        hamsterPaint = new Paint();
    }

    public void onDraw(Canvas c) {
        // draw grid

        // draw hamster
        drawHamster(c);
    }

    private void drawHamster(Canvas c) {
        float size = (float)Math.max(getWidth(), getHeight());
        size = size * 0.15f;

        int areaWidth = getWidth()/Game.GRID_SIZE;
        int areaHeight = getHeight()/Game.GRID_SIZE;

        float imageSizeW = hamsterImage.getWidth();
        float imageSizeH = hamsterImage.getHeight();
        Position loc = new Position();
        float scaleFactor = size/Math.max(imageSizeW, imageSizeH);

        float x = loc.x * areaWidth + areaWidth/2 - imageSizeW/2*scaleFactor;
        float y = loc.y * areaHeight + areaHeight/2 - imageSizeH/2*scaleFactor;

        c.save();
        c.translate(x, y);
        c.scale(scaleFactor, scaleFactor);
        c.drawBitmap(hamsterImage, 0, 0, hamsterPaint);
        c.restore();
    }
}
