package edu.sdsmt.Hamster_Run_Kraus_Adam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Game g;
    private Bitmap hamsterImage;
    private Paint hamsterPaint;
    private Paint gridPaint;
    private Paint tubePaint;
    private Paint foodPaint;
    private Paint zoomPaint;
    private Paint personPaint;
    private Paint barPaint;
    private Paint homePaint;

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
        // hamster image for drawing
        hamsterImage = BitmapFactory.decodeResource(getResources(), R.drawable.hamster);
        hamsterPaint = new Paint();

        // paints for grid and circles
        gridPaint = new Paint();
        gridPaint.setColor(Color.BLUE);
        gridPaint.setStrokeWidth(5);

        tubePaint = new Paint();
        tubePaint.setColor(Color.BLUE);

        foodPaint = new Paint();
        foodPaint.setColor(Color.CYAN);

        zoomPaint = new Paint();
        zoomPaint.setColor(Color.MAGENTA);

        personPaint = new Paint();
        personPaint.setColor(Color.YELLOW);

        barPaint = new Paint();
        barPaint.setColor(Color.DKGRAY);

        homePaint = new Paint();
        homePaint.setColor(Color.GREEN);

        g = ((MainActivity)getContext()).getGame();
    }

    public void onDraw(Canvas c) {
        // draw grid
        float size = (float)Math.max(getWidth(), getHeight());
        float offset = size/(Game.GRID_SIZE);

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
        drawCircle(c, 2, 3,5,  personPaint);

        drawCircle(c, 0, 4, 5, zoomPaint);
        drawCircle(c, 2, 1, 5, zoomPaint);

        drawCircle(c, 0, 1, 5, foodPaint);
        drawCircle(c, 0, 3, 5, foodPaint);
        drawCircle(c, 2, 2, 5, foodPaint);
        drawCircle(c, 3, 0, 5, foodPaint);

        drawCircle(c, 2, 0, 5, barPaint);
        drawCircle(c, 3, 3, 5, barPaint);
        drawCircle(c, 4, 3, 5, barPaint);
        drawCircle(c, 2, 4, 5, barPaint);

        drawCircle(c, 4, 4, 5, homePaint);

        // draw hamster
        drawHamster(c);
    }

    private void drawCircle(Canvas c, int posX, int posY, int incR, Paint p) {
        float size = (float)Math.max(getWidth(), getHeight());
        float offset = size/(Game.GRID_SIZE);

        float x = posX*offset + offset/2;
        float y = posY*offset + offset/2;
        float r = (offset/2)-30+incR;

        c.drawCircle(x, y, r, p);
    }

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
}
