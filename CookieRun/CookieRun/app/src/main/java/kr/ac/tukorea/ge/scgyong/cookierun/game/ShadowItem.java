package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class ShadowItem extends MapObject {
    private static final int SIZE = 66;
    private static final int BORDER = 2;
    private static final int ITEMS_IN_ROW = 30;
    private static final int ITEM_WIDTH = 100;
    private static final int ITEM_HEIGHT = 100;

    public int index;
    private final Rect srcRect = new Rect();
    private final RectF collisionRect = new RectF();

    private static final int[] SOUND_IDS = {
            R.raw.jelly,
            R.raw.jelly_item,
            R.raw.jelly_coin,
    };

    public ShadowItem() {
        super(MainScene.Layer.item);
        bitmap = BitmapPool.get(R.mipmap.jelly);
        width = ITEM_WIDTH;
        height = ITEM_HEIGHT;
    }

    public static ShadowItem get(int index, float left, float top) {
        return Scene.top().getRecyclable(ShadowItem.class).init(index, left, top);
    }

    public static ShadowItem get(char tile, float left, float top) {
        int index;
        switch (tile) {
            case '1': index = 1; break; // HEALTH
            case '2': index = 2; break; // INVINCIBLE
            case '3': index = 3; break; // SHURIKEN
            default: return null;
        }
        return get(index, left, top);
    }

    public ShadowItem init(int index, float left, float top) {
        this.index = index;
        setSrcRect(index);
        dstRect.set(left, top, left + width, top + height);
        updateCollisionRect(0.15f);
        return this;
    }

    private void setSrcRect(int index) {
        int col = index % ITEMS_IN_ROW;
        int row = index / ITEMS_IN_ROW;
        int left = col * (SIZE + BORDER) + BORDER;
        int top = row * (SIZE + BORDER) + BORDER;
        srcRect.set(left, top, left + SIZE, top + SIZE);
    }

    @Override
    public void update() {
        super.update();
        updateCollisionRect(0.15f);
    }

    @Override
    public Rect getSrcRect() {
        return srcRect;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    public int getSoundResId() {
        return SOUND_IDS[index % SOUND_IDS.length];
    }

    public int getIndex() {
        return index;
    }

    public void applyEffect(Player player) {
        switch (index) {
            case 1:
                player.increaseHealth();
                break;
            case 2:
                player.setInvincible(3.0f);
                break;
            case 3:
                player.fireShurikens();
                break;
        }
    }
}
