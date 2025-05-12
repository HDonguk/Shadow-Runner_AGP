package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;


public class Shuriken extends MapObject {
    private static final float SPEED = 1000.0f;
    private static final float SIZE = 80.0f;

    private final RectF collisionRect = new RectF();
    private final Rect srcRect = new Rect();

    public Shuriken(float x, float y) {
        super(MainScene.Layer.item);
        this.x = x;
        this.y = y;
        setSize(SIZE, SIZE);

        bitmap = BitmapPool.get(kr.ac.tukorea.ge.scgyong.cookierun.R.mipmap.jelly);// ✅ jelly.png 재사용
        setSrcRect(4); // jelly.png에서 수리검 인덱스
    }
    public static Shuriken get(float x, float y) {
        return Scene.top().getRecyclable(Shuriken.class).init(x, y);
    }
    public Shuriken init(float x, float y) {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        this.x = x;
        this.y = y;
        dstRect.set(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
        updateCollisionRect();
        return this;
    }


    private void setSrcRect(int index) {
        int itemsPerRow = 30;
        int spriteSize = 66;
        int border = 2;

        int x = index % itemsPerRow;
        int y = index / itemsPerRow;

        int left = x * (spriteSize + border) + border;
        int top = y * (spriteSize + border) + border;
        srcRect.set(left, top, left + spriteSize, top + spriteSize);
    }

    @Override
    public void update() {
        x += SPEED * GameView.frameTime;
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        dstRect.set(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
        updateCollisionRect();

        MainScene scene = (MainScene) Scene.top();
        for (Object obj : scene.objectsAt(MainScene.Layer.obstacle)) {
            if (!(obj instanceof Obstacle)) continue;
            Obstacle obstacle = (Obstacle) obj;
            if (RectF.intersects(getCollisionRect(), obstacle.getCollisionRect())) {
                scene.remove(MainScene.Layer.obstacle, obstacle);
                scene.remove(MainScene.Layer.item, this);
                return;
            }
        }

        if (x > Metrics.width + SIZE) {
            scene.remove(MainScene.Layer.item, this);
        }

    }

    private void updateCollisionRect() {
        collisionRect.set(dstRect);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public Rect getSrcRect() {
        return srcRect;
    }
}
