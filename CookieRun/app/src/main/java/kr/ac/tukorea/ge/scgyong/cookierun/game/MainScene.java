package kr.ac.tukorea.ge.scgyong.cookierun.game;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import android.view.MotionEvent;
import android.util.Log;
import java.util.ArrayList;

public class MainScene extends Scene {
    public enum Layer {
        bg, floor, item, obstacle, player, ui, touch, controller;
        public static final int COUNT = values().length;
    }
    private final IGameObject player;
    private HealthDisplay healthDisplay;
    private static final String TAG = MainScene.class.getSimpleName();
    
    // 터치 제스처 관련 변수
    private float touchStartX, touchStartY;
    private boolean isSliding = false;
    
    // 게임 오버 관련 변수
    private boolean isGameOver = false;
    private float gameOverTimer = 0f;
    private static final float GAME_OVER_DELAY = 1.0f; // 1초 후 게임 오버 화면 표시

    public MainScene(int stage, int cookieId) {
        this(stage, cookieId, false);
    }

    public MainScene(int stage, int cookieId, boolean infiniteMode) {
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.cookie_run_bg_1, -50));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.cookie_run_bg_2, -100f));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.cookie_run_bg_3, -150f));

        healthDisplay = new HealthDisplay(5);
        add(Layer.ui, healthDisplay);

        if (cookieId == 107584) { // Ninja Cookie
            player = new NewPlayer(cookieId, healthDisplay);
        } else {
            player = new Player(cookieId, healthDisplay);
        }
        add(Layer.player, player);

        if (infiniteMode) {
            add(Layer.controller, new InfiniteMapLoader(this));
        } else {
            add(Layer.controller, new MapLoader(this, stage));
        }
        add(Layer.controller, new CollisionChecker(this, (IBoxCollidable)player));
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
        new PauseScene(this).push();
        return true;
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    @Override
    public void onEnter() {
        Sound.playMusic(R.raw.main);
        
        // 플레이어 상태 초기화
        if (player instanceof Player) {
            ((Player)player).resetState();
        } else if (player instanceof NewPlayer) {
            ((NewPlayer)player).resetState();
        }
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

    @Override
    public void update() {
        super.update();
        
        // 게임 오버 상태 체크
        if (!isGameOver && healthDisplay != null && healthDisplay.getCurrentHealth() <= 0) {
            isGameOver = true;
            gameOverTimer = 0f;
            Log.d(TAG, "게임 오버! 체력이 0이 되었습니다.");
        }
        
        // 게임 오버 후 일정 시간 후 게임 오버 화면 표시
        if (isGameOver) {
            gameOverTimer += GameView.frameTime;
            if (gameOverTimer >= GAME_OVER_DELAY) {
                showGameOverDialog();
                isGameOver = false; // 한 번만 표시
            }
        }
    }
    
    // 게임 오버 다이얼로그 표시
    private void showGameOverDialog() {
        new android.app.AlertDialog.Builder(GameView.view.getContext())
                .setTitle("게임 오버")
                .setMessage("체력이 0이 되었습니다. 게임을 재시작하시겠습니까?")
                .setNegativeButton("아니오", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialogInterface, int i) {
                        // 게임 종료
                        GameView.view.popScene();
                    }
                })
                .setPositiveButton("재시작", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialogInterface, int i) {
                        // 게임 재시작
                        restartGame();
                    }
                })
                .setCancelable(false) // 뒤로가기로 닫을 수 없음
                .create()
                .show();
    }

    // 게임 완전 재시작
    public void restartGame() {
        Log.d(TAG, "게임 완전 재시작 시작");
        
        // 0. 게임 오버 상태 초기화
        isGameOver = false;
        gameOverTimer = 0f;
        
        // 1. 플레이어 상태 초기화
        if (player instanceof Player) {
            ((Player)player).resetState();
        } else if (player instanceof NewPlayer) {
            ((NewPlayer)player).resetState();
        }
        
        // 2. 체력 초기화
        if (healthDisplay != null) {
            healthDisplay.resetHealth();
        }
        
        // 3. 모든 오브젝트 제거 (플레이어, 배경, UI 제외)
        clearLayer(Layer.obstacle);
        clearLayer(Layer.item);
        clearLayer(Layer.floor);
        
        // 4. 맵 로더 재초기화
        for (IGameObject obj : objectsAt(Layer.controller)) {
            if (obj instanceof MapLoader) {
                ((MapLoader)obj).restart();
            } else if (obj instanceof InfiniteMapLoader) {
                ((InfiniteMapLoader)obj).restart();
            }
        }
        
        Log.d(TAG, "게임 완전 재시작 완료");
    }
    
    // 특정 레이어의 모든 오브젝트 제거
    private void clearLayer(Layer layer) {
        ArrayList<IGameObject> objects = new ArrayList<>(objectsAt(layer));
        for (IGameObject obj : objects) {
            remove(layer, obj);
        }
    }

    // ✅ 터치 제스처 스와이프 입력 추가
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 1. 스와이프(제스처) 우선 처리
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStartX = event.getX();
                touchStartY = event.getY();
                isSliding = false;
                return true; // 반드시 true 반환!

            case MotionEvent.ACTION_MOVE:
                float dx = event.getX() - touchStartX;
                float dy = event.getY() - touchStartY;

                if (!isSliding && Math.abs(dx) > Math.abs(dy) && dx > 100) {
                    // 오른쪽 스와이프 → slide 시작
                    if (player instanceof Player) {
                        ((Player)player).slide(true);
                    } else if (player instanceof NewPlayer) {
                        ((NewPlayer)player).slide(true);
                    }
                    isSliding = true;
                    return true; // 슬라이드 제스처가 감지되면 true 반환
                }
                break;

            case MotionEvent.ACTION_UP:
                // 스와이프 방향별 처리
                float totalDx = event.getX() - touchStartX;
                float totalDy = event.getY() - touchStartY;

                if (Math.abs(totalDy) > Math.abs(totalDx)) {
                    if (totalDy < -100) {
                        // 위로 스와이프 → 점프
                        if (player instanceof Player) {
                            ((Player)player).jump();
                        } else if (player instanceof NewPlayer) {
                            ((NewPlayer)player).jump();
                        }
                        return true; // 점프 제스처가 감지되면 true 반환
                    } else if (totalDy > 100) {
                        // 아래로 스와이프 → 일시정지
                        new PauseScene(this).push();
                        return true; // 일시정지 제스처가 감지되면 true 반환
                    }
                }

                // 손을 떼면 슬라이드 해제
                if (isSliding) {
                    if (player instanceof Player) {
                        ((Player)player).slide(false);
                    } else if (player instanceof NewPlayer) {
                        ((NewPlayer)player).slide(false);
                    }
                    isSliding = false;
                    return true; // 슬라이드 해제가 감지되면 true 반환
                }
                break;
        }
        // 2. 스와이프가 감지되지 않은 경우에만 버튼 등 나머지 처리
        return super.onTouchEvent(event);
    }
}
