package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class MainScene extends Scene {
    public enum Layer {
        bg, floor, item, obstacle, player, ui, touch, controller;
        public static final int COUNT = values().length;
    }

    private final Player player;
    private static final String TAG = MainScene.class.getSimpleName();

    // 스와이프 감지를 위한 좌표
    private float touchStartX, touchStartY;
    private boolean isSliding = false;
    public MainScene(int stage, int cookieId) {
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.cookie_run_bg_1, -50));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.cookie_run_bg_2, -100f));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.cookie_run_bg_3, -150f));

        player = new Player(cookieId);
        add(Layer.player, player);

        // 기존 버튼 제거 (슬라이드, 점프, 낙하, 일시정지 버튼 등)

        add(Layer.controller, new MapLoader(this, stage));
        add(Layer.controller, new CollisionChecker(this, player));
    }

    private void pauseAnimations() {
        for (IGameObject obj : objectsAt(Layer.obstacle)) {
            ((MapObject)obj).pause();
        }
    }
    private void resumeAnimations() {
        for (IGameObject obj : objectsAt(Layer.obstacle)) {
            ((MapObject)obj).resume();
        }
    }

    @Override
    public boolean onBackPressed() {
        new PauseScene().push();
        return true;
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    @Override
    public void onEnter() {
        Sound.playMusic(R.raw.main);
    }

    @Override
    public void onPause() {
        Sound.pauseMusic();
        pauseAnimations();
    }

    @Override
    public void onResume() {
        resumeAnimations();
        Sound.resumeMusic();
    }

    @Override
    public void onExit() {
        Sound.stopMusic();
    }

    // ✅ 터치 제스처 스와이프 입력 추가
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStartX = event.getX();
                touchStartY = event.getY();
                isSliding = false;
                return true;

            case MotionEvent.ACTION_MOVE:
                float dx = event.getX() - touchStartX;
                float dy = event.getY() - touchStartY;

                if (!isSliding && Math.abs(dx) > Math.abs(dy) && dx > 100) {
                    // 오른쪽 스와이프 → slide 시작
                    player.slide(true);
                    isSliding = true;
                }
                return true;

            case MotionEvent.ACTION_UP:
                // 스와이프 방향별 처리
                float totalDx = event.getX() - touchStartX;
                float totalDy = event.getY() - touchStartY;

                if (Math.abs(totalDy) > Math.abs(totalDx)) {
                    if (totalDy < -100) {
                        player.jump();
                    } else if (totalDy > 100) {
                        player.fall();
                    }
                }

                // 손을 떼면 슬라이드 해제
                if (isSliding) {
                    player.slide(false);
                    isSliding = false;
                }

                return true;
        }
        return false;
    }
}
