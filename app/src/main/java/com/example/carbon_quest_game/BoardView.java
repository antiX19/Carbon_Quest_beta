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

    private static final int BOARD_SIZE = 32; // Nombre total de cases sur le plateau
    private static final int BOARD_WIDTH = 800; // Largeur du plateau en pixels
    private static final int BOARD_HEIGHT = 800; // Hauteur du plateau en pixels
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
        setPlayerPositions(0, 0);
        // Load and resize tokens during initialization
        Bitmap originalTokenPlayer1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.luffy);
        Bitmap originalTokenPlayer2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.zorro);

        tokenPlayer1 = Bitmap.createScaledBitmap(originalTokenPlayer1, tokenWidth, tokenHeight, false);
        tokenPlayer2 = Bitmap.createScaledBitmap(originalTokenPlayer2, tokenWidth, tokenHeight, false);
    }

    public void setPlayerPositions(int position1, int position2) {
        player1Position = position1 + 1;
        player2Position = position2 + 1;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect dest = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bitmap, null, dest, paint);

        Coordinates player1Coordinates = getScaledCenterCoordinates(player1Position, canvas.getWidth(), canvas.getHeight());
        drawPawn(canvas, tokenPlayer1, player1Coordinates.x, player1Coordinates.y);

        Coordinates player2Coordinates = getScaledCenterCoordinates(player2Position, canvas.getWidth(), canvas.getHeight());
        drawPawn(canvas, tokenPlayer2, player2Coordinates.x, player2Coordinates.y);
    }

    private void drawPawn(Canvas canvas, Bitmap pawnBitmap, int x, int y) {

        // Calculer la position pour centrer l'image
        int left = x - tokenWidth / 2;
        int top = y - tokenHeight / 2;
        int right = x + tokenWidth / 2;
        int bottom = y + tokenHeight / 2;

        Rect dest = new Rect(left, top, right, bottom);
        canvas.drawBitmap(pawnBitmap, null, dest, paint);
    }

    private Coordinates getScaledCenterCoordinates(int position, int viewWidth, int viewHeight) {
        int referenceWidth = 800; // Largeur de référence
        int referenceHeight = 800; // Hauteur de référence

        float scaleX = (float) viewWidth / referenceWidth;
        float scaleY = (float) viewHeight / referenceHeight;

        Coordinates referenceCoordinates = getCenterCoordinates(position);
        int scaledX = (int) (referenceCoordinates.x * scaleX);
        int scaledY = (int) (referenceCoordinates.y * scaleY);

        return new Coordinates(scaledX, scaledY);
    }

    private static class Coordinates {
        int x;
        int y;

        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private Coordinates getCenterCoordinates(int position) {
        switch (position) {
            // Bottom side
            default:
                return new Coordinates(50, 740);
            case 2:
                return new Coordinates(170, 740);
            case 3:
                return new Coordinates(250, 740);
            case 4:
                return new Coordinates(326, 740);
            case 5:
                return new Coordinates(400, 740);
            case 6:
                return new Coordinates(480, 740);
            case 7:
                return new Coordinates(555, 740);
            case 8:
                return new Coordinates(638, 740);
            case 9:
                return new Coordinates(750, 750);
            // Right side
            case 10:
                return new Coordinates(740, 630);
            case 11:
                return new Coordinates(740, 548);
            case 12:
                return new Coordinates(740, 478);
            case 13:
                return new Coordinates(740, 400);
            case 14:
                return new Coordinates(740, 320);
            case 15:
                return new Coordinates(740, 240);
            case 16:
                return new Coordinates(740, 160);
            //Top side
            case 17:
                return new Coordinates(740, 50);
            case 18:
                return new Coordinates(640, 50);
            case 19:
                return new Coordinates(556, 50);
            case 20:
                return new Coordinates(479, 50);
            case 21:
                return new Coordinates(400, 50);
            case 22:
                return new Coordinates(330, 50);
            case 23:
                return new Coordinates(236, 50);
            case 24:
                return new Coordinates(165, 50);
            //Left side
            case 25:
                return new Coordinates(50, 50);
            case 26:
                return new Coordinates(65, 150);
            case 27:
                return new Coordinates(65, 240);
            case 28:
                return new Coordinates(65, 320);
            case 29:
                return new Coordinates(65, 400);
            case 30:
                return new Coordinates(65, 477);
            case 31:
                return new Coordinates(65, 555);
        }
    }
}