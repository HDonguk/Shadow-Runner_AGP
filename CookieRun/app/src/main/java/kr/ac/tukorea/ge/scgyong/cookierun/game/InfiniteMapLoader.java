package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.graphics.Canvas;
import android.util.Log;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

import java.util.ArrayList;
import java.util.Random;

public class InfiniteMapLoader implements IGameObject {
    private static final String TAG = InfiniteMapLoader.class.getSimpleName();
    private static final float SPAWN_DISTANCE = 1000f; // 진행 거리 기준
    private static final float CLEANUP_DISTANCE = 20000f; // 매우 느리게 제거
    private static final float RUN_SPEED = 600f; // 임시 러너 속도(초당 픽셀)
    private static final float PLAYER_SCREEN_X = 200f; // 플레이어의 화면 내 고정 x좌표
    
    // 장애물 관련 상수 추가
    private static final float OBSTACLE_SPAWN_DISTANCE = 800f; // 장애물 생성 거리 (1500f에서 800f로 단축)
    private static final float OBSTACLE_CLEANUP_DISTANCE = 20000f; // 장애물 제거 거리
    private static final float OBSTACLE_SPAWN_CHANCE = 0.8f; // 장애물 생성 확률 (30%에서 80%로 증가)

    private final MainScene scene;
    private float lastSpawnX = 0f;
    private float worldX = 0f; // 게임 진행 거리
    private boolean initialFloorCreated = false;
    
    // 장애물 관련 변수 추가
    private float lastObstacleSpawnX = 0f;
    private Random random = new Random();

    public InfiniteMapLoader(MainScene scene) {
        this.scene = scene;
        Log.d(TAG, "InfiniteMapLoader 생성됨");
    }

    private void createInitialFloor() {
        Log.d(TAG, "초기 바닥 생성 시작");
        float floorY = Metrics.height - Floor.Type.T_10x2.height();
        float gap = Metrics.GRID_UNIT * 12; // 바닥 간격 3배
        for (int i = 0; i < 20; i++) {
            float x = i * gap;
            Floor floor = Floor.get('O', x, floorY);
            if (floor != null) {
                scene.add(MainScene.Layer.floor, floor);
                Log.d(TAG, "바닥 생성: x=" + x);
            } else {
                Log.e(TAG, "바닥 생성 실패: x=" + x);
            }
        }
        lastSpawnX = 20 * gap;
        Log.d(TAG, "초기 바닥 생성 완료. lastSpawnX=" + lastSpawnX);
    }

    @Override
    public void update() {
        // 초기 바닥이 아직 생성되지 않았다면 생성
        if (!initialFloorCreated) {
            createInitialFloor();
            initialFloorCreated = true;
            return;
        }
        // 진행 거리(worldX) 증가
        worldX += RUN_SPEED * GameView.frameTime;
        // 새로운 바닥 생성
        generateNewFloor();
        // 오래된 바닥 제거
        cleanupOldFloor();
        // 새로운 장애물 생성
        generateNewObstacles();
        // 오래된 장애물 제거
        cleanupOldObstacles();
    }

    private void generateNewFloor() {
        float floorY = Metrics.height - Floor.Type.T_10x2.height();
        float gap = Metrics.GRID_UNIT * 12; // 바닥 간격 3배
        // Log.d(TAG, "generateNewFloor: worldX=" + worldX + ", lastSpawnX=" + lastSpawnX + ", SPAWN_DISTANCE=" + SPAWN_DISTANCE); // 주석 처리
        if (worldX + SPAWN_DISTANCE > lastSpawnX) {
            Log.d(TAG, "새로운 바닥 생성: worldX=" + worldX + ", lastSpawnX=" + lastSpawnX);
            for (int i = 0; i < 5; i++) {
                float x = lastSpawnX + (i * gap);
                Floor floor = Floor.get('O', x, floorY);
                if (floor != null) {
                    scene.add(MainScene.Layer.floor, floor);
                    Log.d(TAG, "새 바닥 추가: x=" + x);
                } else {
                    Log.e(TAG, "새 바닥 생성 실패: x=" + x);
                }
            }
            lastSpawnX += gap * 5;
        }
    }

    private void cleanupOldFloor() {
        float cleanupX = worldX - CLEANUP_DISTANCE;
        ArrayList<IGameObject> toRemove = new ArrayList<>();
        for (IGameObject obj : scene.objectsAt(MainScene.Layer.floor)) {
            if (obj instanceof Floor) {
                Floor floor = (Floor) obj;
                // Log.d(TAG, "바닥 x=" + floor.getX() + ", worldX=" + worldX + ", cleanupX=" + cleanupX); // 주석 처리
                if (floor.getX() < cleanupX) {
                    toRemove.add(obj);
                    Log.d(TAG, "오래된 바닥 제거: x=" + floor.getX());
                }
            }
        }
        for (IGameObject obj : toRemove) {
            scene.remove(MainScene.Layer.floor, obj);
        }
    }

    private void generateNewObstacles() {
        if (worldX + OBSTACLE_SPAWN_DISTANCE > lastObstacleSpawnX) {
            // 장애물 생성 확률 체크
            if (random.nextFloat() < OBSTACLE_SPAWN_CHANCE) {
                // 장애물 타입 랜덤 선택
                char obstacleType = getRandomObstacleType();
                float obstacleX = lastObstacleSpawnX + random.nextFloat() * 200f; // 랜덤 간격을 500f에서 200f로 단축
                float obstacleY = Metrics.height - Floor.Type.T_10x2.height() - 100f; // 바닥 위에 생성
                
                Obstacle obstacle = ObstacleFactory.get(obstacleType, obstacleX, obstacleY);
                if (obstacle != null) {
                    scene.add(MainScene.Layer.obstacle, obstacle);
                    // Log.d(TAG, "장애물 생성: 타입=" + obstacleType + ", x=" + obstacleX); // 주석 처리
                }
            }
            lastObstacleSpawnX += OBSTACLE_SPAWN_DISTANCE;
        }
    }

    private char getRandomObstacleType() {
        float rand = random.nextFloat();
        if (rand < 0.4f) {
            return 'X'; // 일반 장애물 (40%)
        } else if (rand < 0.7f) {
            return 'Y'; // 애니메이션 장애물 1 (30%)
        } else if (rand < 0.9f) {
            return 'Z'; // 애니메이션 장애물 2 (20%)
        } else {
            return 'W'; // 낙하 장애물 (10%)
        }
    }

    private void cleanupOldObstacles() {
        float cleanupX = worldX - OBSTACLE_CLEANUP_DISTANCE;
        ArrayList<IGameObject> toRemove = new ArrayList<>();
        for (IGameObject obj : scene.objectsAt(MainScene.Layer.obstacle)) {
            if (obj instanceof Obstacle) {
                Obstacle obstacle = (Obstacle) obj;
                if (obstacle.getX() < cleanupX) {
                    toRemove.add(obj);
                    // Log.d(TAG, "오래된 장애물 제거: x=" + obstacle.getX()); // 주석 처리
                }
            }
        }
        for (IGameObject obj : toRemove) {
            scene.remove(MainScene.Layer.obstacle, obj);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // InfiniteMapLoader는 직접 그리지 않음
    }

    // 무한맵 재시작
    public void restart() {
        Log.d(TAG, "InfiniteMapLoader 재시작");
        // 모든 상태 초기화
        worldX = 0f;
        lastSpawnX = 0f;
        lastObstacleSpawnX = 0f;
        initialFloorCreated = false;
        
        // 기존 맵 오브젝트들 제거
        clearMapObjects();
        
        // 초기 바닥 다시 생성
        createInitialFloor();
    }

    // 맵 오브젝트들 제거
    private void clearMapObjects() {
        // floor, obstacle 레이어의 오브젝트들을 제거
        for (IGameObject obj : new ArrayList<>(scene.objectsAt(MainScene.Layer.floor))) {
            scene.remove(MainScene.Layer.floor, obj);
        }
        for (IGameObject obj : new ArrayList<>(scene.objectsAt(MainScene.Layer.obstacle))) {
            scene.remove(MainScene.Layer.obstacle, obj);
        }
    }
} 