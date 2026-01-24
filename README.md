# 우리주변게임친구 서버

## 🚀 프로젝트 요약

<p align="center" width="100%">
    <img width="1283" alt="Frame 322(1)" src="https://user-images.githubusercontent.com/83222282/229736196-9d1d9899-3893-44b3-ad51-b04cb3c04e50.png">
</p>


스타트업을 창업하며 나온 재밌는 아이디어를 개발해보았습니다. 프라이머의 당근마켓 CTO 협업하여 서비스를 발전시켰고 3달동안 서비스하였습니다. 학교 친구들과 오늘 할 게임을 등록하여 공유하는 어플리케이션입니다.



### 📅 작업기간: 2022.7 ~ 2022.11

> Springboot 재개발 기간: 2023.03 ~ 2023.04

### 👨🏻‍💻 투입인원: 2명

### 💻 주요업무

![Frame 323](https://user-images.githubusercontent.com/83222282/236085891-c2d2808c-ea67-4897-8083-808d5715896f.png)

[Frontend]
- Clean Architecture 패턴 적용, Repository 패턴으로 데이터 계층 분리
- GetX + get_it 기반 상태 관리 및 의존성 주입 구현
- Freezed, Retrofit, Injectable 등 코드 생성 도구 활용으로 보일러플레이트 최소화
- Firebase Cloud Messaging 푸시 알림 연동
- Secure Storage 기반 토큰 관리 및 인터셉터 패턴 인증 처리

[Backend]
- JWT Stateless 인증 + SMS 2FA 구현
- JAP, QueryDSL 쿼리 최적화
- Firebase Admin SDK 연동 푸시 알림 시스템

## Getting Stated

### Firebase Setting

- resources

```text
Firebase Admin SDK `.json`파일 추가
```

- application.properties

```text
project.properties.firebase-create-scoped: "https://www.googleapis.com/auth/firebase.messaging"
```

---

### Jwt
- application.properties

```text
jwt:
  password: {jwt secret key}
```

---

### Image file path
```text
file:
    path: {local folder path}/profile
```

---

### SMS Login

Naver SMS API 사용 <br>

- application-sms.properties 파일 추가

```text
sms:
  serviceId: {serviceId}
  accessKey: {accessKey}
  secretKey: {secretKey}
  sms.caller = {phone number}
```

---
