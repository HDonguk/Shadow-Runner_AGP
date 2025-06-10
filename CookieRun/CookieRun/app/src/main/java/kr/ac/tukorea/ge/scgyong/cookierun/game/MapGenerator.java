package kr.ac.tukorea.ge.scgyong.cookierun.game;

import java.util.Random;
import android.graphics.Canvas;
import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapGenerator implements IGameObject {
    private final MainScene scene;
    private final int stage;
    private final Random random = new Random();
    private ArrayList<String> generatedMap;
    private static final int MAP_HEIGHT = 9;
    private static final int MAP_WIDTH = 100;
    private static final float INTERVAL = 100f;  // MapLoader와 동일한 간격 사용
    private static final float FLOOR_Y = 700f;   // Y 위치 고정

    public MapGenerator(MainScene scene, int stage) {
        this.scene = scene;
        this.stage = stage;
        this.generatedMap = new ArrayList<>();
        generateMap();
    }

    private void generateMap() {
        // 스테이지별로 다른 시드값 사용
        random.setSeed(stage * 1000);
        
        for (int row = 0; row < MAP_HEIGHT; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < MAP_WIDTH; col++) {
                if (row == MAP_HEIGHT - 1) {
                    // 바닥은 항상 생성
                    line.append('1');
                } else {
                    // 스테이지별로 다른 확률 적용
                    char tile = generateTileForStage();
                    line.append(tile);
                }
            }
            generatedMap.add(line.toString());
        }
    }

    public char generateTileForStage() {
        int rand = random.nextInt(100);
        
        // 스테이지별로 다른 확률 적용
        switch (stage) {
            case 1: // 초급 스테이지
                if (rand < 30) return '1';      // ShadowItem 1
                else if (rand < 45) return '2'; // ShadowItem 2
                else if (rand < 60) return '3'; // ShadowItem 3
                else if (rand < 80) return '4'; // Obstacle
                break;
                
            case 2: // 중급 스테이지
                if (rand < 20) return '1';
                else if (rand < 35) return '2';
                else if (rand < 50) return '3';
                else if (rand < 90) return '4'; // 장애물 비중 증가
                break;
                
            case 3: // 고급 스테이지
                if (rand < 15) return '1';
                else if (rand < 30) return '2';
                else if (rand < 45) return '3';
                else if (rand < 95) return '4'; // 장애물 비중 더 증가
                break;
        }
        return '0'; // 빈 공간
    }

    public ArrayList<String> getGeneratedMap() {
        return generatedMap;
    }

    @Override
    public void update() {
        // MapLoader가 맵을 로드하므로 여기서는 업데이트가 필요 없음
    }

    @Override
    public void draw(Canvas canvas) {
        // 시각 요소 없음
    }
}
