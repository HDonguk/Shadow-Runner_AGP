package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.io.InputStream;
import java.util.HashMap;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class NewPlayer extends SheetSprite implements IBoxCollidable {
    private static final String TAG = NewPlayer.class.getSimpleName();
    private static final float GRAVITY = 1700f;
    private static final float NORMAL_COOKIE_DST_SIZE = 386;
    private static final float INVINCIBLE_TIME = 2.0f;  // 무적 시간 2초

    public enum State {
        running, jump, doubleJump, falling, slide, hurt
    }
    protected State state = State.running;
    private float jumpSpeed;
    private final RectF collisionRect = new RectF();
    private Obstacle obstacle;
    private float invincibleTimer = 0f;  // 무적 시간 타이머

    private final Player.CookieInfo cookieInfo;
    private float scale = 1.0f, magSpeed = 0;
    private static final float SCALE_NORMAL = 1.0f;
    private static final float SCALE_MAGNIFIED = 2.0f;

    // New: HashMaps to store bitmaps and their source rectangles for each state
    private HashMap<State, Bitmap> animationBitmaps;
    private HashMap<State, Rect[]> animationRects;
    private HealthDisplay healthDisplay;

    protected static float[][] edgeInsetRatios = {
            { 0.3f, 0.5f, 0.3f, 0.0f }, // State.running
            { 0.3f, 0.6f, 0.3f, 0.0f }, // State.jump
            { 0.3f, 0.6f, 0.3f, 0.0f }, // State.doubleJump
            { 0.3f, 0.5f, 0.3f, 0.0f }, // State.falling
            { 0.2f, 0.75f, 0.2f, 0.0f }, // State.slide
            { 0.3f, 0.50f, 0.4f, 0.0f }, // State.hurt
    };

    public NewPlayer(int cookieId, HealthDisplay healthDisplay) {
        super(0, 15); // FPS를 8에서 15로 증가
        this.healthDisplay = healthDisplay;
        
        // 쿠키 정보 로드
        Player.load(GameView.view.getContext());
        cookieInfo = Player.cookieInfoMap.get(cookieId);
        
        setPosition(200f, 200f, NORMAL_COOKIE_DST_SIZE, NORMAL_COOKIE_DST_SIZE);
        loadAnimationStrips(); // Load all animation strips
        setState(State.running);
    }

    private void loadAnimationStrips() {
        animationBitmaps = new HashMap<>();
        animationRects = new HashMap<>();
        AssetManager assets = GameView.view.getContext().getAssets();

        try {
            // Running animation
            InputStream isRunning = assets.open("Animations/Running.png");
            Bitmap bmpRunning = BitmapFactory.decodeStream(isRunning);
            animationBitmaps.put(State.running, bmpRunning);
            animationRects.put(State.running, makeRectsForStrip(bmpRunning));

            // Jumping animation
            InputStream isJumping = assets.open("Animations/Jumping.png");
            Bitmap bmpJumping = BitmapFactory.decodeStream(isJumping);
            animationBitmaps.put(State.jump, bmpJumping);
            animationRects.put(State.jump, makeRectsForStrip(bmpJumping));

            // Double_Jump animation
            InputStream isDoubleJump = assets.open("Animations/Double_Jump.png");
            Bitmap bmpDoubleJump = BitmapFactory.decodeStream(isDoubleJump);
            animationBitmaps.put(State.doubleJump, bmpDoubleJump);
            animationRects.put(State.doubleJump, makeRectsForStrip(bmpDoubleJump));

            // Falling animation
            InputStream isFalling = assets.open("Animations/Falling.png");
            Bitmap bmpFalling = BitmapFactory.decodeStream(isFalling);
            animationBitmaps.put(State.falling, bmpFalling);
            animationRects.put(State.falling, makeRectsForStrip(bmpFalling));

            // Slide animation (Roll.png)
            InputStream isSlide = assets.open("Animations/Roll.png");
            Bitmap bmpSlide = BitmapFactory.decodeStream(isSlide);
            animationBitmaps.put(State.slide, bmpSlide);
            animationRects.put(State.slide, makeRectsForStrip(bmpSlide));

            // Hurt animation (Landing with Impact.png)
            InputStream isHurt = assets.open("Animations/Landing with Impact.png");
            Bitmap bmpHurt = BitmapFactory.decodeStream(isHurt);
            animationBitmaps.put(State.hurt, bmpHurt);
            animationRects.put(State.hurt, makeRectsForStrip(bmpHurt));

        } catch (Exception e) {
            Log.e(TAG, "Error loading animation strips: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // New method to make source rectangles for a horizontal animation strip
    private Rect[] makeRectsForStrip(Bitmap stripBitmap) {
        // Assuming frames are square and height is the dimension
        int frameHeight = stripBitmap.getHeight();
        int numFrames = stripBitmap.getWidth() / frameHeight;
        Rect[] rects = new Rect[numFrames];
        for (int i = 0; i < numFrames; i++) {
            rects[i] = new Rect(i * frameHeight, 0, (i + 1) * frameHeight, frameHeight);
        }
        return rects;
    }

    private void setState(State state) {
        this.state = state;
        this.bitmap = animationBitmaps.get(state); // Set the bitmap to the current animation strip
        this.srcRects = animationRects.get(state); // Set srcRects for the current animation strip
        updateCollisionRect();
    }

    @Override
    public void update() {
        super.update(); // Call super.update() to advance animation frames
        float foot = collisionRect.bottom;
        
        // 무적 시간 업데이트
        if (invincibleTimer > 0) {
            invincibleTimer -= GameView.frameTime;
        }

        switch (state) {
            case jump:
            case doubleJump:
            case falling:
                float dy = jumpSpeed * GameView.frameTime;
                jumpSpeed += GRAVITY * GameView.frameTime;
                if (jumpSpeed >= 0) {
                    float floor = findNearestFloorTop(foot);
                    if (foot + dy >= floor) {
                        dy = floor - foot;
                        setState(State.running);
                    }
                }
                foot += dy;
                setCookiePosition(foot);
                break;
            case running:
            case slide:
                float floor = findNearestFloorTop(foot);
                if (foot < floor) {
                    setState(State.falling);
                    jumpSpeed = 0;
                }
                break;
            case hurt:
                if (!CollisionHelper.collides(this, obstacle)) {
                    setState(State.running);
                    obstacle = null;
                }
                break;
        }
        if (magSpeed != 0) {
            scale += GameView.frameTime * magSpeed;
            if (magSpeed < 0 && scale <= SCALE_NORMAL) {
                magSpeed = 0;
                scale = SCALE_NORMAL;
            } else if (magSpeed > 0 && scale >= SCALE_MAGNIFIED) {
                magSpeed = 0;
                scale = SCALE_MAGNIFIED;
            }
            width = height = NORMAL_COOKIE_DST_SIZE * scale;
            setCookiePosition(foot);
        }

        // 바닥 낙사 체크
        float deathY = Metrics.height + 200; // 화면 아래 200픽셀 더
        if (getCollisionRect().bottom > deathY && healthDisplay.getCurrentHealth() > 0) {
            Log.d(TAG, "낙사 감지! 플레이어 bottom=" + getCollisionRect().bottom + ", deathY=" + deathY + ", 체력=" + healthDisplay.getCurrentHealth());
            while (healthDisplay.getCurrentHealth() > 0) {
                healthDisplay.decreaseHealth();
            }
            Log.d(TAG, "바닥에서 떨어져 사망!");
        }
    }

    private float findNearestFloorTop(float foot) {
        Floor platform = findNearestFloor(foot);
        if (platform == null) return Metrics.height + 1000f; // 바닥이 없으면 더 아래로 떨어지도록
        return platform.getCollisionRect().top;
    }

    private Floor findNearestFloor(float foot) {
        Floor nearest = null;
        MainScene scene = (MainScene) Scene.top();
        if (scene == null) return null;
        float top = Metrics.height;
        for (IGameObject obj: scene.objectsAt(MainScene.Layer.floor)) {
            Floor floor = (Floor) obj;
            RectF rect = floor.getCollisionRect();
            if (rect.left > x || x > rect.right) continue;
            if (rect.top < foot) continue;
            if (top > rect.top) {
                top = rect.top;
                nearest = floor;
            }
        }
        return nearest;
    }

    private void setCookiePosition(float foot) {
        float hw = width / 2;
        dstRect.set(x - hw, foot - height, x + hw, foot);
        updateCollisionRect();
    }

    private void updateCollisionRect() {
        float[] insets = edgeInsetRatios[state.ordinal()]; // Use state-specific insets
        collisionRect.set(
            dstRect.left + width * insets[0],
            dstRect.top + height * insets[1],
            dstRect.right - width * insets[2],
            dstRect.bottom - height * insets[3]);
    }

    public void jump() {
        if (state == State.running) {
            setState(State.jump);
            jumpSpeed = -cookieInfo.jumpPower;
            Sound.playEffect(R.raw.jump1);
        } else if (state == State.jump) {
            setState(State.doubleJump);
            jumpSpeed = -cookieInfo.jumpPower * 0.8f;
            Sound.playEffect(R.raw.jump1);
        }
    }

    public void slide(boolean startsSlide) {
        if (startsSlide) {
            setState(State.slide);
        } else {
            setState(State.running);
        }
    }

    public void fall() {
        if (state == State.running) {
            setState(State.falling);
            jumpSpeed = 0;
        }
    }

    public void magnify(boolean enlarges) {
        magSpeed = enlarges ? 2.0f : -2.0f;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
    
    public float getX() {
        return x;
    }

    public void hurt(Obstacle obstacle) {
        if (state == State.hurt || invincibleTimer > 0) return;  // 이미 hurt 상태이거나 무적 시간 중이면 무시
        this.obstacle = obstacle;
        setState(State.hurt);
        Sound.playEffect(R.raw.hurt);
        if (healthDisplay != null) {
            healthDisplay.decreaseHealth();
            if (healthDisplay.getCurrentHealth() <= 0) {
                Log.d(TAG, "플레이어가 죽었습니다!");
            }
        }
        invincibleTimer = INVINCIBLE_TIME;  // 무적 시간 설정
    }

    // 게임 재시작 시 상태 완전 초기화
    public void resetState() {
        state = State.running;
        jumpSpeed = 0f;
        obstacle = null;
        invincibleTimer = 0f;
        scale = SCALE_NORMAL;
        magSpeed = 0f;
        setPosition(200f, 200f, NORMAL_COOKIE_DST_SIZE, NORMAL_COOKIE_DST_SIZE);
        setState(State.running);
        Log.d(TAG, "플레이어 상태 초기화 완료");
    }
} 