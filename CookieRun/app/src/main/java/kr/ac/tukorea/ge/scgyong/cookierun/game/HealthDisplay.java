package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class HealthDisplay implements IGameObject {
    private static final String TAG = HealthDisplay.class.getSimpleName();
    private static final float HEART_WIDTH = 100;
    private static final float HEART_HEIGHT = 100;
    private static final float MARGIN_X = 20;
    private static final float MARGIN_Y = 20;

    private Bitmap heartBitmap;
    private Bitmap blackHeartBitmap;
    private int maxHealth;
    private int currentHealth;

    private RectF dstRect = new RectF();

    public HealthDisplay(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        heartBitmap = BitmapFactory.decodeResource(GameView.view.getResources(), R.mipmap.heart);
        blackHeartBitmap = BitmapFactory.decodeResource(GameView.view.getResources(), R.mipmap.black);

        if (heartBitmap == null) {
            Log.e(TAG, "Heart bitmap failed to load from R.mipmap.heart");
        } else {
            Log.d(TAG, "Heart bitmap loaded successfully. Size: " + heartBitmap.getWidth() + "x" + heartBitmap.getHeight());
        }
        if (blackHeartBitmap == null) {
            Log.e(TAG, "Black heart bitmap failed to load from R.mipmap.black");
        } else {
            Log.d(TAG, "Black heart bitmap loaded successfully. Size: " + blackHeartBitmap.getWidth() + "x" + blackHeartBitmap.getHeight());
        }
    }

    @Override
    public void update() {
        // 체력 표시는 업데이트 로직이 필요 없습니다.
    }

    @Override
    public void draw(Canvas canvas) {
        float x = MARGIN_X;
        float y = MARGIN_Y;

        for (int i = 0; i < maxHealth; i++) {
            dstRect.set(x, y, x + HEART_WIDTH, y + HEART_HEIGHT);
            if (i < currentHealth) {
                canvas.drawBitmap(heartBitmap, null, dstRect, null);
            } else {
                canvas.drawBitmap(blackHeartBitmap, null, dstRect, null);
            }
            x += HEART_WIDTH + 10; // 하트 사이 간격
        }
    }

    public void decreaseHealth() {
        if (currentHealth > 0) {
            currentHealth--;
        }
    }

    public void increaseHealth() {
        if (currentHealth < maxHealth) {
            currentHealth++;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    // 체력을 최대값으로 초기화
    public void resetHealth() {
        currentHealth = maxHealth;
        Log.d(TAG, "체력 초기화: " + currentHealth + "/" + maxHealth);
    }
} 