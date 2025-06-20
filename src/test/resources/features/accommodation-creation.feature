Feature: 호스트의 숙소 생성

  Background:
    Given HOST 사용자로 로그인이 되어 있다.

  Scenario: 모든 필수 정보가 올바르면 숙소 생성에 성공한다
    Given 유효한 숙소 등록 정보가 준비되어 있다.
    And 저장소에 동일한 name 을 가진 숙소가 존재하지 않는다.
    When 호스트가 숙소생성 API를 호출했을 때
    Then 숙소 생성 응답 상태로 201 Created를 받는다.
    And 응답 본문은 success는 true이고 바디에 생성된 숙소의 정보가 포함되어야 한다.

#  Scenario: 필수 필드(name)가 없으면 400 Bad Request를 반환한다
#    Given name이 비어 있는 숙소 정보가 주어졌을 때
#    When 호스트가 숙소생성 API를 호출했을 때
#    Then 응답 상태 코드는 400 Bad Request여야 한다
#    And 오류 메시지에 "name은 필수 입력 항목입니다."가 포함되어야 한다
#
