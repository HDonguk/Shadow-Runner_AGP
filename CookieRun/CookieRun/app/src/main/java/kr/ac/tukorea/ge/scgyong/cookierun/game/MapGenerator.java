package kr.ac.tukorea.ge.scgyong.cookierun.game;

import java.util.Random;
import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapGenerator implements IGameObject {
    private final MainScene scene;
    private final int stage;
    private final Random random = new Random();

    private float x;                        // 현재 생성 위치
    private static final float INTERVAL = 150f;  // 생성 간격
    private static final float FLOOR_Y = 700f;   // Y 위치 고정

    public MapGenerator(MainScene scene, int stage) {
        this.scene = scene;
        this.stage = stage;
        this.x = Metrics.width; // 시작 위치: 화면 오른쪽 바깥
    }

    @Override
    public void update() {
        // ✅ x는 화면 우측에서 시작해서 점점 앞으로 전진하는 개념
        while (x < Metrics.width + 300f) {
            generateObject(x);
            x += INTERVAL; // 증가시켜야 새로운 오브젝트 위치가 늘어남
        }

        // 다음 update에서 화면이 왼쪽으로 스크롤된 만큼만 x도 같이 증가
        x += -MapObject.SPEED * GameView.frameTime;
    }

    private void generateObject(float x) {
        float y = FLOOR_Y;

        int rand = random.nextInt(100);
        if (rand < 20) {
            ShadowItem item = ShadowItem.get('1', x, y);
            if (item != null) scene.add(MainScene.Layer.item, item);
        } else if (rand < 35) {
            ShadowItem item = ShadowItem.get('2', x, y);
            if (item != null) scene.add(MainScene.Layer.item, item);
        } else if (rand < 50) {
            ShadowItem item = ShadowItem.get('3', x, y);
            if (item != null) scene.add(MainScene.Layer.item, item);
        } else if (rand < 80) {
            scene.add(MainScene.Layer.obstacle, ObstacleFactory.get('3', x, y));
        }
    }




    @Override
    public void draw(Canvas canvas) {
        // 시각 요소 없음
    }
}
