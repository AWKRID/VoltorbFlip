# 찌리리공 뒤집기
---
## 🔍프로젝트 소개 
---
- 옛날 닌텐도 게임 중 포켓몬스터 하트골드/소울실버 버전에 있던 미니게임 '찌리리공 뒤집기'를 Kotlin으로 구현해보았습니다.
  
- 간단하게 5X5칸의 숫자를 맞추는 퍼즐 문제입니다.


## 💻사용 예시 
--- 
### 1. 프로그램 시작 시 메뉴
```
찌리리공 뒤집기 게임에 오신 것을 환영합니다!
찌리리공 뒤집기 Lv. 1로 플레이 하시겠습니까?
1. 플레이한다.
2. 게임 설명 보기
3, 조작법 설명 보기
4. 종료하기
-----------------------------
```

### 2. 게임 시작하기
- 게임 시작시 아래와 같은 게임 보드가 나타납니다. 
- ^ 표시는 현재 위치를 알려주는 것이고, wasd(WASD)를 입력하여 위치를 이동할 수 있습니다.
- c 혹은 C를 입력하여 해당 위치의 카드를 뒤집을 수 있습니다.
  
```
찌리리공 뒤집기 Lv. 1
-----------------------------
수집한 코인: 0 C
현재 게임에서 모은 코인: 0
-----------------------------
 x   x   x   x   x  2/3
 ^  
 x   x   x   x   x  8/0

 x   x   x   x   x  6/1

 x   x   x   x   x  3/2

 x   x   x   x   x  6/0

4/1 8/0 5/1 2/3 6/1 
-----------------------------
```

- 2행 카드를 모두 뒤집은 모습
  
```
찌리리공 뒤집기 Lv. 1
-----------------------------
수집한 코인: 0 C
현재 게임에서 모은 코인: 6
-----------------------------
 x   x   x   x   x  2/3

 1   2   1   1   3  8/0
 ^  
 x   x   x   x   x  6/1

 x   x   x   x   x  3/2

 x   x   x   x   x  6/0

4/1 8/0 5/1 2/3 6/1 
-----------------------------
```
만약 찌리리공(숫자 0)을 뒤집게 된다면 해당 게임에서 얻은 코인을 잃고 종료합니다.

```
찌리리공 뒤집기 Lv. 1
-----------------------------
수집한 코인: 0 C
현재 게임에서 모은 코인: 6
-----------------------------
 x   x   x   💣  x  2/3
             ^  
 1   2   1   1   3  8/0

 x   x   x   x   x  6/1

 x   x   x   x   x  3/2

 x   x   x   x   x  6/0

4/1 8/0 5/1 2/3 6/1 
-----------------------------
게임에 실패했습니다!
 메인메뉴로 돌아갑니다.
```

찌리리공을 피해서 모든 2와 3을 찾는 경우 게임을 승리합니다.
```
찌리리공 뒤집기 Lv. 1
-----------------------------
수집한 코인: 0 C
현재 게임에서 모은 코인: 36
-----------------------------
 x   x   x   x   x  3/2

 3   x   x   x   x  6/1
 ^  
 1   2   1   1   1  6/0

 1   2   3   1   1  8/0

 x   x   x   x   x  2/3

6/1 5/2 5/2 5/0 4/1 
-----------------------------
게임 클리어~!!
숨어 있던 2와 3의 카드를 모두 찾았습니다!
36 C을 획득했습니다!
```

게임을 승리하면 Lv이 한 단계 높아지고, 얻을 스 있는 코인의 수가 늘어납니다. 
```
게임 레벨이 Lv. 2으로 올랐습니다.
다음 게임에서 받을 수 있는 동전이 늘어납니다!
-----------------------------
```


### 3. 게임 설명 보기
```
<게임 설명 보기>
-----------------------------
- 찌리리공 뒤집기는 카드를 뒤집어 숫자를 찾아내는 게임입니다.
- 카드에는 1~3까지 숫자와 찌리리공(0)이 숨어 있습니다. 
- 각 행에 대한 힌트는 우측에, 각 열에 대한 힌트는 하단에 표기되어 있습니다.
Hint: [전체 숫자 합/찌리리공 개수]
- 가장 먼저 뒤집은 숫자는 획득 동전에 더해지며,
그 이후 뒤집은 숫자는 획득 동전에 곱해집니다.
- 만약 찌리리공 카드를 뒤집은 경우 게임이 종료되며
해당 게임에서 얻은 동전을 모두 잃습니다.
- 숨겨져 있는 2와 3의 숫자를 모두 찾으면 게임을 승리합니다!
-----------------------------
```

### 4. 조작법 설명 보기
<조작법 설명 보기>
```
-----------------------------
WASD키를 활용하여 게임 보드를 이동할 수 있습니다.
원하는 방향의 키를 입력 후 Enter키를 누르면 이동합니다.
현재 자리의 카드를 확인하고 싶은 경우 C를 입력하여 확인합니다.
현재 게임을 종료하고 싶은 경우 QUIT을 입력해주세요
-----------------------------
```

