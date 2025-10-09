# be-airdnd
2025 마스터즈 백엔드 팀프로젝트

## 팀 구성원

디노, 머드, 케이딘

## 프로젝트 소개
- AirDnD는 숙소 예약 플랫폼으로, 사용자가 숙소를 검색하고 예약할 수 있습니다.
- 실제 Airbnb 서비스를 참고하여 핵심 예약 기능을 구현한 클론 코딩 프로젝트입니다.

## 기술 스택
<img width="5064" height="2460" alt="image" src="https://github.com/user-attachments/assets/25c67988-1cd3-478d-8f77-384d5fd35136" />

## CI/CD
- Github Actions, Docker Hub, 셸 스크립트를 사용하여 CI/CD 파이프 라인을 구축했습니다.
- dev 브랜치에 변경사항이 push 되면, deploy-docker.yml(파일 링크) 에 작성된 워크 플로우에 따라 Github Actions가 동작합니다.
- 민감한 정보(Docker Hub id/pw, Ec2 ip주소, SSH key)는 Github Secrets로 관리했습니다.
- Nginx와 Docker를 기반으로 Blue-Green 배포 전략을 적용하여 서비스 중단 없이 신규 버전을 배포할 수 있는 무중단 배포 시스템을 구축했습니다.
### 동작 순서
- 1. 빌드
actions/checkout를 통해 러너 환경에 레포지토리 소스 코드 클론
JDK를 설치하여, java 빌드 환경 설정
./gradlew clean build를 통해 java 소스 코드를 빌드(.jar 파일 생성)
- 2. Docker 이미지 작업
Docker Hub에 로그인
프로젝트에 Dockerfile을 통해 빌드하여 도커 이미지 파일 생성
생성된 도커 이미지 파일은 Docker Hub에 push
- 3. EC2에 배포
SSH를 통해 EC2 서버 접속
deploy.sh(파일 링크) 셸 스트립트 실행
현재 nginx 설정 파일에서 active 중인 컨테이너를 찾음
Docker Hub에서 최신 이미지 pull
새 컨테이너 실행 (.env 파일 포함)
새 컨테이너가 정상 실행 되었는지 헬스체크
nginx 설정 변경 (트래픽 전환)
nginx -s reload를 통해 nginx 리로드
이전 컨테이너 제거

## ERD

https://www.erdcloud.com/d/3sn5XyLRBj9dAhskE

![img.png](img.png)
