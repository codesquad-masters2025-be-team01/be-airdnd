Feature: User Signup

  Scenario: 중복된 유저가 없으면 회원가입을 성공한다.
    Given 유효한 회원가입 요청 정보가 준비되어 있다.
    And 저장소에 동일한 loginId, email, phone을 가진 유저가 존재하지 않는다.
    When 사용자가 회원가입 API를 호출하면
    Then userRepository의 존재 검증 로직이 수행되어야 하고,
    And 회원가입 시도한 유저의 정보와, 회원가입하면서 반환한 유저의 정보가 같아야 한다.

 Scenario Outline: 중복된 필드가 있을 경우 회원가입에 실패한다.
   Given 중복된 <field> 를 가진 회원가입 요청 정보가 준비되어 있다.
   When 사용자가 회원가입 API를 호출하면
   Then 오류 메시지는 "<message>"이어야 한다.

   Examples:
   | field  | message |
   | loginId  | 이미 존재하는 로그인 아이디입니다. |
   | email    | 이미 존재하는 이메일입니다. |
   | phone    | 이미 존재하는 전화번호입니다.  |

  Scenario: POST /api/auth/signup 성공 시 201 반환하고 서비스 호출해야 한다.
    Given 유효한 회원가입 요청 JSON을 준비한다.
    When 사용자가 POST "/api/auth/signup" 요청을 보낸다.
    Then 응답 상태 코드는 201이다.

  Scenario: POST /api/auth/signup 유효성 검사 실패 시 400 반환하고, 예외 코드와 메시지를 포함한다.
    Given 유효하지 않은 회원가입 요청 JSON을 준비한다.
    When 사용자가 POST "/api/auth/signup" 요청을 보낸다.
    Then 응답 상태 코드는 400이다.
    And 응답 바디 JSON에 에러 코드와 메시지가 포함되어야 한다.


