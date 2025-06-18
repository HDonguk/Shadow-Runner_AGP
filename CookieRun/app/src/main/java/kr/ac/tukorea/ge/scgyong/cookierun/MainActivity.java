package kr.ac.tukorea.ge.scgyong.cookierun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.tukorea.ge.scgyong.cookierun.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Cookie> cookies = new ArrayList<>();
    private int cookieIndex = 0;
    private int stage = 1; // 초기 스테이지 설정 (사용하지 않음)
    private boolean infiniteMode = true; // 무한맵 모드를 기본값으로 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 기본 쿠키 설정
        cookies.add(new Cookie(107584, "Player", R.mipmap.cookie_player));

        updateCookieDisplay();
        updateStageDisplay();
        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        binding.prevCookieButton.setOnClickListener(v -> {
            if (cookieIndex > 0) {
                cookieIndex--;
                updateCookieDisplay();
            }
        });
        binding.nextCookieButton.setOnClickListener(v -> {
            if (cookieIndex < cookies.size() - 1) {
                cookieIndex++;
                updateCookieDisplay();
            }
        });

        binding.prevButton.setOnClickListener(v -> {
            // 무한맵 모드에서는 스테이지 선택 비활성화
        });
        binding.nextButton.setOnClickListener(v -> {
            // 무한맵 모드에서는 스테이지 선택 비활성화
        });

        // 무한맵 모드 토글 버튼 제거

        // 게임 시작 버튼
        binding.startButton.setOnClickListener(v -> {
            startGame();
        });
    }

    private void updateCookieDisplay() {
        if (cookies.isEmpty()) return;
        Cookie selectedCookie = cookies.get(cookieIndex);
        binding.cookieImageView.setImageResource(selectedCookie.imageResId);
        binding.cookieNameTextView.setText(selectedCookie.name);
    }

    private void updateStageDisplay() {
        // 무한맵 모드만 표시
        binding.stageTextView.setText("Infinite Mode");
        binding.prevButton.setEnabled(false);
        binding.nextButton.setEnabled(false);
    }

    private void updateModeDisplay() {
        // 무한맵 모드만 표시하므로 이 메서드는 더 이상 필요하지 않음
        updateStageDisplay();
    }

    private void startGame() {
        Cookie selectedCookie = cookies.get(cookieIndex);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("stage", stage);
        intent.putExtra("cookieId", selectedCookie.id);
        intent.putExtra("cookieName", selectedCookie.name);
        intent.putExtra("cookieImageResId", selectedCookie.imageResId);
        intent.putExtra("infiniteMode", infiniteMode);
        startActivity(intent);
    }
} 