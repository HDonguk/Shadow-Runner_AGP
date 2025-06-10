package kr.ac.tukorea.ge.scgyong.cookierun.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.Gauge;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapLoader implements IGameObject {
    private final MainScene scene;
    private final Random random = new Random();
    private float x;
    private int index;
    private ArrayList<String> lines;
    private int stage_width, page_width;
    private static final int STAGE_HEIGHT = 9;
    private boolean isUsingGeneratedMap = false;
    private MapGenerator mapGenerator;

    public MapLoader(MainScene mainScene, int stage) {
        this.scene = mainScene;
        this.lines = new ArrayList<>();
        
        // MapGenerator를 먼저 생성하고 맵을 생성
        this.mapGenerator = new MapGenerator(mainScene, stage);
        this.lines = mapGenerator.getGeneratedMap();
        this.isUsingGeneratedMap = true;
        calculateMapDimensions();
    }

    private void calculateMapDimensions() {
        if (lines.isEmpty()) return;
        page_width = lines.get(0).length();
        stage_width = page_width;
    }

    private void loadStage(Context context, int stage) {
        if (isUsingGeneratedMap) {
            calculateMapDimensions();
            return;
        }

        // 기존 파일 로드 로직은 백업으로만 유지
        AssetManager assets = context.getAssets();
        try {
            String file = String.format("stage_%02d.txt", stage);
            InputStream is = assets.open(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            lines.clear();
            page_width = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                if (page_width == 0) {
                    page_width = line.indexOf('|');
                }
                lines.add(line);
            }

            int pages = lines.size() / STAGE_HEIGHT;
            int lastCol = lines.get(lines.size() - 1).length();
            stage_width = (pages - 1) * page_width + lastCol;
        } catch (IOException e) {
            // 파일 로드 실패 시 생성된 맵 사용
            this.lines = mapGenerator.getGeneratedMap();
            this.isUsingGeneratedMap = true;
            calculateMapDimensions();
        }
    }

    @Override
    public void update() {
        x += MapObject.SPEED * GameView.frameTime;
        while (x < Metrics.width) {
            createColumn();
            index++;
            x += 100f;
        }
    }

    private void createColumn() {
        for (int row = 0; row < STAGE_HEIGHT; row++) {
            char tile = getAt(index, row);
            float y = 100 * row;
            createObject(tile, x, y);
        }
    }

    protected interface MapObjectCreator {
        MapObject get(char tile, float left, float top);
    }

    protected static MapObjectCreator[] mapCreators = {
            ShadowItem::get, Floor::get, ObstacleFactory::get,
    };

    private void createObject(char tile, float left, float top) {
        for (MapObjectCreator creator: mapCreators) {
            MapObject mapObject = creator.get(tile, left, top);
            if (mapObject != null) {
                scene.add(mapObject);
                return;
            }
        }
    }

    private char getAt(int col, int row) {
        if (col >= stage_width) return 0;
        try {
            int lineIndex = row;
            String line = lines.get(lineIndex);
            return line.charAt(col % page_width);
        } catch (Exception e) {
            return 0;
        }
    }

    Gauge gauge = new Gauge(0.025f, R.color.mapGaugeFg, R.color.mapGaugeBg);
    @Override
    public void draw(Canvas canvas) {
        gauge.draw(canvas, 200, 100, 1200, (float)index / stage_width);
    }
}
