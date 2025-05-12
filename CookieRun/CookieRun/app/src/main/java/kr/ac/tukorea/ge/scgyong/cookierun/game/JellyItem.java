package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class ShadowItem extends MapObject {
    public enum Type { HEALTH, INVINCIBLE, SHURIKEN }

    private static final int SIZE = 66;
    private static final int BORDER = 2;
    private static final int ITEM_WIDTH = 100;
    private static final int ITEM_HEIGHT = 100;

    private static final int[] SOUND_IDS = {
            R.raw.jelly,
            R.raw.jelly_alphabet,
            R.raw.jelly_item,
            R.raw.jelly_gold,
            R.raw.jelly_coin,
            R.raw.jelly_big_coin,
    };

    private Type type;
    private final Rect srcRect = new Rect();
    private final RectF collisionRect = new RectF();

    public ShadowItem() {
        super(MainScene.Layer.item);
        bitmap = BitmapPool.get(R.mipmap.jelly); // 기존 jelly 시트 사용
        width = ITEM_WIDTH;
        height = ITEM_HEIGHT;
    }

    public static ShadowItem get(Type type, float left, float top) {
        return Scene.top().getRecyclable(ShadowItem.class).init(type, left, top);
    }

    public ShadowItem init(Type type, float left, float top) {
        this.type = type;

        int index = getSpriteIndex(type);
        setSrcRect(index);

        dstRect.set(left, top, left + width, top + height);
        updateCollisionRect(0.15f);
        return this;
    }

    private int getSpriteIndex(Type type) {
        switch (type) {
            case HEALTH: return 1;
            case INVINCIBLE: return 2;
            case SHURIKEN: return 3;
        }
        return 0;
    }

    private void setSrcRect(int index) {
        int itemsPerRow = 30;
        int x = index % itemsPerRow;
        int y = index / itemsPerRow;

        int left = x * (SIZE + BORDER) + BORDER;
        int top = y * (SIZE + BORDER) + BORDER;
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

    public void applyEffect(Player player) {
        switch (type) {
            case HEALTH:
                player.increaseHealth();
                break;
            case INVINCIBLE:
                player.setInvincible(3.0f);
                break;
            case SHURIKEN:
                player.fireShurikens();
                break;
        }
    }

    public int getSoundResId() {
        return SOUND_IDS[getSpriteIndex(type) % SOUND_IDS.length];
    }

    public Type getType() {
        return type;
    }
}
