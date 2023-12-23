# 블로그 검색 서비스 구현 #

## 프로젝트 정보
- Java 11
- Spring Boot 2.7.9
- H2 Database
- Spring Data JPA
- Embedded redis
- Gradle
 
<br>

## 실행 방법
1. 프로젝트의 [application-1.0.-SNAPSHOT.jar](https://github.com/ldonghee/BlogSearch/blob/master/application-1.0-SNAPSHOT.jar) 다운로드
2. 터미널에서 파일 경로로 이동 후 `java -jar application-1.0.-SNAPSHOT.jar` 명령어 입력

<br>

## 설계
- 멀티 모듈과 hexagonal 아키텍처 도입
- 서로 다른 Bounded Context 기준으로 나누어 상호 관계가 명확해지도록 하였음
- 단방향 흐름을 통해 모듈관 의존성 최소화
- 보유한 성격과 특성(역할과 책임)에 따라 모듈 분리 
- 모듈 설명
  - application : 애플리케이션 모듈
  - domain : 도메인 모듈(핵심 비지니스)
  - infrastructure 
    - datastore-h2 : h2 데이터베이스 모듈
    - datastore-redis : 레디스 케시 모듈
  - external-api : 외부 연동 모듈 (카카오/네이버 연동)
- 의존성을 단방향으로 흐르게 하여 독립적인

![hexagonal 아키텍처](images/hexagonal.png)

![시스템 설계](images/architecture.png)

<br>

## 추가 구현 내용
- 트래픽이 많은 상황을 대비해 블로그 검색 API는 Embedded Redis를 활용하여 로컬 캐시 적용하여 요청을 최소화
- 동시성 이슈관련해 aop와 redis 서버와 Redisson 을 사용하여 분산락 적용 
- 카카오 API 정상 응답 못받을 경우, 네이버 API 호출하여 결과 제공


<br>

## API 명세
### 1. 블로그 검색
#### Request
```bash
curl -X GET http://localhost:8080/blog/search?keyword=acd&page=1&size=2&sort=accuracy
```
| Name  | Type      | Description                                                  | Required |
|:------| :-------- | :----------------------------------------------------------- | :------- |
| query | `String`  | 검색 키워드                                                  | O        |
| sort  | `String`  | 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy | X        |
| page  | `Integer` | 결과 페이지 번호, 1~50 사이의 값, 기본 값 1                  | X        |
| size  | `Integer` | 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10       | X        |

<br />

#### Response
```json
{
  "statusCode": 200,
  "statusMessage": "OK",
  "result": [
    {
      "title": "[심즈3] 평생소망이루기 : 1인 <b>심</b> 밴드(1)",
      "url": "https://jin-plus.tistory.com/188",
      "contents": "어느덧 에런은 성인이 됐고 예술 학교에서 졸업했다. 그리고 이렇게 평생 소망을 선택할 수 있는 화면이 뜸. 에런의 평생 소망은 바로 &#39;1인 <b>심</b> 밴드&#39;다. 이어 &#39;르프로마쥬 예술 학교&#39;를 졸업했는다는 메시지와 함께 에런은 어딘가에서 졸업 가운을 입고 나타난다 ㅋㅋㅋ 집에 오자마자 얼굴부터 손봐주려 했다. 사실...",
      "blogName": "진사생활",
      "datetime": "2023-12-22T11:47:47.000+09:00"
    },
    {
      "title": "<b>심</b>부정맥혈전증 1",
      "url": "https://cu-kang.tistory.com/473",
      "contents": "것은 혈전이 떨어져 올라가 폐동맥을 막으면 숨도 못쉬고 죽는 급사란다 아직까지 죽지는 않았으니 그나마 천만다행이다~ㅋ 인터넷 지식인으로 폭풍 검색하니 <b>심</b>부정맥혈전증이란다 골든타임이 72시간이란다 한 쪽 다리만 붓는 느낌이 든다면 얼른 병원으로 달려 가란다 더욱이 운동은 증상을 악화시키니 금물이란다...",
      "blogName": "정말 멋진 날이야",
      "datetime": "2023-12-15T16:17:37.000+09:00"
    }
  ]
}
```

<br/>

### 2. 인기 검색어 목록 조회

#### Request
```bash
curl -X GET http://localhost:8080/keyword/popular
```

<br/>

#### Response
```json
{
  "statusCode": 200,
  "statusMessage": "OK",
  "result": [
    {
      "query": "참외",
      "count": 11
    },
    {
      "query": "여름",
      "count": 7
    },
    {
      "query": "반려견",
      "count": 6
    },
    {
      "query": "휴대폰",
      "count": 5
    },
    {
      "query": "심",
      "count": 5
    },
    {
      "query": "사람",
      "count": 4
    },
    {
      "query": "test",
      "count": 3
    },
    {
      "query": "ac",
      "count": 2
    },
    {
      "query": "매크",
      "count": 2
    },
    {
      "query": "강아지",
      "count": 2
    }
  ]
}
```

<br/>