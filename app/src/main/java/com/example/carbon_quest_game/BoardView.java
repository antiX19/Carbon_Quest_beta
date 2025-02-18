package com.example.carbon_quest_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class BoardView extends View {

    private Bitmap bitmap;
    private Paint paint;
    private int player1Position = 0;
    private int player2Position = 0;
    private Bitmap tokenPlayer1;
    private Bitmap tokenPlayer2;
    private int tokenWidth = 100; // Default token width
    private int tokenHeight = 100; // Default token height

    public BoardView(Context context) {
        super(context);
        init(null, 0);
    }

    public BoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public BoardView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context);
        init(attributeSet, defStyle);
    }

    private void init(AttributeSet attributeSet, int defStyle) {
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.plateau);

        // Load and resize tokens during initialization
        Bitmap originalTokenPlayer1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.luffy);
        Bitmap originalTokenPlayer2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.zorro);

        tokenPlayer1 = Bitmap.createScaledBitmap(originalTokenPlayer1, tokenWidth, tokenHeight, false);
        tokenPlayer2 = Bitmap.createScaledBitmap(originalTokenPlayer2, tokenWidth, tokenHeight, false);

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect dest = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bitmap, null, dest, paint);

        int[] coords1 = getTokenCoordinates(player1Position, dest);
        int[] coords2 = getTokenCoordinates(player2Position, dest);
        setTokenSize(100,100);
        canvas.drawBitmap(tokenPlayer1, coords1[0] - tokenPlayer1.getWidth() / 2, coords1[1] - tokenPlayer1.getHeight() / 2, paint);
        canvas.drawBitmap(tokenPlayer2, coords2[0] - tokenPlayer2.getWidth() / 2, coords2[1] - tokenPlayer2.getHeight() / 2, paint);
    }

    public void setTokenSize(int width, int height) {
        tokenWidth = width;
        tokenHeight = height;

        // Resize tokens when size is updated
        tokenPlayer1 = Bitmap.createScaledBitmap(tokenPlayer1, tokenWidth, tokenHeight, false);
        tokenPlayer2 = Bitmap.createScaledBitmap(tokenPlayer2, tokenWidth, tokenHeight, false);

        invalidate();
    }

    public void setPlayerTokenPositions(int pos1, int pos2) {
        player1Position = pos1;
        player2Position = pos2;
        invalidate();
    }

    private int[] getTokenCoordinates(int cell, Rect dest) {
        int cellWidth = dest.width() / 5;
        int cellHeight = dest.height() / 5;
        int x, y;

        if (cell == 0) {
            x = dest.left + cellWidth / 2;
            y = dest.bottom - cellHeight / 2;
        } else {
            x = dest.left + (cell % 5) * cellWidth + cellWidth / 2;
            y = dest.top + (cell / 5) * cellHeight + cellHeight / 2;
        }

        return new int[]{x, y};
    }
}
