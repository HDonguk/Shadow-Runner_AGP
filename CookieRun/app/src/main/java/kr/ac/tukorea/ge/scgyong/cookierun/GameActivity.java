package kr.ac.tukorea.ge.scgyong.cookierun;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.tukorea.ge.scgyong.cookierun.BuildConfig;
import kr.ac.tukorea.ge.scgyong.cookierun.databinding.ActivityGameBinding;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.ac.tukorea.ge.scgyong.cookierun.game.MainScene;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        Metrics.setGameSize(1600, 900);
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int stage = intent.getIntExtra("stage", 1);
        int cookieId = intent.getIntExtra("cookieId", 107584);
        boolean infiniteMode = intent.getBooleanExtra("infiniteMode", false);

        gameView = new GameView(this);
        binding.gameLayout.addView(gameView);

        MainScene scene = new MainScene(stage, cookieId, infiniteMode);
        gameView.pushScene(scene);
    }
} 