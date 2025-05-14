# Shadow-Runner_AGP
Android Game Programming Project

- **프로젝트 제목:** [Shadow Runner] 
- **발표 영상 자료:** [Shadow Runner 발표 영상 (1분 30초)](https://youtu.be/L4PnCH_Uf5M)  
- **프로젝트 Git Repository:** [Shadow Runner Git Repository](https://github.com/HDonguk/Shadow-Runner_AGP)  
- **README.md 파일 바로가기:** [README.md](https://github.com/HDonguk/Shadow-Runner_AGP/blob/main/README.md)

---

## 1. 게임 컨셉 (High Concept 및 핵심 메카닉)

**Shadow Runner**는 어둠 속을 질주하는 그림자 닌자를 주인공으로 한 액션 플랫포머 게임입니다.

- **핵심 아이디어:**  
  플레이어는 터치 및 스와이프 제스처를 활용하여 닌자의 점프, 슬라이드, 공격 동작을 구사하면서 장애물을 피하고 콤보를 이어가는 게임입니다.
- **핵심 메카닉:**  
  - **터치/스와이프 제어:** 직관적인 입력 방식으로 캐릭터의 점프, 슬라이드, 공격 명령 실행  
  - **실시간 충돌 감지:** 장애물 및 적과의 충돌 시 생명 감소 또는 게임 오버 처리  
  - **콤보 시스템:** 연속 액션을 통해 점수 보너스 및 파워업 아이템 획득

---

## 2. 개발 범위 (정량적 제시)

- **총 레벨 수:** 5 레벨 (각 레벨마다 배경 및 난이도 변화)
- **UI 요소:**  
  - 메인 메뉴 (시작, 설정, 기록 확인 버튼)  
  - 게임 내 HUD (점수, 콤보, 남은 생명)  
  - 게임 오버 및 레벨 클리어 화면
- **게임 오브젝트:**  
  - 플레이어 캐릭터 (Shadow Runner)  
  - 장애물 (벽, 함정 등)  
  - 파워업 아이템 (무적, 점수 증가, 속도 조절 등)
- **핵심 기능 구현:**  
  - 터치/스와이프 이벤트를 통한 캐릭터 제어 (ViewBinding, Event Handling)  
  - 실시간 충돌 감지 및 게임 상태 전환 (게임 오버, 콤보 증가)  
  - 배경 스크롤 및 레벨별 디자인 (Custom View 활용)  
  - 애니메이션 효과 (캐릭터 달리기, 점프, 슬라이드)

---

## 3. 예상 게임 실행 흐름

1. **메인 메뉴:**  
   - 시작, 설정, 기록 확인 버튼 제공
2. **레벨 시작:**  
   - 각 레벨에 맞는 배경과 장애물이 동적으로 생성되어 화면에 표시
3. **게임 진행:**  
   - 플레이어는 터치와 스와이프 제스처로 캐릭터를 조작  
   - 일정 콤보 달성 시 보너스 점수 및 파워업 아이템 획득  
   - 장애물과의 충돌 시 생명 감소 또는 게임 오버 처리
4. **레벨 클리어:**  
   - 레벨 목표 도달 시 클리어 화면과 함께 다음 레벨로 이동
5. **게임 종료/재시작:**  
   - 게임 오버 시 재도전하거나 메인 메뉴로 복귀 선택 제공

> **실행 흐름 예시 스케치**
> ![Game Flow Sketch](https://github.com/user-attachments/assets/7a2410cb-72b6-4bba-bf74-dadc239d8a00) 
> *(실제 게임 스크린샷이나 스케치 이미지를 활용하여, 게임 진행 흐름을 직관적으로 이해할 수 있도록 구성)*

---

## 4. 개발 일정 (8주 상세 일정)

- **1주차 (4월 8일 시작):**  80%완료 
  - 프로젝트 초기 설정 및 Git Repository 생성  
  - 메인 메뉴 UI 구성
- **2주차:**     90%완료
  - 기본 캐릭터 컨트롤 (터치/스와이프 이벤트) 및 애니메이션 구현  9
- **3주차:**     40%완료
  - 레벨별 배경 스크롤 및 장애물 생성 알고리즘 구현 (Custom View 활용)
- **4주차:**     0%완료
  - 충돌 감지 로직 및 콤보, 점수 시스템 구현
- **5주차:**    50%완료
  - 파워업 아이템 및 추가 장애물 구현, 게임 난이도 조절
- **6주차:**    20%완료
  - UI 개선 및 애니메이션 효과 최적화, 사운드 효과 통합
- **7주차:**    0%
  - 전체 게임 테스트, 디버깅 및 밸런스 조정
- **8주차:**    0%
  - 최종 디버깅, 성능 최적화 및 발표 영상 제작 (정확히 1분 30초)
  - 
![image](https://github.com/user-attachments/assets/079f2164-fdb4-472f-b8ed-bdbb6e5c4bfb)

![image](https://github.com/user-attachments/assets/8acb2b93-3d6c-4eb2-a77b-8bff688bee15)

---

## 5. MainScene 주요 GameObject 상세 설명

1.  MapLoader / MapGenerator (자동 맵 구성 요소)

기능 요약:

레벨에 따라 맵 요소(장애물, 아이템, 바닥 등)를 자동으로 배치
Stage 번호를 기반(시드)로 .tmj 맵 파일 또는 프로그램적으로 생성된 MapGenerator 결과를 불러옴

구성 정보:
MapLoader는 TMJ 포맷 파일을 로드
MapGenerator는 내부 알고리즘에 따라 동적으로 장애물/아이템 위치를 배치 (5스테이지 이상 적용됨)

![image](https://github.com/user-attachments/assets/1cf51798-2a2e-4326-900c-b713e9b1b900)



2. Touch Motion Buttons (점프/슬라이드/낙하 입력)

기능 요약:
화면의 UI 및 스와이프를 통해 Player의 점프, 슬라이드, 낙하, 공격 동작을 직접 제어

구성 정보:
Button 객체를 사용해 터치 감지를 구현 (onTouch 리스너 활용)

버튼 3종류: Jump, Slide, Fall

상호작용 정보:
사용자 터치에 따라 Player.jump(), Player.slide(), Player.fall() 메서드가 실행됨

핵심 코드 예시:
![image](https://github.com/user-attachments/assets/0a193ee0-fbe9-49c9-8744-960a82e9a0c5)


3. Shadow Item (소모 아이템)
클래스 위치: Player.java 내부, Item.java 내부 applyEffect()

기능 요약:
특정 아이템(Item.Type.shadow) 획득 시, 수리검 공격을 통한 장애물 제거 가능 

구성 정보:
Player 클래스에 magnify(boolean enlarges) 메서드가 존재
확대 여부는 scale 값과 magSpeed로 점진적으로 조절됨

상호작용 정보:
충돌 후, Item.applyEffect() → player.magnify(true) 호출로 효과 발동

핵심 코드 예시:
![image](https://github.com/user-attachments/assets/0b4d1be8-defc-4962-a418-4b6cad265936)
![image](https://github.com/user-attachments/assets/1fdbbd96-5d19-452b-bad1-794652477ad9)



4. Player 

기능 요약:
스크롤되는 배경에서 자동으로 달리며, 사용자 조작(점프/슬라이드/공격)에 반응함
콤보 시스템, 충돌 판정, 생명/체력/확대 효과 등 주요 기능의 중심

상호작용 정보:
CollisionChecker로 Obstacle 또는 Item과 충돌 감지
jump(), slide(), fall() 동작은 Touch 버튼과 연결됨
applyItemEffect()로 아이템 효과 반영됨

핵심 코드 예시:
![image](https://github.com/user-attachments/assets/9ca2bc06-ba47-44af-a32d-c0ec989b1f0c)

단일 객체지만 가장 많은 상호작용과 상태 변화를 담당하는 중심 클래스입니다

5.  Obstacle (장애물 오브젝트)

기능 요약:
MapLoader나 MapGenerator에 의해 배치되며, 플레이어가 피해야 하는 대상
충돌 시 체력 감소 또는 즉시 게임 오버 처리

상호작용 정보:
Player와의 충돌을 감지하여 CollisionChecker가 생명 감소 처리
종류별로 충돌 효과가 다를 수 있음 (즉사 vs 점수 감소 등)

핵심 코드 예시:
if (player.collidesWith(obstacle)) {
    player.decreaseHp();  // 또는 gameOver()
}

6. CollisionChecker (충돌 판정 전담 컨트롤러)

기능 요약:
update()마다 모든 충돌을 판별하고 후속 처리 (아이템 효과, 데미지 등)를 트리거함

상호작용 정보:
Player, Obstacle, Item의 충돌을 각각 구분하여 처리

핵심 코드 예시:
if (player.collidesWith(item)) {
    item.applyEffect(player);
}


### 요약

**Shadow Runner**는 어둠 속을 질주하며 장애물을 피하고 콤보를 이어가는 액션 플랫포머 게임입니다.  
이번 학기에는 Android의 터치/스와이프 이벤트 처리, ViewBinding, Custom View 등 다양한 기능을 활용하여 게임을 개발하며, 위의 8주간의 개발 일정을 따라 체계적으로 진행합니다. 


