# Shadow Runner - Android Game Programming Project

## 📱 프로젝트 개요
- **프로젝트 제목:** Shadow Runner (쿠키런 기반 닌자 테마 게임)
- **발표 영상:** [Shadow Runner 발표 영상 (3분 30초 이내)](https://youtu.be/your-video-link)
- **Git Repository:** [Shadow Runner GitHub](https://github.com/your-username/Shadow-Runner_AGP)
- **개발 기간:** 2024년 4월 ~ 6월 (8주)

---

## 🎮 게임 소개

**Shadow Runner**는 기존 쿠키런 게임을 기반으로 한 닌자 테마의 러닝 액션 게임입니다.

### 기존 쿠키런과의 차이점
- **캐릭터 변경:** 기존 쿠키 캐릭터 → 닌자 캐릭터 (Ninja Cookie)
- **애니메이션 방식:** 단일 스프라이트 시트 → 애니메이션 스트립 이미지
- **제어 방식:** 버튼 기반 → 스와이프 제스처 우선 + 버튼
- **게임 오버 처리:** 체력 0 시 자동 재시작 옵션 제공

### 핵심 메카닉
- **스와이프 제스처:** 위로 스와이프(점프), 아래로 스와이프(일시정지), 오른쪽 스와이프(슬라이드)
- **충돌 시스템:** 장애물 충돌 시 체력 감소, 아이템 획득 시 효과 적용
- **무한 모드:** 무한히 진행되는 맵 시스템
- **재시작 시스템:** 일시정지 및 게임 오버 시 완전한 상태 초기화

---

## 📊 개발 계획 및 실제 진행

### 주차별 개발 진척도

| 주차 | 계획 내용 | 계획 진척도 | 실제 진척도 | 주요 성과 |
|------|-----------|-------------|-------------|-----------|
| 1주차 | 프로젝트 초기 설정, Git Repository | 80% | 100% | 프로젝트 구조 완성 |
| 2주차 | 기본 캐릭터 컨트롤, 애니메이션 | 90% | 95% | Player 클래스 완성 |
| 3주차 | 배경 스크롤, 장애물 생성 | 40% | 85% | MapLoader, InfiniteMapLoader 완성 |
| 4주차 | 충돌 감지, 콤보 시스템 | 0% | 90% | CollisionChecker 완성 |
| 5주차 | 파워업 아이템, 난이도 조절 | 50% | 80% | 아이템 시스템 완성 |
| 6주차 | UI 개선, 애니메이션 최적화 | 20% | 70% | HealthDisplay, PauseScene 완성 |
| 7주차 | 전체 테스트, 디버깅 | 0% | 60% | 게임 오버 시스템 완성 |
| 8주차 | 최종 최적화, 발표 준비 | 0% | 90% | 완전한 게임 시스템 |

### GitHub 커밋 통계
- **주차별 커밋 분포:**

  - ![image](https://github.com/user-attachments/assets/92ee0345-ae24-4394-8763-d7a24eb31e5b)


### 목표 변경 사항
- **변경된 계획:** 무한 모드 + 고정 맵 시스템
- **변경 이유:** 사용자 경험 향상과 게임의 재미 요소 증가를 위해 무한 모드를 추가

---

## 🛠️ 사용된 기술 및 개발 내용

### 사용된 기술
- **Android Studio Hedgehog | 2023.1.1:** 주요 개발 환경 및 IDE
- **Java 11:** 게임 로직 구현 및 객체지향 프로그래밍
- **Gradle 8.11.1:** 빌드 시스템 및 의존성 관리
- **Git/GitHub:** 버전 관리 및 협업 도구
- **Android Framework 35:** View, Canvas, MotionEvent, AlertDialog 등
- **A2DG Framework:** 게임 엔진 프레임워크 (Scene, GameObject, CollisionHelper 등)
- **XML Resource Management:** 레이아웃, 문자열, 이미지 리소스 관리
- **Asset Management:** 애니메이션 스트립 이미지 파일 관리

### 참고한 자료
- **쿠키런 게임 (Devsisters):** 기본 게임 메카닉 및 플레이어 동작 참고
- **Android Developer Documentation:** 터치 이벤트 처리, MotionEvent 활용법
- **게임 개발 패턴:** MVC 패턴, GameObject 패턴, Observer 패턴 적용
- **스프라이트 애니메이션 기법:** 애니메이션 스트립 기반 프레임별 애니메이션
- **플랫포머 게임 설계:** 중력, 점프, 충돌 감지 시스템 설계
- **무한 러너 게임:** 동적 맵 생성 및 오브젝트 재활용 시스템

### 수업 내용에서 차용한 것
- **A2DG Framework 전체 구조:**
  - `Scene` 클래스: 화면 전환 및 게임 상태 관리
  - `GameObject` 인터페이스: 모든 게임 오브젝트의 기본 구조
  - `IGameObject` 인터페이스: 업데이트 및 렌더링 메서드 정의
  - `IBoxCollidable` 인터페이스: 충돌 감지를 위한 박스 충돌 시스템
  - `CollisionHelper` 클래스: 충돌 감지 알고리즘
  - `GameView` 클래스: 게임 루프 및 화면 렌더링
  - `Metrics` 클래스: 화면 크기 및 좌표계 관리
  - `Sound` 클래스: 배경음악 및 효과음 처리 시스템

- **기본 게임 오브젝트들:**
  - `SheetSprite`: 스프라이트 시트 기반 애니메이션
  - `Button`: 터치 이벤트 처리 버튼 시스템
  - `HorzScrollBackground`: 수평 스크롤 배경
  - `Floor`: 바닥 플랫폼 오브젝트
  - `Obstacle`: 장애물 기본 클래스
  - `ObstacleFactory`: 장애물 생성 팩토리 패턴

- **게임 시스템:**
  - `MapLoader`: 텍스트 파일 기반 맵 로딩 시스템
  - `CollisionChecker`: 충돌 감지 및 처리 시스템
  - `Player`: 기본 플레이어 클래스 (쿠키 캐릭터용)

### 직접 개발한 것

#### 1. NewPlayer 클래스 (애니메이션 스트립 기반 플레이어)
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/NewPlayer.java`

**개발 배경:** 기존 Player 클래스가 단일 스프라이트 시트를 사용하여 애니메이션이 제한적이었음. 닌자 캐릭터의 특성에 맞는 부드러운 애니메이션을 위해 상태별 별도 이미지 파일을 사용하는 시스템 개발.

**주요 기능:**
- **애니메이션 스트립 시스템:** 상태별 별도 이미지 파일 사용
  - Running.png: 달리기 애니메이션 (8프레임)
  - Jumping.png: 점프 애니메이션 (6프레임)
  - Double_Jump.png: 더블 점프 애니메이션 (5프레임)
  - Falling.png: 낙하 애니메이션 (4프레임)
  - Roll.png: 슬라이드 애니메이션 (6프레임)
  - Landing with Impact.png: 피격 애니메이션 (3프레임)

- **상태별 충돌 박스 조정:** 각 애니메이션 상태에 맞는 충돌 영역 최적화
  - 달리기: 30% 좌우 여백, 50% 상단 여백
  - 점프/더블점프: 30% 좌우 여백, 60% 상단 여백
  - 슬라이드: 20% 좌우 여백, 75% 상단 여백
  - 피격: 30% 좌우 여백, 50% 상단 여백

- **무적 시간 시스템:** 피격 후 2초간 무적 상태 유지
- **닌자 테마 최적화:** 쿠키런의 쿠키릭터를 닌자 캐릭터로 완전 변경
- **FPS 조정:** 애니메이션 속도를 15FPS로 설정하여 자연스러운 움직임 구현

**기술적 도전과 해결:**
- **메모리 관리:** 여러 이미지 파일 로드 시 메모리 사용량 최적화
- **애니메이션 동기화:** 상태 변경 시 애니메이션 프레임 초기화
- **충돌 박스 동적 조정:** 상태별로 다른 충돌 영역을 실시간으로 계산

**핵심 코드:**
```java
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

        // ... 다른 애니메이션들도 동일하게 로드
    } catch (Exception e) {
        Log.e(TAG, "Error loading animation strips: " + e.getMessage());
        throw new RuntimeException(e);
    }
}

private Rect[] makeRectsForStrip(Bitmap stripBitmap) {
    int frameHeight = stripBitmap.getHeight();
    int numFrames = stripBitmap.getWidth() / frameHeight;
    Rect[] rects = new Rect[numFrames];
    for (int i = 0; i < numFrames; i++) {
        rects[i] = new Rect(i * frameHeight, 0, (i + 1) * frameHeight, frameHeight);
    }
    return rects;
}
```

#### 2. 스와이프 제스처 시스템 (MainScene 내 구현)
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/MainScene.java`

**개발 배경:** 기존 버튼 기반 조작이 직관적이지 않아 사용자 경험이 떨어짐. 모바일 게임의 특성에 맞는 스와이프 제스처 시스템 도입으로 직관적인 조작 구현.

**주요 기능:**
- **제스처 우선 처리:** 스와이프가 버튼보다 우선 처리되도록 구현
- **다양한 제스처 지원:**
  - 위로 스와이프 (100px 이상): 점프
  - 아래로 스와이프 (100px 이상): 일시정지
  - 오른쪽 스와이프 (100px 이상): 슬라이드
- **터치 이벤트 최적화:** ACTION_DOWN에서 반드시 true 반환하여 이벤트 전파 보장
- **제스처와 버튼 충돌 방지:** 스와이프 감지 시 버튼 이벤트 차단
- **슬라이드 지속 처리:** 오른쪽 스와이프 시 슬라이드 상태 유지

**기술적 도전과 해결:**
- **터치 이벤트 충돌:** 제스처와 버튼 이벤트가 동시에 발생하는 문제 해결
- **제스처 감지 정확도:** 터치 시작점과 종료점의 거리 계산 최적화
- **반응성 향상:** ACTION_DOWN에서 true 반환으로 터치 이벤트 독점 처리

**핵심 코드:**
```java
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
                return true;
            }
            break;

        case MotionEvent.ACTION_UP:
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
                    return true;
                } else if (totalDy > 100) {
                    // 아래로 스와이프 → 일시정지
                    new PauseScene(this).push();
                    return true;
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
                return true;
            }
            break;
    }
    // 2. 스와이프가 감지되지 않은 경우에만 버튼 등 나머지 처리
    return super.onTouchEvent(event);
}
```

#### 3. InfiniteMapLoader 클래스 (무한 맵 생성 시스템)
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/InfiniteMapLoader.java`

**개발 배경:** 기존 MapLoader가 고정된 맵 파일만 로드하여 게임의 재미 요소가 제한적이었음. 무한히 진행되는 맵 시스템을 구현하여 게임의 지속성과 재미를 증대.

**주요 기능:**
- **동적 맵 생성:** 실시간으로 새로운 맵 요소 생성
- **메모리 최적화:** 화면 밖 오브젝트 자동 제거
- **확장 가능한 구조:** 새로운 맵 요소 쉽게 추가 가능
- **시드 기반 생성:** 일정한 패턴으로 맵 생성하여 재현 가능성 보장
- **난이도 조절:** 진행 거리에 따라 장애물 밀도 증가

**기술적 도전과 해결:**
- **메모리 누수 방지:** 화면 밖 오브젝트의 적절한 제거 시점 결정
- **맵 생성 알고리즘:** 자연스러운 맵 패턴을 위한 생성 로직 개발
- **성능 최적화:** 실시간 맵 생성 시 프레임 드롭 방지

**핵심 코드:**
```java
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
    if (worldX >= lastSpawnX - Metrics.width) {
        float floorY = Metrics.height - Floor.Type.T_10x2.height();
        float gap = Metrics.GRID_UNIT * 12; // 바닥 간격
        
        for (int i = 0; i < 5; i++) {
            float x = lastSpawnX + i * gap;
            Floor floor = Floor.get('O', x, floorY);
            if (floor != null) {
                scene.add(MainScene.Layer.floor, floor);
                Log.d(TAG, "바닥 생성: x=" + x);
            }
        }
        lastSpawnX += 5 * gap;
    }
}

private void cleanupOldFloor() {
    ArrayList<IGameObject> floors = new ArrayList<>(scene.objectsAt(MainScene.Layer.floor));
    for (IGameObject obj : floors) {
        Floor floor = (Floor) obj;
        if (floor.getX() < worldX - Metrics.width - 200) {
            scene.remove(MainScene.Layer.floor, floor);
            Log.d(TAG, "바닥 제거: x=" + floor.getX());
        }
    }
}
```

#### 4. HealthDisplay 클래스 (체력 표시 UI)
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/HealthDisplay.java`

**개발 배경:** 기존 게임에 체력 시스템이 없어 게임의 난이도 조절과 재도전 요소가 부족했음. 시각적으로 직관적인 체력 표시 시스템을 구현하여 게임의 완성도 향상.

**주요 기능:**
- **시각적 체력 표시:** 하트 이미지로 현재 체력 표시
- **체력 관리:** 증가/감소/초기화 기능
- **게임 오버 판정:** 체력 0 시 게임 오버 상태 반환
- **UI 최적화:** 화면 좌상단에 고정 배치
- **애니메이션 효과:** 체력 변화 시 시각적 피드백

**기술적 도전과 해결:**
- **이미지 리소스 관리:** 하트 이미지 로딩 실패 시 대체 처리
- **UI 레이아웃:** 다양한 화면 크기에 대응하는 UI 배치
- **성능 최적화:** 매 프레임마다 그리기 작업 최적화

**핵심 코드:**
```java
public HealthDisplay(int maxHealth) {
    this.maxHealth = maxHealth;
    this.currentHealth = maxHealth;
    heartBitmap = BitmapFactory.decodeResource(GameView.view.getResources(), R.mipmap.heart);
    blackHeartBitmap = BitmapFactory.decodeResource(GameView.view.getResources(), R.mipmap.black);

    if (heartBitmap == null) {
        Log.e(TAG, "Heart bitmap failed to load from R.mipmap.heart");
    } else {
        Log.d(TAG, "Heart bitmap loaded successfully. Size: " + heartBitmap.getWidth() + "x" + heartBitmap.getHeight());
    }
}

@Override
public void draw(Canvas canvas) {
    float x = MARGIN_X;
    float y = MARGIN_Y;

    for (int i = 0; i < maxHealth; i++) {
        dstRect.set(x, y, x + HEART_WIDTH, y + HEART_HEIGHT);
        if (i < currentHealth) {
            canvas.drawBitmap(heartBitmap, null, dstRect, null);
        } else {
            canvas.drawBitmap(blackHeartBitmap, null, dstRect, null);
        }
        x += HEART_WIDTH + 10; // 하트 사이 간격
    }
}

public void resetHealth() {
    currentHealth = maxHealth;
    Log.d(TAG, "체력 초기화: " + currentHealth + "/" + maxHealth);
}
```

#### 5. PauseScene 클래스 (일시정지 및 재시작 시스템)
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/PauseScene.java`

**개발 배경:** 기존 게임에 일시정지 기능이 없어 사용자 경험이 떨어졌음. 완전한 재시작 기능을 포함한 일시정지 시스템을 구현하여 게임의 사용성 향상.

**주요 기능:**
- **일시정지 화면:** 게임 일시정지 시 표시되는 UI
- **재시작 기능:** MainScene 참조를 통한 완전한 게임 재시작
- **투명 배경:** 기존 게임 화면 위에 오버레이
- **사용자 선택:** 재시작 또는 게임 종료 선택 가능
- **MainScene 참조:** Scene 스택을 통한 MainScene 접근

**기술적 도전과 해결:**
- **Scene 간 통신:** PauseScene에서 MainScene의 메서드 호출
- **메모리 관리:** MainScene 참조를 통한 안전한 재시작
- **UI/UX 설계:** 직관적인 일시정지 화면 구성

**핵심 코드:**
```java
public class PauseScene extends Scene {
    private MainScene mainScene; // MainScene 참조 저장
    
    public PauseScene(MainScene mainScene) {
        this.mainScene = mainScene;
        initLayers(Layer.values().length);
        
        // 재시작 버튼
        add(Layer.touch, new Button(R.mipmap.btn_exit_n, 800f, 550f, 267f, 100f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                new android.app.AlertDialog.Builder(GameView.view.getContext())
                        .setTitle("Confirm")
                        .setMessage("Do you really want to restart the game?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Restart", new android.content.DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(android.content.DialogInterface dialogInterface, int i) {
                                if (mainScene != null) {
                                    mainScene.restartGame();
                                    android.util.Log.d("PauseScene", "MainScene 재시작 성공");
                                } else {
                                    android.util.Log.e("PauseScene", "MainScene 참조가 null임");
                                }
                                pop(); // PauseScene 닫기
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        }));
    }
}
```

#### 6. 게임 오버 처리 시스템 (MainScene 내 구현)
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/MainScene.java`

**개발 배경:** 체력이 0이 되었을 때 게임이 그대로 진행되어 사용자가 혼란스러워했음. 자동으로 게임 오버 다이얼로그를 표시하고 재시작 옵션을 제공하는 시스템 구현.

**주요 기능:**
- **자동 게임 오버 감지:** 체력 0 시 자동으로 게임 오버 상태 진입
- **지연된 다이얼로그:** 1초 후 게임 오버 다이얼로그 표시
- **재시작 옵션:** 게임 오버 시 재시작 또는 종료 선택
- **상태 초기화:** 재시작 시 모든 게임 상태 완전 초기화
- **게임 오버 상태 관리:** 중복 다이얼로그 표시 방지

**기술적 도전과 해결:**
- **타이밍 제어:** 게임 오버 감지와 다이얼로그 표시 사이의 적절한 지연
- **상태 관리:** 게임 오버 상태의 정확한 추적과 초기화
- **사용자 경험:** 게임 오버 시 자연스러운 전환과 선택 제공

**핵심 코드:**
```java
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

private void showGameOverDialog() {
    new android.app.AlertDialog.Builder(GameView.view.getContext())
            .setTitle("게임 오버")
            .setMessage("체력이 0이 되었습니다. 게임을 재시작하시겠습니까?")
            .setNegativeButton("아니오", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(android.content.DialogInterface dialogInterface, int i) {
                    GameView.view.popScene();
                }
            })
            .setPositiveButton("재시작", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(android.content.DialogInterface dialogInterface, int i) {
                    restartGame();
                }
            })
            .setCancelable(false)
            .create()
            .show();
}
```

#### 7. 완전한 재시작 시스템 (MainScene.restartGame())
**파일 위치:** `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/MainScene.java`

**개발 배경:** 기존 재시작 기능이 플레이어 위치만 초기화하여 게임이 완전히 초기화되지 않았음. 모든 게임 요소를 완전히 초기화하는 시스템 구현.

**주요 기능:**
- **게임 오버 상태 초기화:** 게임 오버 플래그와 타이머 리셋
- **플레이어 상태 초기화:** 위치, 체력, 애니메이션 상태 완전 리셋
- **체력 시스템 초기화:** HealthDisplay를 최대 체력으로 복원
- **맵 오브젝트 제거:** 모든 장애물, 아이템, 바닥 오브젝트 제거
- **맵 로더 재초기화:** MapLoader와 InfiniteMapLoader 상태 리셋

**기술적 도전과 해결:**
- **메모리 정리:** 모든 게임 오브젝트의 안전한 제거
- **상태 동기화:** 여러 시스템 간의 상태 일관성 유지
- **성능 최적화:** 대량의 오브젝트 제거 시 프레임 드롭 방지

**핵심 코드:**
```java
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

private void clearLayer(Layer layer) {
    ArrayList<IGameObject> objects = new ArrayList<>(objectsAt(layer));
    for (IGameObject obj : objects) {
        remove(layer, obj);
    }
}
```

#### 8. 맵 로더 재시작 시스템
**파일 위치:** 
- `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/MapLoader.java`
- `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/InfiniteMapLoader.java`

**개발 배경:** 재시작 시 맵 로더의 상태가 초기화되지 않아 이전 맵 요소가 남아있었음. 맵 로더의 완전한 재초기화 시스템 구현.

**주요 기능:**
- **MapLoader.restart():** 기존 맵 오브젝트 제거 후 재로드
- **InfiniteMapLoader.restart():** 무한 맵 상태 초기화 및 재시작
- **메모리 정리:** 모든 맵 오브젝트 완전 제거
- **상태 복원:** 맵 로더를 초기 상태로 복원

**핵심 코드:**
```java
// MapLoader.java
public void restart() {
    Log.d(TAG, "MapLoader 재시작");
    clearMapObjects();
    loadMap();
}

private void clearMapObjects() {
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

// InfiniteMapLoader.java
public void restart() {
    Log.d(TAG, "InfiniteMapLoader 재시작");
    worldX = 0f;
    lastSpawnX = 0f;
    initialFloorCreated = false;
    
    // 기존 맵 오브젝트들 제거
    clearAllMapObjects();
}

private void clearAllMapObjects() {
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
```

#### 9. 플레이어 상태 초기화 시스템
**파일 위치:**
- `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/Player.java`
- `app/src/main/java/kr/ac/tukorea/ge/scgyong/cookierun/game/NewPlayer.java`

**개발 배경:** 재시작 시 플레이어의 상태가 완전히 초기화되지 않아 이전 상태가 남아있었음. 모든 플레이어 상태를 완전히 초기화하는 시스템 구현.

**주요 기능:**
- **Player.resetState():** 쿠키 캐릭터 상태 완전 초기화
- **NewPlayer.resetState():** 닌자 캐릭터 상태 완전 초기화
- **위치 초기화:** 플레이어 위치를 시작 지점으로 복원
- **상태 초기화:** 점프, 슬라이드, 피격 상태 모두 초기화
- **애니메이션 초기화:** 애니메이션 프레임과 상태 리셋

**핵심 코드:**
```java
// Player.java
public void resetState() {
    state = State.running;
    jumpSpeed = 0f;
    obstacle = null;
    scale = SCALE_NORMAL;
    magSpeed = 0f;
    setPosition(200f, 200f, NORMAL_COOKIE_DST_SIZE, NORMAL_COOKIE_DST_SIZE);
    setState(State.running);
    Log.d(TAG, "플레이어 상태 초기화 완료");
}

// NewPlayer.java
public void resetState() {
    state = State.running;
    jumpSpeed = 0f;
    obstacle = null;
    scale = SCALE_NORMAL;
    magSpeed = 0f;
    invincibleTimer = 0f;
    setPosition(200f, 200f, NORMAL_COOKIE_DST_SIZE, NORMAL_COOKIE_DST_SIZE);
    setState(State.running);
    Log.d(TAG, "닌자 플레이어 상태 초기화 완료");
}
```

---

## 🎯 주요 개발 성과

### 1. 애니메이션 시스템 혁신
- **기존:** 단일 스프라이트 시트 사용
- **개선:** 상태별 애니메이션 스트립 이미지 사용
- **효과:** 더 부드럽고 자연스러운 애니메이션

### 2. 직관적인 제어 시스템
- **스와이프 제스처:** 위(점프), 아래(일시정지), 오른쪽(슬라이드)
- **터치 이벤트 최적화:** 제스처 우선 처리로 버튼과의 충돌 방지
- **사용자 경험 향상:** 더 직관적이고 반응성 좋은 조작

### 3. 완전한 재시작 시스템
- **상태 초기화:** 플레이어, 체력, 맵, 맵 로더 모두 초기화
- **게임 오버 처리:** 체력 0 시 자동으로 재시작 옵션 제공
- **일시정지 통합:** 일시정지와 게임 오버 모두 동일한 재시작 기능

### 4. 무한 모드 구현
- **동적 맵 생성:** 실시간으로 새로운 맵 요소 생성
- **메모리 최적화:** 화면 밖 오브젝트 자동 제거
- **확장성:** 새로운 맵 요소 쉽게 추가 가능

---

## 😔 아쉬운 점들

### 하고 싶었지만 못 한 것들 및 결국 해결하지 못한 문제
- **캐릭터 커스터마이징:** 다양한 닌자 캐릭터 선택
게임 내 HUD (점수, 콤보, 남은 생명)
게임 오버 및 레벨 클리어 화면
파워업 아이템 (무적, 점수 증가, 속도 조절 등)

### 앱스토어 출시를 위해 보충할 것들
- **디자인 개발:** 실질적 출시 퀄리티정도의 이미지
- **인앱 결제:** 캐릭터 및 아이템 구매
- **푸시 알림:** 일일 미션 및 이벤트 알림
- **데이터 분석:** 사용자 행동 분석
- **다국어 지원:** 영어, 일본어 등

### 해결하지 못한 문제/버그
- **메모리 누수:** 장시간 플레이 시 메모리 사용량 증가
- **터치 이벤트 지연:** 빠른 연속 터치 시 반응 지연
- **장시간 플레이시:** 장시간 플레이시 앱이 꺼지는 현상 발생 메모리로 인한 오류라고 판단

### 개발 과정에서 겪은 어려움
- **Android Studio 성능:** 메모리에 대한 관리가 기존 했던 방식과 많이 달라 제대로 다루지 못함
- **메모리 관리:** 이미지 리소스 최적화
- **터치 이벤트 처리:** 제스처와 버튼 이벤트 충돌 해결

---

## 📚 수업에 대한 소감

### 이번 수업에서 기대한 것
- **실무 중심 학습:** 이론보다 실제 개발 경험
- **게임 개발 기초:** 게임 엔진 및 프레임워크 이해
- **Android 개발 능력:** 모바일 앱 개발 역량 향상
- **팀워크 경험:** 협업 및 프로젝트 관리

### 얻은 것
- **안드로이드 개발:** 안드로이드를 기반한 개발 과정 경험
- **프레임워크 활용:** A2DG 프레임워크 마스터
- **문제 해결 능력:** 디버깅 및 버그 수정 능력 향상
- **사용자 중심 사고:** UX/UI 설계 및 개선

### 얻지 못한 것
- **네트워크 프로그래밍:** 안드로이드에서의 서버 제작 과정은 어떠한지
- **성능 최적화:** 고급 최적화 기법
- **앱스토어 출시:** 실제 배포 과정 및 앱 추출 과정

### 더 좋은 수업이 되기 위한 제안
1. **실습 시간 증가:** 배우는 양을 조금 줄이더라도 특히 sound 부분. 수업 앞부분의 내용들을 더 깊게 다뤘다면 좋았을 것 같습니다.
2. **개인 프로젝트:** 프레임워크에 대한 모든 정보를 더 미리 알았더라면 프로젝트의 진행방향이 달랐을 것 같습니다.
4. **기술 세미나:** 최신 게임 개발 기술 소개
6. **하드웨어 지원:** 개발용 디바이스 대여 시스템

---

## 🎯 결론

**Shadow Runner** 프로젝트를 통해 Android 게임 개발의 전 과정을 경험할 수 있었습니다. 기존 쿠키런 프로젝트를 기반으로 하여 무한맵, 슬라이드 제스쳐 추가 등을 진행 하였습니다. 

특히 스와이프 제스처 시스템과 완전한 재시작 기능은 사용자 경험을 크게 향상시켰으며, 무한 모드 구현을 통해 게임의 재미 요소를 증대시켰습니다.

이번 프로젝트를 통해 얻은 실전 개발 경험과 문제 해결 능력은 향후 게임 개발자로서의 성장에 큰 도움이 될 것입니다.

---

## 📞 연락처
- **개발자:** [홍동욱]
- **이메일:** [hong991027@gmail.com]
- **GitHub:** 

*이 프로젝트는 Android Game Programming 수업의 기말 프로젝트로 제작되었습니다.*
