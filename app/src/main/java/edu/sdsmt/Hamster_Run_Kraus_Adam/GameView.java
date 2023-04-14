package edu.sdsmt.Hamster_Run_Kraus_Adam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Custom game view
 */
public class GameView extends View {
    public static final int LOC_INC = 5;
    private Game g;
    private Bitmap hamsterImage;
    private Paint hamsterPaint;
    private Paint gridPaint;
    private Paint gridBackPaint;
    private Paint tubePaint;
    private Paint foodPaint;
    private Paint zoomPaint;
    private Paint personPaint;
    private Paint barPaint;
    private Paint homePaint;
    private Paint barrierPaint;
    private int hamsterTint;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    /**
     * Creates paint objects, images, and tint
     * @param context
     */
    public void init(Context context) {
        // hamster image for drawing
        hamsterImage = BitmapFactory.decodeResource(getResources(), R.drawable.hamster);
        hamsterPaint = new Paint();

        // paints for grid and circles
        gridPaint = new Paint();
        gridPaint.setColor(Color.BLUE);
        gridPaint.setStrokeWidth(5);

        gridBackPaint = new Paint();
        gridBackPaint.setColor(Color.LTGRAY);

        tubePaint = new Paint();
        tubePaint.setColor(Color.BLUE);

        foodPaint = new Paint();
        foodPaint.setColor(Color.rgb(150, 75, 0));

        zoomPaint = new Paint();
        zoomPaint.setColor(Color.MAGENTA);

        personPaint = new Paint();
        personPaint.setColor(Color.YELLOW);

        barPaint = new Paint();
        barPaint.setColor(Color.DKGRAY);

        homePaint = new Paint();
        homePaint.setColor(Color.GREEN);

        barrierPaint = new Paint();
        barrierPaint.setColor(Color.BLACK);

        g = ((MainActivity)getContext()).getGame();

        hamsterTint = Color.WHITE;
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

    /**
     * Draws game area
     * @param c canvas
     */
    public void onDraw(Canvas c) {
        float size = (float)Math.max(getWidth(), getHeight());
        float offset = size/(Game.GRID_SIZE);

        // grid background
        c.drawRect(0, 0, size, size, gridBackPaint);

        // draw grid
        for(int i = 0; i <= Game.GRID_SIZE; i++) {
            c.drawLine(0, i*offset, size, i*offset, gridPaint);
            c.drawLine(i*offset, 0, i*offset, size, gridPaint);
        }

        // draw default tube circles
        for(int i = 0; i < Game.GRID_SIZE; i++) {
            for(int j = 0; j < Game.GRID_SIZE; j++) {
                drawCircle(c, i, j, 0, tubePaint);
            }
        }

        // draw specific location circles
        drawCircle(c, 2, 3, LOC_INC,  personPaint);

        drawCircle(c, 0, 4, LOC_INC, zoomPaint);
        drawCircle(c, 2, 1, LOC_INC, zoomPaint);

        drawCircle(c, 0, 1, LOC_INC, foodPaint);
        drawCircle(c, 0, 3, LOC_INC, foodPaint);
        drawCircle(c, 2, 2, LOC_INC, foodPaint);
        drawCircle(c, 3, 0, LOC_INC, foodPaint);

        // drawCircle(c, 2, 0, LOC_INC, barPaint);
        drawCircle(c, 3, 3, LOC_INC, barPaint);
        // drawCircle(c, 4, 3, LOC_INC, barPaint);
        // drawCircle(c, 2, 4, LOC_INC, barPaint);

        drawCircle(c, 4, 4, LOC_INC, homePaint);

        drawCircle(c, 2, 0, LOC_INC, barrierPaint);
        drawCircle(c, 2, 4, LOC_INC, barrierPaint);
        drawCircle(c, 4, 3, LOC_INC, barrierPaint);

        // draw hamster
        drawHamster(c);
    }

    /**
     * Draws a circle
     * @param c canvas
     * @param posX game grid x position
     * @param posY game grid y position
     * @param incR increase to circle radius
     * @param p paint for circle color
     */
    private void drawCircle(Canvas c, int posX, int posY, int incR, Paint p) {
        float size = (float)Math.max(getWidth(), getHeight());
        float offset = size/(Game.GRID_SIZE);

        float x = posX*offset + offset/2;
        float y = posY*offset + offset/2;
        float r = (offset/2)-30+incR;

        c.drawCircle(x, y, r, p);
    }

    /**
     * Draws hamster
     * @param c canvas
     */
    private void drawHamster(Canvas c) {
        float size = (float)Math.max(getWidth(), getHeight());
        size = size * 0.15f;

        int areaWidth = getWidth()/Game.GRID_SIZE;
        int areaHeight = getHeight()/Game.GRID_SIZE;

        float imageSizeW = hamsterImage.getWidth();
        float imageSizeH = hamsterImage.getHeight();
        Position loc = g.getPlayerLocation();
        float scaleFactor = size/Math.max(imageSizeW, imageSizeH);

        float x = loc.x * areaWidth + areaWidth/2 - imageSizeW/2*scaleFactor;
        float y = loc.y * areaHeight + areaHeight/2 - imageSizeH/2*scaleFactor;

        c.save();
        c.translate(x, y);
        c.scale(scaleFactor, scaleFactor);
        c.drawBitmap(hamsterImage, 0, 0, hamsterPaint);
        c.restore();
    }

    /**
     * Tints the hamster color
     * @param color tint color
     */
    public void tintHamster(int color) {
        hamsterTint = color;
        hamsterPaint.setColorFilter(new PorterDuffColorFilter(hamsterTint, PorterDuff.Mode.MULTIPLY));
    }

    /**
     * Gets current hamster tint color
     * @return tint color
     */
    public int getHamsterTint() {
        return hamsterTint;
    }
}
