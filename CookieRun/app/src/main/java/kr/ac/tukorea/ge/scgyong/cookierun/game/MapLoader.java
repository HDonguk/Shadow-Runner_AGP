package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.Gauge;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapLoader implements IGameObject {
    private static final String TAG = MapLoader.class.getSimpleName();
    private static final int STAGE_HEIGHT = 16;

    private final MainScene scene;
    private final int stage;

    public MapLoader(MainScene scene, int stage) {
        this.scene = scene;
        this.stage = stage;
        loadMap();
    }

    private void loadMap() {
        AssetManager assets = GameView.view.getContext().getAssets();
        try {
            InputStream is = assets.open("stage_" + stage + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char tile = line.charAt(col);
                    if (tile == ' ') continue;
                    createObject(tile, col * Metrics.GRID_UNIT, (STAGE_HEIGHT - 1 - row) * Metrics.GRID_UNIT);
                }
                row++;
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createObject(char tile, float x, float y) {
        switch (tile) {
            case 'F':  // Floor
                scene.add(MainScene.Layer.floor, Floor.get(tile, x, y));
                break;
            case '1':  // Small Obstacle
            case '2':  // Large Obstacle
                scene.add(MainScene.Layer.obstacle, ObstacleFactory.get(tile, x, y));
                break;
            case 'J':  // Jelly
                scene.add(MainScene.Layer.item, JellyItem.get(tile, x, y));
                break;
        }
    }

    @Override
    public void update() {
        // 고정 맵 로딩에서는 맵 개체가 자체 업데이트를 처리하므로 사용되지 않습니다.
    }

    @Override
    public void draw(android.graphics.Canvas canvas) {
        // MapLoader는 아무것도 직접 그리지 않습니다.
    }

    // 맵 재시작
    public void restart() {
        Log.d(TAG, "MapLoader 재시작");
        // 기존 맵 오브젝트들을 제거하고 다시 로드
        clearMapObjects();
        loadMap();
    }

    // 맵 오브젝트들 제거
    private void clearMapObjects() {
        // floor, obstacle, item 레이어의 오브젝트들을 제거
        for (IGameObject obj : new ArrayList<>(scene.objectsAt(MainScene.Layer.floor))) {
            scene.remove(MainScene.Layer.floor, obj);
        }
        for (IGameObject obj : new ArrayList<>(scene.objectsAt(MainScene.Layer.obstacle))) {
            scene.remove(MainScene.Layer.obstacle, obj);
        }
        for (IGameObject obj : new ArrayList<>(scene.objectsAt(MainScene.Layer.item))) {
            scene.remove(MainScene.Layer.item, obj);
        }
    }
}
