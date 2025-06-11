package com.dmz.airdnd.common.auth;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dmz.airdnd.fixture.TestUserFactory;
import com.dmz.airdnd.user.dto.request.UserRequest;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private AuthService authService;

	@Test
	@DisplayName("POST /api/auth/signup 성공 시 201 반환하고 서비스 호출")
	void success_signup() throws Exception {
		// given
		String json = """
			{
			  "loginId": "user123",
			  "password": "pass!234",
			  "email": "user@example.com",
			  "phone": "01012345678"
			}
			""";

		when(authService.signup(any())).thenReturn(TestUserFactory.createTestUser());

		// when & then
		mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.data").isEmpty())
			.andExpect(jsonPath("$.error").isEmpty());

		// service 호출 확인
		verify(authService).signup(any(UserRequest.class));
	}

	@ParameterizedTest
	@MethodSource("provideInvalidUserRequests")
	@DisplayName("POST /api/auth/signup 유효성 검사 실패 시 400 반환하고, 예외 코드와 메시지를 포함한다.")
	void fail_signup(String invalidJson, String message) throws Exception {

		mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).content(invalidJson))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.success").value(false))
			.andExpect(jsonPath("$.data").isEmpty())
			.andExpect(jsonPath("$.error").isNotEmpty())
			.andExpect(jsonPath("$.error.code").value("INVALID_REQUEST_FORMAT"))
			.andExpect(jsonPath("$.error.message").value(message));

		// 서비스 호출 여부 확인
		verify(authService, never()).signup(any(UserRequest.class));
	}

	private static Stream<Arguments> provideInvalidUserRequests() {
		return Stream.of(
			// 1) loginId 빈 값 → NotBlank 위반
			Arguments.of("""
				{
				  "loginId": "",
				  "password": "validPass123",
				  "email": "test@example.com",
				  "phone": "01012345678"
				}
				""", "로그인 아이디는 필수 입력 항목입니다."),

			// 2) loginId 너무 짧음 → 길이(5~25자) 위반
			Arguments.of("""
				{
				  "loginId": "abc",
				  "password": "validPass123",
				  "email": "test@example.com",
				  "phone": "01012345678"
				}
				""", "로그인 아이디는 5~25자 이내여야 합니다."),

			// 3) loginId 너무 김 → 길이(5~25자) 위반
			Arguments.of("""
				{
				  "loginId": "a".repeat(26),
				  "password": "validPass123",
				  "email": "test@example.com",
				  "phone": "01012345678"
				}
				""", "로그인 아이디는 5~25자 이내여야 합니다."),

			// 4) password 빈 값 → NotBlank 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "",
				  "email": "test@example.com",
				  "phone": "01012345678"
				}
				""", "비밀번호는 필수 입력 항목입니다."),

			// 5) password 너무 짧음 → 길이(5~25자) 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "123",
				  "email": "test@example.com",
				  "phone": "01012345678"
				}
				""", "비밀번호는 5~25자 이내여야 합니다."),

			// 6) password 너무 김 → 길이(5~25자) 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "a".repeat(26),
				  "email": "test@example.com",
				  "phone": "01012345678"
				}
				""", "비밀번호는 5~25자 이내여야 합니다."),

			// 7) email 빈 값 → NotBlank 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "",
				  "phone": "01012345678"
				}
				""", "이메일은 필수 입력 항목입니다."),

			// 8) email 형식 오류
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "invalid-email",
				  "phone": "01012345678"
				}
				""", "올바른 이메일 형식이어야 합니다."),

			// 9) email 너무 짧음 → 길이(5~25자) 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "a@b.c",
				  "phone": "01012345678"
				}
				""", "이메일은 5~25자 이내여야 합니다."),

			// 10) email 너무 김 → 길이(5~25자) 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "a".repeat(21) + "@ex.com",
				  "phone": "01012345678"
				}
				""", "이메일은 5~25자 이내여야 합니다."),

			// 11) phone 빈 값 → NotBlank 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "test@example.com",
				  "phone": ""
				}
				""", "전화번호는 필수 입력 항목입니다."),

			// 12) phone 너무 짧음 → 길이(8~25자) 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "test@example.com",
				  "phone": "0101234"
				}
				""", "전화번호는 8~25자 이내여야 합니다."),

			// 13) phone 너무 김 → 길이(8~25자) 위반
			Arguments.of("""
				{
				  "loginId": "validLogin",
				  "password": "validPass123",
				  "email": "test@example.com",
				  "phone": "1234567890123456789012345"
				}
				""", "전화번호는 8~25자 이내여야 합니다.")
		);
	}
}
